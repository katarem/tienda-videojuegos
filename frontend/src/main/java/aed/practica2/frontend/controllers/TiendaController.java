package aed.practica2.frontend.controllers;

import aed.practica2.backend.models.Generos;
import aed.practica2.backend.models.Producto;
import aed.practica2.backend.models.Roles;
import aed.practica2.frontend.App;
import aed.practica2.frontend.Compontents.Alerts;
import aed.practica2.frontend.Compontents.GamesView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TiendaController implements Initializable {

    @FXML
    private Pane panePrincipal;

    private GridPane gamesView;

    @FXML
    private ChoiceBox<Generos> categoryFilter;

    @FXML
    private ScrollPane gamesContainer;

    @FXML
    private ImageView profileImage;

    @FXML
    private Label adminLabel;

    @FXML
    private VBox gamesControl;

    @FXML
    private TextField nameLabel, precioLabel;

    @FXML
    private TextArea generosLabel;

    private Producto nuevoJuego;

    public TiendaController() {
        try {
            FXMLLoader f = new FXMLLoader(getClass().getResource("/tienda.fxml"));
            f.setController(this);
            f.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeProfileImage();
        adminMode();
        var juegos = App.service.getProductos();
        categoryFilter.getItems().addAll(Generos.values());
        categoryFilter.setValue(Generos.TODOS);
        categoryFilter.setOnAction(e -> filter());
        gamesView = new GridPane();
        gamesView = GamesView.drawGames(juegos);
        gamesContainer.setContent(gamesView);
    }

    private void adminMode() {
        var rol = App.user.getRol();
        if(rol.equals(Roles.ADMIN)){
            adminLabel.setVisible(true);
            gamesControl.setVisible(true);
        }
    }

    @FXML
    private void newGameImage(){
        TextInputDialog t = new TextInputDialog("Añada el link de la imagen.");
        t.setHeaderText("Busque una imagen en la red y copie el link de la misma.");
        t.showAndWait().ifPresent(img -> {
            if(nuevoJuego==null) nuevoJuego = new Producto();
            nuevoJuego.setImagen(img);
        });
    }

    @FXML
    private void insertGame(){
        try{
            var gameName = nameLabel.getText();
            var precio = precioLabel.getText();
            ArrayList<Generos> generos = new ArrayList<>();

            var generosValues = Arrays.stream(generosLabel.getText().split(",")).toList();
            generosValues.forEach(value -> {
                try{
                    var genero = Generos.valueOf(value);
                    generos.add(genero);
                } catch(IllegalArgumentException ignored){}
            });
            if(nuevoJuego == null) nuevoJuego = new Producto();
            nuevoJuego.setNombre(gameName);
            nuevoJuego.setPrecio(Double.parseDouble(precio));
            nuevoJuego.setGeneros(generos);
            App.service.addProducto(nuevoJuego);
            updateGames();
        } catch(Exception e){
            //TODO excepcion personalizada si no has puesto todos los datos
            Alerts.showError("juego no agregado",e.getMessage());
        }

    }

    @FXML
    private void updateGames(){
        var games = App.service.getProductos();
        gamesView = GamesView.drawGames(games);
        gamesContainer.setContent(gamesView);
        categoryFilter.setValue(Generos.TODOS);
        generosLabel.setText("");
        precioLabel.setText("");
        nameLabel.setText("");
    }

    private void changeProfileImage() {
        var img = App.user.getProfileImage();
        if (!img.isEmpty()) {
            profileImage.setImage(new Image(img));
            profileImage.setFitHeight(60);
            profileImage.setFitWidth(60);
        }
    }

    private void filter() {
        var juegos = App.service.getProductos();
        var selected = categoryFilter.getValue();
        if (selected.equals(Generos.TODOS)) gamesView = GamesView.drawGames(juegos);
        else {
            var juegosFiltrados = juegos.stream().filter(juego -> juego.getGeneros().contains(selected)).toList();
            gamesView = GamesView.drawGames(juegosFiltrados);
        }
        gamesContainer.setContent(gamesView);
    }

    @FXML
    private void back() {
        App.stage.setTitle("INICIO");
        App.stage.setScene(new Scene(new InicioController().getView()));
        App.stage.show();
    }

    public Pane getView() {
        return panePrincipal;
    }
}
