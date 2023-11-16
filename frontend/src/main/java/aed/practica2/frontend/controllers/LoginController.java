package aed.practica2.frontend.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public LoginController(){
        try{
            FXMLLoader l = new FXMLLoader(getClass().getResource("login-view.fxml"));
            l.setController(this);
            l.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Vista y controlador de LoginController
    }
}
