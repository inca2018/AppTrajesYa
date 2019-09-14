package inca.jesus.trajesya.data.modelo;

public class UbicacionDireccion {
    int idUbicacionDireccion;
    String direccionEntrega;
    String referenciaDireccion;
    UnidadTerritorial distrito;
    Usuario usuarioUbicacionDireccion;
    String fechaRegistro;
    String fechaUpdate;
    boolean Select;

    public UbicacionDireccion(){
    }

    public int getIdUbicacionDireccion() {
        return idUbicacionDireccion;
    }

    public void setIdUbicacionDireccion(int idUbicacionDireccion) {
        this.idUbicacionDireccion = idUbicacionDireccion;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getReferenciaDireccion() {
        return referenciaDireccion;
    }

    public void setReferenciaDireccion(String referenciaDireccion) {
        this.referenciaDireccion = referenciaDireccion;
    }

    public UnidadTerritorial getDistrito() {
        return distrito;
    }

    public void setDistrito(UnidadTerritorial distrito) {
        this.distrito = distrito;
    }

    public Usuario getUsuarioUbicacionDireccion() {
        return usuarioUbicacionDireccion;
    }

    public void setUsuarioUbicacionDireccion(Usuario usuarioUbicacionDireccion) {
        this.usuarioUbicacionDireccion = usuarioUbicacionDireccion;
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

    public boolean isSelect() {
        return Select;
    }

    public void setSelect(boolean select) {
        Select = select;
    }
}
