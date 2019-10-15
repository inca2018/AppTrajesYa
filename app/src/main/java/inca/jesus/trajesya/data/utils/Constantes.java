package inca.jesus.trajesya.data.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.R;
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

    public final static int TIEMPO_PUBLICIDAD=10000;
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
    public final static List<Producto> Base_ListaProductosTopTradicionales=new ArrayList<>();//OK INCAA
    public final static List<Producto> Base_ListaProductosTopOtros=new ArrayList<>();//OK  INCAA


    public final static List<Producto> Base_ListaProductoNuevo = new ArrayList<>();//OK INCAA
    public final static List<Producto> Base_ListaProductoMasVisto = new ArrayList<>();//OK INCAA
    public final static List<Producto> Base_ListaProductoMasAlquilados = new ArrayList<>();//FALTA  OK INCA
    public final static List<Producto> Base_ListaProductoTendencias = new ArrayList<>();//FALTA


    public  static List<Producto> PRODUCTOS_BUSCADOS=new ArrayList<>();


    public final static String TERMINOS_CONDICIONES="TERMINOS Y CONDICIONES\n" +
            "Bienvenidos a TRAJESYA.COM la aplicación donde usted podrá alquilar una variedad de prendas de vestir en la facilidad de su hogar, en adelante le presentamos los términos y condiciones de TRAJES YA.COM\n" +
            "ART.1- El cliente autoriza el uso de sus datos para la gestión de pedidos, promociones, ofertas, concursos, que realice o ejecute TRAJESYA.COM\n" +
            "ART.2- El usuario asume totalmente la responsabilidad por el mantenimiento de la confidencialidad del nombre y su clave secreta registrada en la aplicación, la cual le permite efectuar compras, solicitar servicios y obtener información. Dicha clave es de uso personal y su entrega a terceros por parte del usuario, exime de responsabilidad alguna a TRAJESYA.COM\n" +
            "ART.3- El usuario gozará de los derechos que le reconoce la legislación sobre protección al consumidor vigente en el territorio de Perú, y además los que se le otorgan en estos términos y condiciones. El usuario dispondrá en todo momento de los derechos de información, rectificación y cancelación de los datos personales, conforme lo establece la Ley Nº29733, Ley de protección de datos personales y podrá ejercerlos de acuerdo con lo indicado en la Política de Privacidad.\n" +
            "La sola visita de esta aplicacion, en el cual se ofrecen determinados bienes y el acceso a determinados servicios, no impone al consumidor obligación alguna, a menos que haya aceptado en forma inequívoca las condiciones ofrecidas por trajes ya.com, en la forma indicada en el aplicacion y en estos Términos y Condiciones.\n" +
            "ART.4- Para acceder al servicio de delivery en el aplicacion debe ingresar la dirección en donde el Usuario desea que le llegue su pedido. La dirección del Usuario se asociará ala central de pedidos de traesya.com, y le mostrará los productos que esa tienda ofrece en ese momento. Al momento de ingresar la dirección y ser asociado, la aplicacion podría mostrar restricciones para ser aceptadas por el usuario, y continuar el proceso de compra. \n" +
            "Estas restricciones son:\n" +
            "1)\tZona de reparto: la aplicación valida que la dirección ingresada pertenece a la zona de reparto de trajesya.com. Si la dirección del usuario no correspondiera a una zona de reparto, sé dará por cancelado el pedido.\n" +
            "2)\tModo de pago: La aplicación indica al usuario si la tienda no cuenta con algún medio de pago (POS) en ese momento.\n" +
            "3)\tTienda cerrada: La aplicacion indica al usuario si los horarios de la central se encuentran abiertos.\n" +
            "ART.5- Los pedidos pueden realizarse en cualquier momento, pero estos serán atendidos recién en el horario de atención al cliente mencionado en la aplicación(10am-4pm).\n" +
            "ART.6- Los productos y servicios ofrecidos en esta APP, salvo que se señale una forma diferente para casos particulares u ofertas de determinados bienes o servicios, sólo pueden ser pagados con: (i) efectivo o (ii) tarjeta de crédito o débito (Visa o MasterCard). En ambas modalidades, el pago se realizará al momento en que se haga entrega del producto.\n" +
            "En el caso de haber elegido la opción \"pago con efectivo\": Al momento de realizar el check out, se dispone de un espacio para colocar el monto en efectivo con el que se efectuará el pago. De esa manera, la persona que realizará la entrega podrá entregar el vuelto correspondiente, de ser el caso. TRAJESYA.COM NO asume responsabilidad sobre la entrega de vuelto adecuado si el Usuario no ingresó el monto correcto con el que efectuará el pago.\n"+"En el caso de haber elegido la opción \"pago tarjeta de crédito/débito\": Usted podrá indicar si pagará con tarjeta Visa o MasterCard, con la finalidad que la persona que realiza la entrega pueda llevar el POS correspondiente al tipo de \n" +
            "tarjeta. TRAJESYA.COM NO asume responsabilidad sobre el pago con POS, si el Cliente no eligió la tarjeta correcta en LA APP.\n" +
            "La app de TRAJESYA.COM no almacena información sobre las tarjetas.\n" +
            "\n" +
            "ART.7- Las fotos publicadas en la app son fotos e imágenes referenciales.\n" +
            "ART.8-En el caso de pérdida, daño de los productos alquilados por trajes ya.com, el cliente se responsabiliza de lo sucedido mientras este se encuentre en su posesión para ello será resarcido el total del producto afectado, el cual estará adjuntado. \n" +
            "ART.9- El cliente tendrá el producto como máximo 24 horas una vez entregado el pedido, en el caso este sobrepase el tiempo estimado abonará un adicional por día transcurrido (de no haberse hecho la coordinación para el recojo).\n";
    public final static String POLITICA_ALQUILER="Bienvenidos a TRAJESYA.COM la aplicación donde usted podrá alquilar una variedad de prendas de vestir en la facilidad de su hogar, en adelante le presentamos los términos y condiciones de TRAJES YA.COM\n" +
            "ART.1- El cliente autoriza el uso de sus datos para la gestión de pedidos, promociones, ofertas, concursos, que realice o ejecute TRAJESYA.COM\n" +
            "ART.2- El usuario asume totalmente la responsabilidad por el mantenimiento de la confidencialidad del nombre y su clave secreta registrada en la aplicación, la cual le permite efectuar compras, solicitar servicios y obtener información. Dicha clave es de uso personal y su entrega a terceros por parte del usuario, exime de responsabilidad alguna a TRAJESYA.COM\n" +
            "ART.3- El usuario gozará de los derechos que le reconoce la legislación sobre protección al consumidor vigente en el territorio de Perú, y además los que se le otorgan en estos términos y condiciones. El usuario dispondrá en todo momento de los derechos de información, rectificación y cancelación de los datos personales, conforme lo establece la Ley Nº29733, Ley de protección de datos personales y podrá ejercerlos de acuerdo con lo indicado en la Política de Privacidad.\n";
    public final static String PRIVACIDAD_CONFIDENCIALIDAD="La sola visita de esta aplicacion, en el cual se ofrecen determinados bienes y el acceso a determinados servicios, no impone al consumidor obligación alguna, a menos que haya aceptado en forma inequívoca las condiciones ofrecidas por trajes ya.com, en la forma indicada en el aplicacion y en estos Términos y Condiciones.\n" +
            "ART.4- Para acceder al servicio de delivery en el aplicacion debe ingresar la dirección en donde el Usuario desea que le llegue su pedido. La dirección del Usuario se asociará ala central de pedidos de traesya.com, y le mostrará los productos que esa tienda ofrece en ese momento. Al momento de ingresar la dirección y ser asociado, la aplicacion podría mostrar restricciones para ser aceptadas por el usuario, y continuar el proceso de compra. \n" +
            "Estas restricciones son:\n" +
            "1)\tZona de reparto: la aplicación valida que la dirección ingresada pertenece a la zona de reparto de trajesya.com. Si la dirección del usuario no correspondiera a una zona de reparto, sé dará por cancelado el pedido.\n" +
            "2)\tModo de pago: La aplicación indica al usuario si la tienda no cuenta con algún medio de pago (POS) en ese momento.\n" +
            "3)\tTienda cerrada: La aplicacion indica al usuario si los horarios de la central se encuentran abiertos.\n";
    public final static String PREGUNTAS_PRECUENTES=" ¿Quienes Somos?\n ¿Donde nos Ubican? \n ¿Aq que nos dedicamos?";

    public static List<ReservaItem> RESERVA_ITEMS=new ArrayList<>();
    public static UbicacionDireccion UBICACION_SELECT=new UbicacionDireccion();
    public static TipoPago TIPO_PAGO_SELECT=new TipoPago();
    public static TipoTarjeta TIPO_TARJETA_SELECT=new TipoTarjeta();
    public static TipoComprobante TIPO_COMPROBANTE_SELECT=new TipoComprobante();
    public static String TIEMPO;

    public static Reserva RESERVA_DETALLE;


    public static final String SUCCESS ="success";
    public static final String OPERACION="operacion";

    public static String FECHA_RESERVA;
    public static String HORA_RESERVA;


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
