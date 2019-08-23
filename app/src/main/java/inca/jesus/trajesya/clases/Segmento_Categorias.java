package inca.jesus.trajesya.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 21/06/2017.
 */

public class Segmento_Categorias implements Parcelable {
    public int id;
    public int id_categoria;
    private String nombre;
    private int idDrawable;
    private boolean select;

    public static final List<Segmento_Categorias> SUBCATEGORIA = new ArrayList<Segmento_Categorias>();

    public Segmento_Categorias(){

    }
    public Segmento_Categorias(int id, int id_categoria, String nombre, int idDrawable, boolean select) {
        this.id = id;
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.select=select;
    }

    static{

        SUBCATEGORIA.add(new Segmento_Categorias(1,1,"TV Y VIDEO", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(2,1,"AUDIO", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(3,1,"ENTRETENIMIENTO", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(4,1,"CAMARAS Y LENTES", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(5,1,"SEGURIDAD", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(6,1,"TELEFONIA", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(7,1,"OTROS", R.drawable.cat_3,false));

        SUBCATEGORIA.add(new Segmento_Categorias(8,2,"PORTATILES", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(9,2,"ESCRITORIO", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(10,2,"ACCESORIOS", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(11,2,"IMPRESIÓN", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(12,2,"NETWORKING", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(13,2,"SOFTWARE", R.drawable.cat_3,false));

        SUBCATEGORIA.add(new Segmento_Categorias(14,3,"CELULARES Y SMARTPHONE", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(15,3,"TABLETS Y IPADS", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(16,3,"ACCESORIOS", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(17,3,"SMARTWATCHES Y GADGETS", R.drawable.cat_3,false));

        SUBCATEGORIA.add(new Segmento_Categorias(18,4,"REFRIGERACIÓN", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(18,4,"COCINAS Y HORNOS", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(20,4,"LAVADO Y SECADO", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(21,4,"CLIMATIZACIÓN", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(22,4,"ELECTRODOMESTICOS", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(23,4,"CUIDADO PERSONAL", R.drawable.cat_3,false));

        SUBCATEGORIA.add(new Segmento_Categorias(24,5,"COCINA", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(25,5,"COMEDOR Y BAR", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(26,5,"PARRILLA", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(27,5,"BAÑO", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(28,5,"DECORACIÓN", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(29,5,"ILUMINACIÓN", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(30,5,"LIMPIEZA", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(31,5,"HERRAMIENTAS", R.drawable.cat_3,false));

        SUBCATEGORIA.add(new Segmento_Categorias(32,6,"SALA", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(33,6,"COMEDOR Y BAR", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(34,6,"COCINA", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(35,6,"DORMITORIO", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(36,6,"OFICINA", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(37,6,"TERRAZAS", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(38,6,"ORGANIZADORES", R.drawable.cat_3,false));

        SUBCATEGORIA.add(new Segmento_Categorias(39,7,"BOX TARIMA", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(40,7,"BOX SPRING", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(41,7,"COLCHONES", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(42,7,"JUEGOS DE DORMITORIO", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(43,7,"MUEBLES DE DORMITORIO", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(44,7,"INFANTIL", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(45,7,"ROPA DE CAMA", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(46,7,"BAÑO", R.drawable.cat_3,false));

        SUBCATEGORIA.add(new Segmento_Categorias(47,8,"MUJER", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(48,8,"HOMBRE", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(49,8,"CALZADO MUJER", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(50,8,"CALZADO HOMBRE", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(51,8,"RELOJES Y LENTES", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(52,8,"CARTERAS,BILLETERAS,MALETAS Y MOCHILAS", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(53,8,"JOYERÍA", R.drawable.cat_3,false));
        SUBCATEGORIA.add(new Segmento_Categorias(54,8,"MÁS", R.drawable.cat_3,false));

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_categoria() {
        return id_categoria;
    }
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getIdDrawable() {
        return idDrawable;
    }
    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }
    public boolean isSelect() {
        return select;
    }
    public void setSelect(boolean select) {
        this.select = select;
    }


    protected Segmento_Categorias(Parcel in) {
        id = in.readInt();
        id_categoria = in.readInt();
        nombre = in.readString();
        idDrawable = in.readInt();
        select = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(id_categoria);
        dest.writeString(nombre);
        dest.writeInt(idDrawable);
        dest.writeByte((byte) (select ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Creator<Segmento_Categorias> CREATOR = new Creator<Segmento_Categorias>() {
        @Override
        public Segmento_Categorias createFromParcel(Parcel in) {
            return new Segmento_Categorias(in);
        }

        @Override
        public Segmento_Categorias[] newArray(int size) {
            return new Segmento_Categorias[size];
        }
    };
}