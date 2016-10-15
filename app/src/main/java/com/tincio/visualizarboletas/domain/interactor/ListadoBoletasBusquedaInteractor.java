package com.tincio.visualizarboletas.domain.interactor;

import android.os.AsyncTask;

import com.tincio.visualizarboletas.data.request.ListadoBoletasBusquedaRequest;
import com.tincio.visualizarboletas.data.request.ListadoBoletasRequest;
import com.tincio.visualizarboletas.data.services.WRHWSAutenticacionSoap11Binding;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosMobileResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosNoRevisadosResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosResponse;
import com.tincio.visualizarboletas.domain.callback.ListadoBoletasBusquedaCallback;
import com.tincio.visualizarboletas.domain.callback.ListadoBoletasCallback;

/**
 * Created by innovagmd on 16/09/16.
 */
public class ListadoBoletasBusquedaInteractor {

    ListadoBoletasBusquedaCallback callback;

    public ListadoBoletasBusquedaInteractor(ListadoBoletasBusquedaCallback callback){
        this.callback = callback;
    }

    public void getListadoBoletas(ListadoBoletasBusquedaRequest request){
        ListadoBoletasTask task = new ListadoBoletasTask(request);
        task.execute();
    }

    private class ListadoBoletasTask extends AsyncTask<Void, Void, WRHgetListadoDocumentosMobileResponse>{

        ListadoBoletasBusquedaRequest request= null;
        ListadoBoletasTask(ListadoBoletasBusquedaRequest request){
            this.request = request;
        }

        @Override
        protected WRHgetListadoDocumentosMobileResponse doInBackground(Void... voids) {
            WRHgetListadoDocumentosMobileResponse datos=null;
            try{
                WRHWSAutenticacionSoap11Binding soap11Binding = new WRHWSAutenticacionSoap11Binding();
                datos=  soap11Binding.getListadoDocumentosMobile(request.getIdEmpresa(), request.getNroDocumentoIdentificacion(), request.getIdTipoDocumento(), request.getPeriodo());
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
        protected void onPostExecute(WRHgetListadoDocumentosMobileResponse datos) {
            callback.onResponse(datos);
        }
    }

}