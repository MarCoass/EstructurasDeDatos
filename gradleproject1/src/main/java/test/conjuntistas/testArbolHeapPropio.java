package test.conjuntistas;

import conjuntistas.ArbolHeap;

public class testArbolHeapPropio {

    public static void main(String args[]) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Test insertar: ");
        ArbolHeap arbol = new ArbolHeap();
        System.out.println("Inserto 1 -> Exito: " + arbol.insertar(1));
        System.out.println(arbol.toString());
        System.out.println("Inserto 4 -> Exito: " + arbol.insertar(4));
        System.out.println(arbol.toString());
        System.out.println("Inserto 2 -> Exito: " + arbol.insertar(2));
        System.out.println(arbol.toString());
        System.out.println("Inserto 6 -> Exito: " + arbol.insertar(6));
        System.out.println(arbol.toString());
        System.out.println("Inserto 3 -> Exito: " + arbol.insertar(3));
        System.out.println(arbol.toString());
        System.out.println("Inserto 5 -> Exito: " + arbol.insertar(5));
        System.out.println(arbol.toString());
        System.out.println("Inserto 9 -> Exito: " + arbol.insertar(9));
        System.out.println(arbol.toString());
        System.out.println("Inserto 0 -> Exito: " + arbol.insertar(0));
        System.out.println(arbol.toString());
        System.out.println("Inserto 8 -> Exito: " + arbol.insertar(8));
        System.out.println(arbol.toString());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Test eliminar");
        System.out.println("Cima: " + arbol.recuperarCima());
        System.out.println("Exito: " + arbol.eliminarCima());
        System.out.println(arbol.toString());
        
        System.out.println("Cima: " + arbol.recuperarCima());
        System.out.println("Exito: " + arbol.eliminarCima());
        System.out.println(arbol.toString());
    }
}
