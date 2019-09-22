package inca.jesus.trajesya.data.modelo;

import java.util.List;

public class Reserva {
    int idReserva;
    int TipoReserva;
    String fechaEntrega;
    String horaReserva;
    TipoPago tipoPagoReserva;
    TipoTarjeta tipoTarjetaReserva;
    TipoComprobante tipoComprobante;
    UbicacionDireccion ubicacionDireccionReserva;
    Usuario usuarioReserva;
    String fechaRegistro;
    Estado estadoReserva;
    String telefono;
    List<ReservaItem> ListaItems;
    double totalDelivery;
    double totalBase;
    double totalUrgencia;
    double totalDescuento;
    boolean select;
    String tiempo;

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

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getTotalDelivery() {
        return totalDelivery;
    }

    public void setTotalDelivery(double totalDelivery) {
        this.totalDelivery = totalDelivery;
    }

    public double getTotalBase() {
        return totalBase;
    }

    public void setTotalBase(double totalBase) {
        this.totalBase = totalBase;
    }

    public double getTotalUrgencia() {
        return totalUrgencia;
    }

    public void setTotalUrgencia(double totalUrgencia) {
        this.totalUrgencia = totalUrgencia;
    }

    public double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
