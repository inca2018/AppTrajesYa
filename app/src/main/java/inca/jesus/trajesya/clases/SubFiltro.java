package inca.jesus.trajesya.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.R;

public class SubFiltro implements Parcelable {
    public int id;
    public int id_filtro;
    public String nombre_sub_filtro;
    public boolean select;
    public int color_nombre;

    public SubFiltro(int id, int id_filtro, String nombre_sub_filtro, boolean select, int color_nombre) {
        this.id = id;
        this.id_filtro = id_filtro;
        this.nombre_sub_filtro = nombre_sub_filtro;
        this.select = select;
        this.color_nombre = color_nombre;
    }

    public   SubFiltro(){
    }

    public static final List<SubFiltro> LIST_SUB_FILTRO=new ArrayList<SubFiltro>();
    public static final List<SubFiltro> ITEM_SUB_FILTRO=new ArrayList<SubFiltro>();

    static{

        LIST_SUB_FILTRO.add(new SubFiltro(1,1,"Precio más Bajo",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(2,1,"Precio más Alto",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(3,2,"sub Filtro 1",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(4,2,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(5,2,"sub Filtro 3",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(6,2,"sub Filtro 4",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(7,2,"sub Filtro 5",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(8,3,"sub Filtro 1",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(9,3,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(10,3,"sub Filtro 3",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(11,3,"sub Filtro 4",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(12,4,"sub Filtro 1",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(13,4,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(14,4,"sub Filtro 3",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(15,4,"sub Filtro 4",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(16,4,"sub Filtro 5",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(17,5,"sub Filtro 1",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(18,5,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(19,5,"sub Filtro 3",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(20,6,"sub Filtro 1",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(21,6,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(22,7,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(23,7,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(24,7,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(25,7,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(26,7,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(27,7,"sub Filtro 2",false, R.color.negro));
        LIST_SUB_FILTRO.add(new SubFiltro(28,7,"sub Filtro 2",false, R.color.negro));

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_filtro() {
        return id_filtro;
    }

    public void setId_filtro(int id_filtro) {
        this.id_filtro = id_filtro;
    }

    public String getNombre_sub_filtro() {
        return nombre_sub_filtro;
    }

    public void setNombre_sub_filtro(String nombre_sub_filtro) {
        this.nombre_sub_filtro = nombre_sub_filtro;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getColor_nombre() {
        return color_nombre;
    }

    public void setColor_nombre(int color_nombre) {
        this.color_nombre = color_nombre;
    }

    protected SubFiltro(Parcel in) {
        id = in.readInt();
        id_filtro = in.readInt();
        nombre_sub_filtro = in.readString();
        select = in.readByte() != 0x00;
        color_nombre = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(id_filtro);
        dest.writeString(nombre_sub_filtro);
        dest.writeByte((byte) (select ? 0x01 : 0x00));
        dest.writeInt(color_nombre);
    }

    @SuppressWarnings("unused")
    public static final Creator<SubFiltro> CREATOR = new Creator<SubFiltro>() {
        @Override
        public SubFiltro createFromParcel(Parcel in) {
            return new SubFiltro(in);
        }

        @Override
        public SubFiltro[] newArray(int size) {
            return new SubFiltro[size];
        }
    };
}