package com.tincio.visualizarboletas.domain.interactor;

import android.os.AsyncTask;

import com.tincio.visualizarboletas.data.request.DetalleBoletasRequest;
import com.tincio.visualizarboletas.data.services.JHADocumentoWS;
import com.tincio.visualizarboletas.data.services.WRHWSAutenticacionSoap11Binding;
import com.tincio.visualizarboletas.domain.callback.DetalleBoletaCallback;

/**
 * Created by innovagmd on 25/10/16.
 */
public class DetalleBoletasInteractor {

    DetalleBoletaCallback callback;

    public DetalleBoletasInteractor(DetalleBoletaCallback callback){
        this.callback = callback;
    }

    public void getDetalleBoletas(DetalleBoletasRequest request){
        DetalleBoletasTask task = new DetalleBoletasTask(request);
        task.execute();
    }

    private class DetalleBoletasTask extends AsyncTask<Void, Void, JHADocumentoWS> {

        DetalleBoletasRequest request= null;
        DetalleBoletasTask(DetalleBoletasRequest request){
            this.request = request;
        }

        @Override
        protected JHADocumentoWS doInBackground(Void... voids) {
            JHADocumentoWS datos=null;
            try{
                WRHWSAutenticacionSoap11Binding soap11Binding = new WRHWSAutenticacionSoap11Binding();
                datos=  soap11Binding.obtenerPdf(request.getsPeriodo(), request.getIdTipoDocumento(), request.getCodAplicacion(),request.getDniUsuario(),
                        request.getClave(),request.getIsEmpresa(),request.getIpUsuario());
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
        protected void onPostExecute(JHADocumentoWS datos) {
            callback.onResponse(datos);
        }
    }
}
