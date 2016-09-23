package com.tincio.visualizarboletas.domain.interactor;

import com.tincio.visualizarboletas.domain.callback.LoginCallback;

/**
 * Created by innovagmd on 16/09/16.
 */
public class LoginInteractor {

    LoginCallback callback;

    public LoginInteractor(LoginCallback callback){
        this.callback = callback;
    }

    void getUserLogueado(){

    }
}
