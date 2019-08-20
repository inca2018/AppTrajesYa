package inca.jesus.trajesya.Data.Modelo;

public class TipoTarjeta {
    int idTipoTarjeta;
    String nombreTarjeta;
    String fechaRegistro;
    String fechaUpdate;
    Estado estadoTipoTarjeta;

    public TipoTarjeta(){

    }

    public int getIdTipoTarjeta() {
        return idTipoTarjeta;
    }

    public void setIdTipoTarjeta(int idTipoTarjeta) {
        this.idTipoTarjeta = idTipoTarjeta;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
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

    public Estado getEstadoTipoTarjeta() {
        return estadoTipoTarjeta;
    }

    public void setEstadoTipoTarjeta(Estado estadoTipoTarjeta) {
        this.estadoTipoTarjeta = estadoTipoTarjeta;
    }
}
