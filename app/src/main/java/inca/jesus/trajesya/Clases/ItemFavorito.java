package inca.jesus.trajesya.Clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 08/06/2017.
 */

public class ItemFavorito {
    int id;
    ProductoX p;
    String usuario;
    String fecha;


    public ItemFavorito(int id, ProductoX p,String usuario,String fecha) {
        this.id = id;
        this.p = p;
        this.usuario=usuario;
        this.fecha=fecha;
    }

    public ItemFavorito(){

    }

    public static final List<ItemFavorito> ListaFavoritos= new ArrayList<ItemFavorito>();


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
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
