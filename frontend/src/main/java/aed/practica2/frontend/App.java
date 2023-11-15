package aed.practica2.frontend;

import aed.practica2.backend.db.DBService;
import aed.practica2.backend.models.Cuenta;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {

        DBService service = new DBService();
        var cuentas = service.getCuentas().stream()
                .map(Cuenta::getUsername)
                .toList();

        ListView<String> listView = new ListView<>();
        listView.setItems(FXCollections.observableList(cuentas));
        BorderPane panel = new BorderPane();
        panel.setCenter(listView);
        stage.setTitle("Tienda :D");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.setScene(new Scene(panel));
        stage.show();
    }

    public static void main( String[] args ) {
        launch(args);
    }
}
