package aed.practica2.frontend.controllers;

import aed.practica2.backend.utils.Security;
import aed.practica2.frontend.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class LoginController {

    @FXML
    private Pane panePrincipal;

    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField textFieldPassword;

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
    private void login(){
        var username = textFieldUsername.getText();
        var password = textFieldPassword.getText();
        var cuentas = App.service.getCuentas();
        var out = Security.login(cuentas, username,password);
        switch(out.charAt(0)){
            case 'U': success("Login exitoso! :)",out); break;
            default: showError("Error en login :(",out); break;
        }
    }

    @FXML
    private void register(){
        //TODO hacer la funcion XD
    }


    private void showError(String title, String message){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setContentText(message);
        a.showAndWait();
    }

    private void success(String title, String message){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setContentText(message);
        a.showAndWait();
    }



    @FXML
    public Pane getView(){
        return panePrincipal;
    }

}
