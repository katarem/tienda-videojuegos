package aed.practica2.backend.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Modelo de producto de la tienda
 * */
public class Producto implements Serializable {

    private String nombre;
    private String id;
    private double precio;

    public Producto(String nombre, String id, double precio) {
        this.nombre = nombre;
        this.id = id;
        this.precio = precio;
    }

    public Producto(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Double.compare(precio, producto.precio) == 0 && Objects.equals(nombre, producto.nombre) && Objects.equals(id, producto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, id, precio);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", precio=" + precio +
                '}';
    }
}
