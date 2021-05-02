/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author NitroPc
 */
public class ServicioFicheroTSV {

    //Metodo que genera el fichero Tsv pasandole un ArrayList de app y la ruta con el nombre del 
    //fichero a crear
    public void generaTsv(ArrayList<App> listaApp, String nombre) {

        try ( BufferedWriter flujo1 = new BufferedWriter(new FileWriter(nombre))) {

            for (int i = 0; i < listaApp.size(); i++) {

                flujo1.write(listaApp.get(i).toString());

                //Este metodo salta a la siguiente linea
                flujo1.newLine();

            }

            // Metodo fluh() guarda cambios en disco 
            flujo1.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    

}
