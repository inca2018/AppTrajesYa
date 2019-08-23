package inca.jesus.trajesya.clases;

/**
 * Created by Jesus on 06/06/2017.
 */

public class ItemCarrito {
    int id;
    ProductoX p;
    String Fecha_reg;
    String usuario;
    int cantidad;
    Double total;

    public ItemCarrito(int id, ProductoX p, String fecha_reg, String usuario,int cantidad,Double total) {
        this.id = id;
        this.p = p;
        this.Fecha_reg = fecha_reg;
        this.usuario = usuario;
        this.cantidad=cantidad;
        this.total=total;
    }
    public ItemCarrito(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductoX getP() {
        return p;
    }

    public void setP(ProductoX p) {
        this.p = p;
    }

    public String getFecha_reg() {
        return Fecha_reg;
    }

    public void setFecha_reg(String fecha_reg) {
        Fecha_reg = fecha_reg;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
