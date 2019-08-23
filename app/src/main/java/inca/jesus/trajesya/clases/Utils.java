package inca.jesus.trajesya.clases;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import inca.jesus.trajesya.R;

/**
 * Clase de utilidades
 */
public class Utils {

    public static void setBadgeCount(Context context, LayerDrawable icon, int count) {

        BadgeDrawable badge;

        // Reusar drawable
        Drawable reuse = icon.findDrawableByLayerId(R.id.action_4);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.action_4, badge);
    }
}