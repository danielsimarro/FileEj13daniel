/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 * @author daniel
 */
public class PruebaEj12 {

    //Prueba del metodo para leer xml creado en la clase SevicioFicheroXml
    public static void main(String[] args) {
        
        //Creamos un objeto ServicioFichero para acceder al metodo y realizamos un 
        //try cath al realizar la llamada al metodo
        ServicioFicheroXML leerXml = new ServicioFicheroXML();
        
        try {
            leerXml.leerXml("./appsxml/aplicaciones.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(PruebaEj12.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
