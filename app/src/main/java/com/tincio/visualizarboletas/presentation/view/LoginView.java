package com.tincio.visualizarboletas.presentation.view;

import com.tincio.visualizarboletas.data.services.WRHUsuarioDatos;

/**
 * Created by innovagmd on 16/09/16.
 */
public interface LoginView extends MvpView {

    void getUsuarioLogueado(WRHUsuarioDatos userLogueado);
}
