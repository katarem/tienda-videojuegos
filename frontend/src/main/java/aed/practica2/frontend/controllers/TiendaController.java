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
    private void insertGame() {
        try {
            var gameName = nameLabel.getText();
            var precio = precioLabel.getText();

            if (gameName.isEmpty() || precio.isEmpty()) {
                throw new CamposVaciosException("Nombre y precio no pueden estar vacíos");
            }

            try {
                double parsedPrecio = Double.parseDouble(precio);
            } catch (NumberFormatException e) {
                throw new PrecioInvalidoException("El precio debe ser un número válido");
            }

            if (!gameName.matches("^[a-zA-Z0-9 ]*$")) {
                throw new TituloInvalidoException("El título no puede contener caracteres especiales");
            }

            ArrayList<Generos> generos = new ArrayList<>();

            var generosValues = Arrays.stream(generosLabel.getText().split(",")).toList();
            generosValues.forEach(value -> {
                try {
                    var genero = Generos.valueOf(value);
                    generos.add(genero);
                } catch (IllegalArgumentException e) {
                    Alerts.showError("Género no existe", e.getMessage());
                }
            });

            if (nuevoJuego == null) nuevoJuego = new Producto();
            nuevoJuego.setNombre(gameName);
            nuevoJuego.setPrecio(Double.parseDouble(precio));
            nuevoJuego.setGeneros(generos);
            App.service.addProducto(nuevoJuego);
            updateGames();
        } catch (CamposVaciosException | PrecioInvalidoException | TituloInvalidoException e) {
            Alerts.showError("Error al agregar juego", e.getMessage());
        } catch (Exception e) {
            Alerts.showError("Error general", e.getMessage());
        }
    }



    public class TituloInvalidoException extends Exception {
        public TituloInvalidoException(String message) {
            super(message);
        }
    }

    public class PrecioInvalidoException extends Exception {
        public PrecioInvalidoException(String message) {
            super(message);
        }
    }
    public class CamposVaciosException extends Exception {
        public CamposVaciosException(String message) {
            super(message);
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
