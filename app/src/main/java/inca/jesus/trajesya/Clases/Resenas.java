package inca.jesus.trajesya.Clases;

/**
 * Created by Jesus on 05/06/2017.
 */

public class Resenas {
    int id;
    ProductoX Producto;
    String Usuario;
    String fecha;
    float ranking_resena;
    String titulo;
    String resena_Descripcion;

    public Resenas(int id, ProductoX producto, String usuario, String fecha, float ranking_resena, String titulo, String resena_Descripcion) {
        this.id = id;
        Producto = producto;
        Usuario = usuario;
        this.fecha = fecha;
        this.ranking_resena = ranking_resena;
        this.titulo = titulo;
        this.resena_Descripcion = resena_Descripcion;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ProductoX getProducto() {
        return Producto;
    }
    public void setProducto(ProductoX producto) {
        Producto = producto;
    }
    public String getUsuario() {
        return Usuario;
    }
    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public float getRanking_resena() {
        return ranking_resena;
    }
    public void setRanking_resena(float ranking_resena) {
        this.ranking_resena = ranking_resena;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getResena_Descripcion() {
        return resena_Descripcion;
    }
    public void setResena_Descripcion(String resena_Descripcion) {
        this.resena_Descripcion = resena_Descripcion;
    }
}
