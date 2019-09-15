package inca.jesus.trajesya.data.utils;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.data.modelo.Categoria;
import inca.jesus.trajesya.data.modelo.Producto;
import inca.jesus.trajesya.data.modelo.Promocion;
import inca.jesus.trajesya.data.modelo.Publicidad;
import inca.jesus.trajesya.data.modelo.Reserva;
import inca.jesus.trajesya.data.modelo.ReservaItem;
import inca.jesus.trajesya.data.modelo.SubCategoria;
import inca.jesus.trajesya.data.modelo.TipoComprobante;
import inca.jesus.trajesya.data.modelo.TipoPago;
import inca.jesus.trajesya.data.modelo.TipoTarjeta;
import inca.jesus.trajesya.data.modelo.UbicacionDireccion;

public class Constantes {
    public final static String VERSION="1.0";
    public final static String PATH="http://trajesya.com";
    public final static String LOGIN=PATH+"/App/Login/CLogin.php";
    public final static String GESTION=PATH+"/App/Gestion/CGestion.php";
    public final static String PATH_IMAGEN="http://admin.trajesya.com/assets/images/";


    public final static String TEXT_CONDICION_RESERVA="* La entrega de los pedidos estan sujetos a un plazo de 24 Horas realizada la reserva, sin embargo aceptando la Reserva URGENTE se realiza un recargo por la gestión de entrega dentro de las 24 Horas realizada la Reserva,y según lo coordinado con el counter.";
    public final static int INICIO_HORARIO=9;
    public final static int FIN_HORARIO=21;

    public final static int TIEMPO_PUBLICIDAD=20000;
    public static boolean CANTIDAD_PUBLICIDAD=false;

    public static boolean SECTOR_VACIO=true;
    public final static int CANTIDAD_MAX_STOCK=10;

    public final static List<Categoria> Base_Categorias_Todo = new ArrayList<>();
    public final static List<SubCategoria> Base_SubCategorias_Todo = new ArrayList<>();
    public final static List<Producto> Base_Producto_Todo = new ArrayList<>();

    public final static List<Promocion> Base_ListaPromociones = new ArrayList<>();
    public final static List<Publicidad> Base_ListaPublicidad = new ArrayList<>();
    public final static List<TipoPago> Base_ListaTipoPago =new ArrayList<>();
    public final static List<TipoTarjeta> Base_ListaTipoTarjeta =new ArrayList<>();
    public final static List<TipoComprobante> Base_ListaTipoComprobante =new ArrayList<>();

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



    public static Reserva RESERVA_LOCAL=new Reserva();
    public static List<ReservaItem> RESERVA_ITEMS=new ArrayList<>();
    public static UbicacionDireccion UBICACION_SELECT=new UbicacionDireccion();
    public static TipoPago TIPO_PAGO_SELECT=new TipoPago();
    public static TipoTarjeta TIPO_TARJETA_SELECT=new TipoTarjeta();
    public static TipoComprobante TIPO_COMPROBANTE_SELECT=new TipoComprobante();


    public static final String SUCCESS ="success";
    public static final String OPERACION="operacion";


    public static final String VariableDescripcion="Descripcion";
    public static final String VariableimagenPortada="imagenPortada";
    public static final String VariablefechaRegistro="fechaRegistro";
    public static final String VariablefechaUpdate="fechaUpdate";
    public static final String VariableGrupo_idGrupo="Grupo_idGrupo";
    public static final String VariableNombreGrupo="NombreGrupo";
    public static final String VariableEstado_idEstado="Estado_idEstado";
    public static final String VariableCategoria_idCategoria="Categoria_idCategoria";
    public static final String VariableSubCategoria_idSubCategoria="SubCategoria_idSubCategoria";
    public static final String VariableDepartamento="departamento";
    public static final String VariableProvincia="provincia";
    public static final String VariableDistrito="distrito";
    public static final String VariablePrecioVenta="precioVenta";
    public static final String VariableprecioAlquiler="precioAlquiler";


    public static  final String VariableDepartamento_idDepartamento="Departamento_idDepartamento";
    public static  final String VariableProvincia_idProvincia="Provincia_idProvincia";
    public static  final String VariableDistrito_idDistrito="Distrito_idDistrito";
    public static  final String VariableidProducto="idProducto";
    public static  final String VariableNombreProducto="NombreProducto";
    public static  final String VariableDescripcionProducto="DescripcionProducto";


}
