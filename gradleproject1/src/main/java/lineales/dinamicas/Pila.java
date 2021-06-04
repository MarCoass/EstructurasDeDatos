package lineales.dinamicas;
//CORRECCION: - clone(): No es correcto; se entrelazan los nodos de ambas pilas.
public class Pila {

    private Nodo tope;

    //Constructor
    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object elemento) {
        //recibe un objeto, crea un nodo enlazaado al tope actual con este elemento y cambia el tope a este nuevo nodo
        Nodo nuevoNodo = new Nodo(elemento, null);
        nuevoNodo.setEnlace(tope);
        this.tope = nuevoNodo; 
        return true;
    }

    public boolean desapilar() {
        //desapila el elemento que este en el tope y cambia el tope al Enlace del nodo desapilado
        boolean exito;
        if (tope != null) {
            this.tope = this.tope.getEnlace();
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    public Object obtenerTope() {
        //devuelve el elemento del nodo tope
        Object elemTope = new Object();
        if (esVacia()) {
            elemTope = null;
        } else {
            elemTope = this.tope.getElemento();
        }
        return elemTope;
    }

    public boolean esVacia() {
        //retorna si la pila es vacia
        return (tope == null);
    }

    public void vaciar() {
        this.tope = null;
    }

    public Pila clone() {
        Pila clon = new Pila();
        if (this.tope!=null) {
            Nodo aux = new Nodo(this.tope.getElemento(),this.tope.getEnlace());
            clon.tope = aux;
            while(aux!=null){
                Nodo nuevo = new Nodo(aux.getElemento(),aux.getEnlace());
                aux = nuevo.getEnlace();
            }
        }
        return clon;
    }

    public String toString() {
        String texto = "";
        if (this.tope == null) {
            texto = "Pila vacia.";
        } else {
            Nodo aux = this.tope;
            while (aux != null) {
                //Duda: si lo escibo como se explica en el video, me tira la pila en el orden inverso.
                texto = aux.getElemento().toString()+" "+texto;
                aux = aux.getEnlace();
            }
        }
        return texto;
    }
}
