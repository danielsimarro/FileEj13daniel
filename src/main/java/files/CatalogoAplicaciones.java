/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NitroPc
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogoAplicaciones {
    
    // XmLElementWrapper define un contenedor para la lista 
    // de app
    @XmlElementWrapper(name = "catalogo")

    // XmlElement establece el nombre de los elementos
    // Cada elemento de la lista llevará esta etiqueta en el fichero xml
    @XmlElement(name = "app")
    private ArrayList<App> listaApp;
    
    private String descripcion;

    public ArrayList<App> getListaApp() {
        return listaApp;
    }

    public void setListaApp(ArrayList<App> listaApp) {
        this.listaApp = listaApp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
