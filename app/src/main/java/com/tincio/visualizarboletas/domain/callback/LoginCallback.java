package com.tincio.visualizarboletas.domain.callback;

import com.tincio.visualizarboletas.data.services.WWOUsuarioClave;

/**
 * Created by innovagmd on 16/09/16.
 */
public interface LoginCallback {

    void onResponse(WWOUsuarioClave userLogueado);
}
