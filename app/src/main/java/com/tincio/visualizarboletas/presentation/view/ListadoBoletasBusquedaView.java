package com.tincio.visualizarboletas.presentation.view;

import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosMobileResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosNoRevisadosResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosResponse;

/**
 * Created by innovagmd on 15/10/16.
 */
public interface ListadoBoletasBusquedaView {
    void getListaDocumentos(WRHgetListadoDocumentosMobileResponse documentos);

}
