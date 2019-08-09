package inca.jesus.trajesya.Data.Utils;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.Data.Modelo.Categoria;
import inca.jesus.trajesya.Data.Modelo.Producto;
import inca.jesus.trajesya.Data.Modelo.Promocion;

public class Constantes {
    public final static String PATH="http://trajesya.com";
    public final static String LOGIN=PATH+"/App/Login/CLogin.php";
    public final static String GESTION=PATH+"/App/Gestion/CGestion.php";
    public final static String PATH_IMAGEN="http://admin.trajesya.com/assets/images/";

    public final static List<Categoria> Base_Categorias = new ArrayList<>();

    public final static List<Promocion> Base_ListaPromociones = new ArrayList<>();

    public final static List<Producto> Base_ListaProductoNuevo = new ArrayList<>();
    public final static List<Producto> Base_ListaProductoMasVisto = new ArrayList<>();
    public final static List<Producto> Base_ListaProductoMasAlquilados = new ArrayList<>();
    public final static List<Producto> Base_ListaProductoTendencias = new ArrayList<>();


}
