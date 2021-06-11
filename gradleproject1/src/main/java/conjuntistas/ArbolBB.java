package conjuntistas;
//Arreglar listarRango() para que funcione aunque uno de los valores no se encuentre en el arbol
import lineales.dinamicas.Lista;

public class ArbolBB {

    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    //-----------------------pertenece()-----------------------//
    public boolean pertenece(Comparable elemento) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = perteneceRecursivo(elemento, this.raiz);
        }
        return exito;
    }

    private boolean perteneceRecursivo(Comparable elemento, NodoABB nodo) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elemento) == 0) {
                //elem = nodo.getElem()
                exito = true;
            } else {
                if (nodo.getElem().compareTo(elemento) > 0) {
                    exito = perteneceRecursivo(elemento, nodo.getIzq());
                } else {
                    exito = perteneceRecursivo(elemento, nodo.getDer());
                }
            }
        }
        return exito;
    }

    //-----------------------insertar()-----------------------//
    public boolean insertar(Comparable elemento) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elemento);
        } else {
            exito = insertarRecursivo(this.raiz, elemento);
        }
        return exito;
    }

    private boolean insertarRecursivo(NodoABB n, Comparable elemento) {
        boolean exito = true;
        //precondicion: nodo no es nulo
        if (n != null) {
            if ((elemento.compareTo(n.getElem()) == 0)) {
                //elemento repetido
                exito = false;
            } else if (elemento.compareTo(n.getElem()) < 0) {
                //elemento es menor que n.getElem()
                //si tiene HI baja a la izquierda, sino agrega elemento
                if (n.getIzq() != null) {
                    exito = insertarRecursivo(n.getIzq(), elemento);
                } else {
                    n.setIzq(new NodoABB(elemento));
                }
            } else //elemento es mayor que n.getElem()
            //si tiene HD baja a la derecha, sino agrega elemento
            if (n.getDer() != null) {
                exito = insertarRecursivo(n.getDer(), elemento);
            } else {
                n.setDer(new NodoABB(elemento));
            }
        } else {
            exito = false;
        }
        return exito;
    }

    //-----------------------eliminar()-----------------------//
    public boolean eliminar(Comparable elemento) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = eliminarRecursivo(elemento, this.raiz, null);
        }
        return exito;
    }

    private boolean eliminarRecursivo(Comparable elem, NodoABB nodo, NodoABB padre) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) > 0) {
                //el nodo es mas grande que elem, avanzo por izquierda
                exito = eliminarRecursivo(elem, nodo.getIzq(), nodo);
            } else if (nodo.getElem().compareTo(elem) < 0) {
                //el nodo es menor que el elem, avanzo por derecha
                exito = eliminarRecursivo(elem, nodo.getDer(), nodo);
            } else {
                //si no es mayor ni menor, es igual. Elimino segun caso correspondiente
                if (nodo.getIzq() == null && nodo.getDer() == null) {
                    //caso 1: el nodo es hoja
                    eliminarCaso1(elem, padre);
                } else if (nodo.getIzq() != null && nodo.getDer() != null) {
                    //caso 3: tiene 2 hijos
                    eliminarCaso3(nodo, padre);
                } else {
                    //caso 2: tiene un solo hijo
                    eliminarCaso2(nodo, padre);
                }
                exito = true;
                System.out.println("exito: " + exito);
            }
        }
        return exito;
    }

    private void eliminarCaso1(Comparable elem, NodoABB nodoPadre) {
        //Caso 1: el nodo a eliminar es hoja, simplemente se setea la posicion de hijo que ocupa a null
        if (nodoPadre.getIzq() != null && nodoPadre.getIzq().getElem().compareTo(elem) == 0) {
            nodoPadre.setIzq(null);
        } else {
            nodoPadre.setDer(null);
        }
    }

    private void eliminarCaso2(NodoABB nodoElem, NodoABB nodoPadre) {
        //Caso 2: el nodo tiene un solo elemento, se setea este como hijo en la posicion de nodoPadre ocupada por elem
        if (nodoPadre.getIzq().equals(nodoElem)) {
            if (nodoElem.getIzq() != null) {
                nodoPadre.setIzq(nodoElem.getIzq());
            } else {
                nodoPadre.setIzq(nodoElem.getDer());
            }
        } else {
            if (nodoElem.getIzq() != null) {
                nodoPadre.setDer(nodoElem.getIzq());
            } else {
                nodoPadre.setDer(nodoElem.getDer());
            }
        }
    }

    private void eliminarCaso3(NodoABB nodoElem, NodoABB nodoPadre) {
        /*Caso 3: el nodo a eliminar encuentra entre sus descendientes uno que suba a ocupar el lugar del elem
        Planteado con candidato A: el mayor elemento del subarbol izquierdo
        Busco el candidato A: se comienza bajando al h.der y se repite el movimiento 
        pero bajando hacia la izquierda hasta que se encuentre un nodo que no tenga h.der*/
        NodoABB candidato = nodoElem.getDer();
        NodoABB padreCandidato = nodoElem;
        while (candidato.getDer() != null) {
            candidato = candidato.getIzq();
            padreCandidato = padreCandidato.getIzq();
        }
        //Reemplazo el valor de nodoElem por el de candidato y elimino candidato
        nodoElem.setElem(candidato.getElem());
        if (padreCandidato.getIzq().getElem().compareTo(candidato.getElem()) == 0) {
            padreCandidato.setIzq(null);
        } else {
            padreCandidato.setDer(null);
        }
    }

    //-----------------------listar()-----------------------//
    public Lista listar() {
        //recorrido inorden devuelve los elementos ordenados
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarRecursivo(this.raiz, lista);
        }
        return lista;
    }

    private void listarRecursivo(NodoABB nodo, Lista lista) {
        if (nodo != null) {
            listarRecursivo(nodo.getIzq(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listarRecursivo(nodo.getDer(), lista);
        }
    }

    //-----------------------listarRango()-----------------------//
    public Lista listarRango(Comparable elemMin, Comparable elemMax) {
        Lista lista = new Lista();
        //precondicion: elemMin y elemMax existen
        if (pertenece(elemMin) && pertenece(elemMax)) {
            listarRangoRecursivo(elemMin, elemMax, this.raiz, lista);
        }
        return lista;
    }
    //Como hacer para solo recorrer los nodos necesarios?
    private void listarRangoRecursivo(Comparable elemMin, Comparable elemMax, NodoABB nodo, Lista lista) {
        if (nodo != null) {
            //recorrido inorden para que la lista quede ordenada
            listarRangoRecursivo(elemMin, elemMax, nodo.getIzq(), lista);
            //si el nodo se encuentra entre el intervalo min y max lo agrego
            if (nodo.getElem().compareTo(elemMin) >= 0 && nodo.getElem().compareTo(elemMax) <= 0) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            listarRangoRecursivo(elemMin, elemMax, nodo.getDer(), lista);
        }
    }

    //-----------------------minimoElem()-----------------------//
    public Comparable minimoElem() {
        Comparable elem = null;
        if (this.raiz != null) {
            elem = minimoElemRecursivo(this.raiz);
        }
        return elem;
    }

    private Comparable minimoElemRecursivo(NodoABB nodo) {
        Comparable elem = nodo.getElem();
        if (nodo.getIzq() != null) {
            elem = minimoElemRecursivo(nodo.getIzq());
        }
        return elem;
    }

    //-----------------------maximoElem()-----------------------//
    public Comparable maximoElem() {
        Comparable elem = null;
        if (this.raiz != null) {
            elem = maximoElemRecursivo(this.raiz);
        }
        return elem;
    }

    private Comparable maximoElemRecursivo(NodoABB nodo) {
        Comparable elem = nodo.getElem();
        if (nodo.getDer() != null) {
            elem = maximoElemRecursivo(nodo.getDer());
        }
        return elem;
    }

    //-----------------------vacio()-----------------------//
    public boolean esVacio() {
        return (this.raiz == null);
    }

    //-----------------------toString()-----------------------//
    @Override
    public String toString() {
        String texto = "";
        if (this.raiz != null) {
            texto = toStringRecursivo(this.raiz);
        }
        return texto;
    }

    private String toStringRecursivo(NodoABB nodo) {
        String texto = "";
        if (nodo != null) {
            //pongo el elemento
            texto = "\n" + nodo.getElem().toString() + "\t";
            //si tiene hijo derecho lo pone, sino pone un guion
            if (nodo.getIzq() != null) {
                texto = texto + "HI: " + nodo.getIzq().getElem().toString() + "\t";
            } else {
                texto = texto + "HI: -\t";
            }
            //repite lo mismo para el hijo derecho
            if (nodo.getDer() != null) {
                texto = texto + "HD: " + nodo.getDer().getElem().toString();
            } else {
                texto = texto + "HD: -";
            }
            //paso a escribir el hijo izquierdo
            texto = texto + toStringRecursivo(nodo.getIzq());
            //y al hijo derecho
            texto = texto + toStringRecursivo(nodo.getDer());
        }

        return texto;
    }

    //-----------------------clone()-----------------------//
    @Override
    public ArbolBB clone() {
        ArbolBB clon = new ArbolBB();
        if (this.raiz != null) {
            NodoABB nodoClon = new NodoABB(this.raiz.getElem());
            clon.raiz = nodoClon;
            cloneRecursivo(nodoClon, this.raiz);
        }
        return clon;
    }

    private void cloneRecursivo(NodoABB nodoClon, NodoABB nodoOriginal) {
        if (nodoOriginal.getIzq() != null) {
            nodoClon.setIzq(new NodoABB(nodoOriginal.getIzq().getElem()));
            cloneRecursivo(nodoClon.getIzq(), nodoOriginal.getIzq());
        }
        if (nodoOriginal.getDer() != null) {
            nodoClon.setDer(new NodoABB(nodoOriginal.getDer().getElem()));
            cloneRecursivo(nodoClon.getDer(), nodoOriginal.getDer());

        }
    }
    //-----------------------PRACTICA 2DO PARCIAL-----------------------//
    //-----------------------eliminarMinimo()-----------------------//
    /*implementar el metodo eliminarMinimo() que elimine el elemento mas
    pequeño del arbol en un solo recorrido y visitando lo minimo indisplensable*/
    public void eliminarMinimo(){
        if(this.raiz!=null){
            eliminarMinimoRecursivo(this.raiz);
        }
    }
    
    private void eliminarMinimoRecursivo(NodoABB nodo){
        if (nodo!=null) {
            if (nodo.getIzq().getIzq()!=null) {
                //el nodo izq tiene hijo izquierdo, significa que no es el menor
                eliminarMinimoRecursivo(nodo.getIzq());
            } else {
                //el hijo es hoja
                nodo.setIzq(null);
                
            }
        }
    }
    //-----------------------obtenerNodo()-----------------------//
    private NodoABB obtenerNodo(Comparable elem, NodoABB nodo) {
        NodoABB resultado = null ;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) == 0) {
                resultado = nodo;
            } else {
                if (nodo.getElem().compareTo(elem) > 0) {
                    resultado = obtenerNodo(elem, nodo.getIzq());
                } else {
                    resultado = obtenerNodo(elem, nodo.getDer());
                }
            }
        
        }
        return resultado;
    }
