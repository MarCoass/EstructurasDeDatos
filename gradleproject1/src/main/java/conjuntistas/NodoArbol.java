
package conjuntistas;

public class NodoArbol {
    
    private Comparable elem;
    private NodoArbol izq;
    private NodoArbol der;
    
    public NodoArbol(Comparable elemento, NodoArbol nodoIzq, NodoArbol nodoDer){
        this.elem = elemento;
        this.izq = nodoIzq;
        this.der = nodoDer;
    }

    public Comparable getElem() {
        return elem;
    }

    public NodoArbol getIzq() {
        return izq;
    }

    public NodoArbol getDer() {
        return der;
    }

    public void setElem(Comparable elemento) {
        this.elem = elemento;
    }

    public void setIzq(NodoArbol izquierdo) {
        this.izq = izquierdo;
    }

    public void setDer(NodoArbol derecho) {
        this.der = derecho;
    }
    
    
}
