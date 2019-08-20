package inca.jesus.trajesya.Data.Utils;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.Data.Modelo.Categoria;
import inca.jesus.trajesya.Data.Modelo.Producto;
import inca.jesus.trajesya.Data.Modelo.Promocion;
import inca.jesus.trajesya.Data.Modelo.Publicidad;
import inca.jesus.trajesya.Data.Modelo.SubCategoria;

public class Constantes {
    public final static String VERSION="1.0";
    public final static String PATH="http://trajesya.com";
    public final static String LOGIN=PATH+"/App/Login/CLogin.php";
    public final static String GESTION=PATH+"/App/Gestion/CGestion.php";
    public final static String PATH_IMAGEN="http://admin.trajesya.com/assets/images/";

    public final static List<Categoria> Base_Categorias_Todo = new ArrayList<>();
    public final static List<SubCategoria> Base_SubCategorias_Todo = new ArrayList<>();
    public final static List<Producto> Base_Producto_Todo = new ArrayList<>();

    public final static List<Promocion> Base_ListaPromociones = new ArrayList<>();
    public final static List<Publicidad> Base_ListaPublicidad = new ArrayList<>();



    public final static List<Producto> Base_ListaProductoRecientes= new ArrayList<>();// FALTA
    public final static List<Producto> Base_ListaProductoPromociones=new ArrayList<>();//OK
    public final static List<Producto> Base_ListaProductosTopTradicionales=new ArrayList<>();//OK
    public final static List<Producto> Base_ListaProductosTopOtros=new ArrayList<>();//OK


    public final static List<Producto> Base_ListaProductoNuevo = new ArrayList<>();//OK
    public final static List<Producto> Base_ListaProductoMasVisto = new ArrayList<>();//OK
    public final static List<Producto> Base_ListaProductoMasAlquilados = new ArrayList<>();//FALTA
    public final static List<Producto> Base_ListaProductoTendencias = new ArrayList<>();//FALTA


    public  static List<Producto> PRODUCTOS_BUSCADOS=new ArrayList<>();


    public final static String TERMINOS_CONDICIONES="Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.";
    public final static String POLITICA_ALQUILER="Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.";
    public final static String PRIVACIDAD_CONFIDENCIALIDAD="Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.";
    public final static String PREGUNTAS_PRECUENTES="Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.";


}
