package com.tincio.visualizarboletas.presentation.presenter;

import com.tincio.visualizarboletas.data.request.ListadoBoletasBusquedaRequest;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosMobileResponse;
import com.tincio.visualizarboletas.domain.callback.ListadoBoletasBusquedaCallback;
import com.tincio.visualizarboletas.domain.interactor.ListadoBoletasBusquedaInteractor;
import com.tincio.visualizarboletas.presentation.view.ListadoBoletasBusquedaView;

/**
 * Created by innovagmd on 11/10/16.
 */
public class ListadoBoletasBusquedaPresenter implements ListadoBoletasBusquedaCallback {

    ListadoBoletasBusquedaView view;
    ListadoBoletasBusquedaInteractor interactor;
    public ListadoBoletasBusquedaPresenter(ListadoBoletasBusquedaView view){
        this.view = view;
        interactor = new ListadoBoletasBusquedaInteractor(this);
    }

    public void getListadoBoletas(ListadoBoletasBusquedaRequest request){
        interactor.getListadoBoletas(request);
    }

    @Override
    public void onResponse(WRHgetListadoDocumentosMobileResponse listaDocumentos) {
            view.getListaDocumentos(listaDocumentos);
    }
}
