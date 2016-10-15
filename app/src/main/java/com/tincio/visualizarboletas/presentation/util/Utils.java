package com.tincio.visualizarboletas.presentation.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.format.Formatter;

import com.tincio.visualizarboletas.R;

import java.util.Date;


/**
 * Created by tincio on 12/09/2016.
 */
public class Utils {

    public static ProgressDialog showProgressDialog(Context ctx){
        ProgressDialog dialog;
        dialog=ProgressDialog.show(ctx,null, null);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.progress_layout);

        return dialog;
    }

    public static Drawable getDrawableByName(Context context, String name){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(context.getResources().getIdentifier(name,"mipmap",context.getPackageName()),null);
        } else {
            return context.getResources().getDrawable(context.getResources().getIdentifier(name,"mipmap",context.getPackageName()));
        }
    }

    public static String getIp(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        return ipAddress;
    }

    public static String getDay(Date date){
        String day = (String) android.text.format.DateFormat.format("dd", date);
        return day;
    }

    public static String getMonthName(Date date){
        String stringMonth = (String) android.text.format.DateFormat.format("MMM", date);
        return stringMonth;
    }

    public static String getMonth(Date date){
        return  (String) android.text.format.DateFormat.format("MM", date); //06
    }

    public static String getYear(Date date){
        return (String) android.text.format.DateFormat.format("yyyy", date);
    }
}
