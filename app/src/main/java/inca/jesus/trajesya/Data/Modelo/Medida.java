package inca.jesus.trajesya.Data.Modelo;

public class Medida {
    int idMedida;
    String nombreMedida;
    String simboloMedida;
    String fechaRegistro;
    String fechaUpdate;
    Estado estadoMedida;

    public boolean select;
    public int color;

    public Medida(){
    }

    public int getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(int idMedida) {
        this.idMedida = idMedida;
    }

    public String getNombreMedida() {
        return nombreMedida;
    }

    public void setNombreMedida(String nombreMedida) {
        this.nombreMedida = nombreMedida;
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

    public String getSimboloMedida() {
        return simboloMedida;
    }

    public void setSimboloMedida(String simboloMedida) {
        this.simboloMedida = simboloMedida;
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
