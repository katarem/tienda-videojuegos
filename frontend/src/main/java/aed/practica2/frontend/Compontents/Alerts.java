package aed.practica2.frontend.Compontents;

import javafx.scene.control.Alert;

public class Alerts {

    public static void showError(String title, String message){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(message);
        a.showAndWait();
    }

    public static void success(String title, String message){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(message);
        a.showAndWait();
    }
}
