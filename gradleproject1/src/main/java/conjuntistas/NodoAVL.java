
package conjuntistas;

public class NodoAVL {
    
    private Comparable elem;
    private int altura;
    private NodoAVL izq;
    private NodoAVL der;

    public NodoAVL(Comparable elem) {
        this.elem = elem;
        this.altura = 0;
        this.izq = null;
        this.der = null;
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAVL getIzq() {
        return izq;
    }

    public void setIzq(NodoAVL izq) {
        this.izq = izq;
    }

    public NodoAVL getDer() {
        return der;
    }

    public void setDer(NodoAVL der) {
        this.der = der;
    }
    
    public void recalcularAltura(){
        //altura de un nodo Max(alturaHI, alturaHD)+1
        altura = Math.max(izq.getAltura(), der.getAltura())+1;
    }
    
}
