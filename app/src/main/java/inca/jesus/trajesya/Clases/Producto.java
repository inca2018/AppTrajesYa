package inca.jesus.trajesya.Clases;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    int idProducto;
    String NombreProducto;
    String ImagenProducto;
    double PrecioProducto;
    String Proveedor;
    double Descuento;



    public static List<Producto> BUSCADOR = new ArrayList<Producto>();

    public Producto(){

    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public String getImagenProducto() {
        return ImagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        ImagenProducto = imagenProducto;
    }

    public static List<Producto> getBUSCADOR() {
        return BUSCADOR;
    }

    public static void setBUSCADOR(List<Producto> BUSCADOR) {
        Producto.BUSCADOR = BUSCADOR;
    }

    public double getPrecioProducto() {
        return PrecioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        PrecioProducto = precioProducto;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String proveedor) {
        Proveedor = proveedor;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double descuento) {
        Descuento = descuento;
    }
}
