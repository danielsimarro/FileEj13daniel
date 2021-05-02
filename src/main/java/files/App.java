/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author daniel
 */
// Anotación @XmlRootElement, nombre de la etiqueta XML raíz.
@XmlRootElement(name = "app")
// Anotación @XmlAccesorType define el elemento que usará JAXB durante el 
// procesamiento de datos (en este caso por atributo)
@XmlAccessorType(XmlAccessType.FIELD)

public class App {

    //Atributos de la clase
    private int codigo;
    private String nombre;
    private String descripcion;
    private Double espacioKb;
    //Formatea el tiempo al generar el xml
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    //Formatea la fecha en el formato indicado para los ficheros json
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    //Esto se utiliza para imprimir correctamente el json
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)

    private LocalDate fechaCreacion;

    //Contador para asignar a los codigos
    private static int contador;
    //Atributo Random que generara números aleatorios
    private static final Random aleatorio = new Random();

    //Constructor por defecto
    public App() {
        contador++;
    }

    //Constructor parametrizado
    public App(int codigo, String nombre, String descripcion, double espacioKb, LocalDate fechaCreacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.espacioKb = espacioKb;
        this.fechaCreacion = fechaCreacion;
        contador++;
    }

    //Metodo para crearAplicaciones aleatorias, estas devuelven app
    public static App crearAppAleatoria() {

        App aplicacion = new App();
        aplicacion.setCodigo(contador);
        aplicacion.setNombre(nombreAleatorio(aplicacion.getCodigo()));
        aplicacion.setDescripcion(descripcionAleatoria());
        aplicacion.setEspacioKb(kbAleatoria());
        aplicacion.setFechaCreacion(generarFechaAleatoria(aplicacion));

        return aplicacion;
    }

    //Metodo privado que genera la fecha aleatoriamente, con un algoristo que le suma
    //a la fecha actual el codigo + espacioKb
    private static LocalDate generarFechaAleatoria(App aplicacion) {

        double kbDecimal = aplicacion.getEspacioKb();

        int kbEntero = (int) kbDecimal;

        Long diasAleatorios = (long) (aplicacion.getCodigo() + kbEntero);

        LocalDate fechaAleatoria = LocalDate.now().plusDays(diasAleatorios);

        fechaAleatoria.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return fechaAleatoria;
    }

    //Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getEspacioKb() {
        return espacioKb;
    }

    public void setEspacioKb(Double espacioKb) {
        this.espacioKb = espacioKb;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        App.contador = contador;
    }

    //HashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.codigo;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.descripcion);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.espacioKb) ^ (Double.doubleToLongBits(this.espacioKb) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.fechaCreacion);
        return hash;
    }

    //equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final App other = (App) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (Double.doubleToLongBits(this.espacioKb) != Double.doubleToLongBits(other.espacioKb)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        return true;
    }

    //Metodo to String para imprimir los atributos del objeto
    @Override
    public String toString() {
        return codigo + "\t" + nombre + "\t" + descripcion + "\t" + espacioKb + "\t" + fechaCreacion;
    }

    //Metodo para generar el tamaño aleatorio en kb entre 100 y 1024
    private static double kbAleatoria() {

        //Utilizamos el metodo doubles acompañado de un .sum para que decuelva un double 
        //El primer caracter indica los valores que queremos coger y los otros do el rango
        return aleatorio.doubles(1, 100.0, 1024.0).sum();
    }

    //Metodo para crear la descripción, donde seleccionamos una 
    //descripcion de un cadena trozeada con un split de forma aleatoria
    private static String descripcionAleatoria() {
        String descripcion = "Finanzas,Juegos,Educación,Entretenimiento,Conocimiento,Deporte,Parejas,Logica,Conducción,Lectura";
        String[] lista = descripcion.split(",");
        int random = aleatorio.nextInt(9 - 0 + 1) + 0;

        return lista[random];
    }

    //Metodo para crear el nombre aleatorio ("app" + cod + letra), la letra se genera aleatoria
    private static String nombreAleatorio(int codigo) {
        String nombre = "app" + codigo;

        char letraMinus = (char) (aleatorio.nextInt(26) + 'a');

        String letra = Character.toString(letraMinus);

        return nombre + letra;
    }

}
