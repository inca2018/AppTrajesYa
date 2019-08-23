package inca.jesus.trajesya.clases;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 26/06/2017.
 */

public class Filtro {
    int id;
    String nombre_filtro;
    boolean select;
    int color;
    int color_nombre;
    boolean visible;


    public Filtro(int id, String nombre_filtro, boolean select, int color, int color_nombre,boolean visible) {
        this.id = id;
        this.nombre_filtro = nombre_filtro;
        this.select = select;
        this.color = color;
        this.color_nombre = color_nombre;
        this.visible=visible;
    }

    public  Filtro(){

    }

    public static final List<Filtro> LISTA_FILTRO=new ArrayList<Filtro>();
    static {
        LISTA_FILTRO.add(new Filtro(1,"Ordenar por ",false, R.color.blanco,R.color.negro,false));
        LISTA_FILTRO.add(new Filtro(2,"Filtro 1 ",false, R.color.blanco,R.color.negro,false));
        LISTA_FILTRO.add(new Filtro(3,"Filtro 2 ",false, R.color.blanco,R.color.negro,false));
        LISTA_FILTRO.add(new Filtro(4,"Filtro 3 ",false, R.color.blanco,R.color.negro, false));
        LISTA_FILTRO.add(new Filtro(5,"Filtro 4 ",false, R.color.blanco,R.color.negro, false));
        LISTA_FILTRO.add(new Filtro(6,"Filtro 5 ",false, R.color.blanco,R.color.negro, false));
        LISTA_FILTRO.add(new Filtro(7,"Filtro 6 ",false, R.color.blanco,R.color.negro, false));

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_filtro() {
        return nombre_filtro;
    }

    public void setNombre_filtro(String nombre_filtro) {
        this.nombre_filtro = nombre_filtro;
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

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getColor_nombre() {
        return color_nombre;
    }

    public void setColor_nombre(int color_nombre) {
        this.color_nombre = color_nombre;
    }
}
