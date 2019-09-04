package inca.jesus.trajesya.activities;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inca.jesus.trajesya.data.conexion.VolleySingleton;
import inca.jesus.trajesya.data.modelo.Categoria;
import inca.jesus.trajesya.data.modelo.Estado;
import inca.jesus.trajesya.data.modelo.Galeria;
import inca.jesus.trajesya.data.modelo.Grupo;
import inca.jesus.trajesya.data.modelo.Medida;
import inca.jesus.trajesya.data.modelo.Producto;
import inca.jesus.trajesya.data.modelo.Promocion;
import inca.jesus.trajesya.data.modelo.Publicidad;
import inca.jesus.trajesya.data.modelo.Sesion;
import inca.jesus.trajesya.data.modelo.SubCategoria;
import inca.jesus.trajesya.data.modelo.UnidadTerritorial;
import inca.jesus.trajesya.data.modelo.Usuario;
import inca.jesus.trajesya.data.utils.Constantes;
import inca.jesus.trajesya.R;

import static inca.jesus.trajesya.data.utils.Constantes.OPERACION;
import static inca.jesus.trajesya.data.utils.Constantes.SUCCESS;
import static inca.jesus.trajesya.data.utils.Constantes.VariableprecioAlquiler;

public class Splash extends AppCompatActivity {

