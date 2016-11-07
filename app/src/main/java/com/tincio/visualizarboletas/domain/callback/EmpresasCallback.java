package com.tincio.visualizarboletas.domain.callback;

import com.tincio.visualizarboletas.data.services.WRHgetEmpresasResponse;

/**
 * Created by innovagmd on 16/09/16.
 */
public interface EmpresasCallback {

    void onResponse(WRHgetEmpresasResponse empresas);
}
