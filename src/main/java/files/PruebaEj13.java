/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author NitroPc
 */
public class PruebaEj13 {

    //Atributo que gestiona el contenido que hay en la carpeta aplicaciones con un ArrayLis
    private static ArrayList<String> nombresAplicacion;
    //Metodo scanner para guardar los datos que introduzca el usuario
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        //Con el metodo contenidoAplicaciones guardamos los nombres de todas las aplicaciones que hay dentro
        //de la carpeta aplicaciones
        nombresAplicacion = new ArrayList<>(contenidoAplicaciones());

        //Mostramos el listado de los ficheros que hay en aplicaciones
        System.out.println("Lista de apps dentro de aplicaciones: ");

        nombresAplicacion.forEach(System.out::println);

        boolean appCorrecta = false;
        String appLeer;

        do {

            System.out.println("Que apps de las mostrada por pantalla desea leer: ");

            appLeer = teclado.next();

            if (nombresAplicacion.contains(appLeer)) {
                appCorrecta = true;
            }

        } while (!appCorrecta);

        ServicioFicheroJSON leerJson = new ServicioFicheroJSON();

        App aplicacion = new App();

        try {
            aplicacion = leerJson.leerJsonFicheros("./aplicaciones/" + appLeer);
        } catch (IOException ex) {
            Logger.getLogger(PruebaEj13.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("--------------------------------------");
        System.out.println("Mostando datos de la app seleccionada: ");
        System.out.println(aplicacion.toString());

        borrarFichero( appLeer);
        
        System.out.println("--------------------------------------");
        System.out.println("Mostrando la lista con la app seleccionada eliminada de esta:");
        nombresAplicacion.forEach(System.out::println);

        Map<String, LocalDate> mapFiltrado = generarMap(leerJson);

        System.out.println("--------------------------------------");
        System.out.println("Mostrando el nombre y la fecha de creación de los \narchivos, cuyo tamaño es entre 200kb y 500Kb:");
        for (Map.Entry<String, LocalDate> map : mapFiltrado.entrySet()) {
            System.out.println(map.getKey() + "\t" + map.getValue());
        }

    }

    //Metodo que devuelve una lista con todos los nombres de los archivos que contiene la carpeta aplicaciones
    public static ArrayList<String> contenidoAplicaciones() {

        ArrayList<String> nombresAplicacion = new ArrayList<>();

        File aplicaciones = new File("./aplicaciones");
        if (aplicaciones.exists()) {
            File[] ficheros = aplicaciones.listFiles();
            for (File file2 : ficheros) {
                nombresAplicacion.add(file2.getName());
            }
        } else {
            System.out.println("El directorio a listar no existe");
        }

        return nombresAplicacion;

    }

    //Metodo que borra el fichero que se le pasa la ruta por parametro
    public static void borrarFichero(String ruta) {

        Path element = Paths.get("./aplicaciones/" + ruta);
        try {
            Files.delete(element);
        } catch (IOException e) {
            System.out.println("Problema borrando el archivo.");
            System.out.println(e.toString());
        }

        nombresAplicacion.remove(ruta);
    }

    //Metodo que genera una estructura map con el nombre de la app (clave) y la fecha de creación (valor)
    // de aquellos cuyo tamaño es de entre 200 y 500 Kb a partir de un arrayList que genera al leer las aplicaciones json
    public static Map<String, LocalDate> generarMap(ServicioFicheroJSON leerJson) {

        //Guardamos en un arrayList de app las lecturas de las aplicaciones
        ArrayList<App> listaAplicacion = leerJson.leerJsonArray("./appsjson/aplicaciones.json");

        //Creamos un Map donde filtramos el espacion Kb y lo pasamos a Map
        Map<String, LocalDate> mapFiltrado = listaAplicacion.stream()
                .filter(a -> a.getEspacioKb() >= 200 && a.getEspacioKb() <= 500)
                .collect(Collectors.toMap(b -> b.getNombre(), c -> c.getFechaCreacion()));

        return mapFiltrado;

    }
}
