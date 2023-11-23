package aed.practica2.frontend.Compontents;

import aed.practica2.backend.models.Producto;
import aed.practica2.frontend.App;
import aed.practica2.frontend.controllers.GameController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class GamesView {

    private static GridPane grid;


    public static GridPane drawGames(List<Producto> games){
        setupGrid();
        var coords = setConstraints(games.size(),5);
        var column = coords[0];
        var rows = coords[1];
        var count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if (count == games.size()) break;
                var game = games.get(count);
                var newJuego = GameComponent(game);
                grid.add(newJuego, j, i);
                count++;
            }
        }
        return grid;
    }


    private static void setupGrid(){
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding(new Insets(5,5,5,5));
        grid.setMaxHeight(GridPane.USE_PREF_SIZE);
        grid.setPrefHeight(GridPane.USE_COMPUTED_SIZE);
        grid.setMinHeight(GridPane.USE_PREF_SIZE);
        grid.setMaxWidth(GridPane.USE_PREF_SIZE);
        grid.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
        grid.setMinWidth(GridPane.USE_PREF_SIZE);
    }

    private static int[] setConstraints(int size, int columnCount) {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        for (int i = 0; i < columnCount; i++) {
            ColumnConstraints c = new ColumnConstraints();
            c.setFillWidth(false);
            c.setPrefWidth(140);
            c.setMaxWidth(140);
            c.setMinWidth(140);
            c.setHgrow(Priority.ALWAYS);
            c.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(c);
        }
        var rows = Math.ceilDiv(size,columnCount);
        for (int i = 0; i < rows; i++) {
            RowConstraints r = new RowConstraints();
            r.setValignment(VPos.CENTER);
            r.setPrefHeight(200);
            r.setMaxHeight(200);
            r.setMinHeight(200);
            r.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(r);
        }
        return new int[]{columnCount, rows};
    }

    private static VBox GameComponent(Producto game){
        var newJuego = new VBox();
        var nombreJuego = new Label(game.getNombre());
        var imagenJuego = new ImageView(new Image(game.getImagen()));

        nombreJuego.setWrapText(true);
        nombreJuego.setMaxWidth(120);
        nombreJuego.setPrefHeight(50);
        nombreJuego.setTextAlignment(TextAlignment.CENTER);
        nombreJuego.setAlignment(Pos.CENTER);

        imagenJuego.setFitWidth(120);
        imagenJuego.setFitHeight(140);

        newJuego.setSpacing(5);
        newJuego.setPadding(new Insets(5,5,5,5));
        newJuego.setAlignment(Pos.CENTER);
        newJuego.getChildren().addAll(imagenJuego, nombreJuego);
        newJuego.setMinWidth(VBox.USE_PREF_SIZE);
        newJuego.setPrefWidth(VBox.USE_COMPUTED_SIZE);
        newJuego.setMaxWidth(VBox.USE_PREF_SIZE);
        newJuego.setMaxHeight(VBox.USE_PREF_SIZE);
        newJuego.setPrefHeight(200);
        newJuego.setMinHeight(VBox.USE_PREF_SIZE);

        newJuego.setOnMouseEntered(e -> {
            Border border = new Border(new javafx.scene.layout.BorderStroke(
                    Color.BLACK,
                    BorderStrokeStyle.SOLID,
                    null,
                    new BorderWidths(1)));
            newJuego.setBorder(border);
        });

        newJuego.setOnMouseExited(e -> {
            newJuego.setBorder(Border.EMPTY);
        });

        newJuego.setOnMouseClicked(e ->  {
            var g = new GameController();
            var selectedGame = App.service.getProductos().stream().filter(juego -> juego.getNombre().equals(nombreJuego.getText())).findAny().get();
            g.setJuego(selectedGame);
            g.showGame();

        });
        return newJuego;
    }
}
