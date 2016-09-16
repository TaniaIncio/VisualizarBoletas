package com.tincio.visualizarboletas.presentation.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by juan on 25/05/2016.
 */
public class Images {
    public static Drawable getDrawableByName(Context context, String name){
        Drawable drawable = null;
        try{
            int resourceId = context.getResources().getIdentifier(name,"mipmap",context.getPackageName());
            drawable =  context.getResources().getDrawable(resourceId);
        }catch(Exception e){
            throw e;
        }
        return drawable;
    }
}
