package com.tincio.visualizarboletas.presentation;

import android.app.Application;

import com.tincio.visualizarboletas.data.services.WWOEmpresa;

import java.util.ArrayList;

/**
 * Created by juan on 07/11/2016.
 */

public class VisualizarBoletasApplication extends Application {
    VisualizarBoletasApplication application;
    static ArrayList<WWOEmpresa> listaEmpresa;

    public static ArrayList<WWOEmpresa>  getEmpresas(){
        return listaEmpresa;
    }
    public static void setEmpresas(ArrayList<WWOEmpresa> listaEmpresas){
        listaEmpresa= listaEmpresas;
    }

}
