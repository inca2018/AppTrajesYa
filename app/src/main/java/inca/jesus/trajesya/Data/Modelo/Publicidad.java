package inca.jesus.trajesya.Data.Modelo;

public class Publicidad {
    int idPublicidad;
    String nombrePublicidad;
    String imagenPublicidad;
    String linkPublicidad;
    String fechaRegistro;
    String fechaUpdate;
    Estado estadoPublicidad;
    public Publicidad(){

    }

    public int getIdPublicidad() {
        return idPublicidad;
    }

    public void setIdPublicidad(int idPublicidad) {
        this.idPublicidad = idPublicidad;
    }

    public String getNombrePublicidad() {
        return nombrePublicidad;
    }

    public void setNombrePublicidad(String nombrePublicidad) {
        this.nombrePublicidad = nombrePublicidad;
    }

    public String getImagenPublicidad() {
        return imagenPublicidad;
    }

    public void setImagenPublicidad(String imagenPublicidad) {
        this.imagenPublicidad = imagenPublicidad;
    }

    public String getLinkPublicidad() {
        return linkPublicidad;
    }

    public void setLinkPublicidad(String linkPublicidad) {
        this.linkPublicidad = linkPublicidad;
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

    public Estado getEstadoPublicidad() {
        return estadoPublicidad;
    }

    public void setEstadoPublicidad(Estado estadoPublicidad) {
        this.estadoPublicidad = estadoPublicidad;
    }
}
