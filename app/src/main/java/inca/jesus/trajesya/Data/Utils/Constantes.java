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
}
