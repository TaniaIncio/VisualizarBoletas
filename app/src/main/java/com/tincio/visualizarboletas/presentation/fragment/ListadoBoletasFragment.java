package com.tincio.visualizarboletas.presentation.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.presentation.adapter.RecyclerBoletasAdapter;
import com.tincio.visualizarboletas.data.model.Boleta;
import com.tincio.visualizarboletas.presentation.util.Images;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoBoletasFragment extends Fragment {
    
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
    public ListadoBoletasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    List<Boleta> lista2;
    List<Boleta> lista;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.linear_lista_boletas, container, false);
        ButterKnife.bind(this,view);
        layoutManager = new LinearLayoutManager(getContext());
        rcvBoletas.setLayoutManager(layoutManager);
        lista =new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
       // Boleta mBoleta = new Boleta(1,calendar.getTime(), "BOL - 001 ",true);
        lista.add(new Boleta(1,"31-Jul-2016", "BOL - 0011 ",true));
        lista.add(new Boleta(1,"30-Jun-2016", "BOL - 0010 ",true));
        lista.add(new Boleta(1,"31-May-2016", "BOL - 0009 ",true));
        lista.add(new Boleta(1,"30-Abr-2016", "BOL - 0008 ",true));
        lista.add(new Boleta(1,"31-Mar-2016", "BOL - 0007 ",true));
        lista.add(new Boleta(1,"29-Feb-2016", "BOL - 0006 ",true));
        lista.add(new Boleta(1,"31-Ene-2016", "BOL - 0005 ",true));
        lista.add(new Boleta(1,"31-Dic-2016", "BOL - 0004 ",true));
        lista.add(new Boleta(1,"30-Nov-2016", "BOL - 0003 ",true));
        lista.add(new Boleta(1,"31-Oct-2016", "BOL - 0002 ",true));
        lista.add(new Boleta(1,"30-Ago-2016", "BOL - 0001 ",true));
//
        lista2 =new ArrayList<>();
        // Boleta mBoleta = new Boleta(1,calendar.getTime(), "BOL - 001 ",true);
        lista2.add(new Boleta(1,"31-Jul-2016", "BOL - 0011 ",true));
        lista2.add(new Boleta(1,"30-Jun-2016", "BOL - 0010 ",true));
        lista2.add(new Boleta(1,"31-May-2016", "BOL - 0009 ",true));
        lista2.add(new Boleta(1,"30-Abr-2016", "BOL - 0008 ",true));

        adapter = new RecyclerBoletasAdapter(lista);
        rcvBoletas.setAdapter(adapter);

        linearTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setList(lista);
                adapter.notifyDataSetChanged();
                imgTodos.setImageDrawable(Images.getDrawableByName(getActivity(), "rgs_ico_date"));
                imgNoLeido.setImageDrawable(Images.getDrawableByName(getActivity(), "cnt_ico_email_big"));
            }
        });
        linearNoLeido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setList(lista2);
                adapter.notifyDataSetChanged();
                imgTodos.setImageDrawable(Images.getDrawableByName(getActivity(), "acc_ico_calendar"));
                imgNoLeido.setImageDrawable(Images.getDrawableByName(getActivity(), "mss_ico_mss_detail"));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setOnItemClickLIstener(new RecyclerBoletasAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(Boleta Boleta) {
                visualizarBoleta();
            }
        });
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
