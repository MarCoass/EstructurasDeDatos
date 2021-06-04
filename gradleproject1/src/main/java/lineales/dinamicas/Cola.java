package lineales.dinamicas;

public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        frente = null;
        fin = null;
    }

    public boolean poner(Object nuevoElem) {
        boolean exito = true;
        Nodo nuevo = new Nodo(nuevoElem, null);
        if (frente == null) {
            this.frente = nuevo;
            this.fin = nuevo;
        } else {
            this.fin.setEnlace(nuevo);
            this.fin = nuevo;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {
            exito = false;
        } else {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elemFrente = null;
        if (this.frente != null) {
            elemFrente = this.frente.getElemento();
        }
        return elemFrente;
    }

    public boolean esVacia() {
        return this.frente == null;
    }

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    public Cola clone() {
        Cola clon = new Cola();
        if (this.frente != null) {
            //nodo que apunta a la cola original
            Nodo auxOriginal = this.frente;
            clon.frente = new Nodo(auxOriginal.getElemento(), null);
            //nodo que apunta a la cola clon
            Nodo auxClon = clon.frente;
            auxOriginal = auxOriginal.getEnlace();
            while (auxOriginal != null) {
                auxClon.setEnlace(new Nodo(auxOriginal.getElemento(), null));
                auxOriginal = auxOriginal.getEnlace();
                auxClon = auxClon.getEnlace();
            }
            clon.fin = auxClon;
        }
        return clon;
    }

    public String toString() {
        String texto = "";
        if (this.frente == null) {
            texto = "Cola vacia";
        } else {
            Nodo aux = this.frente;
            while (aux != null) {
                texto = texto + "[" + aux.getElemento() + "]";
                aux = aux.getEnlace();
            }

        }
        return texto;
    }

}
