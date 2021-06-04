package conjuntistas;

public class ArbolHeap {

    private final int TAMANIO = 10;
    private Comparable[] heap;
    private int ultimo;

    public ArbolHeap() {
        heap = new Comparable[TAMANIO];
        ultimo = 0;
    }

    //-----------------------insertar()-----------------------//
    public boolean insertar(Comparable elem) {
        boolean exito = false;
        if (ultimo + 1 < TAMANIO) {
            this.heap[ultimo + 1] = elem;
            ultimo++;
            hacerSubir(ultimo);
            exito = true;
        }
        return exito;
    }

    //-----------------------eliminarCima()-----------------------//
    public boolean eliminarCima() {
        boolean exito;

        if (this.ultimo == 0) {
            //Caso particular: estructura vacia
            exito = false;
        } else {
            //saca la raiz, pone la ultima hoja en su lugar y a ultimo le resta uno
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;

            //baja la raiz hasta que llegue a su posicion para continuar siendo heap minimo
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    //-----------------------hacerBajar()-----------------------//
    private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                // temp tiene al menos un hijo izquierdo y lo considera menor

                if (posH < this.ultimo) {
                    // hijoMenor tiene hermano derecho

                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
                        // el hijo derecho es el menor de los dos
                        posH++;
                    }
                }

                // compara al hijo menor con el padre
                if (this.heap[posH].compareTo(temp) < 0) {
                    // el hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    // el padre es menor que sus hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                // el temp es hoja, esta bien ubicado
                salir = true;
            }
        }
    }
    //-----------------------hacerSubir()-----------------------//

    private void hacerSubir(int posElem) {
        boolean salir = false;
        Comparable temp = this.heap[posElem];
        int posPadre;
        while (!salir) {
            if (posElem > 1) {
                if (this.heap[posElem].compareTo(this.heap[posElem - 1]) < 0) {
                    //el elemento anterior es mayor que el elemento en posElem
                    this.heap[posElem] = this.heap[posElem - 1];
                    this.heap[posElem - 1] = temp;
                    posElem--;
                } else {
                    //elemento bien ubicado
                    salir = true;
                }
            } else {
                //elem en raiz
                salir = true;
            }
        }
    }

    //-----------------------recuperarCima()-----------------------//
    public Comparable recuperarCima() {
        return this.heap[1];
    }

    //-----------------------toString()-----------------------//
    public String toString() {
        String texto = "";
        if (ultimo != 0) {
            for (int i = 1; i <= ultimo; i++) {

                texto = texto + "[" + this.heap[i].toString() + "] ";
                if (2 * i <= ultimo && this.heap[2 * i] != null) {
                    texto = texto + "HI: " + this.heap[2 * i];
                } else {
                    texto = texto + "HI: - ";
                }
                if ((2 * i) + 1 <= ultimo && this.heap[(2 * i) + 1] != null) {
                    texto = texto + ". HD: " + this.heap[(2 * i) + 1] + "\n";
                } else {
                    texto = texto + ". HD: - \n";
                }
            }
        }
        return texto;
    }

    //-----------------------clone()-----------------------//
    public ArbolHeap clone() {
        ArbolHeap clon = new ArbolHeap();
        clon.ultimo = this.ultimo;
        for (int i = 0; i <= this.ultimo; i++) {
            clon.heap[i] = this.heap[i];
        }

        return clon;
    }

     
}
