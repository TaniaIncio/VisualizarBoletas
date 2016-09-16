package com.tincio.visualizarboletas.presentation.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.tincio.visualizarboletas.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaBoletasFragment extends Fragment {

    public static final String TAG = "Buscar Boletas";
    @Bind(R.id.spinner_mes)
    MaterialSpinner spinnerMes;
    @Bind(R.id.spinner_anio)
    MaterialSpinner spinnerAnio;
    @Bind(R.id.linear_boleta_seleccionada)
    LinearLayout linearBoletaSeleccionada;
    @Bind(R.id.linear_row_boletas)
    LinearLayout linearRowBoleta;
    public BusquedaBoletasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_busqueda_boletas, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinnerMes.setItems("Seleccione","Enero", "Febrero", "Marzo", "Abril", "Mayo","Junio","Julio","Agosto","Setiembre",
                "Octubre","Noviembre","Diciembre");
        spinnerMes.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
               checkBusqueda();
            }
        });
        spinnerAnio.setItems("Seleccione","2013", "2014", "2015", "2016");
        spinnerAnio.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                checkBusqueda();
            }
        });
        linearRowBoleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visualizarBoleta();
            }
        });
    }

    void checkBusqueda(){
        if(spinnerMes.getSelectedIndex()!=0 && spinnerAnio.getSelectedIndex()!=0){
            linearBoletaSeleccionada.setVisibility(View.VISIBLE);
        }else
            linearBoletaSeleccionada.setVisibility(View.GONE);
    }
    void visualizarBoleta(){
        /*Uri uri = Uri.parse("http://www.google.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*/
        FragmentTransaction Ft = getActivity().getSupportFragmentManager().beginTransaction();
        Ft.replace(R.id.frame_base, new DetalleBoletaFragment(), TAG);
        Ft.addToBackStack(TAG);
        Ft.commit();
    }
}
