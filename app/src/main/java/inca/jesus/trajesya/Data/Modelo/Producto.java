package inca.jesus.trajesya.Data.Modelo;

public class Producto {
    int idProducto;
    String nombreProducto;
    String descripcionProducto;
    String imagenProducto;
    String fechaRegistro;
    String fechaUpdate;
    Categoria categoriaProducto;
    SubCategoria subCategoriaProducto;
    UnidadTerritorial departamentoProducto;
    UnidadTerritorial provinciaProducto;
    UnidadTerritorial distritoProducto;
    Estado estadoProducto;


    public Producto(){

    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
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

    public Categoria getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(Categoria categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public SubCategoria getSubCategoriaProducto() {
        return subCategoriaProducto;
    }

    public void setSubCategoriaProducto(SubCategoria subCategoriaProducto) {
        this.subCategoriaProducto = subCategoriaProducto;
    }

    public UnidadTerritorial getDepartamentoProducto() {
        return departamentoProducto;
    }

    public void setDepartamentoProducto(UnidadTerritorial departamentoProducto) {
        this.departamentoProducto = departamentoProducto;
    }

    public UnidadTerritorial getProvinciaProducto() {
        return provinciaProducto;
    }

    public void setProvinciaProducto(UnidadTerritorial provinciaProducto) {
        this.provinciaProducto = provinciaProducto;
    }

    public UnidadTerritorial getDistritoProducto() {
        return distritoProducto;
    }

    public void setDistritoProducto(UnidadTerritorial distritoProducto) {
        this.distritoProducto = distritoProducto;
    }

    public Estado getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(Estado estadoProducto) {
        this.estadoProducto = estadoProducto;
    }
}