    private ProgressBar p;
    private TextView txtProgress;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = getApplicationContext();
        TextView txtVersion = findViewById(R.id.txtVersion);
        txtVersion.setText("Versión " + Constantes.VERSION);
        try {
            VerificarConexion(context);
        } catch (Exception e) {
            Log.i("Inca", String.valueOf(e));
        }
    }

    public void VerificarConexion(Context context) throws Exception {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                p = findViewById(R.id.progress);
                txtProgress = findViewById(R.id.texxt_progress);

                LimpiarListasGenerales();
                ListarCategoriasDisponibles(getApplicationContext());
            } else {
                Toast.makeText(context, "No tiene Conexión a Internet!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "No tiene Conexión a Internet!", Toast.LENGTH_SHORT).show();
        }
    }

    public void LimpiarListasGenerales() {
        Constantes.Base_Categorias_Todo.clear();
        Constantes.Base_SubCategorias_Todo.clear();
        Constantes.Base_Producto_Todo.clear();
        Constantes.Base_ListaPromociones.clear();
        Constantes.Base_ListaProductoRecientes.clear();
        Constantes.Base_ListaProductoPromociones.clear();
        Constantes.Base_ListaProductosTopTradicionales.clear();
        Constantes.Base_ListaProductosTopOtros.clear();
        Constantes.Base_ListaProductoNuevo.clear();
        Constantes.Base_ListaProductoMasVisto.clear();
        Constantes.Base_ListaProductoMasAlquilados.clear();
        Constantes.Base_ListaProductoTendencias.clear();
    }

    public void ListarCategoriasDisponibles(final Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean(SUCCESS);

                            if (success) {
                                JSONArray categorias = jsonResponse.getJSONArray("categorias");
                                for (int i = 0; i < categorias.length(); i++) {
                                    JSONObject objeto = categorias.getJSONObject(i);
                                    Categoria temp = new Categoria();
                                    temp.setIdCategoria(objeto.getInt("idCategoria"));
                                    temp.setNombreCategoria(objeto.getString("NombreCategoria"));
                                    temp.setDescripcionCategoria(objeto.getString("Descripcion"));
                                    temp.setImagenCategoria(objeto.getString(Constantes.VariableimagenPortada));
                                    temp.setFechaRegistro(objeto.getString(Constantes.VariablefechaRegistro));
                                    temp.setFechaUpdate(objeto.getString(Constantes.VariablefechaUpdate));

                                    Grupo grupoCategoria = new Grupo();
                                    grupoCategoria.setIdGrupo(objeto.getInt("Grupo_idGrupo"));
                                    grupoCategoria.setDescripcionGrupo(objeto.getString("NombreGrupo"));
                                    temp.setGrupoCategoria(grupoCategoria);

                                    Estado estadoCategoria = new Estado();
                                    estadoCategoria.setIdEstado(objeto.getInt(Constantes.VariableEstado_idEstado));
                                    temp.setEstadoCategoria(estadoCategoria);

                                    Log.i("Inca", "Recuperar Categorias:" + temp.getDescripcionCategoria());

                                    Constantes.Base_Categorias_Todo.add(temp);
                                }
                                Log.e("Inca", "Servidor Listar Categorias");

                                Progreso(10);

                                ListarSubCategoriasDisponibles(context);
                            } else {

                                Toast.makeText(context, "Categorias no Disponibles.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca", "Error JSON EN solicitud:" + e);
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
                params.put(Constantes.OPERACION, "ListarCategorias");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void ListarSubCategoriasDisponibles(final Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean(SUCCESS);

                            if (success) {
                                JSONArray categorias = jsonResponse.getJSONArray("subcategorias");
                                for (int i = 0; i < categorias.length(); i++) {
                                    JSONObject objeto = categorias.getJSONObject(i);
                                    SubCategoria temp = new SubCategoria();
                                    temp.setIdSubCategoria(objeto.getInt("idSubCategoria"));
                                    temp.setNombreSubCategoria(objeto.getString("NombreSubCategoria"));
                                    temp.setDescripcionSubCategoria(objeto.getString("Descripcion"));
                                    temp.setImagenSubCategorias(objeto.getString(Constantes.VariableimagenPortada));
                                    temp.setFechaRegistro(objeto.getString(Constantes.VariablefechaRegistro));
                                    temp.setFechaUpdate(objeto.getString(Constantes.VariablefechaUpdate));

                                    Categoria categoria = new Categoria();
                                    categoria.setIdCategoria(objeto.getInt(Constantes.VariableCategoria_idCategoria));
                                    temp.setCategoriaSubCategoria(categoria);

                                    Estado estadoSubCategoria = new Estado();
                                    estadoSubCategoria.setIdEstado(objeto.getInt(Constantes.VariableEstado_idEstado));
                                    temp.setEstadoSubCategoria(estadoSubCategoria);

                                    Constantes.Base_SubCategorias_Todo.add(temp);
                                    Log.i("Inca", "Recuperar SubCategoria:" + temp.getDescripcionSubCategoria());
                                }

                                Log.e("Inca", "Servidor Listar SubCategorias");
                                ListarProductosDisponibles(context);

                                Progreso(18);
                            } else {

                                Toast.makeText(context, "SubCategorias no Disponibles.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca", "Error JSON SubCategorias:" + e);
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
                params.put(Constantes.OPERACION, "ListarSubCategoriaTodo");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    public void ListarProductosDisponibles(final Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean(SUCCESS);
                            if (success) {
                                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                JSONArray productoCate = jsonResponse.getJSONArray("productosTodos");
                                for (int i = 0; i < productoCate.length(); i++) {
                                    JSONObject objeto = productoCate.getJSONObject(i);
                                    iterarProducto(objeto, formato);
                                }
                                Log.e("Inca", "Servidor Listar Productos");
                                ListarPromocionesDisponibles(context);

                                buscarListarProductoMasVisto();

                                Progreso(25);
                            } else {
                                Toast.makeText(context, "Productos no Disponibles.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca", "Error JSON Productos Disponibles:" + e);
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
                params.put(Constantes.OPERACION, "ListarProductoTodo");

                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    private void iterarProducto(JSONObject objeto, SimpleDateFormat formato) throws JSONException {
        Producto temp = new Producto();
        temp.setIdProducto(objeto.getInt(Constantes.VariableidProducto));
        temp.setNombreProducto(objeto.getString(Constantes.VariableNombreProducto));
        temp.setDescripcionProducto(objeto.getString(Constantes.VariableDescripcionProducto));
        temp.setImagenProducto(objeto.getString(Constantes.VariableimagenPortada));
        temp.setFechaRegistro(objeto.getString(Constantes.VariablefechaRegistro));
        temp.setFechaUpdate(objeto.getString(Constantes.VariablefechaUpdate));
        /*---Formatear Registro--*/
        try {
            Date date = formato.parse(temp.getFechaRegistro());
            temp.setFechaRegistroDate(date);
        } catch (ParseException e) {
            Log.i("Inca", String.valueOf(e));
        }

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(objeto.getInt(Constantes.VariableCategoria_idCategoria));
        temp.setCategoriaProducto(categoria);

        SubCategoria subCategoria = new SubCategoria();
        subCategoria.setIdSubCategoria(objeto.getInt(Constantes.VariableSubCategoria_idSubCategoria));
        temp.setSubCategoriaProducto(subCategoria);

        UnidadTerritorial departamento = new UnidadTerritorial();
        departamento.setIdUnidadTerritorial(objeto.getInt(Constantes.VariableDepartamento_idDepartamento));
        departamento.setNombreUnidadTerritorial(objeto.getString(Constantes.VariableDepartamento));
        temp.setDepartamentoProducto(departamento);

        UnidadTerritorial provincia = new UnidadTerritorial();
        provincia.setIdUnidadTerritorial(objeto.getInt(Constantes.VariableProvincia_idProvincia));
        provincia.setNombreUnidadTerritorial(objeto.getString(Constantes.VariableProvincia));
        temp.setDepartamentoProducto(provincia);

        UnidadTerritorial distrito = new UnidadTerritorial();
        distrito.setIdUnidadTerritorial(objeto.getInt(Constantes.VariableDistrito_idDistrito));
        distrito.setNombreUnidadTerritorial(objeto.getString(Constantes.VariableDistrito));
        temp.setDepartamentoProducto(distrito);

        Estado estadoProducto = new Estado();
        estadoProducto.setIdEstado(objeto.getInt(Constantes.VariableEstado_idEstado));
        temp.setEstadoProducto(estadoProducto);

        temp.setVerificadoProducto(objeto.getString("verificado"));
        temp.setPrecioAlquiler(objeto.getDouble(VariableprecioAlquiler));
        temp.setPrecioVenta(objeto.getDouble(Constantes.VariablePrecioVenta));

        temp.setPrecioPromocion(objeto.getDouble("DescuentoProducto"));
        temp.setNumeroVisitas(objeto.getInt("Visitas"));

        Grupo grupo = new Grupo();
        grupo.setIdGrupo(objeto.getInt("Grupo"));
        temp.setGrupo(grupo);
        /*------Recuperar Galerias-----------*/

        if (!objeto.isNull("galeria")) {
            JSONArray galeriaProducto = new JSONArray(objeto.getString("galeria"));

            List<Galeria> listaGaleriaTemp = new ArrayList<>();
            for (int u = 0; u < galeriaProducto.length(); u++) {
                JSONObject objetoGaleria = galeriaProducto.getJSONObject(u);

                Galeria galeria = new Galeria();
                galeria.setIdGaleria(objetoGaleria.getInt("idGaleria"));
                galeria.setNombreGaleria(objetoGaleria.getString("NombreGaleria"));
                galeria.setImagenGaleria(objetoGaleria.getString(Constantes.VariableimagenPortada));
                listaGaleriaTemp.add(galeria);
            }
            temp.setGaleriaProducto(listaGaleriaTemp);
        }

        /*------Recuperar Medidas-----------*/
        if (!objeto.isNull("medida")) {
            JSONArray medidaProducto = new JSONArray(objeto.getString("medida"));

            List<Medida> listaMedidaTemp = new ArrayList<>();
            for (int u = 0; u < medidaProducto.length(); u++) {
                JSONObject objetoMedida = medidaProducto.getJSONObject(u);

                Medida medida = new Medida();
                medida.setIdMedida(objetoMedida.getInt("idProductoMedida"));
                medida.setNombreMedida(objetoMedida.getString("NombreMedida"));
                medida.setSimboloMedida(objetoMedida.getString("simbolo"));

                listaMedidaTemp.add(medida);
            }
            temp.setMedidaProducto(listaMedidaTemp);
        } else {
            Log.e("Inca", "Error de encontrar medidas");
        }

        Constantes.Base_Producto_Todo.add(temp);
        if ((int) temp.getPrecioPromocion() != 0) {
            Constantes.Base_ListaProductoPromociones.add(temp);
        }

        Log.i("Inca", "Recuperar Productos de Categorias:" + temp.getNombreProducto());

    }

    private void buscarListarProductoMasVisto() {
        List<Producto> temporal;
        List<Producto> recientes;
        temporal = Constantes.Base_Producto_Todo;
        recientes = Constantes.Base_Producto_Todo;
        Producto aux;
        for (int i = 0; i < temporal.size() - 1; i++) {
            for (int j = 0; j < temporal.size() - i - 1; j++) {
                if (temporal.get(j + 1).getNumeroVisitas() > temporal.get(j).getNumeroVisitas()) {
                    aux = temporal.get(j + 1);
                    temporal.set(j + 1, temporal.get(j));
                    temporal.set(j, aux);
                }
            }
        }

        buscarListarproductosSegunCategoria(temporal);
        buscarListarProductosRecientes(recientes);
        buscrListarProductosNuevos(recientes);
    }

    private void buscrListarProductosNuevos(List<Producto> recientes) {

        for (int i = 0; i < recientes.size(); i++) {
            if (i <= 10) {
                Constantes.Base_ListaProductoNuevo.add(recientes.get(i));
                Log.i("Inca", "Producto Recientes Agregado: " + recientes.get(i).getNombreProducto());
            } else {
                Log.i("Inca", "Producto Recientes NO Agregado: " + recientes.get(i).getNombreProducto());
            }
        }
    }

    private void buscarListarProductosRecientes(List<Producto> recientes) {
        Producto aux;
        for (int i = 0; i < recientes.size() - 1; i++) {
            for (int j = 0; j < recientes.size() - i - 1; j++) {
                if (recientes.get(j + 1).getFechaRegistroDate().after(recientes.get(j).getFechaRegistroDate())) {
                    aux = recientes.get(j + 1);
                    recientes.set(j + 1, recientes.get(j));
                    recientes.set(j, aux);
                }
            }
        }
    }

    private void buscarListarproductosSegunCategoria(List<Producto> temporal) {
        for (int i = 0; i < temporal.size(); i++) {
            if (i <= 10) {
                Constantes.Base_ListaProductoMasVisto.add(temporal.get(i));
                if (temporal.get(i).getGrupo().getIdGrupo() == 1) {
                    Constantes.Base_ListaProductosTopTradicionales.add(temporal.get(i));
                } else {
                    Constantes.Base_ListaProductosTopOtros.add(temporal.get(i));
                }

                Log.i("Inca", "Producto mas visto Agregado: " + temporal.get(i).getNombreProducto());
            } else {
                Log.i("Inca", "Producto mas visto NO Agregado: " + temporal.get(i).getNombreProducto());
            }
        }
    }

    public void ListarPromocionesDisponibles(final Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean(SUCCESS);

                            if (success) {
                                JSONArray categorias = jsonResponse.getJSONArray("promociones");
                                for (int i = 0; i < categorias.length(); i++) {
                                    JSONObject objeto = categorias.getJSONObject(i);
                                    Promocion temp = new Promocion();
                                    temp.setIdPromocion(objeto.getInt("idPromocion"));
                                    temp.setNombrePromocion(objeto.getString("NombrePromocion"));
                                    temp.setImagenPromocion(objeto.getString("imagenPromocion"));
                                    temp.setLinkPromocion(objeto.getString("linkPromocion"));
                                    temp.setFechaRegistro(objeto.getString(Constantes.VariablefechaRegistro));
                                    temp.setFechaUpdate(objeto.getString(Constantes.VariablefechaUpdate));

                                    Estado estado = new Estado();
                                    estado.setIdEstado(objeto.getInt(Constantes.VariableEstado_idEstado));
                                    temp.setEstadoPromocion(estado);
                                    Log.i("Inca", "Promocion Recuperada :" + temp.getNombrePromocion());

                                    Constantes.Base_ListaPromociones.add(temp);
                                }
                                Log.i("Inca", "Servidor Listar Promociones");
                                Progreso(28);
                                ListarPublicidadDisponibles(context);


                            } else {

                                Toast.makeText(context, "Categorias no Disponibles.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Inca", "Error JSON Promociones:" + e);
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
                params.put(OPERACION, "ListarPromociones");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void ListarPublicidadDisponibles(final Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean(SUCCESS);

                            if (success) {
                                JSONArray categorias = jsonResponse.getJSONArray("publicidad");
                                for (int i = 0; i < categorias.length(); i++) {
                                    JSONObject objeto = categorias.getJSONObject(i);
                                    Publicidad temp = new Publicidad();
                                    temp.setIdPublicidad(objeto.getInt("idPublicidad"));
                                    temp.setNombrePublicidad(objeto.getString("NombrePublicidad"));
                                    temp.setImagenPublicidad(objeto.getString("imagenPublicidad"));
                                    temp.setLinkPublicidad(objeto.getString("linkPublicidad"));
                                    temp.setFechaRegistro(objeto.getString(Constantes.VariablefechaRegistro));
                                    temp.setFechaUpdate(objeto.getString(Constantes.VariablefechaUpdate));

                                    Estado estado = new Estado();
                                    estado.setIdEstado(objeto.getInt(Constantes.VariableEstado_idEstado));
                                    temp.setEstadoPublicidad(estado);
                                    Log.i("Inca", "Publicidad Recuperada :" + temp.getNombrePublicidad());

                                    Constantes.Base_ListaPublicidad.add(temp);
                                }
                                Log.i("Inca", "Servidor Listar Publicidad");

                                Progreso(56);
                                ListarProductosMasAlquilados(context);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Inca", "Error JSON Publicidad:" + e);
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
                params.put(OPERACION, "ListarPublicidad");
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void ListarProductosMasAlquilados(final Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray productos = jsonResponse.getJSONArray("productos");
                                for (int i = 0; i < productos.length(); i++) {
                                    JSONObject objeto = productos.getJSONObject(i);
                                    Producto temp = new Producto();
                                    temp.setIdProducto(objeto.getInt("idProducto"));
                                    temp.setNombreProducto(objeto.getString("NombreProducto"));
                                    temp.setDescripcionProducto(objeto.getString("DescripcionProducto"));
                                    temp.setImagenProducto(objeto.getString("imagenPortada"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Categoria categoria = new Categoria();
                                    categoria.setIdCategoria(objeto.getInt("Categoria_idCategoria"));
                                    temp.setCategoriaProducto(categoria);

                                    SubCategoria subCategoria = new SubCategoria();
                                    subCategoria.setIdSubCategoria(objeto.getInt("SubCategoria_idSubCategoria"));
                                    temp.setSubCategoriaProducto(subCategoria);

                                    UnidadTerritorial departamento = new UnidadTerritorial();
                                    departamento.setIdUnidadTerritorial(objeto.getInt("Departamento_idDepartamento"));
                                    departamento.setNombreUnidadTerritorial(objeto.getString("departamento"));
                                    temp.setDepartamentoProducto(departamento);

                                    UnidadTerritorial provincia = new UnidadTerritorial();
                                    provincia.setIdUnidadTerritorial(objeto.getInt("Provincia_idProvincia"));
                                    provincia.setNombreUnidadTerritorial(objeto.getString("provincia"));
                                    temp.setDepartamentoProducto(provincia);

                                    UnidadTerritorial distrito = new UnidadTerritorial();
                                    distrito.setIdUnidadTerritorial(objeto.getInt("Distrito_idDistrito"));
                                    distrito.setNombreUnidadTerritorial(objeto.getString("distrito"));
                                    temp.setDepartamentoProducto(distrito);

                                    Estado estadoProducto = new Estado();
                                    estadoProducto.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoProducto(estadoProducto);

                                    temp.setPrecioAlquiler(Double.parseDouble(objeto.getString("precioAlquiler")));
                                    temp.setPrecioVenta(Double.parseDouble(objeto.getString("precioVenta")));

                                    Constantes.Base_ListaProductoMasAlquilados.add(temp);

                                    Log.i("Inca", "Recupero Producto:" + temp.getNombreProducto());
                                }
                                Log.e("Inca", "Servidor Listar Productos");
                                Progreso(81);

                                ListarProductosTendencias(context);

                            } else {
                                Toast.makeText(context, "Productos no Disponibles.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca", "Error JSON:" + e);
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

    public void ListarProductosTendencias(final Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.GESTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                JSONArray productos = jsonResponse.getJSONArray("productos");
                                for (int i = 0; i < productos.length(); i++) {
                                    JSONObject objeto = productos.getJSONObject(i);
                                    Producto temp = new Producto();
                                    temp.setIdProducto(objeto.getInt("idProducto"));
                                    temp.setNombreProducto(objeto.getString("NombreProducto"));
                                    temp.setDescripcionProducto(objeto.getString("DescripcionProducto"));
                                    temp.setImagenProducto(objeto.getString("imagenPortada"));
                                    temp.setFechaRegistro(objeto.getString("fechaRegistro"));
                                    temp.setFechaUpdate(objeto.getString("fechaUpdate"));

                                    Categoria categoria = new Categoria();
                                    categoria.setIdCategoria(objeto.getInt("Categoria_idCategoria"));
                                    temp.setCategoriaProducto(categoria);

                                    SubCategoria subCategoria = new SubCategoria();
                                    subCategoria.setIdSubCategoria(objeto.getInt("SubCategoria_idSubCategoria"));
                                    temp.setSubCategoriaProducto(subCategoria);

                                    UnidadTerritorial departamento = new UnidadTerritorial();
                                    departamento.setIdUnidadTerritorial(objeto.getInt("Departamento_idDepartamento"));
                                    departamento.setNombreUnidadTerritorial(objeto.getString("departamento"));
                                    temp.setDepartamentoProducto(departamento);

                                    UnidadTerritorial provincia = new UnidadTerritorial();
                                    provincia.setIdUnidadTerritorial(objeto.getInt("Provincia_idProvincia"));
                                    provincia.setNombreUnidadTerritorial(objeto.getString("provincia"));
                                    temp.setDepartamentoProducto(provincia);

                                    UnidadTerritorial distrito = new UnidadTerritorial();
                                    distrito.setIdUnidadTerritorial(objeto.getInt("Distrito_idDistrito"));
                                    distrito.setNombreUnidadTerritorial(objeto.getString("distrito"));
                                    temp.setDepartamentoProducto(distrito);

                                    Estado estadoProducto = new Estado();
                                    estadoProducto.setIdEstado(objeto.getInt("Estado_idEstado"));
                                    temp.setEstadoProducto(estadoProducto);

                                    temp.setPrecioAlquiler(Double.parseDouble(objeto.getString("precioAlquiler")));
                                    temp.setPrecioVenta(Double.parseDouble(objeto.getString("precioVenta")));

                                    Constantes.Base_ListaProductoTendencias.add(temp);
                                    Log.i("Inca", "Recupero Producto Tendencias:" + temp.getNombreProducto());
                                }
                                Log.e("Inca", "Servidor Listar Productos Tendencias");
                                Progreso(100);
                                mover();

                            } else {
                                Toast.makeText(context, "Productos no Disponibles Tendencias.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Inca", "Error JSON:" + e);
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

    void mover() {

        Sesion sesion=new Sesion();
        Usuario usuarioRecuperado = sesion.RecuperarSesion(context);
        if (usuarioRecuperado.isSesionFB()) {
            Intent intent = new Intent(Splash.this, ActivityPrincipal.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            Intent intent = new Intent(Splash.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }
    public void Progreso(int tiempo) {
        p.setProgress(tiempo);
        txtProgress.setText("Cargando... " + tiempo + " %");
    }
}
