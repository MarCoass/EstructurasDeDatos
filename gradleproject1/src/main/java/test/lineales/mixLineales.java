package test.lineales;

import lineales.dinamicas.*;
import jerarquicas.*;

public class mixLineales {
    //-----------------Simulacro parcial-----------------//

    public Cola generar(Cola cola) {

        Pila pila = new Pila();
        Cola copia = cola.clone();
        Cola generada = new Cola();

        if (!copia.esVacia()) {
            char aux = (char) copia.obtenerFrente();
            while (!copia.esVacia()) {
                if (aux == '$') {
                    while (!pila.esVacia()) {
                        generada.poner(pila.obtenerTope());
                        pila.desapilar();
                    }
                } else {
                    pila.apilar(aux);
                    generada.poner(aux);
                }
                copia.sacar();
                aux = (char) copia.obtenerFrente();
            }
        }
        return generada;
    }

    public boolean verificarBalanceo(Cola q) {
        boolean exito = true;
        Lista listaAux = new Lista();
        Cola copia = q.clone();
        char aux = (char) copia.obtenerFrente();
        while (!copia.esVacia()) {
            if (esSigno(aux)) {
                listaAux.insertar(aux, listaAux.longitud() + 1);
            }
            copia.sacar();
            aux = (char) copia.obtenerFrente();

        }
        if (listaAux.longitud() % 2 == 0) {
            int i = 1;
            int largo = listaAux.longitud();
            int mitad = largo / 2;

            while (exito && i <= mitad) {
                if (!esElPar((char) listaAux.recuperar(i), (char) listaAux.recuperar(largo - i))) {
                    exito = false;
                }
                i++;
            }
        } else {
            exito = false;
        }
        return exito;
    }

    private boolean esSigno(char x) {
        boolean exito = false;
        char[] signos = {'(', ')', '[', ']', '{', '}'};
        int i = 0;
        while (i < signos.length && !exito) {
            if (signos[i] == x) {
                exito = true;
            }
            i++;
        }
        return exito;
    }

    private boolean esElPar(char a, char b) {
        boolean exito = ((a == '(' && b == ')')
                || (a == '[' && b == ']')
                || (a == '{' && b == '}'));

        return exito;
    }
    //---------------------Ejercicios de Parcial---------------------//

    public static Lista crearLista(Cola c1) {
        /*En una clase TestLineales, que utiliza los TDA Lista, Pila y Cola vistos en la materia, 
        implementar el método crearLista(Cola c1) que recibe por parámetro una estructura cola cola1 
        que tiene el siguiente formato: c1$c2$c3$….$cn, donde cada ci es una sucesión de elementos de la cola, 
        se debe generar como salida una lista con todas las secuencias impares invertidas y las pares igual que en la original. */
        Lista lista = new Lista();
        Cola copia = c1.clone();
        if (!copia.esVacia()) {
            int cont = 0;
            int pos = 1;
            Lista listaAux = new Lista();
            while (copia.obtenerFrente() != null) {

                while (copia.obtenerFrente() != null && (char) copia.obtenerFrente() != '$') {
                    listaAux.insertar(copia.obtenerFrente(), listaAux.longitud() + 1);
                    copia.sacar();
                    cont++;
                }

                if ((cont % 2) == 0) {
                    for (int i = 1; i <= listaAux.longitud(); i++) {
                        lista.insertar(listaAux.recuperar(i), pos);
                        pos++;
                    }
                } else {
                    Pila pilaAux = new Pila();
                    for (int i = 1; i <= listaAux.longitud(); i++) {
                        pilaAux.apilar(listaAux.recuperar(i));
                    }
                    while (!pilaAux.esVacia()) {
                        lista.insertar(pilaAux.obtenerTope(), pos);
                        pilaAux.desapilar();
                        pos++;
                    }
                }

                if (copia.obtenerFrente() != null && (char) copia.obtenerFrente() == '$') {
                    lista.insertar('$', pos);
                    copia.sacar();
                    pos++;
                }
                listaAux.vaciar();
                cont = 0;
            }
        }
        return lista;
    }

    public static void main(String[] arg) {

        ArbolBin arbol = new ArbolBin();
        arbol.insertar(10, 0, 'D');
        arbol.insertar(5, 10, 'I');
        arbol.insertar(2, 5, 'I');
        arbol.insertar(23, 10, 'D');
        arbol.insertar(15, 23, 'I');
        arbol.insertar(24, 15, 'D');
        System.out.println("arbol: " + arbol.toString());
        arbol.modificarSubArboles(23, 7, 78);
        System.out.println("arbol: " + arbol.toString());
    }

}
