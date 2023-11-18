package aed.practica2.frontend.controllers;

import aed.practica2.backend.models.Cuenta;
import aed.practica2.backend.models.Roles;
import aed.practica2.backend.utils.Security;
import aed.practica2.frontend.App;
import aed.practica2.frontend.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private Pane panePrincipal;

    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldPassword;

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
        var username = textFieldUsername.getText();
        var password = textFieldPassword.getText();

        //Usuario existe?
        var existe = App.service.getCuentas().stream()
                .filter(cuenta -> cuenta.getUsername().equals(username))
                .findFirst();
        existe.ifPresent(cuenta -> showError("Usuario ya existente","El usuario " +
                                                cuenta.getUsername() + " ya existe"));

        //Registro válido
        var newCuenta = new Cuenta(username,Security.hashPassword(password),"Correo no configurado.","", Roles.USER);
        App.service.addCuenta(newCuenta);
        success("Usuario registrado correctamente!","AHora ya puede entrar usando su usuario y contraseña.");
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
    private MediaView mediaVideo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String videoFile = "fondoLogin.mp4";
        File archivo = new File(videoFile);
        Media media = new Media(archivo.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaVideo.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));
    }


    @FXML
    public Pane getView(){
        return panePrincipal;
    }

}
