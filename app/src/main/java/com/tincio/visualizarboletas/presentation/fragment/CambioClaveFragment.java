package com.tincio.visualizarboletas.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.request.CambioClaveRequest;
import com.tincio.visualizarboletas.data.services.WWOUsuarioClave;
import com.tincio.visualizarboletas.presentation.presenter.CambioClavePresenter;
import com.tincio.visualizarboletas.presentation.view.CambioClaveView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CambioClaveFragment extends Fragment implements CambioClaveView{

    @Bind(R.id.txt_clave_actual)
    TextView txtClaveActual;
    @Bind(R.id.txt_clave_nueva)
    TextView txtClaveNueva;
    @Bind(R.id.txt_clave_nueva_repeat)
    TextView txtClaveNuevaRepeat;

    CambioClavePresenter presenter;

    public static final String TAG=CambioClaveFragment.class.getSimpleName();
    public CambioClaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cambio_clave, container, false);
        ButterKnife.bind(this, view);
        presenter = new CambioClavePresenter(this);
        return view;
    }

    @OnClick(R.id.linear_guardar_clave)
    void onClickGuardarClave(){
        CambioClaveRequest request = new CambioClaveRequest();
        request.setCodAplicacion("1");
        request.setClave("80605606");
        request.setIdEmpresa("80605606");
        request.setNomUsuario("80605606");
        request.setNuevaClave("80605606");
        presenter.cambioClave(request);
    }

    @Override
    public void responseCambioClave(WWOUsuarioClave clave) {
        if(clave.resultado){
            getActivity().onBackPressed();
        }else{
            Toast.makeText(getActivity(),"", Toast.LENGTH_SHORT).show();
        }
    }
}
