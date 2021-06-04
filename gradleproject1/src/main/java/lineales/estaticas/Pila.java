/**
 * Correciones: No usar Ñ para identificadores -Corregido
 *              clone(): La sentencia clon.tope = this.tope; deberia realizarse una unica vez fuera de la repetitiva. -Corregido
 */

package lineales.estaticas;

public class Pila {

    private static final int TAMANIO = 10;
    private Object[] arreglo;
    private int tope;

    public Pila() {
        arreglo = new Object[TAMANIO];
        tope = -1;
    }

    public boolean apilar(Object nuevoElem) {
        boolean exito = false;
        if (tope + 1 < TAMANIO) {
            tope++;
            arreglo[tope] = nuevoElem;
            exito = true;
        }
        return exito;
    }

    public boolean desapilar() {
        boolean exito = false;
        if (!esVacia()) {
            tope--;
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        Object aux = null;
        if (tope != -1) {
            aux = this.arreglo[tope];
        }
        return aux;
    }

    public boolean esVacia() {
        boolean vacia = false;
        if (tope == -1) {
            vacia = true;
        }
        return vacia;
    }

    public void vaciar() {
        for (int i = 0; i < tope; i++) {
            this.arreglo[i] = null;
        }
        this.tope = -1;
    }

    public Pila clone() {
        Pila clon = new Pila();
        Object aux;
        clon.tope = this.tope;
        for (int i = 0; i <= this.tope; i++) {
            aux = this.arreglo[i];
            clon.arreglo[i] = aux;
        }

        return clon;
    }

    public String toString() {
        String texto = "";
        if (tope != -1) {
            for (int i = 0; i <= this.tope; i++) {
                texto = texto + " " + this.arreglo[i];
            }
        } else {
            texto = "Pila vacia.";
        }

        return texto;
    }

    public boolean esCapicua(){
        //ver si la secuencia de numeros en la pila es capicua
        boolean esCapicua=true;
        int punteroInicio = this.tope;
        int punteroFinal = 0; //marca a la posicion del arreglo
        while(esCapicua && punteroInicio>punteroFinal){
            //mientras esCapicua sea verdadero y el puntero del final sea menor al inicio
            if (arreglo[punteroInicio]!=arreglo[punteroFinal]) {
                //no son iguales, entonces no es capicua
                esCapicua= false;
            }else{
                //los numeros coinciden
                punteroInicio = punteroInicio-1;
                punteroFinal = punteroFinal+1;
            }
        }
        return esCapicua;
    }
    
}
