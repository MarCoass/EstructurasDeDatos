package jerarquicas;
//@author: Martina, Coassin-Fernandez. FAI-2542
import lineales.dinamicas.*;

public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    //------------------insertar()------------------//
    public boolean insertar(Object elem, Object padre) {
        /*no insertar el hijo en la ultima posicion de los hermanos,
        ya que siempre se recorrerian n elementos, esto es poco eficiente*/
        boolean exito = false;
        if (this.raiz == null) {
            //caso particular: primer elemento
            this.raiz = new NodoGen(elem, null, null);
            exito = true;
        } else {
            //veo si existe un nodo con el elemento padre
            NodoGen nodoPadre = obtenerNodo(this.raiz, padre);
            if (nodoPadre != null) {
                if (nodoPadre.getHijoIzquierdo() == null) {
                    //caso particular: el nodo padre no tiene hijos
                    nodoPadre.setHijoIzquierdo(new NodoGen(elem, null, null));
                } else {
                    //creo un nuevo nodo con el hijo izquierdo de padre como hermano derecho
                    NodoGen nuevo = new NodoGen(elem, null, nodoPadre.getHijoIzquierdo());
                    // seteo el nuevo nodo como hijo izquierdo de padre
                    nodoPadre.setHijoIzquierdo(nuevo);

                }
                exito = true;
            }
        }
        return exito;
    }

    //------------------pertenece()------------------//
    public boolean pertenece(Object elem) {
        return perteneceRecursivo(this.raiz, elem);
    }

    private boolean perteneceRecursivo(NodoGen nodo, Object elem) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                exito = true;
            } else {
                //visita el primer hijo
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !exito) {
                    exito = perteneceRecursivo(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return exito;
    }

    //------------------ancestros()------------------//
    public Lista ancestros(Object elem) {
        Lista lista = new Lista();
        if (perteneceRecursivo(this.raiz, elem)) {
            ancestrosRecursivo(elem, lista, this.raiz);
        }
        return lista;
    }

    private boolean ancestrosRecursivo(Object elem, Lista lista, NodoGen nodo) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                exito = true;
            } else {
                exito = ancestrosRecursivo(elem, lista, nodo.getHijoIzquierdo());
                if (exito) {
                    lista.insertar(nodo.getElem(), 1);
                } else {
                    exito = ancestrosRecursivo(elem, lista, nodo.getHermanoDerecho());
                }
            }

        }

        return exito;
    }

    //------------------esVacio()------------------//
    public boolean esVacio() {
        return (this.raiz == null);
    }

    //------------------altura()------------------//
    public int altura() {
        int altura = -1;
        if (this.raiz != null) {
            altura = alturaRecursivo(this.raiz, -1, -1);
        }
        return altura;
    }

    private int alturaRecursivo(NodoGen nodo, int alturaPadre, int alturaMax) {
        int alt;
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null) {
                //el nodo es hoja
                alt = alturaPadre + 1;
                alturaMax = Math.max(alt, alturaMax);
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    alt = alturaRecursivo(hijo, alturaPadre + 1, alturaMax);
                    alturaMax = Math.max(alt, alturaMax);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return alturaMax;
    }

    //------------------nivel()------------------//
    public int nivel(Object elem) {
        int nivel = -1;
        if (this.raiz != null) {
            nivel = nivelRecursivo(this.raiz, -1, elem);
        }
        return nivel;
    }

    private int nivelRecursivo(NodoGen nodo, int nivelPadre, Object elem) {
        int nivel = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                nivel = nivelPadre + 1;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                nivel = nivelRecursivo(hijo, nivelPadre + 1, elem);
                if (nivel == -1) {
                    while (hijo != null && nivel == -1) {
                        hijo = hijo.getHermanoDerecho();
                        nivel = nivelRecursivo(hijo, nivelPadre + 1, elem);
                    }
                }
            }
        }
        return nivel;
    }

    //------------------padre()------------------//
    public Object padre(Object elem) {
        Object padre = null;
        if (this.raiz != null) {
            if (elem != this.raiz.getElem()) {
                padre = padreRecursivo(this.raiz, elem);
            }
        }
        return padre;
    }

    private Object padreRecursivo(NodoGen nodo, Object elem) {
        Object elemPadre = null;
        if (nodo != null) {
            boolean encontrado = false;
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null && !encontrado) {
                if (hijo.getElem().equals(elem)) {
                    encontrado = true;
                    elemPadre = nodo.getElem();
                } else {
                    hijo = hijo.getHermanoDerecho();
                }
            }
            if (!encontrado) {
                hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !encontrado) {
                    elemPadre = padreRecursivo(hijo, elem);
                    if (elemPadre != null) {
                        encontrado = true;
                    } else {
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return elemPadre;
    }

    //------------------obtenerNodo()------------------//
    private NodoGen obtenerNodo(NodoGen nodo, Object elem) {
        NodoGen buscado = null;
        if (nodo != null) {
            //visito el nodo
            if (nodo.getElem().equals(elem)) {
                buscado = nodo;
            } else {
                //visita el primer hijo
                NodoGen hijo = nodo.getHijoIzquierdo();
                buscado = obtenerNodo(hijo, elem);
                while (buscado == null && hijo != null) {
                    hijo = hijo.getHermanoDerecho();
                    buscado = obtenerNodo(hijo, elem);
                }

            }
        }
        return buscado;
    }

    //------------------listarPreOrden()------------------//
    public Lista listarPreorden() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarPreOrdenRecursivo(this.raiz, lista);
        }
        return lista;
    }

    private void listarPreOrdenRecursivo(NodoGen nodo, Lista lista) {
        if (nodo != null) {

            //visito la raiz
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            //para cada hijo de nodo
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                listarPreOrdenRecursivo(hijo, lista);
                hijo = hijo.getHermanoDerecho();
            }

        }
    }

    //------------------listarInOrden()------------------//
    public Lista listarInorden() {
        Lista lista = new Lista();
        listarInordenAux(this.raiz, lista);
        return lista;
    }

    private void listarInordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            //llama recursivamente con el primer hijo del nodo
            if (nodo.getHijoIzquierdo() != null) {
                listarInordenAux(nodo.getHijoIzquierdo(), lista);
            }
            //visita el nodo
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            //llama recursivamente con los otros hijos del nodo
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, lista);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    //------------------listarPosOrden()------------------//
    public Lista listarPosorden() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarPosOrdenRecursivo(this.raiz, lista);
        }
        return lista;
    }

    private void listarPosOrdenRecursivo(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                listarPosOrdenRecursivo(hijo, lista);
                hijo = hijo.getHermanoDerecho();
            }
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    //------------------listarPorNiveles()------------------//
    public Lista listarPorNiveles() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            Cola cola = new Cola();
            NodoGen nodo;
            cola.poner(this.raiz);

            while (!cola.esVacia()) {
                nodo = (NodoGen) cola.obtenerFrente();
                cola.sacar();
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
                nodo = nodo.getHijoIzquierdo();
                while (nodo != null) {
                    cola.poner(nodo);
                    nodo = nodo.getHermanoDerecho();
                }

            }
        }
        return lista;
    }

    //------------------vaciar()------------------//
    public void vaciar() {
        this.raiz = null;
    }

    //------------------toString()------------------//
    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen nodo) {
        String texto = "";
        if (nodo != null) {
            //visita el nodo
            texto = texto + nodo.getElem().toString() + " -> ";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                texto = texto + hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            //recorre recursivamente los hijos 
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                texto = texto + "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return texto;
    }

    //------------------clone()------------------//
    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        if (this.raiz != null) {
            clon.raiz = new NodoGen(this.raiz.getElem(), null, null);
            clon.cloneRecursivo(this.raiz, clon.raiz);
        }
        return clon;
    }

    private void cloneRecursivo(NodoGen auxOriginal, NodoGen auxClon) {
        if (auxOriginal.getHijoIzquierdo() != null) {
            //el nodo tiene hijos
            //creo el hijo mas izquierdo
            auxClon.setHijoIzquierdo(new NodoGen(auxOriginal.getHijoIzquierdo().getElem(), null, null));
            //avanzo los punteros
            auxOriginal = auxOriginal.getHijoIzquierdo();
            auxClon = auxClon.getHijoIzquierdo();

            //creo los nodos hermanos
            NodoGen auxA = auxOriginal.getHermanoDerecho();
            NodoGen auxB = auxClon;
            while (auxA != null) {
                auxB.setHermanoDerecho(new NodoGen(auxA.getElem(), null, null));
                //avanzo lo nodos
                auxB = auxB.getHermanoDerecho();
                auxA = auxA.getHermanoDerecho();
            }

            //llamo para cada hijo
            auxA = auxOriginal;
            auxB = auxClon;
            while (auxA != null) {
                cloneRecursivo(auxA, auxB);
                auxA = auxA.getHermanoDerecho();
                auxB = auxB.getHermanoDerecho();
            }
        }
    }

    //------------------grado()------------------//
    public int grado() {
        int grado = -1;
        if (this.raiz != null) {
            grado = gradoRecursivo(this.raiz);
        }
        return grado;
    }

    private int gradoRecursivo(NodoGen nodo) {
        int grado = 0;
        if (nodo != null) {
            //cuento la cantidad de hijos

            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                grado++;
                hijo = hijo.getHermanoDerecho();
            }
            //para cada hijo comparo si tiene mayor o menos grado
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                int gradoHijo = gradoRecursivo(hijo);
                grado = Math.max(grado, gradoHijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return grado;
    }

    //------------------gradoSubarbol()------------------//
    public int gradoSubarbol(Object elem) {
        int grado = -1;
        NodoGen nodo = obtenerNodo(this.raiz, elem);
        if (nodo != null) {
            grado = gradoSubarbolRecursivo(nodo);

        }

        return grado;
    }

    private int gradoSubarbolRecursivo(NodoGen nodo) {
        int grado = 0;
        if (nodo.getHijoIzquierdo() != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                grado++;
                hijo = hijo.getHermanoDerecho();
            }
        }
        return grado;
    }
}
