package com.tincio.visualizarboletas.presentation.fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.request.DetalleBoletasRequest;
import com.tincio.visualizarboletas.data.services.JHADocumentoWS;
import com.tincio.visualizarboletas.presentation.activity.MainActivity;
import com.tincio.visualizarboletas.presentation.presenter.DetalleBoletasPresenter;
import com.tincio.visualizarboletas.presentation.util.Utils;
import com.tincio.visualizarboletas.presentation.view.DetalleBoletasFragmentView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleBoletaFragment extends Fragment implements DetalleBoletasFragmentView, OnLoadCompleteListener, OnPageChangeListener{

    public static final String TAG= "Detalle de Boleta";/// DetalleBoletaFragment.class.getSimpleName();
    public static PDFView pdfView;
    public static PDFView pdfViewCargo;
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    DetalleBoletasPresenter presenter;
    String stringBase64Boleta;
    String stringBase64Cargo;
    ProgressDialog progress;

    ImageView iconBoleta;
    ImageView iconCargo;
    String periodoEnvio;

    SharedPreferences preferences;
    View view;
    String tipo;
    Boolean download=false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_download).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    public DetalleBoletaFragment() {
        // Required empty public constructor
    }

    public static DetalleBoletaFragment newInstance(Bundle args){
        DetalleBoletaFragment fragment = new DetalleBoletaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_detalle_boleta, container, false);
        ButterKnife.bind(this,view);
        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        presenter = new DetalleBoletasPresenter(this);
        iconBoleta = (ImageView) view.findViewById(R.id.icon_boleta);
        iconCargo = (ImageView) view.findViewById(R.id.icon_cargo);
        pdfView = (PDFView)view.findViewById(R.id.pdfviewboleta);
        pdfViewCargo = (PDFView)view.findViewById(R.id.pdfviewcargo);
        tipo = "B";
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    changeIcon("B");
                }else{
                    changeIcon("C");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        preferences = getActivity().getSharedPreferences(getString(R.string.preferences_app), Context.MODE_PRIVATE);
        periodoEnvio = getArguments().getString("periodo_envio","");
        obtenerPdf();
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_download) {
            download = true;
            if(tipo.equals("B")){
                writePdf(stringBase64Boleta,tipo);
            }else{
                writePdf(stringBase64Cargo,tipo);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.linear_cargo)
    void onClickCargo(){
        mViewPager.setCurrentItem(1);
        tipo = "C";
        changeIcon(tipo);
        //
        pdfView.setVisibility(View.GONE);
        pdfViewCargo.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.linear_boleta)
    void onClickBoleta(){
        mViewPager.setCurrentItem(0);
        tipo = "B";
        changeIcon(tipo);

        pdfView.setVisibility(View.VISIBLE);
        pdfViewCargo.setVisibility(View.GONE);
    }

    void changeIcon(String tipo){
        if(tipo.equals("B")){
            iconBoleta.setImageResource(R.mipmap.men_ico_services_off);
            iconCargo.setImageResource(R.mipmap.fav_ico_services_off);
        }else{
            iconCargo.setImageResource(R.mipmap.men_ico_services_off);
            iconBoleta.setImageResource(R.mipmap.fav_ico_services_off);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(TAG);
    }

    void obtenerPdf(){
        progress = Utils.showProgressDialog(getActivity());
        DetalleBoletasRequest request = new DetalleBoletasRequest();
        request.setCodAplicacion(2);
        request.setClave(preferences.getString(getString(R.string.preferences_pass),""));
        request.setDniUsuario(preferences.getString(getString(R.string.preferences_user),""));
        request.setIdTipoDocumento(1);
        request.setIpUsuario(Utils.getIp(getContext()));
        request.setIsEmpresa(preferences.getInt(getString(R.string.preferences_idempresa),5));
        request.setsPeriodo(preferences.getString(getString(R.string.preferences_periodo),periodoEnvio));//2001_0927
        presenter.getDetalleBoletas(request);
    }

    @Override
    public void getBoletasPdf(JHADocumentoWS boletaPdf) {
        try {
/*            int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
               *//* ActivityCompat.requestPermissions(
                        activity,
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                );*//*
            }*/
            if(progress!=null)
                progress.dismiss();
            //+ "/Sample.pdf"
            writePdf(boletaPdf.bytesDocumento,"B");
            writePdf(boletaPdf.bytesAcuse,"C");

            stringBase64Boleta = boletaPdf.bytesDocumento;
            stringBase64Cargo = boletaPdf.bytesAcuse;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    public class DemoCollectionPagerAdapter extends FragmentPagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Bundle args = new Bundle();
            if(i==0) {
                args.putString("documento", stringBase64Boleta);
                args.putString("tipo", "boleta");
            }else {
                args.putString("documento", stringBase64Cargo);
                args.putString("tipo", "cargo");
            }
            Fragment fragment=null;
            if(i==0){
                fragment = DemoObjectFragment.newInstance(args);
            }else{
                fragment = DemoObjectFragmentCargo.newInstance(args);
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }

    // Instances of this class are fragments representing a single
// object in our collection.
    public static class DemoObjectFragment extends Fragment {

        static String doc;
        static String tipo;

        public static DemoObjectFragment newInstance(Bundle args){
            DemoObjectFragment fragment = new DemoObjectFragment();

            fragment.setArguments(args);
            doc = args.getString("documento");
            tipo = args.getString("tipo");
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            // The last two arguments ensure LayoutParams are inflated
            // properly.
            View rootView = inflater.inflate(
                    R.layout.fragment_detalle_boleta_tab, container, false);
            pdfView = (PDFView)rootView.findViewById(R.id.pdfview);

            return rootView;
        }
    }
    //other fragment
    public static class DemoObjectFragmentCargo extends Fragment {

        static String doc;
        static String tipo;

        public static DemoObjectFragmentCargo newInstance(Bundle args){
            DemoObjectFragmentCargo fragment = new DemoObjectFragmentCargo();

            fragment.setArguments(args);
            doc = args.getString("documento");
            tipo = args.getString("tipo");
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            // The last two arguments ensure LayoutParams are inflated
            // properly.
            View rootView = inflater.inflate(
                    R.layout.fragment_detalle_boleta_tab_cargo, container, false);
            pdfViewCargo = (PDFView)rootView.findViewById(R.id.pdfview_cargo);
            return rootView;
        }
    }

    //other fragment

    /**Crear pdf*/
    void writePdf(String pdf, String tipo){
        //Convert de string a bytes
        FileOutputStream fos = null;
        File path;
        try {
            //File path= new File("/mnt/sdcard/misboletas/");
            //File path = new File(""+getActivity().getFilesDir());
            if(download){
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            }else
                path = new File(""+getActivity().getFilesDir());
            if ( !path.exists() ){ path.mkdir(); }
            File file = new File(path,(tipo.equals("B")?"BOL":"CARGO")+ "_"+(download?periodoEnvio:tipo)+".pdf");
            fos = new FileOutputStream(file);
            byte[] pdfAsBytes = Base64.decode(pdf, 0);
            fos.write(pdfAsBytes);
            fos.flush();
            fos.close();
            if(download==false)
                loadPdfCreate(tipo);
            else
                Toast.makeText(getActivity(),"Archivo guardado en Descargas",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void loadPdfCreate(String tipo){
        //File path= new File("/mnt/sdcard/misboletas/Doc_"+ tipo+ ".pdf");
        //File path = new File(getActivity().getFilesDir(), "Doc_"+ tipo+ ".pdf");
        File path;
        path = new File(""+getActivity().getFilesDir(), (tipo.equals("B")?"BOL":"CARGO")+ "_"+ (download?periodoEnvio:tipo)+ ".pdf");


        if(tipo.equals("B")){
          //  pdfView = (PDFView)view.findViewById(R.id.pdfview);
            pdfView.fromFile(path)
                    .defaultPage(1)
                    .showMinimap(false)
                    .enableSwipe(true)
                    .onLoad(this)
                    .onPageChange(this)
                    .load();
        }else{
            //pdfViewCargo = (PDFView)view.findViewById(R.id.pdfview_cargo);
            pdfViewCargo.fromFile(path)
                    .defaultPage(1)
                    .showMinimap(false)
                    .enableSwipe(true)
                    .load();
        }
        download = false;
    }

}