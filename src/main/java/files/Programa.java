/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

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

    public static void main(String[] args) throws IOException {

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

        //Creacion de los objetos de cada una de las clases para acceder a sus metodos
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
        //con el metodo de la clase de servicioFicheroJson
        //Reutilizaremos el objeto creado anteriormente para acceder a los metodos
        for (int i = 0; i < listaApp.size(); i++) {

            generarFicheroJSON.generaJson(listaApp.get(i), listaApp.get(i).getNombre());

        }
    }

    //Metodo que crea directorios pasandole el nombre que queramos asignarle
    public static void creacionDirectorio(String nombre) {

        Path directory = Paths.get(nombre);
        try {
            Files.createDirectory(directory);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio.");
            System.out.println(e.toString());
        }
    }

}
