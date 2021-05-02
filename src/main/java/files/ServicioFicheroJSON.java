/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NitroPc
 */
public class ServicioFicheroJSON {

    //Metodo que genera un archivo JSON pasandole un ArrayList de app y la ruta del fichero a crear, con su nombre
    public void generaJson(ArrayList<App> listaApp, String nombre) throws IOException {

        ObjectMapper mapeador = new ObjectMapper();

        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Escribe en un fichero JSON la lista que le pasamos
        mapeador.writeValue(new File(nombre), listaApp);

    }

    //Metodo que crea un archivo por cada objeto de la lista app que se le pase, donde el nombre del archivo sera el nombre del objeto
    public void generaJson(App aplicacion, String nombreFichero) throws IOException {

        ObjectMapper mapeador = new ObjectMapper();

        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Escribe en un fichero JSON el objeto que le pasamos
        mapeador.writeValue(new File("./aplicaciones/" + nombreFichero + ".json"), aplicacion);
    }
    
    //Metodo que permita generar con todas las aplicaciones un arrayList, al que le pasaremos
    //la ruta donde se encuentra el fichero a leer
    public ArrayList<App> leerJsonArray(String rutaFichero){
        
        ObjectMapper mapeador = new ObjectMapper();
        ArrayList<App> catalogosApp = new ArrayList<>();
        try {
            catalogosApp = mapeador.readValue(new File(rutaFichero),
                    mapeador.getTypeFactory().constructCollectionType(ArrayList.class, App.class));
        } catch (IOException ex) {
            Logger.getLogger(ServicioFicheroJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return catalogosApp;
    }

    //Metoddo que permite la lectura de un archivo json que le pasemos por parametro y nos genere una app
    public App leerJsonFicheros(String rutaFichero) throws IOException {

        App aplicacion = new App();

        try {
            ObjectMapper mapeador = new ObjectMapper();
            mapeador.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            aplicacion = mapeador.readValue(new File(rutaFichero), App.class);
        } catch (IOException ex) {
            System.out.println("Archivo no encontrado");
        }

        return aplicacion;
    }
    
    
}
