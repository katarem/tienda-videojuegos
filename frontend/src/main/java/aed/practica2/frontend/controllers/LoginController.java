package aed.practica2.frontend.controllers;

import aed.practica2.backend.models.Cuenta;
import aed.practica2.backend.models.Roles;
import aed.practica2.backend.utils.Security;
import aed.practica2.frontend.App;
import aed.practica2.frontend.Compontents.Alerts;
import aed.practica2.frontend.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
    private void login() {
        try {
            var username = textFieldUsername.getText();
            var password = textFieldPassword.getText();

            // Verificar si los campos están vacíos
            if (username.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Los campos están vacíos. Por favor, completa ambos campos.");
            }

            var cuentas = App.service.getCuentas();
            var out = Security.login(cuentas, username, password);

            if (out.charAt(0) == 'U') {
                Alerts.success("Login exitoso! :)", out);
                App.user = App.service.getCuentas().stream().filter(cuenta -> cuenta.getUsername().equals(username)).findFirst().get();
                App.stage.setScene(new Scene(new InicioController().getView()));
                App.stage.show();
            } else {
                Alerts.showError("Error en login :(", out);
            }
        } catch (IllegalArgumentException e) {
            // Manejar la excepción de campos vacíos
            Alerts.showError("Error en login :(", e.getMessage());
        }
    }


    @FXML
    private void register() {
        try {
            var username = textFieldUsername.getText();
            var password = textFieldPassword.getText();

            if (username.isEmpty() || password.isEmpty()) {
                throw new CamposVaciosException("Debes rellenar todas las casillas.");
            }

            System.out.println("Comprobando existencias...");
            // Usuario existe?
            var existe = App.service.getCuentas().stream()
                    .filter(cuenta -> cuenta.getUsername().equals(username))
                    .findFirst();

            if (existe.isPresent()) {
                throw new UsuarioExistenteException("El usuario " + existe.get().getUsername() + " ya existe");
            } else {
                // Registro válido
                var newCuenta = new Cuenta(username, Security.hashPassword(password), "Correo no configurado.", "", Roles.USER, null);
                App.service.addCuenta(newCuenta);
                Alerts.success("Usuario registrado correctamente!", "Ahora ya puede entrar usando su usuario y contraseña.");
            }
        } catch (CamposVaciosException e) {
            Alerts.showError("Campos vacíos", e.getMessage());
        } catch (UsuarioExistenteException e) {
            Alerts.showError("Error al registrar usuario", e.getMessage());
        } catch (Exception e) {
            Alerts.showError("Error desconocido", "Se ha producido un error al procesar el registro.");
            e.printStackTrace();
        }
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

    public class UsuarioExistenteException extends Exception {

        public UsuarioExistenteException(String message) {
            super(message);
        }
    }

    public class CamposVaciosException extends Exception {

        public CamposVaciosException(String message) {
            super(message);
        }
    }

}
