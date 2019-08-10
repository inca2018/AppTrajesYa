package inca.jesus.trajesya.Conectividad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkStateChecker extends BroadcastReceiver {
    //context and database helper object
    private Context context;


    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;


        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        //if there is a network
        if (activeNetwork != null ) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    //Internet
                //Toast.makeText(context, "Si tiene Conexión!", Toast.LENGTH_SHORT).show();
            }else{
                //Sin internet
                Toast.makeText(context, "No tiene Conexión a Internet!", Toast.LENGTH_SHORT).show();
            }
        }else{
            //Sin internet
            Toast.makeText(context, "No tiene Conexión a Internet!", Toast.LENGTH_SHORT).show();
        }
    }
}


