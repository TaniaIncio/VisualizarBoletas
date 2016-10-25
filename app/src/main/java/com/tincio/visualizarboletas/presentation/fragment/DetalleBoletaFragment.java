package com.tincio.visualizarboletas.presentation.fragment;


import android.Manifest;
import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.pdfview.PDFView;
import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.request.DetalleBoletasRequest;
import com.tincio.visualizarboletas.data.services.JHADocumentoWS;
import com.tincio.visualizarboletas.data.services.WRHWSAutenticacionSoap11Binding;
import com.tincio.visualizarboletas.presentation.presenter.DetalleBoletasPresenter;
import com.tincio.visualizarboletas.presentation.view.DetalleBoletasFragmentView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleBoletaFragment extends Fragment implements DetalleBoletasFragmentView{

    public static final String TAG= DetalleBoletaFragment.class.getSimpleName();
    public static PDFView pdfView;
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    DetalleBoletasPresenter presenter;
    public DetalleBoletaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detalle_boleta, container, false);
        ButterKnife.bind(this,view);
        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        presenter = new DetalleBoletasPresenter(this);
        try{
            //llamar servicio web

         //   byte bytes = "";
            String bytes2="";
          /*  bytes.getBytes();
            OutputStream out = new FileOutputStream("/mnt/sdcard/Sample.pdf");
            out.write(bytes.getBytes());
            out.close();*/
            //cargar pdf
        }catch (Exception e){
            Log.i(TAG,  e.toString());
        }

        return view;
    }

    @OnClick(R.id.linear_cargo)
    void onClickCargo(){
        mViewPager.setCurrentItem(1);
    }
    @OnClick(R.id.linear_boleta)
    void onClickBoleta(){
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        obtenerPdf();
   //     verDetalleBoletaPdf();
    }

    void obtenerPdf(){
        DetalleBoletasRequest request = new DetalleBoletasRequest();
        request.setCodAplicacion(1);
        request.setClave("80605606");
        request.setDniUsuario("80605606");
        request.setIdTipoDocumento(1);
        request.setIpUsuario("1");
        request.setIsEmpresa(5);
        request.setsPeriodo("2001_0927");
        presenter.getDetalleBoletas(request);
    }

    @Override
    public void getBoletasPdf(JHADocumentoWS boletaPdf) {
        try {
            int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
               /* ActivityCompat.requestPermissions(
                        activity,
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                );*/
            }
            //+ "/Sample.pdf"
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "Boletas");
            OutputStream out = new FileOutputStream(getActivity().getFilesDir());
            out.write(boletaPdf.bytesAcuse.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class DemoCollectionPagerAdapter extends FragmentPagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new DemoObjectFragment();
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
        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            // The last two arguments ensure LayoutParams are inflated
            // properly.
            View rootView = inflater.inflate(
                    R.layout.fragment_detalle_boleta_tab, container, false);
            pdfView = (PDFView)rootView.findViewById(R.id.pdfview);
            pdfView.fromFile(getActivity().getFilesDir())
                    .defaultPage(1)
                    .showMinimap(false)
                    .enableSwipe(true)
                    .load();
            return rootView;
        }
    }

}