//-----------------------EJERCICIO 2DO PARCIAL-----------------------//
    //-----------------------sumarPreordenDesde()-----------------------//
    /*Desarrollar el método sumarPreordenDesde(elem,k), que recibe como parámetro el elemento elem y el valor entero k. 
Se debe encontrar elem en el árbol, y para ese subárbol de raíz elem, recorrer en preorden y sumar los valores de cada nodo 
visitado hasta que esa suma llegue a superar el valor k, y ahí detener el recorrido. (elem se debe incluir en la suma.)*/
    public int sumarPreordenDesde(Comparable elem, int k) {
        int suma = 0;
         NodoABB nodo = obtenerNodo(elem, this.raiz);
        if (nodo!=null) {
            suma = sumarPreordenRecursivo(nodo, k, suma);
            if (suma < k) {
                suma = suma * (-1);
            }
        }
        return suma;
    }

    private int sumarPreordenRecursivo(NodoABB nodo, int k, int total) {

        if (nodo != null) {
            total = total + (int) nodo.getElem();
            if (total <= k) {
                total = sumarPreordenRecursivo(nodo.getIzq(), k, total);
            }
            if (total < k) {
                total = sumarPreordenRecursivo(nodo.getDer(), k, total);
            } else {
            }
        }
        System.out.println("retorna: " + total);
        return total;
    }
}
