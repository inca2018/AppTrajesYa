package inca.jesus.trajesya.data.modelo;

public class TipoTarjeta {
    int idTipoTarjeta;
    String nombreTarjeta;
    String fechaRegistro;
    String fechaUpdate;
    Estado estadoTipoTarjeta;
    boolean select ;

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

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public void LimpiarValores(TipoTarjeta tipoTarjeta){
        tipoTarjeta.setNombreTarjeta("");
        tipoTarjeta.setIdTipoTarjeta(0);
        tipoTarjeta.setEstadoTipoTarjeta(null);
        tipoTarjeta.setFechaUpdate("");
        tipoTarjeta.setFechaRegistro("");
        tipoTarjeta.setSelect(false);
    }
}
