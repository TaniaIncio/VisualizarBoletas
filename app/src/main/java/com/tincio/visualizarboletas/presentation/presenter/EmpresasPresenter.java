package com.tincio.visualizarboletas.presentation.presenter;

import com.tincio.visualizarboletas.data.services.WRHgetEmpresasResponse;
import com.tincio.visualizarboletas.domain.callback.EmpresasCallback;
import com.tincio.visualizarboletas.domain.interactor.EmpresasInteractor;
import com.tincio.visualizarboletas.presentation.view.SplashView;

/**
 * Created by innovagmd on 11/10/16.
 */
public class EmpresasPresenter implements EmpresasCallback{

    SplashView view;
    EmpresasInteractor interactor;

    public EmpresasPresenter(SplashView view){
        this.view = view;
        interactor = new EmpresasInteractor(this);
    }

    public void getEmpresas(){
        interactor.getEmpresas();
    }
    @Override
    public void onResponse(WRHgetEmpresasResponse empresas) {
        view.getEmpresas(empresas);
    }
}
