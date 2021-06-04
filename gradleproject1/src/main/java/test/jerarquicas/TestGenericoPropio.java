package test.jerarquicas;

/**
 * insertar() testeado 
 * pertenece() testeado 
 * ancestros() testeado 
 * altura() testeado 
 * nivel() testeado 
 * padre() testeado 
 * listarPreOrden() testeado
 * listarInOrden() testeado 
 * listarPosOrden() testeado l
 * istarPorNiveles() testeado 
 * toString() testeado 
 * clone() testeado
 * grado() testeado
 * gradoSubArbol() testeado
 */
import jerarquicas.ArbolGen;

public class TestGenericoPropio {

    public static void main(String args[]) {
        ArbolGen arbolA = new ArbolGen();
        System.out.println("Test insertar: ");
        System.out.println("Inserto A: " + arbolA.insertar('A', 'B'));
        System.out.println("Inserto B: " + arbolA.insertar('B', 'A'));
        System.out.println("Inserto C: " + arbolA.insertar('C', 'A'));
        System.out.println("Inserto D: " + arbolA.insertar('D', 'A'));
        System.out.println("Inserto E: " + arbolA.insertar('E', 'B'));
        System.out.println("Inserto F: " + arbolA.insertar('F', 'B'));
        System.out.println("Inserto G: " + arbolA.insertar('G', 'B'));
        System.out.println("Inserto H: " + arbolA.insertar('H', 'D'));
        System.out.println(arbolA.toString());
        System.out.println("Intento insertar Z como hijo de un elemento que no existe: " + arbolA.insertar('Z', 'V'));

        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Test pertenece: ");
        System.out.println("F pertenece: " + arbolA.pertenece('F'));
        System.out.println("L pertenece: " + arbolA.pertenece('L'));

        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Test ancestros: ");
        System.out.println("Ancestros de A: " + arbolA.ancestros('A'));
        System.out.println("Ancestros de B: " + arbolA.ancestros('B'));
        System.out.println("Ancestros de C: " + arbolA.ancestros('C'));
        System.out.println("Ancestros de D: " + arbolA.ancestros('D'));
        System.out.println("Ancestros de E: " + arbolA.ancestros('E'));
        System.out.println("Ancestros de F: " + arbolA.ancestros('F'));
        System.out.println("Ancestros de H: " + arbolA.ancestros('H'));
        System.out.println("Ancestros de K: " + arbolA.ancestros('K'));

        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Test altura: ");
        System.out.println("Altura: " + arbolA.altura());

        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Test nivel: ");
        System.out.println("Nivel de A: " + arbolA.nivel('A'));
        System.out.println("Nivel de B: " + arbolA.nivel('B'));
        System.out.println("Nivel de C: " + arbolA.nivel('C'));
        System.out.println("Nivel de D: " + arbolA.nivel('D'));
        System.out.println("Nivel de F: " + arbolA.nivel('F'));
        System.out.println("Nivel de G: " + arbolA.nivel('G'));
        System.out.println("Nivel de H: " + arbolA.nivel('H'));
        System.out.println("Nivel de M: " + arbolA.nivel('M'));

        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Test padre: ");
        System.out.println("Padre de A: " + arbolA.padre('A'));
        System.out.println("Padre de B: " + arbolA.padre('B'));
        System.out.println("Padre de C: " + arbolA.padre('C'));
        System.out.println("Padre de D: " + arbolA.padre('D'));
        System.out.println("Padre de E: " + arbolA.padre('E'));
        System.out.println("Padre de F: " + arbolA.padre('F'));
        System.out.println("Padre de G: " + arbolA.padre('G'));
        System.out.println("Padre de H: " + arbolA.padre('H'));
        System.out.println("Padre de K: " + arbolA.padre('K'));

        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Test listados: ");

        System.out.println("PreOrden: " + arbolA.listarPreorden());
        System.out.println("InOrden: " + arbolA.listarInorden());
        System.out.println("PosOrden: " + arbolA.listarPosorden());
        System.out.println("Por niveles: " + arbolA.listarPorNiveles());

        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("Test clon");
        ArbolGen clon = arbolA.clone();
        System.out.println("Original: " + arbolA.listarPorNiveles());
        System.out.println("Clon: " + clon.listarPorNiveles());

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Test vaciar");
        System.out.println("Vacio clon:");
        clon.vaciar();
        System.out.println("Original: " + arbolA.listarPorNiveles());
        System.out.println("Clon: " + clon.listarPorNiveles());

         System.out.println("-----------------------------------------------------------------------------------------");
         System.out.println("Test grado:");
         System.out.println("Grado arbolA: "+arbolA.grado());
        System.out.println("Grado clon: "+clon.grado());
        
         System.out.println("-----------------------------------------------------------------------------------------");
         System.out.println("Test gradoSubarbol:");
         System.out.println("Grado de A: "+arbolA.gradoSubarbol('A'));
         System.out.println("Grado de C: "+arbolA.gradoSubarbol('C'));
         System.out.println("Grado de D: "+arbolA.gradoSubarbol('D'));
    }
}
