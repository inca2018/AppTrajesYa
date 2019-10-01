package inca.jesus.trajesya.data.modelo;

public class TipoComprobante {
    int idTipoComprobante;
    String nombreTipoComprobante;
    String fechaRegistro;
    String fechaUpdate;
    Estado estadoTipoComprobante;
    boolean select;

    public TipoComprobante(){

    }

    public int getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(int idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public String getNombreTipoComprobante() {
        return nombreTipoComprobante;
    }

    public void setNombreTipoComprobante(String nombreTipoComprobante) {
        this.nombreTipoComprobante = nombreTipoComprobante;
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

    public Estado getEstadoTipoComprobante() {
        return estadoTipoComprobante;
    }

    public void setEstadoTipoComprobante(Estado estadoTipoComprobante) {
        this.estadoTipoComprobante = estadoTipoComprobante;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    public void LimpiarValores(TipoComprobante tipoComprobante){
        tipoComprobante.setNombreTipoComprobante("");
        tipoComprobante.setIdTipoComprobante(0);
        tipoComprobante.setEstadoTipoComprobante(null);
        tipoComprobante.setFechaRegistro("");
        tipoComprobante.setFechaUpdate("");
        tipoComprobante.setSelect(false);
    }
}
