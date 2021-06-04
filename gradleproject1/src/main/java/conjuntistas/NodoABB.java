
package conjuntistas;

public class NodoABB {
    
    private Comparable elem;
    private NodoABB izq;
    private NodoABB der;
    
    public NodoABB(Comparable elemento){
        this.elem = elemento;
    }

    public Comparable getElem() {
        return elem;
    }

    public NodoABB getIzq() {
        return izq;
    }

    public NodoABB getDer() {
        return der;
    }

    public void setElem(Comparable elemento) {
        this.elem = elemento;
    }

    public void setIzq(NodoABB izquierdo) {
        this.izq = izquierdo;
    }

    public void setDer(NodoABB derecho) {
        this.der = derecho;
    }
    
    
}
