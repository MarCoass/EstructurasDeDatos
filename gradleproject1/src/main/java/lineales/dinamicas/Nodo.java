
package lineales.dinamicas;


public class Nodo {
    
    private Object elemento;
    private Nodo Enlace;
    
    //Constructor
    public Nodo(Object elemento, Nodo Enlace){
        this.elemento = elemento;
        this.Enlace = Enlace;
    }
    
    //Modificadores
    public void setElemento(Object elemento){
        this.elemento = elemento;
    }
    
    public void setEnlace(Nodo Enlace){
        this.Enlace = Enlace;
    }
    
    //Observadores
    public Object getElemento(){
        return this.elemento;
    }
    
    public Nodo getEnlace(){
        return this.Enlace;
    }
    
}
