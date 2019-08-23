package inca.jesus.trajesya.data.modelo;

public class Categoria {
    int idCategoria;
    String nombreCategoria;
    String descripcionCategoria;
    String imagenCategoria;
    String fechaRegistro;
    String fechaUpdate;
    Grupo grupoCategoria;
    Estado estadoCategoria;

    private boolean select;
    private int color;
    private int color_nombre;

    public Categoria(){

    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public String getImagenCategoria() {
        return imagenCategoria;
    }

    public void setImagenCategoria(String imagenCategoria) {
        this.imagenCategoria = imagenCategoria;
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

    public Grupo getGrupoCategoria() {
        return grupoCategoria;
    }

    public void setGrupoCategoria(Grupo grupoCategoria) {
        this.grupoCategoria = grupoCategoria;
    }

    public Estado getEstadoCategoria() {
        return estadoCategoria;
    }

    public void setEstadoCategoria(Estado estadoCategoria) {
        this.estadoCategoria = estadoCategoria;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor_nombre() {
        return color_nombre;
    }

    public void setColor_nombre(int color_nombre) {
        this.color_nombre = color_nombre;
    }

}
