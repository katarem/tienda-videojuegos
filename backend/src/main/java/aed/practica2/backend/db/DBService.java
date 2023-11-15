package aed.practica2.backend.db;

import aed.practica2.backend.models.Cuenta;
import aed.practica2.backend.models.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
    public void addProducto(Producto p) {
        if(!existeProducto(p)){
            if(p.getId() == 0) p.setId(productos.size());
            this.productos.add(p);
        }
    }

    private boolean existeProducto(Producto p){
        var productoAntiguo = productos.stream().filter(producto -> producto.getId()==p.getId()).findFirst();
        if(productoAntiguo.isPresent()){
            var producto = productoAntiguo.get();
            producto.setGeneros(p.getGeneros());
            producto.setNombre(p.getNombre());
            producto.setPrecio(p.getPrecio());
            return true;
        }
        return false;
    }

    private boolean existeCuenta(Cuenta c){
        var cuentaExistente = usuarios.stream().filter(cuenta -> cuenta.getUsername().equals(c.getUsername())).findFirst();
        if(cuentaExistente.isPresent()){
            var cuenta = cuentaExistente.get();
            cuenta.setPassword(c.getPassword());
            cuenta.setRol(c.getRol());
            cuenta.setEmail(c.getEmail());
            cuenta.setProfileImage(c.getProfileImage());
            return true;
        }
        return false;
    }



    @Override
    public void addCuenta(Cuenta c) {
        if(!existeCuenta(c)) this.usuarios.add(c);
    }

    @Override
    public void removeCuenta(String userID) {
        var cuenta = this.usuarios.stream().filter(usuario -> usuario.getUsername().equals(userID)).findFirst();
        cuenta.ifPresent(usuarios::remove);
    }

    @Override
    public void removeProducto(int productID) {
        var producto = this.productos.stream().filter(item -> item.getId() == productID).findFirst();
        producto.ifPresent(productos::remove);
    }

    @Override
    public void close() {
        database.writeCuentas(usuarios);
        database.writeProductos(productos);
        System.out.println("DATOS PERSISTIDOS EN LA BASE DE DATOS");
    }


}
