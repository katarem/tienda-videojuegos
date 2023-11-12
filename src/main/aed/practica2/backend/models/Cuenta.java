package aed.practica2.backend.models;

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

    public Cuenta(String username, String password, String email, String profileImage) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImage = profileImage;
    }

    public Cuenta(){ }

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
        this.password = password;
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
        return Objects.equals(username, cuenta.username) && Objects.equals(password, cuenta.password) && Objects.equals(email, cuenta.email) && Objects.equals(profileImage, cuenta.profileImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, profileImage);
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
