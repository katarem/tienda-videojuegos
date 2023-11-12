package aed.practica2.backend.db;

import aed.practica2.backend.models.Cuenta;
import aed.practica2.backend.models.Producto;

import java.util.List;

public interface IDBService {
    List<Producto> getProductos();
    List<Cuenta> getCuentas();
    void addProducto(String productID);
    void addCuenta(String userID);

    void removeCuenta(String userID);

    void removeProducto(String productID);


    void close();


}
