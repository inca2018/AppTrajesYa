package inca.jesus.trajesya.data.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class Conectividad {
    Context context;
    public Conectividad(Context context){
        this.context=context;
    }


    public boolean VerificarConexion(){
        boolean permiso;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    permiso=true;
                } else {
                    Toast.makeText(context, "¡Se perdió la conexión a Internet!", Toast.LENGTH_SHORT).show();
                    permiso=false;
                }
            } else {
                Toast.makeText(context, "¡Se perdió la conexión a Internet!", Toast.LENGTH_SHORT).show();
                permiso=false;
            }
        }catch (Exception e) {
        Log.i("Inca", String.valueOf(e));
            permiso=false;
        }

        return permiso;
    }
}
