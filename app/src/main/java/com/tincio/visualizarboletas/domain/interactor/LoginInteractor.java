package com.tincio.visualizarboletas.domain.interactor;

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

        WRHWSAutenticacionSoap11Binding soap11Binding = new WRHWSAutenticacionSoap11Binding();
        //getUsuarioLogin(final Integer codAplicacion,final Integer idEmpresa,final String usuario,final String clave,final String ip,final String keyios,final String keyandroid ) throws Exception
        try{
            WRHUsuarioDatos dataUser= soap11Binding.getUsuarioLogin(request.getCodAplicacion(), request.getIdEmpresa(), request.getUsername(),
                    request.getClave(), request.getIp(), request.getKeyIos(), request.getKeyAndroid());
            callback.onResponse(dataUser);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
