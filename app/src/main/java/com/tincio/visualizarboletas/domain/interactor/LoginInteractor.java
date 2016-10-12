package com.tincio.visualizarboletas.domain.interactor;

import android.os.AsyncTask;

import com.tincio.visualizarboletas.data.request.UserRequest;
import com.tincio.visualizarboletas.data.services.WRHUsuarioDatos;
import com.tincio.visualizarboletas.data.services.WRHWSAutenticacionSoap11Binding;
import com.tincio.visualizarboletas.domain.callback.LoginCallback;

/**
 * Created by innovagmd on 16/09/16.
 */
public class LoginInteractor {

    LoginCallback callback;

    public LoginInteractor(LoginCallback callback){
        this.callback = callback;
    }

    public void logueUser(UserRequest request){
        LogueoUserTask task = new LogueoUserTask(request);
        task.execute();
    }

    private class LogueoUserTask extends AsyncTask<Void, Void, WRHUsuarioDatos>{

        UserRequest request= null;
        LogueoUserTask(UserRequest request){
            this.request = request;
        }

        @Override
        protected WRHUsuarioDatos doInBackground(Void... voids) {
            WRHUsuarioDatos datos=null;
            try{
                WRHWSAutenticacionSoap11Binding soap11Binding = new WRHWSAutenticacionSoap11Binding();
                datos=  soap11Binding.getUsuarioLogin(request.getCodAplicacion(), request.getIdEmpresa(), request.getUsername(),
                    request.getClave(), request.getIp(), request.getKeyIos(), request.getKeyAndroid());
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
        protected void onPostExecute(WRHUsuarioDatos wrhUsuarioDatos) {
            callback.onResponse(wrhUsuarioDatos);
        }
    }
}
