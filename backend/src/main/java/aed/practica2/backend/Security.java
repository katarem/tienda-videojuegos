package aed.practica2.backend;

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

    public static String login(List<Cuenta> users, String username, String password){
        var usuarioExiste = users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
        if(usuarioExiste.isEmpty()) return "Usuario no encontrado.";
        else if(usuarioExiste.get().getPassword().equals(hashPassword(password))) return "Usuario logeado! bienvenido " + usuarioExiste.get().getEmail();
        else return "Contraseña incorrecta";
    }


}
