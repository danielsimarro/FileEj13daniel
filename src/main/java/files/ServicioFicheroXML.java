/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author NitroPc
 */
public class ServicioFicheroXML {

    //Metodo que genera un archivo XML pasandole un Catalogo que contiene una lista 
    //de app en su interior y el nombre/ruta del fichero a crear
    public void generaXml(CatalogoAplicaciones catalogo, String nombre) throws JAXBException{

        // Crea el contexto JAXB. Se encarga de definir los objetos 
        // que vamos a guardar. 
        JAXBContext contexto = JAXBContext.newInstance(CatalogoAplicaciones.class);

        // El contexto JAXB permite crear un objeto Marshaller, que sirve para
        // generar la estructura del fichero XML 
        Marshaller serializador = contexto.createMarshaller();
        
        // Especificamos que la propiedad del formato de salida
        // del serializador sea true, lo que implica que el formato se 
        // realiza con indentación y saltos de línea
        serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        // Llamando al método de serialización marshal (sobrecargado) se pueden
        // serializar objetos java en formato XML y volcarlos donde necesitemos:
        // consola, ficheros. El proceso consiste en que el contexto es el 
        // encargo de leer los objetos java, pasarlos al serializador y éste 
        // crear la salida de serialización
        
        // Volcado al fichero xml
        serializador.marshal(catalogo, new File(nombre));
        
    }
    
    //Metodo que permite la léctura de los ficheros xml que se le pasen por parametros
    public void leerXml(String rutaFichero) throws JAXBException, FileNotFoundException {

        // Crea el contexto JAXB 
        JAXBContext contexto = JAXBContext.newInstance(CatalogoAplicaciones.class);
        // Crea el objeto Unmarshaller
        Unmarshaller um = contexto.createUnmarshaller();

        // Llama al método de unmarshalling
        CatalogoAplicaciones catalogo = (CatalogoAplicaciones) um.unmarshal(new File(rutaFichero));

        ArrayList<App> listaMuebles = catalogo.getListaApp();

        listaMuebles.forEach(System.out::println);
    }
}
