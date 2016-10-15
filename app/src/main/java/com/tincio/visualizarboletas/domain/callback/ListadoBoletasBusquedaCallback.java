package com.tincio.visualizarboletas.domain.callback;


import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosMobileResponse;

/**
 * Created by innovagmd on 16/09/16.
 */
public interface ListadoBoletasBusquedaCallback {
    void onResponse(WRHgetListadoDocumentosMobileResponse listaDocumentos);

}
