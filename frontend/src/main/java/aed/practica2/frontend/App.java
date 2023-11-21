package aed.practica2.frontend;

import aed.practica2.backend.db.DBService;
import aed.practica2.backend.models.Cuenta;
import aed.practica2.frontend.controllers.LoginController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static Stage stage;

    public static Cuenta user;

    public static DBService service;
    @Override
    public void start(Stage stage) {
        service = new DBService();
        App.stage = stage;
        LoginController h = new LoginController();
        App.stage.setTitle("APP");
        App.stage.setResizable(false);
        App.stage.setScene(new Scene(h.getView()));
        App.stage.setOnCloseRequest(e -> exit());
        App.stage.show();
    }
    //Para asegurarnos que siempre se persisten los datos.
    public static void exit(){
        if(App.user!=null)service.addCuenta(App.user);
        service.close();
        App.stage.close();
    }


    public static void main( String[] args ) {
        launch(args);
    }
}
