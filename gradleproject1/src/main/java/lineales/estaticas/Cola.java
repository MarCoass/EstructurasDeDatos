package lineales.estaticas;

/*cola vacia: frente = final
   cola llena: final+1 = frente*/
public class Cola {

    private static final int TAMANIO = 10;
    private Object[] arreglo;
    private int frente;
    private int fin;

    public Cola() {
        this.arreglo = new Object[TAMANIO];
        this.fin = 0;
        this.frente = 0;
    }

    public boolean poner(Object elem) {
        boolean exito = false;
        if ((fin + 1) % TAMANIO != frente) {
            //la cola no esta llena 
            this.arreglo[fin] = elem;
            fin = (fin + 1) % TAMANIO;
            exito = true;
        }

        return exito;
    }

    public boolean sacar() {
        boolean exito = false;

        if (frente != fin) {
            //si la cola no esta vacia
            this.arreglo[frente] = null;
            frente = (frente + 1) % TAMANIO;
            exito = true;
        }

        return exito;
    }

    public Object obtenerFrente() {
        return this.arreglo[frente];
    }

    public boolean esVacia() {
        return frente == fin;
    }

    public void vaciar() {
        for (int i = 0; i < TAMANIO; i++) {
            this.arreglo[i] = null;
        }
        this.frente = 0;
        this.fin = 0;
    }

    public Cola clone() {
        Cola clon = new Cola();
        int i = 0;
        while (arreglo[i] != null) {
            clon.arreglo[i] = this.arreglo[i];
            i++;
        }

        clon.fin = this.fin;
        clon.frente = this.frente;
        return clon;
    }

    public String toString() {
        String texto = "";
        if (this.frente == this.fin) {
            texto = "Cola vacia.";
        } else {
            int i = frente;
            while (this.arreglo[i] != null) {
                texto = texto + " [" + this.arreglo[i] + "]";
                i = (i % TAMANIO) + 1;
                if (i >= TAMANIO) {
                    i = i - 10;
                }
            }

        }
        return texto;
    }

}
