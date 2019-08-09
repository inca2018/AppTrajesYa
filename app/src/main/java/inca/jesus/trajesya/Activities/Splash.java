package inca.jesus.trajesya.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

import inca.jesus.trajesya.Data.Conexion.VolleySingleton;
import inca.jesus.trajesya.Data.Modelo.Categoria;
import inca.jesus.trajesya.Data.Modelo.Estado;
import inca.jesus.trajesya.Data.Modelo.Grupo;
import inca.jesus.trajesya.Data.Modelo.Producto;
import inca.jesus.trajesya.Data.Modelo.Promocion;
import inca.jesus.trajesya.Data.Modelo.SubCategoria;
import inca.jesus.trajesya.Data.Modelo.UnidadTerritorial;
import inca.jesus.trajesya.Data.Utils.Constantes;
import inca.jesus.trajesya.R;

public class Splash extends AppCompatActivity {

    private ProgressBar p;
    private TextView txtProgress;

    private int pStatus = 0;
    private Handler handler = new Handler();
    ImageView ima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        p = findViewById(R.id.progress);
        txtProgress =  findViewById(R.id.texxt_progress);

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                ListarPromocionesDisponibles(getApplicationContext());
                while (pStatus <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            p.setProgress(pStatus);
                            txtProgress.setText("Cargando... "+pStatus+" %");
                        }
                    });
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        mover();
                    }
                });
            }
        }).start();*/

        ListarCategoriasDisponibles(getApplicationContext());

    }

    void mover(){
        Intent intent = new Intent(Splash.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
                                    Constantes.Base_Categorias.add(temp);
                                }
                                Log.e("Inca","Servidor Listar Categorias");

                                ListarPromocionesDisponibles(context);

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

                                ListarProductosNuevos(context);

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
}
