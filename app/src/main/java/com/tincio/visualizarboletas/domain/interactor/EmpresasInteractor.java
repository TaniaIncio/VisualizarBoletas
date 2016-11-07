package com.tincio.visualizarboletas.domain.interactor;

import android.os.AsyncTask;

import com.tincio.visualizarboletas.data.services.WRHWSAutenticacionSoap11Binding;
import com.tincio.visualizarboletas.data.services.WRHgetEmpresasResponse;
import com.tincio.visualizarboletas.domain.callback.EmpresasCallback;

/**
 * Created by innovagmd on 16/09/16.
 */
public class EmpresasInteractor {

    EmpresasCallback callback;

    public EmpresasInteractor(EmpresasCallback callback){
        this.callback = callback;
    }

    public void getEmpresas(){
        EmpresasTask task = new EmpresasTask();
        task.execute();
    }


    private class EmpresasTask extends AsyncTask<Void, Void, WRHgetEmpresasResponse>{

        @Override
        protected WRHgetEmpresasResponse doInBackground(Void... voids) {
            WRHgetEmpresasResponse datos=null;
            try{
                WRHWSAutenticacionSoap11Binding soap11Binding = new WRHWSAutenticacionSoap11Binding();
                datos=  soap11Binding.getEmpresas();
            }catch(Exception e){
                e.printStackTrace();
            }
            return datos;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(WRHgetEmpresasResponse datos) {
            callback.onResponse(datos);
        }
    }
}