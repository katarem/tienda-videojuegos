package aed.practica2.backend.models;

import aed.practica2.backend.utils.Security;

import java.io.Serializable;
import java.util.Objects;

/**
 * Modelo para usuarios de la plataforma
 * */
public class Cuenta implements Serializable{

    private String username;
    private String password;
    private String email;
    private String profileImage;
    private Roles rol;

    public Cuenta(String username, String password, String email, String profileImage, Roles rol) {
        this.username = username;
        setPassword(password);
        this.email = email;
        this.profileImage = profileImage;
        if(rol==null) this.rol = Roles.USER;
        else this.rol = rol;
    }

    public Cuenta(){ }

    public Roles getRol(){ return rol; }

    public void setRol(Roles rol) { this.rol = rol; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
       if(password.length()!=44) this.password = Security.hashPassword(password);
       else this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(username, cuenta.username) && Objects.equals(password, cuenta.password) && Objects.equals(email, cuenta.email) && Objects.equals(profileImage, cuenta.profileImage) && rol == cuenta.rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, profileImage, rol);
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", rol=" + rol +
                '}';
    }
}
