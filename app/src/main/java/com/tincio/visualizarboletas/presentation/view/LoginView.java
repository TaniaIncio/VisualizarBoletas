package com.tincio.visualizarboletas.presentation.view;

import com.tincio.visualizarboletas.data.services.CULUsuarioDatos;
/**
 * Created by innovagmd on 16/09/16.
 */
public interface LoginView extends MvpView {

    void getUsuarioLogueado(CULUsuarioDatos userLogueado);
}
