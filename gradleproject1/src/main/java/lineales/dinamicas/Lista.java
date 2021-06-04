package lineales.dinamicas;

public class Lista {

    private Nodo cabecera;

    public Lista() {
        cabecera = null;
    }

    public boolean insertar(Object nuevoElem, int pos) {
        boolean exito = true;
        if (pos < 1 || pos > this.longitud() + 1) {
            //caso de posicion invalida
            exito = false;
        } else {
            if (pos == 1) {
                //primera posicion
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = false;
        if (pos < 1 || pos > this.longitud()) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());

            }
            exito = true;
        }

        return exito;
    }

    public Object recuperar(int pos) {
        Object elemento;
        if (pos < 1 || pos > this.longitud()) {
            //posicion incorrecta
            elemento = null;
        } else {
            Nodo aux = this.cabecera;
            for (int i = 1; i < pos; i++) {
                aux = aux.getEnlace();
            }
            elemento = aux.getElemento();
        }

        return elemento;
    }

    public int localizar(Object elem) {
        int pos, i, longitud;
        pos = -1;
        i = 1;
        longitud = this.longitud();
        if (this.cabecera != null) {
            Nodo aux = this.cabecera;
            while (pos == -1) {
                if (aux != null) {
                    if (aux.getElemento().equals(elem)) {
                        pos = i;
                    } else {
                        aux = aux.getEnlace();
                        i++;
                    }
                }
            }
        }

        return pos;
    }

    public int longitud() {
        int largo = 0;
        if (cabecera != null) {
            //si la lista no esta vacia
            Nodo aux = cabecera;
            while (aux != null) {
                largo++;
                aux = aux.getEnlace();
            }
        }
        return largo;
    }

    public boolean esVacia() {
        return (this.cabecera == null);
    }

    public void vaciar() {
        this.cabecera = null;
    }

    public Lista clone() {
        Lista clon = new Lista();
        if (!esVacia()) {
            Nodo auxOriginal = this.cabecera;
            //creo el primer nodo y lo asigno como la cabecera
            Nodo auxClon = new Nodo(auxOriginal.getElemento(), null);
            clon.cabecera = auxClon;
            //avanzo el auxiliar de la pila original al nodo siguiente
            auxOriginal = auxOriginal.getEnlace();

            Nodo nuevoNodo;
            while (auxOriginal != null) {
                nuevoNodo = new Nodo(auxOriginal.getElemento(), null);
                auxClon.setEnlace(nuevoNodo);

                //avanzo los auxiliares
                auxOriginal = auxOriginal.getEnlace();
                auxClon = auxClon.getEnlace();
            }
        }

        return clon;
    }

    public String toString() {
        String texto = "";
        if (esVacia()) {
            //caso de lista vacia
            texto = "Lista vacia";
        } else {
            Nodo aux = this.cabecera;
            while (aux != null) {
                texto = texto + " [" + aux.getElemento().toString() + "]";
                aux = aux.getEnlace();
            }
        }
        return texto;
    }
    //---------------------Simulacro Parcial---------------------//

    public Lista obtenerMultiplos(int num) {
        /*Ejercicio tipo parcial : recibe un número y devuelve una lista nueva
        que contiene todos los elementos de las posiciones múltiplos de num, en el mismo orden encontrado,
        haciendo un único recorrido de las estructuras original y copia; y sin usar otras operaciones del TDA.*/

        //Declaracion de variables
        Lista copia = new Lista();
        Nodo punteroOriginal = this.cabecera;
        Nodo punteroCopia = null;
        int cont = 1;
        while (punteroOriginal != null) {
            //si la posicion es multiplo de num
            if (cont % num == 0) {
                //caso particular: lista copia vacia
                if (copia.cabecera == null) {
                    copia.cabecera = new Nodo(punteroOriginal.getElemento(), null);
                    punteroCopia = copia.cabecera;
                } else {
                    //caso general
                    Nodo nuevo = new Nodo(punteroOriginal.getElemento(), null);
                    punteroCopia.setEnlace(nuevo);
                    punteroCopia = punteroCopia.getEnlace();
                }
            }
            cont++;
            punteroOriginal = punteroOriginal.getEnlace();

        }
        return copia;
    }

    public void eliminarApariciones(Object elem) {
        /**
         * Ejercicio tipo parcial:Agregar al TDA Lista la operación
         * eliminarApariciones(TipoElemento x) que elimine todas las apariciones
         * de elementos iguales a x, haciendo un único recorrido de la
         * estructura y sin usar otras operaciones del TDA.*
         */
        if (this.cabecera != null) {
            Nodo aux = this.cabecera;
            if (aux.getElemento().equals(elem)) {
                this.cabecera = cabecera.getEnlace();
                aux = this.cabecera;
            }
            while (aux.getEnlace() != null) {
                if (aux.getEnlace().getElemento().equals(elem)) {
                    aux.setEnlace(aux.getEnlace().getEnlace());
                }
                aux = aux.getEnlace();
            }
        }
    }

    //---------------------Ejercicios de Parcial---------------------//
    public void insertarPosterior(Object valor1, Object valor2) {
        /*Agregar al TDA Lista la operación insertarPosterior(valor1, valor2) que busca 
         todas las apariciones de valor1 en la lista, e inserta un nodo con valor2 en la posición posterior. 
         Si valor1 está en posición 1, debe insertar a valor2 antes y después de valor1.*/
        Nodo aux = this.cabecera;
        if (this.cabecera != null) {
            if (this.cabecera.getElemento().equals(valor1)) {
                Nodo nuevo = new Nodo(valor2, aux);
                this.cabecera = nuevo;
            }
            while (aux != null) {
                if (aux.getElemento().equals(valor1)) {
                    aux.setEnlace(new Nodo(valor2, aux.getEnlace()));
                }
                aux = aux.getEnlace();
            }
        }
    }

}
