package com.tincio.visualizarboletas.presentation.fragment;


import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Base64;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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
    String stringBase64Boleta;
    String stringBase64Cargo;
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
          //  writePdf(boletaPdf.bytesAcuse);

            stringBase64Boleta = boletaPdf.bytesDocumento;
            stringBase64Cargo = boletaPdf.bytesAcuse;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Fragment fragment = DemoObjectFragment.newInstance(args);
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
            writePdf(doc, tipo);
         /*   pdfView = (PDFView)rootView.findViewById(R.id.pdfview);
            pdfView.fromFile(getActivity().getFilesDir())
                    .defaultPage(1)
                    .showMinimap(false)
                    .enableSwipe(true)
                    .load();*/

            return rootView;
        }

        /**Crear pdf*/
        void writePdf(String pdf, String tipo){
            //Convert de string a bytes
            FileOutputStream fos = null;
            try {
                /**crear directorio*/
                File path= new File("/mnt/sdcard/misboletas/");
                if ( !path.exists() ){ path.mkdir(); }
                File file = new File(path,"Doc_"+tipo+".pdf");
                fos = new FileOutputStream(file);
                byte[] pdfAsBytes = Base64.decode(pdf, 0);
                fos.write(pdfAsBytes);
                fos.flush();
                fos.close();
                loadPdfCreate(tipo);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void loadPdfCreate(String tipo){
            File path= new File("/mnt/sdcard/misboletas/Doc_"+ tipo+ ".pdf");
            pdfView.fromFile(path)
                    .defaultPage(1)
                    .showMinimap(false)
                    .enableSwipe(true)
                    .load();
        }
    }


}
