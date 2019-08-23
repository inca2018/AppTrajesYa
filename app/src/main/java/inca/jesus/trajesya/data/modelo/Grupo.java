package inca.jesus.trajesya.data.modelo;

public class Grupo {
    int idGrupo;
    String descripcionGrupo;
    String fechaRegistro;
    String fechaUpdate;
    Estado estadoGrupo;

    public Grupo(){

    }
    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getDescripcionGrupo() {
        return descripcionGrupo;
    }

    public void setDescripcionGrupo(String descripcionGrupo) {
        this.descripcionGrupo = descripcionGrupo;
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

    public Estado getEstadoGrupo() {
        return estadoGrupo;
    }

    public void setEstadoGrupo(Estado estadoGrupo) {
        this.estadoGrupo = estadoGrupo;
    }
}
