package jerarquicas;

//@author Martina Coassin-Fernandez FAI-2542 y Lucas Campoy FAI-2537
import lineales.dinamicas.*;

public class ArbolBin {

    //--------Atributos--------//
    private NodoArbol raiz;

    //--------Constructor--------// 
    public ArbolBin() {
        raiz = null;
    }

    //--------Insertar--------//
    public boolean insertar(Object elem, Object padre, char pos) {
        boolean exito = true;

        NodoArbol nuevo = new NodoArbol(elem, null, null);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoArbol nodoPadre = obtenerNodo(this.raiz, padre);
            if (nodoPadre != null) {
                if (pos == 'I' && nodoPadre.getIzquierdo() == null) {
                    nodoPadre.setIzquierdo(nuevo);
                } else {
                    if (pos == 'D' && nodoPadre.getDerecho() == null) {
                        nodoPadre.setDerecho(nuevo);
                    } else {
                        exito = false;
                    }
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    //--------esVacio--------//
    public boolean esVacio() {
        return this.raiz == null;
    }

    //--------padre--------//
    public Object padre(Object elemento) {
        Object elemPadre = null;

        if (this.raiz != null && this.raiz.getElem() != elemento && obtenerNodo(this.raiz, elemento) != null) {
            //precondicion: el arbol no esta vacio, el elemento buscado no es la raiz y el elemento buscado existe
            elemPadre = padreRecursivo(this.raiz, elemento).getElem();
        }

        return elemPadre;
    }

    private NodoArbol padreRecursivo(NodoArbol nodo, Object buscado) {
        NodoArbol resultado = null;
        //si el nodo tiene como hijo der o izq el elem buscado
        if (nodo != null && nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
            if (nodo.getDerecho().getElem().equals(buscado) || nodo.getIzquierdo().getElem().equals(buscado)) {
                resultado = nodo;
            } else {
                //si no, busco por hijo izq
                resultado = padreRecursivo(nodo.getIzquierdo(), buscado);
                //si no se encontro por izq, busco por derecha
                if (resultado == null) {
                    resultado = padreRecursivo(nodo.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    //--------altura--------//
    public int altura() {
        int altura = -1;
        if (this.raiz != null) {
            if (this.raiz.getDerecho() == null && this.raiz.getIzquierdo() == null) {
                altura = 0;
            } else {
                altura = alturaRecursivo(this.raiz);
            }
        }
        return altura;
    }

    private int alturaRecursivo(NodoArbol nodo) {
        int altura = -1;
        int altIzq, altDer;
        if (nodo != null) {
            //devuelve la altura de los subarboles
            altIzq = alturaRecursivo(nodo.getIzquierdo());
            altDer = alturaRecursivo(nodo.getDerecho());
            //compara cual es la mas alta y le suma uno
            if (altIzq > altDer) {
                altura = altIzq + 1;
            } else {
                altura = altDer + 1;
            }
        }
        return altura;
    }

    //--------nivel--------//
    public int nivel(Object elem) {
        int nivel = -1;
        if (this.raiz != null) {
            nivel = nivelRecursivo(this.raiz, elem, 0);
        }
        return nivel;
    }

    private int nivelRecursivo(NodoArbol nodo, Object elem, int i) {
        int nivel = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                nivel = i;
            } else {
                int porIzquierda = nivelRecursivo(nodo.getIzquierdo(), elem, i + 1);
                if (porIzquierda > i) {
                    nivel = porIzquierda;
                } else {
                    nivel = nivelRecursivo(nodo.getDerecho(), elem, i + 1);
                }
            }
        }
        return nivel;
    }

    //--------vaciar--------//
    public void vaciar() {
        this.raiz = null;
    }

    //--------clone--------//
    @Override
    public ArbolBin clone() {
        ArbolBin clon = new ArbolBin();
        if (this.raiz != null) {
            NodoArbol nodoClon = new NodoArbol(this.raiz.getElem(), null, null);
            clon.raiz = nodoClon;
            cloneRecursivo(nodoClon, this.raiz);
        }
        return clon;
    }

    private void cloneRecursivo(NodoArbol nodoClon, NodoArbol nodoOriginal) {
        if (nodoOriginal.getIzquierdo() != null) {
            nodoClon.setIzquierdo(new NodoArbol(nodoOriginal.getIzquierdo().getElem(), null, null));
            cloneRecursivo(nodoClon.getIzquierdo(), nodoOriginal.getIzquierdo());
        }
        if (nodoOriginal.getDerecho() != null) {
            nodoClon.setDerecho(new NodoArbol(nodoOriginal.getDerecho().getElem(), null, null));
            cloneRecursivo(nodoClon.getDerecho(), nodoOriginal.getDerecho());

        }
    }

    //--------toString--------//
    @Override
    public String toString() {
        String texto = "Arbol vacio";
        if (!esVacio()) {
            texto = toStringRecursivo(this.raiz);
        }
        return texto;
    }

    private String toStringRecursivo(NodoArbol nodo) {
        String texto = "";
        if (nodo != null) {
            //pongo el elemento
            texto = "\n" + nodo.getElem().toString() + "\t";
            //si tiene hijo derecho lo pone, sino pone un guion
            if (nodo.getIzquierdo() != null) {
                texto = texto + "HI: " + nodo.getIzquierdo().getElem().toString() + "\t";
            } else {
                texto = texto + "HI: -\t";
            }
            //repite lo mismo para el hijo derecho
            if (nodo.getDerecho() != null) {
                texto = texto + "HD: " + nodo.getDerecho().getElem().toString();
            } else {
                texto = texto + "HD: -";
            }
            //paso a escribir el hijo izquierdo
            texto = texto + toStringRecursivo(nodo.getIzquierdo());
            //y al hijo derecho
            texto = texto + toStringRecursivo(nodo.getDerecho());
        }

        return texto;
    }

    //--------preOrden--------//
    public Lista listarPreorden() {
        Lista lista = new Lista();
        lista = preOrdenRecursivo(this.raiz, lista);
        return lista;
    }

    private Lista preOrdenRecursivo(NodoArbol nodo, Lista listado) {
        if (nodo != null) {
            listado.insertar(nodo.getElem(), listado.longitud() + 1);
            listado = preOrdenRecursivo(nodo.getIzquierdo(), listado);
            listado = preOrdenRecursivo(nodo.getDerecho(), listado);
        }

        return listado;
    }

    //--------posOrden--------//
    public Lista listarPosorden() {
        Lista lista = new Lista();

        lista = posOrdenRecursivo(this.raiz, lista);

        return lista;
    }

    private Lista posOrdenRecursivo(NodoArbol nodo, Lista listado) {
        if (nodo != null) {
            posOrdenRecursivo(nodo.getIzquierdo(), listado);
            posOrdenRecursivo(nodo.getDerecho(), listado);
            listado.insertar(nodo.getElem(), listado.longitud() + 1);
        }
        return listado;
    }

    //--------inOrden--------//
    public Lista listarInorden() {
        Lista lista = new Lista();
        lista = inOrdenRecursivo(this.raiz, lista);
        return lista;
    }

    private Lista inOrdenRecursivo(NodoArbol nodo, Lista listado) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.getIzquierdo(), listado);
            listado.insertar(nodo.getElem(), listado.longitud() + 1);
            inOrdenRecursivo(nodo.getDerecho(), listado);
        }
        return listado;
    }

    //--------porNivel--------//
    public Lista listarPorNiveles() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            Cola q = new Cola();
            int contador = 1;
            NodoArbol nodo = this.raiz;
            q.poner(nodo);
            while (!q.esVacia()) {
                nodo = (NodoArbol) q.obtenerFrente();
                q.sacar();
                lista.insertar(nodo.getElem(), contador);
                contador++;
                if (nodo.getIzquierdo() != null) {
                    q.poner(nodo.getIzquierdo());
                }
                if (nodo.getDerecho() != null) {
                    q.poner(nodo.getDerecho());
                }
            }

        }

        return lista;
    }

    //--------obtenerNodo--------//
    private NodoArbol obtenerNodo(NodoArbol nodo, Object buscado) {
        NodoArbol resultado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                resultado = nodo;
            } else {
                resultado = obtenerNodo(nodo.getIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(nodo.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    //--------obtenerHojas--------//
    public Lista obtenerHojas() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            lista = obtenerHojasRecursivo(lista, this.raiz);
        }
        return lista;
    }

    private Lista obtenerHojasRecursivo(Lista lista, NodoArbol nodo) {
        if (nodo != null) {
            if (nodo.getIzquierdo() != null || nodo.getDerecho() != null) {
                lista = obtenerHojasRecursivo(lista, nodo.getIzquierdo());
                lista = obtenerHojasRecursivo(lista, nodo.getDerecho());
            } else {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
        }
        return lista;
    }

    //--------frontera--------//
    /* Ejercicio tipo parcial: Implementar la operación frontera() que devuelve una lista 
    con la secuencia formada por los elementos almacenados en las 
    hojas del árbol binario, tomadas de izquierda a derecha.*/
    public Lista frontera() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            lista = fronteraRecursivo(lista, this.raiz);
        }
        return lista;
    }

    private Lista fronteraRecursivo(Lista lista, NodoArbol nodo) {
        if (nodo != null) {
            //si ninguno de sus hijos tiene un elemento, es hoja
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            } else {
                //sino, veo si sus hijos son hoja
                lista = fronteraRecursivo(lista, nodo.getIzquierdo());
                lista = fronteraRecursivo(lista, nodo.getDerecho());
            }
        }
        return lista;
    }

