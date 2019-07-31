package inca.jesus.trajesya.Clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 05/06/2017.
 */

public class ListResenas {

    ArrayList<Resenas> lista_resenas;

    public static final List<Resenas> ALMACEN_RESENAS = new ArrayList<Resenas>();

    public ListResenas(ArrayList<Resenas> lista_resenas) {
        this.lista_resenas = lista_resenas;
    }
    public ListResenas(){

     }
    public void Agregar_lista(Resenas r){
        lista_resenas.add(r);
    }
    public void Eliminar(Resenas  r){
        lista_resenas.remove(r);
    }

    public ArrayList<Resenas> getLista_resenas() {
        return lista_resenas;
    }
    public void setLista_resenas(ArrayList<Resenas> lista_resenas) {
        this.lista_resenas = lista_resenas;
    }

    public Integer recuTotal(){
        int total;
        total=lista_resenas.size();
        return total;
    }

}
