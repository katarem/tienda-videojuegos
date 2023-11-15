package aed.practica2.backend.db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import aed.practica2.backend.models.Cuenta;
import aed.practica2.backend.models.Producto;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Administra la base de datos, que son archivos JSON en este caso
 * */
public class DBManager implements IDBManager{
    public DBManager(){ }

    public DBManager(String PRODUCTOSDBPATH, String CUENTASDBPATH){
        this();
        this.CUENTASDBPATH = CUENTASDBPATH;
        this.PRODUCTOSDBPATH = PRODUCTOSDBPATH;
    }

    private final ObjectMapper mapper = new ObjectMapper();
    private String PRODUCTOSDBPATH = "./backend/src/main/resources/productos.json";
    private String CUENTASDBPATH = "./backend/src/main/resources/cuentas.json";

    @Override
    public ArrayList<Producto> readProductos() {
        try(FileReader reader = new FileReader(PRODUCTOSDBPATH)){
            return mapper.readValue(reader, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Cuenta> readCuentas() {
        try(FileReader reader = new FileReader(CUENTASDBPATH)){
            ArrayList<Cuenta> cuentas = mapper.readValue(reader, new TypeReference<ArrayList<Cuenta>>(){});
            return cuentas;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeProductos(List<Producto> productos) {
        try(FileWriter writer = new FileWriter(PRODUCTOSDBPATH)){
            mapper.writeValue(writer, productos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeCuentas(List<Cuenta> cuentas) {
        try(FileWriter writer = new FileWriter(CUENTASDBPATH)){
            mapper.writeValue(writer, cuentas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