    //--------verificarPatron--------//
    /* Ejercicio tipo parcial: Implementar la operación boolean verificarPatron(Lista patron), que recibe por parámetro una lista patron
    y determine si coincide exactamente con al menos un camino del árbol que comience en la raíz y termine en
    una hoja. El método debe ser eficiente, es decir, recorrer el árbol lo estrictamente necesario*/
    public boolean verificarPatron(Lista patron) {
        boolean exito = false;
        if (this.raiz == null && patron.esVacia()) {
            exito = true;
        }
        if (this.raiz != null && !patron.esVacia()) {
            exito = verificarPatronRecursivo(patron, 0, this.raiz);
        }
        return exito;
    }

    private boolean verificarPatronRecursivo(Lista patron, int pos, NodoArbol nodo) {
        boolean exito = false;
        if (nodo != null && pos <= patron.longitud()) {
            if (verificarPatronRecursivo(patron, pos + 1, nodo.getIzquierdo())) {
                exito = true;
            } else {
                if (verificarPatronRecursivo(patron, pos + 1, nodo.getDerecho())) {
                    exito = true;
                } else {
                    exito = false;
                }
            }
        } else {
            exito = false;
        }
        return exito;
    }

    //--------clonarInvertido--------//
    /*Implementar la operación clonarInvertido() que devuelve un nuevo árbol, que es una copia del árbol original
    (this) pero donde los hijos están cambiados de lugar. Atención: el método devuelve un nuevo árbol, sin
    modificar el árbol original.*/
    public ArbolBin clonarInvertido() {
        ArbolBin clon = new ArbolBin();
        if (this.raiz != null) {
            clon = clonarInvertidoRecursivo(this.raiz, clon, null);
        }
        return clon;
    }

