package aed.practica2.backend.db;

import aed.practica2.backend.models.Cuenta;
import aed.practica2.backend.models.Producto;

import java.util.List;

public interface IDBService {
    List<Producto> getProductos();
    List<Cuenta> getCuentas();
    void addProducto(Producto p);
    void addCuenta(Cuenta c);

    void removeCuenta(String userID);

    void removeProducto(int productID);

    void close();

}
