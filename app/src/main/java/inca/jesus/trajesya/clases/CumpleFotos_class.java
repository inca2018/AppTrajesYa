package inca.jesus.trajesya.clases;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 28/06/2017.
 */

public class CumpleFotos_class {
    public int id;
    public String nombre;
    public int[] fotos;
    public String usuario;
    public String fecha;


    public final static List<CumpleFotos_class> LISTA_GENERAL=new ArrayList<>();
    public final static List<Integer> LISTA_IMAG=new ArrayList<>();
    public final static List<Integer> LISTA_IMAG2=new ArrayList<>();
    public static int[]fotoss=new int[]{R.drawable.cel2,R.drawable.cel3,R.drawable.cel4,R.drawable.cel7};


    static {
        LISTA_IMAG.add(R.drawable.blu1);
        LISTA_IMAG.add(R.drawable.blu2);
        LISTA_IMAG.add(R.drawable.blu3);
        LISTA_IMAG.add(R.drawable.blu4);

        LISTA_IMAG2.add(R.drawable.cel2);
        LISTA_IMAG2.add(R.drawable.cel3);
        LISTA_IMAG2.add(R.drawable.cel4);
        LISTA_IMAG2.add(R.drawable.cel7);

        LISTA_GENERAL.add(new CumpleFotos_class(1,"CumpleFoto 1",fotoss,"Jesus Inca Cardenas","29/06/2017"));
        LISTA_GENERAL.add(new CumpleFotos_class(2,"CumpleFoto 2",fotoss,"Jesus Inca Cardenas","29/06/2017"));
        LISTA_GENERAL.add(new CumpleFotos_class(3,"CumpleFoto 3",fotoss,"Jesus Inca Cardenas","29/06/2017"));
        LISTA_GENERAL.add(new CumpleFotos_class(4,"CumpleFoto 4",fotoss,"Jesus Inca Cardenas","29/06/2017"));
        LISTA_GENERAL.add(new CumpleFotos_class(5,"CumpleFoto 5",fotoss,"Jesus Inca Cardenas","29/06/2017"));
        LISTA_GENERAL.add(new CumpleFotos_class(6,"CumpleFoto 6",fotoss,"Jesus Inca Cardenas","29/06/2017"));
    }

    public CumpleFotos_class(){

    }

    public CumpleFotos_class(int id, String nombre, int[] fotos,String usuario,String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fotos = fotos;
        this.usuario=usuario;
        this.fecha=fecha;
    }

    public void setFotos(int[] fotos) {
        this.fotos = fotos;
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

    public int[] getFotos() {
        return fotos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
