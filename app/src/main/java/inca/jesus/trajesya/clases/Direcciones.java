package inca.jesus.trajesya.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 19/06/2017.
 */

public class Direcciones {
    String nombre,apellido;
    int celular1,celular2;
    String direccion;
    String nro_lote;
    String Dep_int;
    String Urbanizacion;
    String Referencia;

    public static final List<String> TipoDomicilo=new ArrayList<String>();

    static{
        TipoDomicilo.add("Casa");
        TipoDomicilo.add("Centro");
        TipoDomicilo.add("Condominio");
        TipoDomicilo.add("Departamento");
        TipoDomicilo.add("Galeria");
        TipoDomicilo.add("Local");
        TipoDomicilo.add("Mercado");
        TipoDomicilo.add("Oficina");
        TipoDomicilo.add("Otro");
        TipoDomicilo.add("Residencial");
    }
}
