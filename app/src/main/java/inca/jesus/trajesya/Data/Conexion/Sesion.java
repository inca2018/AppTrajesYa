package inca.jesus.trajesya.Data.Conexion;

import android.content.Context;
import android.content.SharedPreferences;

import inca.jesus.trajesya.Data.Modelo.Perfil;
import inca.jesus.trajesya.Data.Modelo.Usuario;

import static android.content.Context.MODE_PRIVATE;

public class Sesion {


    public final static String PATH="trajesya.com";
    public final static String LOGIN=PATH+"/";


    public Sesion(){
    }


    public void RegistrarSesion(Context context, Usuario usuario) {
        // Registrar Informacion de usuario en SharedPreferences  para reutilizacion
        SharedPreferences pref = context.getSharedPreferences("Sesion", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("idUsuario", usuario.getIdUsuario());
        editor.putString("usuario", usuario.getUsuario());
        editor.putString("nombres", usuario.getNombreUsuario());
        editor.putString("apellidos", usuario.getApellidoUsuario());
        editor.putString("imagen", usuario.getImagenUsuario());
        editor.putInt("idPerfil", usuario.getPerfilUsuario().getIdPerfil());
        editor.putString("perfil", usuario.getPerfilUsuario().getNombrePrefil());
        editor.commit();
    }

    public Usuario RecuperarSesion(Context context) {
        // Recuperar Informacion de SharedPreferences y Setear a un Objeto Usuario para reusar sus valores
        Usuario Temporal = new Usuario();
        SharedPreferences pref = context.getSharedPreferences("Sesion", MODE_PRIVATE);
        Temporal.setIdUsuario(pref.getInt("idUsuario", -1));
        Temporal.setUsuario(pref.getString("usuario", ""));
        Temporal.setNombreUsuario(pref.getString("nombres", ""));
        Temporal.setApellidoUsuario(pref.getString("apellidos", ""));
        Temporal.setImagenUsuario(pref.getString("imagen", ""));
        Perfil perfil = new Perfil();
        perfil.setIdPerfil(pref.getInt("idPerfil", -1));
        perfil.setNombrePrefil(pref.getString("perfil", ""));
        Temporal.setPerfilUsuario(perfil);
        return Temporal;
    }


}
