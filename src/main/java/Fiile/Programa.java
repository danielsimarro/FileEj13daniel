/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fiile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author daniel
 */
public class Programa {

    public static void main(String[] args) throws IOException  {

        //Creacion de lista con 50 valores aleatorios
        ArrayList<App> listaApp = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            listaApp.add(App.crearAppAleatoria());
        }

        listaApp.forEach(System.out::println);

        //Creación de directorios
        creacionDirectorio("appstsv");
        creacionDirectorio("appsxml");
        creacionDirectorio("appsjson");
        creacionDirectorio("copias");
        creacionDirectorio("aplicaciones");

        //Creacion de objetos para acceder a los metodos y crear los ficheros
        ServicioFicheroTSV generarFicheroTSV = new ServicioFicheroTSV();
        generarFicheroTSV.generaTsv(listaApp, "./appstsv/aplicaciones.tsv");

        ServicioFicheroJSON generarFicheroJSON = new ServicioFicheroJSON();
        try {
            generarFicheroJSON.generaJson(listaApp, "./appsjson/aplicaciones.json");
        } catch (IOException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Para crear un fichero xml primero crearemos un catalogo y le pasaremos una lista de app y el nombre del catalogo
        CatalogoAplicaciones catalogo = new CatalogoAplicaciones();
        catalogo.setListaApp(listaApp);
        catalogo.setDescripcion("Mi catalogo");

        ServicioFicheroXML generarFicheroXML = new ServicioFicheroXML();
        try {
            generarFicheroXML.generaXml(catalogo, "./appsxml/aplicaciones.xml");
        } catch (JAXBException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Recorremos el bucle y vamos extrayendo los objetos de las posiciones y generando ficheros
        for (int i = 0; i < listaApp.size(); i++) {

            creacionFicherosJson(listaApp.get(i));

        }
    }

    //Metodo que crea directorios pasandole el nombre de con el que queremos llamarlo
    private static void creacionDirectorio(String nombre) {

        Path directory = Paths.get(nombre);
        try {
            Files.createDirectory(directory);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio.");
            System.out.println(e.toString());
        }
    }

    //Metodo que crea un archivo por cada objeto de la lista app que se le pase, donde el nombre del archivo sera el nombre del objeto
    private static void creacionFicherosJson(App aplicacion) throws IOException {

        ObjectMapper mapeador = new ObjectMapper();

        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Escribe en un fichero JSON el objeto que le pasamos
        mapeador.writeValue(new File("./aplicaciones/" + aplicacion.getNombre() + ".json"), aplicacion);
    }
}
