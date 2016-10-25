package com.tincio.visualizarboletas.presentation.presenter;

import com.tincio.visualizarboletas.data.request.DetalleBoletasRequest;
import com.tincio.visualizarboletas.data.request.ListadoBoletasRequest;
import com.tincio.visualizarboletas.data.services.JHADocumentoWS;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosNoRevisadosResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosResponse;
import com.tincio.visualizarboletas.domain.callback.DetalleBoletaCallback;
import com.tincio.visualizarboletas.domain.callback.ListadoBoletasCallback;
import com.tincio.visualizarboletas.domain.interactor.DetalleBoletasInteractor;
import com.tincio.visualizarboletas.domain.interactor.ListadoBoletasInteractor;
import com.tincio.visualizarboletas.presentation.view.DetalleBoletasFragmentView;
import com.tincio.visualizarboletas.presentation.view.ListadoBoletasFragmentView;

/**
 * Created by innovagmd on 11/10/16.
 */
public class DetalleBoletasPresenter implements DetalleBoletaCallback{

    DetalleBoletasFragmentView view;
    DetalleBoletasInteractor interactor;
    public DetalleBoletasPresenter(DetalleBoletasFragmentView view){
        this.view = view;
        interactor = new DetalleBoletasInteractor(this);
    }

    public void getDetalleBoletas(DetalleBoletasRequest request){
        interactor.getDetalleBoletas(request);
    }

    @Override
    public void onResponse(JHADocumentoWS listaDocumentos) {
        view.getBoletasPdf(listaDocumentos);
    }
}
