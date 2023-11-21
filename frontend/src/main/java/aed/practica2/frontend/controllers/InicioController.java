package aed.practica2.frontend.controllers;

import aed.practica2.frontend.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable {

    @FXML private Pane panePrincipal;

    @FXML private Label userNameLabel;

    @FXML private ImageView userProfileImage;

    public InicioController(){
        try{
            FXMLLoader f = new FXMLLoader(getClass().getResource("/inicio.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var user = App.user;
        userNameLabel.setText(user.getUsername());
        setProfileImage();
    }

    @FXML
    private void exit(){
        App.exit();
    }

    @FXML
    private void goToPerfil(){
        App.stage.setScene(new Scene(new PerfilController().getView()));
        App.stage.show();
    }


    private void setProfileImage(){
        var pfp = App.user.getProfileImage();
        if(!pfp.isEmpty()){
            Image i = new Image(pfp);
            userProfileImage.setImage(i);
        }
    }



    public Pane getView() {
        return panePrincipal;
    }
}
