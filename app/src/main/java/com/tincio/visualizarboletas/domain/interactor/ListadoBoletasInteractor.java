package com.tincio.visualizarboletas.domain.interactor;

import android.os.AsyncTask;

import com.tincio.visualizarboletas.data.request.ListadoBoletasRequest;
import com.tincio.visualizarboletas.data.request.UserRequest;
import com.tincio.visualizarboletas.data.services.WRHUsuarioDatos;
import com.tincio.visualizarboletas.data.services.WRHWSAutenticacionSoap11Binding;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosNoRevisadosResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosResponse;
import com.tincio.visualizarboletas.domain.callback.ListadoBoletasCallback;
import com.tincio.visualizarboletas.domain.callback.LoginCallback;

/**
 * Created by innovagmd on 16/09/16.
 */
public class ListadoBoletasInteractor {

    ListadoBoletasCallback callback;

    public ListadoBoletasInteractor(ListadoBoletasCallback callback){
        this.callback = callback;
    }

    public void getListadoBoletas(ListadoBoletasRequest request){
        ListadoBoletasTask task = new ListadoBoletasTask(request);
        task.execute();
    }

    public void getListadoBoletasNoLeidos(ListadoBoletasRequest request){
        ListadoBoletasNoLeidosTask task = new ListadoBoletasNoLeidosTask(request);
        task.execute();
    }

    private class ListadoBoletasTask extends AsyncTask<Void, Void, WRHgetListadoDocumentosResponse>{

        ListadoBoletasRequest request= null;
        ListadoBoletasTask(ListadoBoletasRequest request){
            this.request = request;
        }

        @Override
        protected WRHgetListadoDocumentosResponse doInBackground(Void... voids) {
            WRHgetListadoDocumentosResponse datos=null;
            try{
                WRHWSAutenticacionSoap11Binding soap11Binding = new WRHWSAutenticacionSoap11Binding();
                datos=  soap11Binding.getListadoDocumentos(request.getCodAplicacion(), request.getIdEmpresa(), request.getNroDocumentoIdentificacion(),
                        request.getIdTipoDocumento());
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
        protected void onPostExecute(WRHgetListadoDocumentosResponse datos) {
            callback.onResponse(datos);
        }
    }

    //boletas no leidas
    private class ListadoBoletasNoLeidosTask extends AsyncTask<Void, Void, WRHgetListadoDocumentosNoRevisadosResponse>{

        ListadoBoletasRequest request= null;
        ListadoBoletasNoLeidosTask(ListadoBoletasRequest request){
            this.request = request;
        }

        @Override
        protected WRHgetListadoDocumentosNoRevisadosResponse doInBackground(Void... voids) {
            WRHgetListadoDocumentosNoRevisadosResponse datos=null;
            try{
                WRHWSAutenticacionSoap11Binding soap11Binding = new WRHWSAutenticacionSoap11Binding();
                datos=  soap11Binding.getListadoDocumentosNoRevisados(request.getCodAplicacion(), request.getIdEmpresa(), request.getNroDocumentoIdentificacion(),
                        request.getIdTipoDocumento());
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
        protected void onPostExecute(WRHgetListadoDocumentosNoRevisadosResponse datos) {
            callback.onResponseNoLeidos(datos);
        }
    }
}