package com.tincio.visualizarboletas.presentation.view;
import com.tincio.visualizarboletas.data.services.WRHgetEmpresasResponse;

/**
 * Created by innovagmd on 16/09/16.
 */
public interface SplashView extends MvpView {

    void getEmpresas(WRHgetEmpresasResponse empresas);
}
