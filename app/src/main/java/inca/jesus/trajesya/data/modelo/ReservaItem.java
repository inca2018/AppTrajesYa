package inca.jesus.trajesya.data.modelo;

public class ReservaItem {
    int idReservaitem;
    int idReserva;
    Producto productoItem;
    int Cantidad;
    Medida medidaReservaItem;
    String fechaRegistro;
    double total;

    public ReservaItem(){
    }

    public int getIdReservaitem() {
        return idReservaitem;
    }

    public void setIdReservaitem(int idReservaitem) {
        this.idReservaitem = idReservaitem;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Producto getProductoItem() {
        return productoItem;
    }

    public void setProductoItem(Producto productoItem) {
        this.productoItem = productoItem;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public Medida getMedidaReservaItem() {
        return medidaReservaItem;
    }

    public void setMedidaReservaItem(Medida medidaReservaItem) {
        this.medidaReservaItem = medidaReservaItem;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        String Objeto=
                "{\"productoItem\": " +
                productoItem.getIdProducto()+ ",\"Cantidad\": " +
                Cantidad+ ",\"medidaReservaItem\":" +
                medidaReservaItem.getIdMedida()+'}';
        return Objeto;
    }

}
