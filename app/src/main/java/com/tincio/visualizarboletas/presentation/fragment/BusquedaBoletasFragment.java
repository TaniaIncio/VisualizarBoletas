
package com.tincio.visualizarboletas.presentation.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.request.ListadoBoletasBusquedaRequest;
import com.tincio.visualizarboletas.data.services.WRHDocumento;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosMobileResponse;
import com.tincio.visualizarboletas.presentation.adapter.RecyclerBoletasAdapter;
import com.tincio.visualizarboletas.presentation.presenter.ListadoBoletasBusquedaPresenter;
import com.tincio.visualizarboletas.presentation.view.ListadoBoletasBusquedaView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaBoletasFragment extends Fragment implements ListadoBoletasBusquedaView{

    public static final String TAG = "Buscar Boletas";
    @Bind(R.id.spinner_mes)
    MaterialSpinner spinnerMes;
    @Bind(R.id.spinner_anio)
    MaterialSpinner spinnerAnio;
    @Bind(R.id.rec_boletas_busqueda)
    RecyclerView rcvBoletas;
    RecyclerView.LayoutManager layoutManager;
    RecyclerBoletasAdapter adapter;

    SharedPreferences preferences;
    ListadoBoletasBusquedaPresenter presenter;
    public BusquedaBoletasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_busqueda_boletas, container, false);
        ButterKnife.bind(this, view);
        presenter = new ListadoBoletasBusquedaPresenter(this);
        layoutManager = new LinearLayoutManager(getContext());
        rcvBoletas.setLayoutManager(layoutManager);
        preferences = getActivity().getSharedPreferences(getString(R.string.preferences_app), Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinnerMes.setItems(getResources().getStringArray(R.array.order_meses));
        spinnerMes.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
               checkBusqueda();
            }
        });
        spinnerAnio.setItems(getResources().getStringArray(R.array.order_anio));
        spinnerAnio.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                checkBusqueda();
            }
        });
    }

    void checkBusqueda(){
        if(spinnerMes.getSelectedIndex()!=0 && spinnerAnio.getSelectedIndex()!=0){
            ListadoBoletasBusquedaRequest request = new ListadoBoletasBusquedaRequest();
            request.setNroDocumentoIdentificacion(preferences.getString(getString(R.string.preferences_user),""));
            request.setIdEmpresa(preferences.getInt(getString(R.string.preferences_idempresa),5));
            request.setIdTipoDocumento("1");
            Log.i("anio y mes",spinnerMes.getSelectedIndex()+" "+spinnerAnio.getItems().get(spinnerAnio.getSelectedIndex()).toString()+"_"+
                    spinnerMes.getItems().get(spinnerMes.getSelectedIndex()).toString());
            String mes = String.valueOf(spinnerMes.getSelectedIndex()).length()==1?"0"+String.valueOf(spinnerMes.getSelectedIndex()):
                    String.valueOf(spinnerMes.getSelectedIndex());
            Log.i("mes",spinnerAnio.getItems().get(spinnerAnio.getSelectedIndex()).toString()+"_"+mes);
            request.setPeriodo(spinnerAnio.getItems().get(spinnerAnio.getSelectedIndex()).toString()+"_"+mes);
            presenter.getListadoBoletas(request);
        }else{
            rcvBoletas.setVisibility(View.GONE);
        }
    }
    void visualizarBoleta(String periodo){
        FragmentTransaction Ft = getActivity().getSupportFragmentManager().beginTransaction();
        Bundle bunde = new Bundle();
        bunde.putString("periodo_envio",periodo);
        Ft.replace(R.id.frame_base, DetalleBoletaFragment.newInstance(bunde), TAG);
        Ft.addToBackStack(TAG);
        Ft.commit();
    }

    @Override
    public void getListaDocumentos(WRHgetListadoDocumentosMobileResponse documentos) {
        adapter = new RecyclerBoletasAdapter(documentos);

        rcvBoletas.setAdapter(adapter);
        rcvBoletas.setVisibility(View.VISIBLE);
        adapter.setOnItemClickLIstener(new RecyclerBoletasAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(WRHDocumento Boleta) {
                visualizarBoleta(Boleta.periodo);
            }
        });
    }
}
