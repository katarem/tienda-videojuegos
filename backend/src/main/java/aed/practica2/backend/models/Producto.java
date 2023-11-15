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

    private int cantidad;

    public Producto(){this.id = 0; this.cantidad = 1;}

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && Double.compare(precio, producto.precio) == 0 && cantidad == producto.cantidad && Objects.equals(nombre, producto.nombre) && Objects.equals(generos, producto.generos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, id, precio, generos, cantidad);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", precio=" + precio +
                ", generos=" + generos +
                ", cantidad=" + cantidad +
                '}';
    }
}
