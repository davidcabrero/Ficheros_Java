/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajo.ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author David Cabrero
 */
public class TrabajoAD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opción del usuario

        while (!salir) { //Siempre que no se salga (Opción 5)

            System.out.println("1. Restaurantes por código postal"); //Menú
            System.out.println("2. Añadir datos");
            System.out.println("3. Copiar fichero sin los datos indicados por el usuario");
            System.out.println("4. Borrar el fichero introducido por el usuario");
            System.out.println("5. Salir");

            System.out.println("Escribe el número de una de las opciones");
            opcion = sn.nextInt(); //El usuario escribe la opción elegida

            switch (opcion) { //Opciones
                case 1:
                    opcion1();
                    break;
                case 2:
                    opcion2();
                    break;
                case 3:
                    opcion3();
                    break;
                case 4:
                    opcion4();
                case 5:
                    salir = true; //Se sale del programa
                    break;
                default:
                    System.out.println("");
            }

        }
    }

    private static void opcion1() throws FileNotFoundException, IOException {
        Scanner sn = new Scanner(System.in);
        String zip = new String(); //Guardar primer dígito de zipcode
        System.out.println("Escribe el primer dígito del código postal"); //El usuario escribe el primer dígito del codigo postal que elija
        zip = sn.next(); //respuesta
        //Configuramos lector de ficheros
        BufferedReader lector = new BufferedReader(new FileReader("Restaurants.csv"));
        //Leemos línea
        String linea = lector.readLine();
        //Mientras la linea sea distinto de null, es decir, hay líneas para leer
        while (linea != null) {
            //Separamos los datos en un array
            String[] datos = linea.split(",");
            //Preguntamos si el último elemento, el zipcode, comienza por (zip)
            if (datos[datos.length - 1].startsWith(zip)) {
                System.out.println(Arrays.toString(datos)); //Mostramos restaurante
            }
            //Leemos siguiente línea, si no es null, se repetirá el bucle
            linea = lector.readLine();
        }
        //Lectura terminada, cerramos lector
        lector.close();
    }
    
    private static void opcion2() throws FileNotFoundException, IOException {
        //Preguntamos por cada dato y lo guardamos
        Scanner es = new Scanner(System.in);
        System.out.println("Escribe el restaurante"); 
        String restaurant = es.next();
        System.out.println("Escribe la dirección"); 
        String adress = es.next();
        Scanner sr = new Scanner(System.in);
        System.out.println("Escribe la ciudad"); 
        String city = sr.next();
        System.out.println("Escribe el estado"); 
        String state = sr.next();
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el codigo postal"); 
        String zipcode = sc.next();
        //Configuramos lector de ficheros
        BufferedReader lector = new BufferedReader(new FileReader("Restaurants.csv"));
        //Lee la linea del archivo
        String lineaArchivo = lector.readLine();
        String linea = new String(restaurant + "," + adress + "," + city + "," + state + "," + zipcode); //Linea a escribir

        FileWriter fw = new FileWriter("Restaurants.csv", true);              

                fw.write(linea + "\n"); //se escribe
                linea = lector.readLine();


        //Lectura terminada, cerramos lector
        fw.close();
        lector.close();
    }
    

    private static void opcion3() throws FileNotFoundException, IOException {
        Scanner sn = new Scanner(System.in);
        String state = new String(); //Guardar el estado que quiere el usuario
        System.out.println("Escribe las siglas del estado que no quiera escribir en el nuevo archivo"); //El usuario escribe el estado
        state = sn.next(); //respuesta
        //Configuramos lector de ficheros
        BufferedReader lector = new BufferedReader(new FileReader("Restaurants.csv"));
        //Leemos línea
        String linea = lector.readLine();
    
        //Mientras la linea sea distinto de null, es decir, hay líneas para leer  
        FileWriter fw = new FileWriter("Restaurants2.csv");              
        while (linea != null) {
            //Separamos los datos en un array
            String[] datos = linea.split(",");
            
            if (!(datos[3].equals(state))) { //siempre que no sea ese estado
                
                fw.write(linea + "\n"); //se escribe
                
                }
            //Leemos siguiente línea, si no es null, se repetirá el bucle
            linea = lector.readLine();
        }
        //Lectura terminada, cerramos lector
        fw.close();
        lector.close();
    }

    private static void opcion4() {
        File fichero = new File("restaurants2.csv"); //El fichero a borrar
        if (fichero.delete()) {
            System.out.println("El fichero ha sido borrado satisfactoriamente"); //Se ha eliminado
        } else {
            System.out.println("El fichero no puede ser borrado"); //Ha fallado
        }
    }
}
