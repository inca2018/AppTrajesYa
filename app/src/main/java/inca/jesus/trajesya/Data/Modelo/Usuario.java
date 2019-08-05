package inca.jesus.trajesya.Data.Modelo;

import inca.jesus.trajesya.Clases.Perfil;

public class Usuario {
    int idUsuario;
    String Usuario;
    String Password;
    String NombreUsuario;
    String ApellidoUsuario;
    char DniUsuario;
    String CorreoUsuario;
    String keyFacebook;
    String keyGoogle;
    String fechaRegistro;
    String fechaUpdate;
    String ImagenUsuario;
    Perfil perfilUsuario;
    Estado estadoUsuario;

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return ApellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        ApellidoUsuario = apellidoUsuario;
    }

    public char getDniUsuario() {
        return DniUsuario;
    }

    public void setDniUsuario(char dniUsuario) {
        DniUsuario = dniUsuario;
    }

    public String getCorreoUsuario() {
        return CorreoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        CorreoUsuario = correoUsuario;
    }

    public String getKeyFacebook() {
        return keyFacebook;
    }

    public void setKeyFacebook(String keyFacebook) {
        this.keyFacebook = keyFacebook;
    }

    public String getKeyGoogle() {
        return keyGoogle;
    }

    public void setKeyGoogle(String keyGoogle) {
        this.keyGoogle = keyGoogle;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(String fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    public String getImagenUsuario() {
        return ImagenUsuario;
    }

    public void setImagenUsuario(String imagenUsuario) {
        ImagenUsuario = imagenUsuario;
    }

    public Perfil getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(Perfil perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public Estado getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Estado estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }


}
