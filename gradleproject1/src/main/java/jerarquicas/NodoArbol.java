
package jerarquicas;


public class NodoArbol {
    
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    public NodoArbol(Object nuevoElem, NodoArbol izq, NodoArbol del){
        elem = nuevoElem;
        izquierdo = izq;
        derecho = del;
    }
    
    public Object getElem(){
        return this.elem;
    }
    
    public NodoArbol getIzquierdo(){
        return this.izquierdo;
    }
    
    public NodoArbol getDerecho(){
        return this.derecho;
    }
    
    public void setElem(Object nuevoElem){
        this.elem = nuevoElem;
    }
    
    public void setIzquierdo(NodoArbol izq){
        this.izquierdo = izq;
    }
    
    public void setDerecho(NodoArbol der){
        this.derecho = der;
    }
    
}
