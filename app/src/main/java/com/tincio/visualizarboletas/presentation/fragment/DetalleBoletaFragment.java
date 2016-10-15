package com.tincio.visualizarboletas.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tincio.visualizarboletas.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleBoletaFragment extends Fragment{

    public static final String TAG= "DetalleBoletaFragment";
   /* @Bind(R.id.iv_photo)
    ImageView imgBoleta;*/
  /*  @Bind(R.id.viewpdf_boleta)
    PDFViewPager pdfViewPager;*/
    public DetalleBoletaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detalle_boleta, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
   //     verDetalleBoletaPdf();
    }

    void verDetalleBoletaPdf(){
        try{
            //example

            //pdfViewPager.setAdapter(new PDFPagerAdapter(getActivity(),"D:\\Projects\\mobiles\\android\\BoletasPago\\Fuentes\\VisualizarBoletas\\app\\src\\main\\assets\\sample.pdf"));
       //     pdfViewPager = new PDFViewPager(getActivity(), "sample.pdf");
           // CopyAsset copyAsset = new CopyAssetThreadImpl(getActivity(), new Handler());
           // copyAsset.copy("sample.pdf", new File(getActivity().getCacheDir(), "sample.pdf").getAbsolutePath());
            //instanciar pdf
           // pdfViewPager = new PDFViewPager(getActivity(), "sample.pdf");
            //
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
