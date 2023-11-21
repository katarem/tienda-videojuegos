package aed.practica2.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TiendaController implements Initializable {

    @FXML private Pane panePrincipal;

    public TiendaController(){
        try{
            FXMLLoader f = new FXMLLoader(getClass().getResource("/tienda.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }
}
