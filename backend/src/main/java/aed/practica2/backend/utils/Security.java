package aed.practica2.backend.utils;

import aed.practica2.backend.models.Cuenta;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class Security {

    public static String hashPassword(String password) {
        try {
            var md = MessageDigest.getInstance("SHA-256");
            // Add password bytes to digest
            md.update(password.getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();
            // Convert the byte array into a signum representation
            return Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String login(List<Cuenta> users, String username, String password) {
        try {
            var usuarioExiste = users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
            if (usuarioExiste.isEmpty()) {
                throw new UsuarioNoExistenteException("No existe cuenta con ese username.");
            } else if (usuarioExiste.get().getPassword().equals(hashPassword(password))) {
                return "Usuario logeado! Bienvenido " + usuarioExiste.get().getEmail();
            } else {
                throw new ContraseñaIncorrectaException("Contraseña incorrecta");
            }
        } catch (UsuarioNoExistenteException e) {
            return e.getMessage();
        } catch (ContraseñaIncorrectaException e) {
            return e.getMessage();
        }
    }

    static class UsuarioNoExistenteException extends Exception {
        public UsuarioNoExistenteException(String message) {
            super(message);
        }
    }

    static class ContraseñaIncorrectaException extends Exception {
        public ContraseñaIncorrectaException(String message) {
            super(message);
        }
    }


}
