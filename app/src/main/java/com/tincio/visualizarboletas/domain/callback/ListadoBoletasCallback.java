package com.tincio.visualizarboletas.domain.callback;


import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosMobileResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosNoRevisadosResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosResponse;

/**
 * Created by innovagmd on 16/09/16.
 */
public interface ListadoBoletasCallback {
    //void onResponse(WRHgetListadoDocumentosResponse listaDocumentos);
    void onResponse(WRHgetListadoDocumentosMobileResponse listaDocumentos);


    void onResponseNoLeidos(WRHgetListadoDocumentosNoRevisadosResponse listaDocumentos);

}
