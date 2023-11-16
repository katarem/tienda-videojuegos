module frontend {
    exports aed.practica2.frontend;
    requires aed.practica2.backend;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    opens aed.practica2.frontend.controllers;
}