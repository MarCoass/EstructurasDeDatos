
package conjuntistas;
//balance(n) = altura(n.HI)-altura(n.HD)
//se aceptan nodos con balance <= valor absoluto(1)

import lineales.dinamicas.Lista;

class ArbolAVL {
    
    private NodoAVL raiz;
    
    public ArbolAVL(){
        this.raiz =null;
    }
    
    //----------------pertenece()----------------//
     public boolean pertenece(Comparable elemento) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = perteneceRecursivo(elemento, this.raiz);
        }
        return exito;
    }

    private boolean perteneceRecursivo(Comparable elemento, NodoAVL nodo) {
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

    //----------------insertar()----------------//
    
    //----------------eliminar()----------------//
    
    //----------------listar()----------------//
     public Lista listar() {
        //recorrido inorden devuelve los elementos ordenados
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarRecursivo(this.raiz, lista);
        }
        return lista;
    }

    private void listarRecursivo(NodoAVL nodo, Lista lista) {
        if (nodo != null) {
            listarRecursivo(nodo.getIzq(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listarRecursivo(nodo.getDer(), lista);
        }
    }

    //----------------listarRango()----------------//
    //agregar despues de corregir en ArbolBB
    
//-----------------------minimoElem()-----------------------//
    public Comparable minimoElem() {
        Comparable elem = null;
        if (this.raiz != null) {
            elem = minimoElemRecursivo(this.raiz);
        }
        return elem;
    }

    private Comparable minimoElemRecursivo(NodoAVL nodo) {
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

    private Comparable maximoElemRecursivo(NodoAVL nodo) {
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

    private String toStringRecursivo(NodoAVL nodo) {
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
    public ArbolAVL clone() {
        ArbolAVL clon = new ArbolAVL();
        if (this.raiz != null) {
            NodoAVL nodoClon = new NodoAVL(this.raiz.getElem());
            clon.raiz = nodoClon;
            cloneRecursivo(nodoClon, this.raiz);
        }
        return clon;
    }

    private void cloneRecursivo(NodoAVL nodoClon, NodoAVL nodoOriginal) {
        if (nodoOriginal.getIzq() != null) {
            nodoClon.setIzq(new NodoAVL(nodoOriginal.getIzq().getElem()));
            cloneRecursivo(nodoClon.getIzq(), nodoOriginal.getIzq());
        }
        if (nodoOriginal.getDer() != null) {
            nodoClon.setDer(new NodoAVL(nodoOriginal.getDer().getElem()));
            cloneRecursivo(nodoClon.getDer(), nodoOriginal.getDer());

        }
    }
}
