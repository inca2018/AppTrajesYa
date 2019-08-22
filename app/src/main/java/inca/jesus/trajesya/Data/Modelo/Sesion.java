package inca.jesus.trajesya.Data.Modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
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
        editor.putInt("idUsuario", usuario.getIdUsuario());
        editor.putString("KeyFacebook",usuario.getKeyFacebook());
        editor.putString("usuario", usuario.getUsuario());
        editor.putString("Correo",usuario.getCorreoUsuario());
        editor.putString("nombres", usuario.getNombreUsuario());
        editor.putString("apellidos", usuario.getApellidoUsuario());
        editor.putString("imagen", usuario.getImagenUsuario());
        editor.putInt("idPerfil", usuario.getPerfilUsuario().getIdPerfil());
        editor.putString("perfil", usuario.getPerfilUsuario().getNombrePrefil());
        editor.putBoolean("Sesion",usuario.isSesion());
        editor.putBoolean("SesionFB",usuario.isSesionFB());
        editor.commit();
    }
    public void RegistrarVariable(SharedPreferences.Editor editor, Context context, String variables, String tipo, String Datos){

        switch(tipo){
            case "int":
                int dato=Integer.parseInt(Datos);
                Log.i("Inca","Agregado Int Shared:"+dato);
                editor.putInt(variables,dato);
                editor.commit();
                break;
            case "String":
                Log.i("Inca","Agregado String Shared:"+Datos);
                editor.putString(variables,Datos);
                editor.commit();
                break;
            case "boolean":
                boolean datoB=Boolean.parseBoolean(Datos);
                Log.i("Inca","Agregado Boolean Shared:"+Datos);
                editor.putBoolean(variables,datoB);
                editor.commit();
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
        Temporal.setCorreoUsuario(pref.getString("Correo",""));
        Temporal.setApellidoUsuario(pref.getString("apellidos", ""));
        Temporal.setImagenUsuario(pref.getString("imagen", ""));
        Perfil perfil = new Perfil();
        perfil.setIdPerfil(pref.getInt("idPerfil", -1));
        perfil.setNombrePrefil(pref.getString("perfil", ""));
        Temporal.setPerfilUsuario(perfil);
        Temporal.setSesion(pref.getBoolean("Sesion",false));
        Temporal.setSesionFB(pref.getBoolean("SesionFB",false));
        return Temporal;
    }

    public void EliminarSesion(Context context){
        SharedPreferences settings = context.getSharedPreferences("Sesion", MODE_PRIVATE);
        settings.edit().clear().commit();
    }



}
