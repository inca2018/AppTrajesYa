package inca.jesus.trajesya.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 15/06/2017.
 */

public class Videos {
    String ruta;

    public static final List<Videos> ListVideos = new ArrayList<Videos>();

    public Videos(){
    }

    static{
        ListVideos.add(new Videos(""));
    }



    public Videos(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
