package aed.practica2.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class LoginController {

    @FXML
    private Pane panePrincipal;

    public LoginController(){
        try{
            FXMLLoader l = new FXMLLoader(getClass().getResource("/login.fxml"));
            l.setController(this);
            l.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public Pane getView(){
        return panePrincipal;
    }

}
