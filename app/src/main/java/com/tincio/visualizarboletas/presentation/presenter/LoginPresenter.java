package com.tincio.visualizarboletas.presentation.presenter;

import com.tincio.visualizarboletas.data.request.UserRequest;
import com.tincio.visualizarboletas.data.services.WRHUsuarioDatos;
import com.tincio.visualizarboletas.domain.callback.LoginCallback;
import com.tincio.visualizarboletas.domain.interactor.LoginInteractor;
import com.tincio.visualizarboletas.presentation.view.LoginView;

/**
 * Created by innovagmd on 11/10/16.
 */
public class LoginPresenter implements LoginCallback{

    LoginView view;
    LoginInteractor interactor;
    public LoginPresenter(LoginView view){
        this.view = view;
        interactor = new LoginInteractor(this);
    }

    public void logueoUser(UserRequest request){
        interactor.logueUser(request);
    }

    @Override
    public void onResponse(WRHUsuarioDatos userLogueado) {
            view.getUsuarioLogueado(userLogueado);
    }
}
