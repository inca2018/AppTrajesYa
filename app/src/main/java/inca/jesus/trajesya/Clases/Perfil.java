package inca.jesus.trajesya.Clases;

public class Perfil {
    int idPerfil;
    String NombrePrefil;
    String Permisos;
    String fechaRegistro;
    String fechaUpdate;
    Estado estadoPerfil;

    public Perfil(){

    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePrefil() {
        return NombrePrefil;
    }

    public void setNombrePrefil(String nombrePrefil) {
        NombrePrefil = nombrePrefil;
    }

    public String getPermisos() {
        return Permisos;
    }

    public void setPermisos(String permisos) {
        Permisos = permisos;
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

    public Estado getEstadoPerfil() {
        return estadoPerfil;
    }

    public void setEstadoPerfil(Estado estadoPerfil) {
        this.estadoPerfil = estadoPerfil;
    }
}
