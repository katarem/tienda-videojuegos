package aed.practica2.frontend;

import aed.practica2.frontend.controllers.LoginController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static Stage stage;
    @Override
    public void start(Stage stage) {

        App.stage = stage;
        LoginController h = new LoginController();

        App.stage.setTitle("APP");
        App.stage.setResizable(false);
        App.stage.setScene(new Scene(h.getView()));
        App.stage.show();
    }

    public static void main( String[] args ) {
        launch(args);
    }
}
