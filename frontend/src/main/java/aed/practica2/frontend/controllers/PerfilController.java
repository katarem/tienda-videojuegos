package aed.practica2.frontend.controllers;

import aed.practica2.frontend.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PerfilController implements Initializable {

    @FXML
    private Pane panePrincipal;

    @FXML
    private ImageView userProfileImage;

    @FXML
    private Label userNameLabel;

    public PerfilController(){
        try{
            FXMLLoader f = new FXMLLoader(getClass().getResource("/perfil.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    public Pane getView() {
        return panePrincipal;
    }
    private void setProfileImage(){
        var pfp = App.user.getProfileImage();
        if(!pfp.isEmpty()){
            Image i = new Image(pfp);
            userProfileImage.setImage(i);
            userProfileImage.setFitHeight(150);
            userProfileImage.setFitWidth(150);
        }
    }
    @FXML
    private void changeProfileImage(){
        TextInputDialog t = new TextInputDialog("Añada el link de la imagen.");
        t.setContentText("Busque una imagen en la red y copie el link de la misma.");
        t.showAndWait().ifPresent(img -> {
            App.user.setProfileImage(img);
            setProfileImage();
        });
    }

    @FXML
    private void back(){
        App.stage.setScene(new Scene(new InicioController().getView()));
        App.stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProfileImage();
        userNameLabel.setText(App.user.getUsername());
    }
}
