package inca.jesus.trajesya.data.modelo;

import java.util.List;

public class Reserva {
    int idReserva;
    int TipoReserva;
    String fechaEntrega;
    String horaReserva;
    TipoPago tipoPagoReserva;
    TipoTarjeta tipoTarjetaReserva;
    UbicacionDireccion ubicacionDireccionReserva;
    Usuario usuarioReserva;
    String fechaRegistro;
    Estado estadoReserva;
    List<ReservaItem> ListaItems;

    public Reserva(){

    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getTipoReserva() {
        return TipoReserva;
    }

    public void setTipoReserva(int tipoReserva) {
        TipoReserva = tipoReserva;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    public TipoPago getTipoPagoReserva() {
        return tipoPagoReserva;
    }

    public void setTipoPagoReserva(TipoPago tipoPagoReserva) {
        this.tipoPagoReserva = tipoPagoReserva;
    }

    public TipoTarjeta getTipoTarjetaReserva() {
        return tipoTarjetaReserva;
    }

    public void setTipoTarjetaReserva(TipoTarjeta tipoTarjetaReserva) {
        this.tipoTarjetaReserva = tipoTarjetaReserva;
    }

    public UbicacionDireccion getUbicacionDireccionReserva() {
        return ubicacionDireccionReserva;
    }

    public void setUbicacionDireccionReserva(UbicacionDireccion ubicacionDireccionReserva) {
        this.ubicacionDireccionReserva = ubicacionDireccionReserva;
    }

    public Usuario getUsuarioReserva() {
        return usuarioReserva;
    }

    public void setUsuarioReserva(Usuario usuarioReserva) {
        this.usuarioReserva = usuarioReserva;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Estado getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(Estado estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public List<ReservaItem> getListaItems() {
        return ListaItems;
    }

    public void setListaItems(List<ReservaItem> listaItems) {
        ListaItems = listaItems;
    }
}
