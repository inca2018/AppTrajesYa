package inca.jesus.trajesya.Data.Modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Switch;

import inca.jesus.trajesya.Clases.Perfil;
import inca.jesus.trajesya.Data.Modelo.Usuario;

import static android.content.Context.MODE_PRIVATE;

public class Sesion {

    public Sesion(){
    }

    public void RegistrarSesion(Context context, Usuario usuario) {
        // Registrar Informacion de usuario en SharedPreferences  para reutilizacion
        SharedPreferences pref = context.getSharedPreferences("Sesion", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("Login",usuario.isSesion());
        editor.putInt("idUsuario", usuario.getIdUsuario());
        editor.putString("KeyFacebook",usuario.getKeyFacebook());
        editor.putString("usuario", usuario.getUsuario());
        editor.putString("nombres", usuario.getNombreUsuario());
        editor.putString("apellidos", usuario.getApellidoUsuario());
        editor.putString("imagen", usuario.getImagenUsuario());
        editor.putInt("idPerfil", usuario.getPerfilUsuario().getIdPerfil());
        editor.putString("perfil", usuario.getPerfilUsuario().getNombrePrefil());
        editor.commit();
    }
    public void RegistrarVariable(SharedPreferences.Editor editor, Context context, String variables, String tipo, String Datos){

        switch(tipo){
            case "int":
                int dato=Integer.parseInt(Datos);
                editor.putInt(variables,dato);
                break;
            case "String":
                editor.putString(variables,Datos);
                break;
            case "boolean":
                boolean datoB=Boolean.parseBoolean(Datos);
                editor.putBoolean(variables,datoB);
                break;
        }
    }

    public Usuario RecuperarSesion(Context context) {
        // Recuperar Informacion de SharedPreferences y Setear a un Objeto Usuario para reusar sus valores
        Usuario Temporal = new Usuario();
        SharedPreferences pref = context.getSharedPreferences("Sesion", context.MODE_PRIVATE);
        Temporal.setIdUsuario(pref.getInt("idUsuario", -1));
        Temporal.setKeyFacebook(pref.getString("KeyFacebook",""));
        Temporal.setUsuario(pref.getString("usuario", ""));
        Temporal.setNombreUsuario(pref.getString("nombres", ""));
        Temporal.setApellidoUsuario(pref.getString("apellidos", ""));
        Temporal.setImagenUsuario(pref.getString("imagen", ""));
        Perfil perfil = new Perfil();
        perfil.setIdPerfil(pref.getInt("idPerfil", -1));
        perfil.setNombrePrefil(pref.getString("perfil", ""));
        Temporal.setPerfilUsuario(perfil);
        Temporal.setSesion(pref.getBoolean("Login",false));
        return Temporal;
    }

    public void EliminarSesion(Context context){
        SharedPreferences settings = context.getSharedPreferences("Sesion", MODE_PRIVATE);
        settings.edit().clear().commit();
    }



}
