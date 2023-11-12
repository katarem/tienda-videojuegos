package aed.practica2.backend.db;

import aed.practica2.backend.models.Cuenta;
import aed.practica2.backend.models.Producto;

import java.util.ArrayList;
import java.util.List;


/**
 * Servicio para obtener los datos de la Base de datos
 * */
public class DBService implements IDBService {
    private final ArrayList<Producto> productos;
    private final ArrayList<Cuenta> usuarios;
    private final DBManager database;

    public DBService(){
        database = new DBManager();
        productos = database.readProductos();
        usuarios = database.readCuentas();
        System.out.println("-----SERVICIO LISTO PARA USO-----");
    }
    @Override
    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public List<Cuenta> getCuentas() {
        return this.usuarios;
    }

    @Override
    public void addProducto(String productID) {
        //TODO plantear el añadir producto para agregarlo si no existe, si existe actualizarlo
        Producto p = new Producto();
        p.setId(productID);
//        Comprobamos si el producto existe, lo actualiza, si no simplemente lo agrega. Más sencillo para un CRUD realista
//        var product = this.productos.stream().filter(item -> item.getId().equals(productID)).findFirst();
//        product.ifPresent(producto -> {
//
//        });

        this.productos.add(p);
    }

    @Override
    public void addCuenta(String userID) {
        //TODO plantear el añadir cuenta para agregarla si no existe, si existe actualizarla
        Cuenta c = new Cuenta();
        c.setUsername(userID);
        this.usuarios.add(c);
    }

    @Override
    public void removeCuenta(String userID) {
        var cuenta = this.usuarios.stream().filter(usuario -> usuario.getUsername().equals(userID)).findFirst();
        cuenta.ifPresent(usuarios::remove);
    }

    @Override
    public void removeProducto(String productID) {
        var producto = this.productos.stream().filter(item -> item.getId().equals(productID)).findFirst();
        producto.ifPresent(productos::remove);
    }

    @Override
    public void close() {
        database.writeCuentas(usuarios);
        database.writeProductos(productos);
        System.out.println("DATOS PERSISTIDOS EN LA BASE DE DATOS");
    }


}
