package co.edu.usa.reto5.modelo;

public class Entidad {

    int imagen;
    //byte[] imagen;
    String nombre;
    String descripcion;

    public Entidad(int imagen, String nombre, String descripcion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
