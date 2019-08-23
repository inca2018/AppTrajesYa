package inca.jesus.trajesya.clases;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.R;

public class TiposProductos {
    public int id;
    public String nombre;
    public boolean select;
    public int color;
    public int imagen;

    public TiposProductos(int id, String nombre, boolean select,int color, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.select = select;
        this.color=color;
        this.imagen = imagen;
    }
    public TiposProductos(int id,String nombre,boolean select,int color){
        this.id=id;
        this.nombre = nombre;
        this.select = select;
        this.color=color;
    }


    public static final List<TiposProductos> LIST_COLORES=new ArrayList<>();
    public static final List<TiposProductos> LIST_TAMA=new ArrayList<>();

    static {

        LIST_COLORES.add(new TiposProductos(1,"Plata",true,R.color.deseo,R.drawable.cel2));
        LIST_COLORES.add(new TiposProductos(2,"Oscuro",false,R.color.negro,R.drawable.cel2));
        LIST_COLORES.add(new TiposProductos(3,"Dorado",false,R.color.negro,R.drawable.cel2));
        LIST_COLORES.add(new TiposProductos(4,"Plomo",false,R.color.negro,R.drawable.cel2));
        LIST_COLORES.add(new TiposProductos(5,"Negro",false,R.color.negro,R.drawable.cel2));

        LIST_TAMA.add(new TiposProductos(6,"S",true,R.color.deseo));
        LIST_TAMA.add(new TiposProductos(7,"M",false,R.color.blanco));
        LIST_TAMA.add(new TiposProductos(8,"L",false,R.color.blanco));
        LIST_TAMA.add(new TiposProductos(9,"XL",false,R.color.blanco));
        LIST_TAMA.add(new TiposProductos(10,"XXL",false,R.color.blanco));


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