    private ArbolBin clonarInvertidoRecursivo(NodoArbol auxOriginal, ArbolBin clon, NodoArbol auxClon) {
        if (auxOriginal != null) {
            if (clon.raiz == null) {
                clon.raiz = new NodoArbol(auxOriginal.getElem(), null, null);
                auxClon = clon.raiz;
            }
            if (auxOriginal.getIzquierdo() != null) {
                auxClon.setDerecho(new NodoArbol(auxOriginal.getIzquierdo().getElem(), null, null));
                clon = clonarInvertidoRecursivo(auxOriginal.getIzquierdo(), clon, auxClon.getDerecho());
            }
            if (auxOriginal.getDerecho() != null) {
                auxClon.setIzquierdo(new NodoArbol(auxOriginal.getDerecho().getElem(), null, null));
                clon = clonarInvertidoRecursivo(auxOriginal.getDerecho(), clon, auxClon.getIzquierdo());
            }
        }
        return clon;
    }

    //--------modificarSubarboles--------//
    /*Agregar en la clase del TDA que implementa el árbol binario, la operación 
    modificarSubarboles(Object d1, Object d2 y Object d3) que recibe 3 elementos 
    del mismo tipo que los cargados en el árbol y debe modificar el árbol de la siguiente
    manera: Si d1 (padre) está en el árbol, d2 quedará como su hijo izquierdo y d3 como su 
    hijo derecho. Si el padre no tiene hijo de ese lado, debe agregar un nodo para ponerlo y, 
    si ya lo tiene, debe modificar el elemento de dicho nodo.*/
    public void modificarSubArboles(Object d1, Object d2, Object d3) {
        if (this.raiz != null) {
            modificarRecursivo(d1, d2, d3, this.raiz);
        }
    }

    private boolean modificarRecursivo(Object d1, Object d2, Object d3, NodoArbol nodo) {
        boolean encontrado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(d1)) {
                if (nodo.getIzquierdo() != null) {
                    nodo.getIzquierdo().setElem(d2);
                } else {
                    nodo.setIzquierdo(new NodoArbol(d2, null, null));
                }
                if (nodo.getDerecho() != null) {
                    nodo.getDerecho().setElem(d3);
                } else {
                    nodo.setDerecho(new NodoArbol(d3, null, null));
                }
                encontrado = true;
            } else {
                encontrado = modificarRecursivo(d1, d2, d3, nodo.getIzquierdo());
                if (!encontrado) {
                    encontrado = modificarRecursivo(d1, d2, d3, nodo.getDerecho());
                }
            }
        }
        return encontrado;
    }
}
