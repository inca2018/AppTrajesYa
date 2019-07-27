package inca.jesus.trajesya.Clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 01/06/2017.
 */
public class Segmento_Productos implements Parcelable {
    private int id;
    private String nombre;
    private int idDrawable;
    private boolean select;
    private int color;
    private int color_nombre;

    public Segmento_Productos(int id, String nombre, int idDrawable, boolean select,int color,int color_nombre) {
        this.id=id;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.select=select;
        this.color=color;
        this.color_nombre=color_nombre;
    }
    public Segmento_Productos(){
    }


    public static final List<Segmento_Productos> CATEGORIAs = new ArrayList<Segmento_Productos>();
    public static final List<Segmento_Productos> CATEGORIAs2 = new ArrayList<Segmento_Productos>();



    static {
        CATEGORIAs.add(new Segmento_Productos(1,"TECNOLOGICA",R.drawable.cate_1,true,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(2,"COMPUTACION",R.drawable.cate_2,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(3,"CELULARES Y TABLESTS",R.drawable.cate_3,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(4,"ELECTRO HOGAR",R.drawable.cate_4,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(5,"HOGAR Y HERRAMIENTAS",R.drawable.cate_5,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(6,"MUEBLES",R.drawable.cate_6,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(7,"DORMITORIO",R.drawable.cate_7,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(8,"MODA  Y ACCESORIOS",R.drawable.cate_8,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(9,"CUIDADO PERSONAL, BELLEZA Y SALUD",R.drawable.cate_9,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(10,"DEPORTES  Y ENTRETENIMIENTOS",R.drawable.cate_10,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(11,"KIDS",R.drawable.cate_11,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(12,"AUTOMOTRIZ",R.drawable.cate_12,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(13,"MASCOTAS",R.drawable.cate_13,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(14,"LICORES",R.drawable.cate_14,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(15,"SERVICIOS",R.drawable.cate_15,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(16,"GASTRONOMIA",R.drawable.cate_16,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(17,"BELLEZA Y BIENESTAR",R.drawable.cate_17,false,R.color.blanco,R.color.blanco));
        CATEGORIAs.add(new Segmento_Productos(18,"VIAJES",R.drawable.cate_18,false,R.color.blanco,R.color.blanco));

        CATEGORIAs2.add(new Segmento_Productos(19,"",R.drawable.nuevo1,false,R.color.blanco,R.color.blanco));
        CATEGORIAs2.add(new Segmento_Productos(20,"",R.drawable.nuevo2,false,R.color.blanco,R.color.blanco));
        CATEGORIAs2.add(new Segmento_Productos(21,"",R.drawable.nuevo3,false,R.color.blanco,R.color.blanco));
        CATEGORIAs2.add(new Segmento_Productos(22,"",R.drawable.nuevo4,false,R.color.blanco,R.color.blanco));
        CATEGORIAs2.add(new Segmento_Productos(23,"",R.drawable.nuevo5,false,R.color.blanco,R.color.blanco));
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    protected Segmento_Productos(Parcel in) {
        id = in.readInt();
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
        dest.writeString(nombre);
        dest.writeInt(idDrawable);
        dest.writeByte((byte) (select ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Creator<Segmento_Productos> CREATOR = new Creator<Segmento_Productos>() {
        @Override
        public Segmento_Productos createFromParcel(Parcel in) {
            return new Segmento_Productos(in);
        }

        @Override
        public Segmento_Productos[] newArray(int size) {
            return new Segmento_Productos[size];
        }
    };
}