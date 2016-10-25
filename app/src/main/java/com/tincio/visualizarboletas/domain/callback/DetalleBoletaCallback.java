package com.tincio.visualizarboletas.domain.callback;

import com.tincio.visualizarboletas.data.services.JHADocumentoWS;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosNoRevisadosResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosResponse;

/**
 * Created by innovagmd on 25/10/16.
 */
public interface DetalleBoletaCallback {

    void onResponse(JHADocumentoWS listaDocumentos);
}
