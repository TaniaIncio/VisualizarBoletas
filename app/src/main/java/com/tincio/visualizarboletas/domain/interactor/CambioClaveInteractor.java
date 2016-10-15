package com.tincio.visualizarboletas.domain.interactor;

import android.os.AsyncTask;

import com.tincio.visualizarboletas.data.request.CambioClaveRequest;
import com.tincio.visualizarboletas.data.services.WRHWSAutenticacionSoap11Binding;
import com.tincio.visualizarboletas.data.services.WWOUsuarioClave;
import com.tincio.visualizarboletas.domain.callback.CambioClaveCallback;

/**
 * Created by innovagmd on 16/09/16.
 */
public class CambioClaveInteractor {

    CambioClaveCallback callback;
    public CambioClaveInteractor(CambioClaveCallback callback){
        this.callback = callback;
    }

    public void cambioClave(CambioClaveRequest request){
        CambioClaveTask task = new CambioClaveTask(request);
        task.execute();
    }

    private class CambioClaveTask extends AsyncTask<Void, Void, WWOUsuarioClave>{

        CambioClaveRequest request= null;
        CambioClaveTask(CambioClaveRequest request){
            this.request = request;
        }

        @Override
        protected WWOUsuarioClave doInBackground(Void... voids) {
            WWOUsuarioClave datos=null;
            try{
                WRHWSAutenticacionSoap11Binding soap11Binding = new WRHWSAutenticacionSoap11Binding();
                datos=  soap11Binding.getCambioClave(request.getCodAplicacion(), request.getIdEmpresa(), request.getNomUsuario(), request.getClave(),
                        request.getNuevaClave());
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
        protected void onPostExecute(WWOUsuarioClave datos) {
            callback.onResponse(datos);
        }
    }

}