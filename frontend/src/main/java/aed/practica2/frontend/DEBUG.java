package aed.practica2.frontend;

import aed.practica2.backend.db.DBService;
import aed.practica2.frontend.Compontents.GamesView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DEBUG extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        DBService db = new DBService();
        stage.setTitle("DEBUG");
        stage.setScene(new Scene(GamesView.drawGames(db.getProductos())));
        stage.show();
    }
}
