package inca.jesus.trajesya.Clases;

import android.app.Application;

import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Jesus on 12/06/2017.
 */

public class MarketAPP extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        AppEventsLogger.activateApp(this);
    }
}
