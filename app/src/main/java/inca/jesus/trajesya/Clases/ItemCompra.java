package inca.jesus.trajesya.Clases;

public class ItemCompra {
    int id_compra;
    String numEnvio;
    String Fecha_Envio;
    int cantEnvios;
    ProductoX productoX;
    int cantidad;
    Double total;
    public ItemCompra(){
    }
    public ItemCompra(int id_compra, String numEnvio, String fecha_Envio, int cantEnvios, ProductoX productoX,int cantidad,Double total) {
        this.id_compra = id_compra;
        this.numEnvio = numEnvio;
        Fecha_Envio = fecha_Envio;
        this.cantEnvios = cantEnvios;
        this.productoX=productoX;
        this.cantidad=cantidad;
        this.total=total;
    }
    public int getId_compra() {
        return id_compra;
    }
    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }
    public String getNumEnvio() {
        return numEnvio;
    }
    public void setNumEnvio(String numEnvio) {
        this.numEnvio = numEnvio;
    }
    public String getFecha_Envio() {
        return Fecha_Envio;
    }
    public void setFecha_Envio(String fecha_Envio) {
        Fecha_Envio = fecha_Envio;
    }
    public int getCantEnvios() {
        return cantEnvios;
    }
    public void setCantEnvios(int cantEnvios) {
        this.cantEnvios = cantEnvios;
    }
    public ProductoX getProductoX() {
        return productoX;
    }
    public void setProductoX(ProductoX productoX) {
        this.productoX = productoX;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

}
