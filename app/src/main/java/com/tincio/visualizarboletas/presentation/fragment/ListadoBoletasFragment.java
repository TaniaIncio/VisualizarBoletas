package com.tincio.visualizarboletas.presentation.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.request.ListadoBoletasBusquedaRequest;
import com.tincio.visualizarboletas.data.request.ListadoBoletasRequest;
import com.tincio.visualizarboletas.data.services.WRHDocumento;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosMobileResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosNoRevisadosResponse;
import com.tincio.visualizarboletas.data.services.WRHgetListadoDocumentosResponse;
import com.tincio.visualizarboletas.presentation.adapter.RecyclerBoletasAdapter;
import com.tincio.visualizarboletas.data.model.Boleta;
import com.tincio.visualizarboletas.presentation.presenter.ListadoBoletasPresenter;
import com.tincio.visualizarboletas.presentation.util.Images;
import com.tincio.visualizarboletas.presentation.view.ListadoBoletasFragmentView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoBoletasFragment extends Fragment implements ListadoBoletasFragmentView{

    public static final String TAG="Mis Boletas";
    @Bind(R.id.rec_boletas)
    RecyclerView rcvBoletas;
    RecyclerView.LayoutManager layoutManager;
    RecyclerBoletasAdapter adapter;
    @Bind(R.id.linear_noleidos)
    LinearLayout linearNoLeido;
    @Bind(R.id.linear_todos)
    LinearLayout linearTodos;
    @Bind(R.id.listaboletas_img_noleido)
    ImageView imgNoLeido;
    @Bind(R.id.listaboletas_img_todos)
    ImageView imgTodos;

    ListadoBoletasPresenter presenter;
    ListadoBoletasRequest request = new ListadoBoletasRequest();
    List<WRHDocumento> lista;
    SharedPreferences preferences;
    public ListadoBoletasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.linear_lista_boletas, container, false);
        ButterKnife.bind(this,view);
        layoutManager = new LinearLayoutManager(getContext());
        rcvBoletas.setLayoutManager(layoutManager);
        presenter= new ListadoBoletasPresenter(this);
        preferences = getActivity().getSharedPreferences(getString(R.string.preferences_app), Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getListaBoletas(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(TAG);
        /**set events onclick in linearlayout*/

        linearTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgTodos.setImageDrawable(Images.getDrawableByName(getActivity(), "rgs_ico_date"));
                imgNoLeido.setImageDrawable(Images.getDrawableByName(getActivity(), "cnt_ico_email_big"));
                getListaBoletas(true);
            }
        });
        linearNoLeido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgTodos.setImageDrawable(Images.getDrawableByName(getActivity(), "acc_ico_calendar"));
                imgNoLeido.setImageDrawable(Images.getDrawableByName(getActivity(), "mss_ico_mss_detail"));
                getListaBoletas(false);
            }
        });
    }

    void getListaBoletas(Boolean tipoLeido){
        try{
          /*  request.setIdEmpresa(preferences.getInt(getString(R.string.preferences_idempresa),5));
            request.setCodAplicacion(2);
            request.setIdTipoDocumento("1");
            request.setNroDocumentoIdentificacion(preferences.getString(getString(R.string.preferences_user),""));*/
            ListadoBoletasBusquedaRequest request2 = new ListadoBoletasBusquedaRequest();
            request2.setNroDocumentoIdentificacion(preferences.getString(getString(R.string.preferences_user),""));
            request2.setIdEmpresa(preferences.getInt(getString(R.string.preferences_idempresa),5));
            request2.setIdTipoDocumento("1");
            request2.setPeriodo("");
            if(tipoLeido){
                presenter.getListadoBoletas(request2);
            }else{
                request.setIdEmpresa(preferences.getInt(getString(R.string.preferences_idempresa),5));
                request.setCodAplicacion(2);
                request.setIdTipoDocumento("1");
                request.setNroDocumentoIdentificacion(preferences.getString(getString(R.string.preferences_user),""));
                presenter.getListadoBoletasNoLeidos(request);
            }

        }catch(Exception e){
            e.printStackTrace();
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

   /* @Override
    public void getListaDocumentos(WRHgetListadoDocumentosResponse documentos) {
        lista =documentos;
        adapter = new RecyclerBoletasAdapter(lista);
        rcvBoletas.setAdapter(adapter);
        adapter.setOnItemClickLIstener(new RecyclerBoletasAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(WRHDocumento Boleta) {
                visualizarBoleta(Boleta.periodo);
            }
        });
    }*/

    @Override
    public void getListaDocumentos(WRHgetListadoDocumentosMobileResponse documentos) {
        lista =documentos;
        adapter = new RecyclerBoletasAdapter(lista);
        rcvBoletas.setAdapter(adapter);
        adapter.setOnItemClickLIstener(new RecyclerBoletasAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(WRHDocumento Boleta) {
                visualizarBoleta(Boleta.periodo);
            }
        });
    }

    @Override
    public void getListaDocumentosNoLeidos(WRHgetListadoDocumentosNoRevisadosResponse documentos) {
       /* lista =documentos;
        adapter = new RecyclerBoletasAdapter(lista);
        rcvBoletas.setAdapter(adapter);*/
        lista =documentos;
        adapter.setList(lista);
        adapter.notifyDataSetChanged();

    }
}
