package inca.jesus.trajesya.data.modelo;

public class Galeria {
    int idGaleria;
    String nombreGaleria;
    String imagenGaleria;
    String fechaRegistro;
    String fechaUpdate;
    Producto productoGaleria;
    Estado estadoGaleria;

    public Galeria(){

    }

    public int getIdGaleria() {
        return idGaleria;
    }

    public void setIdGaleria(int idGaleria) {
        this.idGaleria = idGaleria;
    }

    public String getNombreGaleria() {
        return nombreGaleria;
    }

    public void setNombreGaleria(String nombreGaleria) {
        this.nombreGaleria = nombreGaleria;
    }

    public String getImagenGaleria() {
        return imagenGaleria;
    }

    public void setImagenGaleria(String imagenGaleria) {
        this.imagenGaleria = imagenGaleria;
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

    public Producto getProductoGaleria() {
        return productoGaleria;
    }

    public void setProductoGaleria(Producto productoGaleria) {
        this.productoGaleria = productoGaleria;
    }

    public Estado getEstadoGaleria() {
        return estadoGaleria;
    }

    public void setEstadoGaleria(Estado estadoGaleria) {
        this.estadoGaleria = estadoGaleria;
    }
}
