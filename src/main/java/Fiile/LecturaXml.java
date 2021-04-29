/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fiile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author daniel
 */
public class LecturaXml {

    //Metodo que permite la léctura de los ficheros xml que se le pasen por parametros
    public void generaJson(String nombre) throws JAXBException, FileNotFoundException {

        // Crea el contexto JAXB 
        JAXBContext contexto = JAXBContext.newInstance(CatalogoAplicaciones.class);
        // Crea el objeto Unmarshaller
        Unmarshaller um = contexto.createUnmarshaller();

        // Llama al método de unmarshalling
        CatalogoAplicaciones catalogo = (CatalogoAplicaciones) um.unmarshal(new File(nombre));

        ArrayList<App> listaMuebles = catalogo.getListaApp();

        listaMuebles.forEach(System.out::println);
    }
}

