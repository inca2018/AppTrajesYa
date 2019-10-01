package inca.jesus.trajesya.data.modelo;

public class TipoPago {
    int idTipoPago;
    String nombreTipoPago;
    String fechaRegistro;
    boolean select;

    public TipoPago(){

    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getNombreTipoPago() {
        return nombreTipoPago;
    }

    public void setNombreTipoPago(String nombreTipoPago) {
        this.nombreTipoPago = nombreTipoPago;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    public void LimpiarValores(TipoPago tipoPago){
        tipoPago.setNombreTipoPago("");
        tipoPago.setFechaRegistro("");
        tipoPago.setIdTipoPago(0);
        tipoPago.setSelect(false);
    }
}
