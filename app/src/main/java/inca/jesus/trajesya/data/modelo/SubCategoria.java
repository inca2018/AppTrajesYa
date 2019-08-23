package inca.jesus.trajesya.data.modelo;

public class SubCategoria {
    int idSubCategoria;
    String nombreSubCategoria;
    String descripcionSubCategoria;
    String imagenSubCategorias;
    String fechaRegistro;
    String fechaUpdate;
    Categoria categoriaSubCategoria;
    Estado estadoSubCategoria;

    private boolean select;

    public SubCategoria(){

    }

    public int getIdSubCategoria() {
        return idSubCategoria;
    }

    public void setIdSubCategoria(int idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

    public String getNombreSubCategoria() {
        return nombreSubCategoria;
    }

    public void setNombreSubCategoria(String nombreSubCategoria) {
        this.nombreSubCategoria = nombreSubCategoria;
    }

    public String getDescripcionSubCategoria() {
        return descripcionSubCategoria;
    }

    public void setDescripcionSubCategoria(String descripcionSubCategoria) {
        this.descripcionSubCategoria = descripcionSubCategoria;
    }

    public String getImagenSubCategorias() {
        return imagenSubCategorias;
    }

    public void setImagenSubCategorias(String imagenSubCategorias) {
        this.imagenSubCategorias = imagenSubCategorias;
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

    public Categoria getCategoriaSubCategoria() {
        return categoriaSubCategoria;
    }

    public void setCategoriaSubCategoria(Categoria categoriaSubCategoria) {
        this.categoriaSubCategoria = categoriaSubCategoria;
    }

    public Estado getEstadoSubCategoria() {
        return estadoSubCategoria;
    }

    public void setEstadoSubCategoria(Estado estadoSubCategoria) {
        this.estadoSubCategoria = estadoSubCategoria;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
