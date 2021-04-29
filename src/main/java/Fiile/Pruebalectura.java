/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fiile;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author daniel
 */
public class Pruebalectura {
    
    public static void main(String[] args) {
        LecturaXml leerXml = new LecturaXml();
        
        try {
            leerXml.generaJson("./appsxml/aplicaciones.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(Pruebalectura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
