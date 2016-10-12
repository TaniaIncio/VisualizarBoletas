package com.tincio.visualizarboletas.domain.callback;

import com.tincio.visualizarboletas.data.services.WWOEmpresa;

/**
 * Created by innovagmd on 16/09/16.
 */
public interface EmpresasCallback {

    void onResponse(WWOEmpresa empresas);
}
