package com.tincio.visualizarboletas.presentation.fragment;


import android.content.SharedPreferences;
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
    SharedPreferences preferences;

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
        preferences = getActivity().getSharedPreferences(getString(R.string.preferences_app), getActivity().MODE_PRIVATE);
        return view;
    }

    @OnClick(R.id.linear_guardar_clave)
    void onClickGuardarClave(){
        if(verificar()){
            CambioClaveRequest request = new CambioClaveRequest();
            request.setCodAplicacion("1");
            request.setClave(txtClaveActual.getText().toString());
            request.setIdEmpresa(String.valueOf(preferences.getInt(getString(R.string.preferences_idempresa),1)));
            request.setNomUsuario(txtClaveActual.getText().toString());
            request.setNuevaClave(txtClaveNueva.getText().toString());
            presenter.cambioClave(request);
        }

    }

    @Override
    public void responseCambioClave(WWOUsuarioClave clave) {
        if(clave.resultado){
            getActivity().onBackPressed();
        }else{
            Toast.makeText(getActivity(),"", Toast.LENGTH_SHORT).show();
        }
    }

    Boolean verificar(){
        if(txtClaveActual.getText().toString().equals("")){
            Toast.makeText(getActivity(),"Por favor ingrese su clave actual", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(txtClaveNueva.getText().toString().equals("")){
                Toast.makeText(getActivity(),"Por favor ingrese su nueva clave", Toast.LENGTH_SHORT).show();
                return false;
            }else{
                if(txtClaveNuevaRepeat.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Por favor repita su nueva clave", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        if(!txtClaveNueva.getText().toString().equals(txtClaveNuevaRepeat.getText().toString())){
            Toast.makeText(getActivity(),"Deben coincidir las nuevas claves", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
