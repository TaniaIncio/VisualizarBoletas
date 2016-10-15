package com.tincio.visualizarboletas.presentation.presenter;

import com.tincio.visualizarboletas.data.request.CambioClaveRequest;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosMobileResponse;
import com.tincio.visualizarboletas.data.services.WWOUsuarioClave;
import com.tincio.visualizarboletas.domain.callback.CambioClaveCallback;
import com.tincio.visualizarboletas.domain.interactor.CambioClaveInteractor;
import com.tincio.visualizarboletas.presentation.view.CambioClaveView;

/**
 * Created by innovagmd on 11/10/16.
 */
public class CambioClavePresenter implements CambioClaveCallback {

    CambioClaveView view;
    CambioClaveInteractor interactor;
    public CambioClavePresenter(CambioClaveView view){
        this.view = view;
        interactor = new CambioClaveInteractor(this);
    }

    public void cambioClave(CambioClaveRequest request){
        interactor.cambioClave(request);
    }

    @Override
    public void onResponse(WWOUsuarioClave clave) {
            view.responseCambioClave(clave);
    }
}
