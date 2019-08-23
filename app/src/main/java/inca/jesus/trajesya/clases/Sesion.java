package inca.jesus.trajesya.clases;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 12/06/2017.
 */

public class Sesion {

    public String id;
    public Uri foto;
    public String nombre;
    public String correo;
    public String pass;

    public static final Sesion USUARIO=new Sesion();
    public static final List<Sesion> LISTA_USUARIOS = new ArrayList<Sesion>();


    public Sesion(String id,Uri foto, String nombre, String correo,String pass) {
        this.id = id;
        this.foto=foto;
        this.nombre = nombre;
        this.correo = correo;
        this.pass=pass;
    }


    public Sesion(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Uri getFoto() {
        return foto;
    }

    public void setFoto(Uri foto) {
        this.foto = foto;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
