package inca.jesus.trajesya.Data.Modelo;

public class Promocion {
    int idPromocion;
    String nombrePromocion;
    String imagenPromocion;
    String linkPromocion;
    String fechaRegistro;
    String fechaUpdate;
    Estado estadoPromocion;

    public Promocion(){

    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getImagenPromocion() {
        return imagenPromocion;
    }

    public void setImagenPromocion(String imagenPromocion) {
        this.imagenPromocion = imagenPromocion;
    }

    public String getLinkPromocion() {
        return linkPromocion;
    }

    public void setLinkPromocion(String linkPromocion) {
        this.linkPromocion = linkPromocion;
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

    public Estado getEstadoPromocion() {
        return estadoPromocion;
    }

    public void setEstadoPromocion(Estado estadoPromocion) {
        this.estadoPromocion = estadoPromocion;
    }
}
