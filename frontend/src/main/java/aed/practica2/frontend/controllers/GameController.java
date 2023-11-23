package aed.practica2.frontend.controllers;

import aed.practica2.backend.models.Generos;
import aed.practica2.backend.models.Producto;
import aed.practica2.backend.models.Roles;
import aed.practica2.frontend.App;
import aed.practica2.frontend.Compontents.Alerts;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML private Pane panelPrincipal;

    @FXML private Label precioLabel, nameLabel, generosLabel;

    @FXML private ImageView gameImage;

    @FXML private Button deleteButton;

    private Dialog dialog = new Dialog();

    private Producto game = new Producto();

    private Stage stage;

    public GameController(){
        try{
            FXMLLoader l = new FXMLLoader(getClass().getResource("/gameview.fxml"));
            l.setController(this);
            l.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setJuego(Producto p){
        game = p;
        gameImage.setImage(new Image(game.getImagen()));
        gameImage.setFitWidth(200);
        gameImage.setFitHeight(230);
        nameLabel.setText(game.getNombre());
        precioLabel.setText(String.format("%.2f€",game.getPrecio()));

        dialog.getDialogPane().setContent(panelPrincipal);

    }

    @FXML
    private void comprar(){
        var ownedGame = App.user.getJuegosAdquiridos().stream().filter(juego -> juego.getId()==game.getId()).findFirst();
        if(ownedGame.isPresent()) Alerts.showError("Juego ya adquirido","Este juego ya estaba en tu biblioteca");
        else{
            App.user.getJuegosAdquiridos().add(game);
            Alerts.success("Juego comprado con éxito","El juego " + game.getNombre() + " se ha añadido a tu biblioteca");
        }
    }

    @FXML
    private void eliminar(){
        App.service.removeProducto(game.getId());
        Alerts.success("Juego eliminado con éxito","EL juego fue eliminado sin problemas");
    }

    private void setGeneros(){
        StringBuilder sb = new StringBuilder();
        game.getGeneros().forEach(genero -> sb.append(genero.toString()));
        generosLabel.setText(sb.toString());
    }

    public Optional showGame(){
        return dialog.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(App.user.getRol().equals(Roles.ADMIN)) deleteButton.setVisible(true);
        stage = (Stage)dialog.getDialogPane().getScene().getWindow();
        stage.setOnCloseRequest(e -> stage.close());

    }
}
