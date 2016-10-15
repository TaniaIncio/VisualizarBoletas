package com.tincio.visualizarboletas.presentation.presenter;

import com.tincio.visualizarboletas.data.request.ListadoBoletasRequest;
import com.tincio.visualizarboletas.data.request.UserRequest;
import com.tincio.visualizarboletas.data.services.WRHUsuarioDatos;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosNoRevisadosResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosResponse;
import com.tincio.visualizarboletas.domain.callback.ListadoBoletasCallback;
import com.tincio.visualizarboletas.domain.interactor.ListadoBoletasInteractor;
import com.tincio.visualizarboletas.presentation.view.ListadoBoletasFragmentView;

/**
 * Created by innovagmd on 11/10/16.
 */
public class ListadoBoletasPresenter implements ListadoBoletasCallback{

    ListadoBoletasFragmentView view;
    ListadoBoletasInteractor interactor;
    public ListadoBoletasPresenter(ListadoBoletasFragmentView view){
        this.view = view;
        interactor = new ListadoBoletasInteractor(this);
    }

    public void getListadoBoletas(ListadoBoletasRequest request){
        interactor.getListadoBoletas(request);
    }

    public void getListadoBoletasNoLeidos(ListadoBoletasRequest request){
        interactor.getListadoBoletasNoLeidos(request);
    }

    @Override
    public void onResponse(WRHgetListadoDocumentosResponse listaDocumentos) {
            view.getListaDocumentos(listaDocumentos);
    }

    @Override
    public void onResponseNoLeidos(WRHgetListadoDocumentosNoRevisadosResponse listaDocumentos) {
        view.getListaDocumentosNoLeidos(listaDocumentos);
    }
}
