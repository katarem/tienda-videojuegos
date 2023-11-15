package aed.practica2.backend.db;

import aed.practica2.backend.models.Cuenta;
import aed.practica2.backend.models.Producto;

import java.util.ArrayList;
import java.util.List;

public interface IDBManager {

    ArrayList<Producto> readProductos();
    ArrayList<Cuenta> readCuentas();
    void writeProductos(List<Producto> productos);
    void writeCuentas(List<Cuenta> cuentas);

}
