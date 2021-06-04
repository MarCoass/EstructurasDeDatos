package jerarquicas;
//@author: Martina, Coassin-Fernandez. FAI-2542
public class NodoGen {

    private Object elem;
    private NodoGen hijoIzq;
    private NodoGen hermanoDerecho;

    public NodoGen(Object nuevoElem, NodoGen izq, NodoGen der) {
        this.elem = nuevoElem;
        this.hijoIzq = izq;
        this.hermanoDerecho = der;
    }

    public Object getElem() {
        return this.elem;
    }

    public NodoGen getHijoIzquierdo() {
        return this.hijoIzq;
    }

    public NodoGen getHermanoDerecho() {
        return this.hermanoDerecho;
    }

    public void setElem(Object nuevoElem) {
        this.elem = nuevoElem;
    }

    public void setHijoIzquierdo(NodoGen izq) {
        this.hijoIzq = izq;
    }

    public void setHermanoDerecho(NodoGen der) {
        this.hermanoDerecho = der;
    }

   

}
