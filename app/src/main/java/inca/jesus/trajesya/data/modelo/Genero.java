package inca.jesus.trajesya.data.modelo;

public class Genero {
    int idGenero;
    String nombreGenero;
    String simboloGenero;
    String fechaRegistro;
    String fechaUpdate;
    Estado estadoMedida;

    public boolean select;
    public int color;

    public Genero(){

    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public String getSimboloGenero() {
        return simboloGenero;
    }

    public void setSimboloGenero(String simboloGenero) {
        this.simboloGenero = simboloGenero;
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

    public Estado getEstadoMedida() {
        return estadoMedida;
    }

    public void setEstadoMedida(Estado estadoMedida) {
        this.estadoMedida = estadoMedida;
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
}
