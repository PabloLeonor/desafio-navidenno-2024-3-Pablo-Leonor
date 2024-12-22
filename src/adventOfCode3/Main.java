/**
 * 
 * Advent of Code 2024 day 3 By Pablo Leonor by Pablo Leonor is marked with CC0 1.0 Universal 
 * 
 * **/
package adventOfCode3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner tomaDatos = new Scanner(System.in);
        String ubicacionEntrada;
        ArrayList<String> lista = new ArrayList<>();

        // Preguntamos al usuario por la ubicación del input
        //Lets ask the user for input location
        System.out.println("Saludos usuario, para continuar, por favor localice el fichero del input (ubicacion+input.txt): ");
        ubicacionEntrada = tomaDatos.nextLine();

        if (ubicacionEntrada.endsWith(".txt")) {
            try {
                FileReader fr = new FileReader(ubicacionEntrada);
                BufferedReader bf = new BufferedReader(fr);
                StringBuilder contenido = new StringBuilder();
               
                String linea;

                
                while ((linea = bf.readLine()) != null) {
                    contenido.append(linea).append(System.lineSeparator());
                }
                
                System.out.println(contenido);
                String input= contenido.toString();
                
                
                //voy a usar una expresión regular , para buscarle lo que quiero, que es mul(x,y)
                Pattern patron = Pattern.compile("mul\\(\\d+,\\d+\\)");
                Matcher matcher = patron.matcher(input);
                
                while(matcher.find()) {
                	lista.add(matcher.group()); //con esto, tenemos almacenado en lista lo que son todos los mul(x,y)
                }
                
                for (String texto :lista) {
                	System.out.println(texto);
                }
                
                //ahora la idea es separar cada uno de estos mul en dos arrays uno X y otro Y 
                String texto;
                String[] textoSplit;
            	int resultado=0;
            	

                for (int i = 0; i < lista.size(); i++) {
                    //primero separamos los valores x e y
                	texto = lista.get(i).replace("mul(", "");
                	texto = texto.replace(")", "");
                	textoSplit = texto.split(",");
                	System.out.println(textoSplit[0]+" y "+textoSplit[1]);
                	resultado +=( Integer.valueOf(textoSplit[0]) * Integer.valueOf(textoSplit[1]) );
                	
                }
                System.out.println("La suma total es: "+resultado);
         
            } catch (IOException ioe) {
                System.err.println("Error al leer el archivo. Verifica la ubicación.");
                ioe.printStackTrace();
            }
        } else {
            System.out.println("Ubicación o tipo de archivo no válido");
        }

        tomaDatos.close();
    }
}
