package aed.practica2.backend.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Modelo de producto de la tienda
 * */
public class Producto implements Serializable {

    private String nombre;
    private int id;
    private double precio;

    private List<Generos> generos;

    private String imagen;

    public Producto(){this.id = 0;}

    public Producto(String nombre, int id, double precio, List<Generos> generos, String imagen) {
        this.nombre = nombre;
        this.id = id;
        this.precio = precio;
        this.generos = generos;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public List<Generos> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Generos> generos) {
        this.generos = generos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && Double.compare(precio, producto.precio) == 0 && Objects.equals(nombre, producto.nombre) && Objects.equals(generos, producto.generos) && Objects.equals(imagen, producto.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, id, precio, generos, imagen);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", precio=" + precio +
                ", generos=" + generos +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
