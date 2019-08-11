package inca.jesus.trajesya.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inca.jesus.trajesya.Data.Conexion.VolleySingleton;
import inca.jesus.trajesya.Data.Modelo.Categoria;
import inca.jesus.trajesya.Data.Modelo.Estado;
import inca.jesus.trajesya.Data.Modelo.Galeria;
import inca.jesus.trajesya.Data.Modelo.Grupo;
import inca.jesus.trajesya.Data.Modelo.Medida;
import inca.jesus.trajesya.Data.Modelo.Producto;
import inca.jesus.trajesya.Data.Modelo.Promocion;
import inca.jesus.trajesya.Data.Modelo.Publicidad;
import inca.jesus.trajesya.Data.Modelo.SubCategoria;
import inca.jesus.trajesya.Data.Modelo.UnidadTerritorial;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;

public class Splash extends AppCompatActivity {

    private ProgressBar p;
    private TextView txtProgress;
    private TextView txtVersion;
    Context context;

    //public static final String DATA_SAVED_BROADCAST = "inca.jesus.datasaved";

    private BroadcastReceiver Sincronizador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context=getApplicationContext();
        txtVersion=findViewById(R.id.txtVersion);
        txtVersion.setText("Versión "+Constantes.VERSION);
        try {
            VerificarConexion(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void VerificarConexion(Context context) throws Exception {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null ) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                p = findViewById(R.id.progress);
                txtProgress =  findViewById(R.id.texxt_progress);

                LimpiarListasGenerales();
                ListarCategoriasDisponibles(getApplicationContext());
            }else{
                Toast.makeText(context, "No tiene Conexión a Internet!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "No tiene Conexión a Internet!", Toast.LENGTH_SHORT).show();
        }
    }
   public void LimpiarListasGenerales(){
       Constantes.Base_Categorias_Todo.clear();
       Constantes.Base_SubCategorias_Todo.clear();
       Constantes.Base_Producto_Todo.clear();

       Constantes.Base_ListaPromociones.clear();
       Constantes.Base_ListaProductoMasVisto.clear();
       Constantes.Base_ListaProductoNuevo.clear();
       Constantes.Base_ListaProductoTendencias.clear();
       Constantes.Base_ListaProductoMasAlquilados.clear();
   }
    public void ListarCategoriasDisponibles(final Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray categorias=jsonResponse.getJSONArray("categorias");
                                for(int i=0;i<categorias.length();i++){
                                    JSONObject objeto= categorias.getJSONObject(i);
                                    Categoria temp=new Categoria();
                                    temp.setIdCategoria(objeto.getInt("idCategoria"));
                                    temp.setNombreCategoria(objeto.getString("NombreCategoria"));
                                    temp.setDescripcionCategoria(objeto.getString("Descripcion"));
                                    temp.setImagenCategoria(objeto.getString("imagenPortada"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Grupo grupoCategoria=new Grupo();
                                    grupoCategoria.setIdGrupo(objeto.getInt("Grupo_idGrupo"));
                                    grupoCategoria.setDescripcionGrupo(objeto.getString("NombreGrupo"));
                                    temp.setGrupoCategoria(grupoCategoria);

                                    Estado estadoCategoria=new Estado();
                                    estadoCategoria.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoCategoria(estadoCategoria);

                                    Log.i("Inca","Recuperar Categorias:"+temp.getDescripcionCategoria());

                                    Constantes.Base_Categorias_Todo.add(temp);
                                }
                                Log.e("Inca","Servidor Listar Categorias");

                                Progreso(10);

                                ListarSubCategoriasDisponibles(context);
                            } else {

                                Toast.makeText(context, "Categorias no Disponibles.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca","Error JSON:"+e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "ListarCategorias");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void ListarSubCategoriasDisponibles(final Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray categorias=jsonResponse.getJSONArray("subcategorias");
                                for(int i=0;i<categorias.length();i++){
                                    JSONObject objeto= categorias.getJSONObject(i);
                                    SubCategoria temp=new SubCategoria();
                                    temp.setIdSubCategoria(objeto.getInt("idSubCategoria"));
                                    temp.setNombreSubCategoria(objeto.getString("NombreSubCategoria"));
                                    temp.setDescripcionSubCategoria(objeto.getString("Descripcion"));
                                    temp.setImagenSubCategorias(objeto.getString("imagenPortada"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Categoria categoria=new Categoria();
                                    categoria.setIdCategoria(objeto.getInt("Categoria_idCategoria"));
                                    temp.setCategoriaSubCategoria(categoria);

                                    Estado estadoSubCategoria=new Estado();
                                    estadoSubCategoria.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoSubCategoria(estadoSubCategoria);

                                    Constantes.Base_SubCategorias_Todo.add(temp);
                                    Log.i("Inca","Recuperar SubCategoria:"+temp.getDescripcionSubCategoria());
                                }

                                Log.e("Inca","Servidor Listar SubCategorias");
                                ListarProductosDisponibles(context);

                                Progreso(18);
                            } else {

                                Toast.makeText(context, "SubCategorias no Disponibles.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca","Error JSON:"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "ListarSubCategoriaTodo");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
    public void ListarProductosDisponibles(final Context context){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {

                                JSONArray productoCate=jsonResponse.getJSONArray("productosTodos");
                                for(int i=0;i<productoCate.length();i++){
                                    JSONObject objeto= productoCate.getJSONObject(i);
                                    Producto temp=new Producto();
                                    temp.setIdProducto(objeto.getInt("idProducto"));
                                    temp.setNombreProducto(objeto.getString("NombreProducto"));
                                    temp.setDescripcionProducto(objeto.getString("DescripcionProducto"));
                                    temp.setImagenProducto(objeto.getString("imagenPortada"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Categoria categoria=new Categoria();
                                    categoria.setIdCategoria(objeto.getInt("Categoria_idCategoria"));
                                    temp.setCategoriaProducto(categoria);

                                    SubCategoria subCategoria=new SubCategoria();
                                    subCategoria.setIdSubCategoria(objeto.getInt("SubCategoria_idSubCategoria"));
                                    temp.setSubCategoriaProducto(subCategoria);

                                    UnidadTerritorial departamento=new UnidadTerritorial();
                                    departamento.setIdUnidadTerritorial(objeto.getInt("Departamento_idDepartamento"));
                                    departamento.setNombreUnidadTerritorial(objeto.getString("departamento"));
                                    temp.setDepartamentoProducto(departamento);

                                    UnidadTerritorial provincia=new UnidadTerritorial();
                                    provincia.setIdUnidadTerritorial(objeto.getInt("Provincia_idProvincia"));
                                    provincia.setNombreUnidadTerritorial(objeto.getString("provincia"));
                                    temp.setDepartamentoProducto(provincia);

                                    UnidadTerritorial distrito=new UnidadTerritorial();
                                    distrito.setIdUnidadTerritorial(objeto.getInt("Distrito_idDistrito"));
                                    distrito.setNombreUnidadTerritorial(objeto.getString("distrito"));
                                    temp.setDepartamentoProducto(distrito);

                                    Estado estadoProducto=new Estado();
                                    estadoProducto.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoProducto(estadoProducto);

                                    temp.setVerificadoProducto(objeto.getString("verificado"));

                                    /*------Recuperar Galerias-----------*/

                                    if(!objeto.isNull("galeria")){
                                        JSONArray galeriaProducto = new JSONArray(objeto.getString("galeria"));

                                        List<Galeria> ListaGaleriaTemp=new ArrayList<>();
                                        for(int u=0;u<galeriaProducto.length();u++){
                                            JSONObject objetoGaleria= galeriaProducto.getJSONObject(u);

                                            Galeria galeria=new Galeria();
                                            galeria.setIdGaleria(objetoGaleria.getInt("idGaleria"));
                                            galeria.setNombreGaleria(objetoGaleria.getString("NombreGaleria"));
                                            galeria.setImagenGaleria(objetoGaleria.getString("imagenPortada"));
                                            ListaGaleriaTemp.add(galeria);
                                        }
                                        temp.setGaleriaProducto(ListaGaleriaTemp);
                                    }

                                    /*------Recuperar Medidas-----------*/
                                    if(!objeto.isNull("medida")){
                                        JSONArray medidaProducto = new JSONArray(objeto.getString("medida"));

                                        List<Medida> ListaMedidaTemp=new ArrayList<>();
                                        for(int u=0;u<medidaProducto.length();u++){
                                            JSONObject objetoMedida= medidaProducto.getJSONObject(u);

                                            Medida medida=new Medida();
                                            medida.setIdMedida(objetoMedida.getInt("idProductoMedida"));
                                            medida.setNombreMedida(objetoMedida.getString("NombreMedida"));

                                            ListaMedidaTemp.add(medida);
                                        }
                                        temp.setMedidaProducto(ListaMedidaTemp);
                                    }else{
                                       Log.e("Inca","Error de encontrar medidas");
                                    }

                                    Constantes.Base_Producto_Todo.add(temp);

                                    Log.i("Inca","Recuperar Productos de Categorias:"+temp.getNombreProducto());
                                }
                                Log.e("Inca","Servidor Listar Productos");
                                ListarPromocionesDisponibles(context);
                                Progreso(25);
                            } else {

                                Toast.makeText(context, "Productos no Disponibles.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca","Error JSON:"+e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "ListarProductoTodo");

                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    public void ListarPromocionesDisponibles(final Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray categorias=jsonResponse.getJSONArray("promociones");
                                for(int i=0;i<categorias.length();i++){
                                    JSONObject objeto= categorias.getJSONObject(i);
                                    Promocion temp=new Promocion();
                                    temp.setIdPromocion(objeto.getInt("idPromocion"));
                                    temp.setNombrePromocion(objeto.getString("NombrePromocion"));
                                    temp.setImagenPromocion(objeto.getString("imagenPromocion"));
                                    temp.setLinkPromocion(objeto.getString("linkPromocion"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoPromocion(estado);
                                    Log.i("Inca","Promocion Recuperada :"+temp.getNombrePromocion());

                                    Constantes.Base_ListaPromociones.add(temp);
                                }
                                Log.i("Inca","Servidor Listar Promociones");
                                Progreso(28);
                                ListarPublicidadDisponibles(context);


                            } else {

                                Toast.makeText(context, "Categorias no Disponibles.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Inca","Error JSON Promociones:"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Inca Error Response", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "ListarPromociones");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void ListarPublicidadDisponibles(final Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray categorias=jsonResponse.getJSONArray("publicidad");
                                for(int i=0;i<categorias.length();i++){
                                    JSONObject objeto= categorias.getJSONObject(i);
                                    Publicidad temp=new Publicidad();
                                    temp.setIdPublicidad(objeto.getInt("idPublicidad"));
                                    temp.setNombrePublicidad(objeto.getString("NombrePublicidad"));
                                    temp.setImagenPublicidad(objeto.getString("imagenPublicidad"));
                                    temp.setLinkPublicidad(objeto.getString("linkPublicidad"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Estado estado=new Estado();
                                    estado.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoPublicidad(estado);
                                    Log.i("Inca","Publicidad Recuperada :"+temp.getNombrePublicidad());

                                    Constantes.Base_ListaPublicidad.add(temp);
                                }
                                Log.i("Inca","Servidor Listar Publicidad");
                                Progreso(33);
                                ListarProductosNuevos(context);

                            } else {

                               // Toast.makeText(context, "Publicidad no Disponibles.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Inca","Error JSON Publicidad:"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Inca Error Response", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "ListarPublicidad");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }



    public void ListarProductosNuevos(final Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray productos=jsonResponse.getJSONArray("productos");
                                for(int i=0;i<productos.length();i++){
                                    JSONObject objeto= productos.getJSONObject(i);
                                    Producto temp=new Producto();
                                    temp.setIdProducto(objeto.getInt("idProducto"));
                                    temp.setNombreProducto(objeto.getString("NombreProducto"));
                                    temp.setDescripcionProducto(objeto.getString("DescripcionProducto"));
                                    temp.setImagenProducto(objeto.getString("imagenPortada"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Categoria categoria=new Categoria();
                                    categoria.setIdCategoria(objeto.getInt("Categoria_idCategoria"));
                                    temp.setCategoriaProducto(categoria);

                                    SubCategoria subCategoria=new SubCategoria();
                                    subCategoria.setIdSubCategoria(objeto.getInt("SubCategoria_idSubCategoria"));
                                    temp.setSubCategoriaProducto(subCategoria);

                                    UnidadTerritorial departamento=new UnidadTerritorial();
                                    departamento.setIdUnidadTerritorial(objeto.getInt("Departamento_idDepartamento"));
                                    departamento.setNombreUnidadTerritorial(objeto.getString("departamento"));
                                    temp.setDepartamentoProducto(departamento);

                                    UnidadTerritorial provincia=new UnidadTerritorial();
                                    provincia.setIdUnidadTerritorial(objeto.getInt("Provincia_idProvincia"));
                                    provincia.setNombreUnidadTerritorial(objeto.getString("provincia"));
                                    temp.setDepartamentoProducto(provincia);

                                    UnidadTerritorial distrito=new UnidadTerritorial();
                                    distrito.setIdUnidadTerritorial(objeto.getInt("Distrito_idDistrito"));
                                    distrito.setNombreUnidadTerritorial(objeto.getString("distrito"));
                                    temp.setDepartamentoProducto(distrito);

                                    Estado estadoProducto=new Estado();
                                    estadoProducto.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoProducto(estadoProducto);

                                    temp.setPrecioAlquiler(Double.parseDouble(objeto.getString("precioAlquiler")));
                                    temp.setPrecioVenta(Double.parseDouble(objeto.getString("precioVenta")));

                                    Constantes.Base_ListaProductoNuevo.add(temp);
                                    Log.i("Inca","Recupero Producto:"+temp.getNombreProducto());
                                }
                                Log.e("Inca","Servidor Listar Productos");

                                Progreso(42);
                                ListarProductosMasVistos(context);

                            } else {
                                Toast.makeText(context, "Productos no Disponibles.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca","Error JSON:"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "ListarProductosNuevos");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
    public void ListarProductosMasVistos(final Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray productos=jsonResponse.getJSONArray("productos");
                                for(int i=0;i<productos.length();i++){
                                    JSONObject objeto= productos.getJSONObject(i);
                                    Producto temp=new Producto();
                                    temp.setIdProducto(objeto.getInt("idProducto"));
                                    temp.setNombreProducto(objeto.getString("NombreProducto"));
                                    temp.setDescripcionProducto(objeto.getString("DescripcionProducto"));
                                    temp.setImagenProducto(objeto.getString("imagenPortada"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Categoria categoria=new Categoria();
                                    categoria.setIdCategoria(objeto.getInt("Categoria_idCategoria"));
                                    temp.setCategoriaProducto(categoria);

                                    SubCategoria subCategoria=new SubCategoria();
                                    subCategoria.setIdSubCategoria(objeto.getInt("SubCategoria_idSubCategoria"));
                                    temp.setSubCategoriaProducto(subCategoria);

                                    UnidadTerritorial departamento=new UnidadTerritorial();
                                    departamento.setIdUnidadTerritorial(objeto.getInt("Departamento_idDepartamento"));
                                    departamento.setNombreUnidadTerritorial(objeto.getString("departamento"));
                                    temp.setDepartamentoProducto(departamento);

                                    UnidadTerritorial provincia=new UnidadTerritorial();
                                    provincia.setIdUnidadTerritorial(objeto.getInt("Provincia_idProvincia"));
                                    UnidadTerritorial distrito=new UnidadTerritorial();
                                    distrito.setIdUnidadTerritorial(objeto.getInt("Distrito_idDistrito"));
                                    provincia.setNombreUnidadTerritorial(objeto.getString("provincia"));
                                    temp.setDepartamentoProducto(provincia);

                                    distrito.setNombreUnidadTerritorial(objeto.getString("distrito"));
                                    temp.setDepartamentoProducto(distrito);

                                    Estado estadoProducto=new Estado();
                                    estadoProducto.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoProducto(estadoProducto);

                                    temp.setPrecioAlquiler(Double.parseDouble(objeto.getString("precioAlquiler")));
                                    temp.setPrecioVenta(Double.parseDouble(objeto.getString("precioVenta")));

                                    Constantes.Base_ListaProductoMasVisto.add(temp);

                                    Log.i("Inca","Recupero Producto mas vistos:"+temp.getNombreProducto());
                                }
                                Log.e("Inca","Servidor Listar Productos Mas vistos");

                                Progreso(56);
                                ListarProductosMasAlquilados(context);
                            } else {
                                Toast.makeText(context, "Productos no Disponibles mas vistos", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca","Error JSON:"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "ListarProductosMasVistos");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
    public void ListarProductosMasAlquilados(final Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray productos=jsonResponse.getJSONArray("productos");
                                for(int i=0;i<productos.length();i++){
                                    JSONObject objeto= productos.getJSONObject(i);
                                    Producto temp=new Producto();
                                    temp.setIdProducto(objeto.getInt("idProducto"));
                                    temp.setNombreProducto(objeto.getString("NombreProducto"));
                                    temp.setDescripcionProducto(objeto.getString("DescripcionProducto"));
                                    temp.setImagenProducto(objeto.getString("imagenPortada"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Categoria categoria=new Categoria();
                                    categoria.setIdCategoria(objeto.getInt("Categoria_idCategoria"));
                                    temp.setCategoriaProducto(categoria);

                                    SubCategoria subCategoria=new SubCategoria();
                                    subCategoria.setIdSubCategoria(objeto.getInt("SubCategoria_idSubCategoria"));
                                    temp.setSubCategoriaProducto(subCategoria);

                                    UnidadTerritorial departamento=new UnidadTerritorial();
                                    departamento.setIdUnidadTerritorial(objeto.getInt("Departamento_idDepartamento"));
                                    departamento.setNombreUnidadTerritorial(objeto.getString("departamento"));
                                    temp.setDepartamentoProducto(departamento);

                                    UnidadTerritorial provincia=new UnidadTerritorial();
                                    provincia.setIdUnidadTerritorial(objeto.getInt("Provincia_idProvincia"));
                                    provincia.setNombreUnidadTerritorial(objeto.getString("provincia"));
                                    temp.setDepartamentoProducto(provincia);

                                    UnidadTerritorial distrito=new UnidadTerritorial();
                                    distrito.setIdUnidadTerritorial(objeto.getInt("Distrito_idDistrito"));
                                    distrito.setNombreUnidadTerritorial(objeto.getString("distrito"));
                                    temp.setDepartamentoProducto(distrito);

                                    Estado estadoProducto=new Estado();
                                    estadoProducto.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoProducto(estadoProducto);

                                    temp.setPrecioAlquiler(Double.parseDouble(objeto.getString("precioAlquiler")));
                                    temp.setPrecioVenta(Double.parseDouble(objeto.getString("precioVenta")));

                                    Constantes.Base_ListaProductoMasAlquilados.add(temp);

                                    Log.i("Inca","Recupero Producto:"+temp.getNombreProducto());
                                }
                                Log.e("Inca","Servidor Listar Productos");
                                Progreso(81);

                                ListarProductosTendencias(context);

                            } else {
                                Toast.makeText(context, "Productos no Disponibles.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca","Error JSON:"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "ListarProductosMasAlquilados");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
    public void ListarProductosTendencias(final Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray productos=jsonResponse.getJSONArray("productos");
                                for(int i=0;i<productos.length();i++){
                                    JSONObject objeto= productos.getJSONObject(i);
                                    Producto temp=new Producto();
                                    temp.setIdProducto(objeto.getInt("idProducto"));
                                    temp.setNombreProducto(objeto.getString("NombreProducto"));
                                    temp.setDescripcionProducto(objeto.getString("DescripcionProducto"));
                                    temp.setImagenProducto(objeto.getString("imagenPortada"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Categoria categoria=new Categoria();
                                    categoria.setIdCategoria(objeto.getInt("Categoria_idCategoria"));
                                    temp.setCategoriaProducto(categoria);

                                    SubCategoria subCategoria=new SubCategoria();
                                    subCategoria.setIdSubCategoria(objeto.getInt("SubCategoria_idSubCategoria"));
                                    temp.setSubCategoriaProducto(subCategoria);

                                    UnidadTerritorial departamento=new UnidadTerritorial();
                                    departamento.setIdUnidadTerritorial(objeto.getInt("Departamento_idDepartamento"));
                                    departamento.setNombreUnidadTerritorial(objeto.getString("departamento"));
                                    temp.setDepartamentoProducto(departamento);

                                    UnidadTerritorial provincia=new UnidadTerritorial();
                                    provincia.setIdUnidadTerritorial(objeto.getInt("Provincia_idProvincia"));
                                    provincia.setNombreUnidadTerritorial(objeto.getString("provincia"));
                                    temp.setDepartamentoProducto(provincia);

                                    UnidadTerritorial distrito=new UnidadTerritorial();
                                    distrito.setIdUnidadTerritorial(objeto.getInt("Distrito_idDistrito"));
                                    distrito.setNombreUnidadTerritorial(objeto.getString("distrito"));
                                    temp.setDepartamentoProducto(distrito);

                                    Estado estadoProducto=new Estado();
                                    estadoProducto.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoProducto(estadoProducto);

                                    temp.setPrecioAlquiler(Double.parseDouble(objeto.getString("precioAlquiler")));
                                    temp.setPrecioVenta(Double.parseDouble(objeto.getString("precioVenta")));

                                    Constantes.Base_ListaProductoTendencias.add(temp);
                                    Log.i("Inca","Recupero Producto Tendencias:"+temp.getNombreProducto());
                                }
                                Log.e("Inca","Servidor Listar Productos Tendencias");
                                Progreso(100);
                                mover();

                            } else {
                                Toast.makeText(context, "Productos no Disponibles Tendencias.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca","Error JSON:"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("INCA", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("operacion", "ListarProductosTendencias");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    void mover(){
        Intent intent = new Intent(Splash.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void Progreso(int tiempo){
        p.setProgress(tiempo);
        txtProgress.setText("Cargando... "+tiempo+" %");
    }

}
