package com.tincio.visualizarboletas.presentation.fragment;


import android.app.ActionBar;
import android.os.Bundle;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleBoletaFragment extends Fragment{

    public static final String TAG= DetalleBoletaFragment.class.getSimpleName();
    public static PDFView pdfView;
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
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
        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);


        try{
         //   byte bytes = "";
            String bytes2="";
          /*  bytes.getBytes();
            OutputStream out = new FileOutputStream("/mnt/sdcard/Sample.pdf");
            out.write(bytes.getBytes());
            out.close();*/
            //cargar pdf


            /*
                .pages(0, 2, 1, 3, 3, 3)
                    .onDraw(onDrawListener)
                    .onLoad(onLoadCompleteListener)
                    .onPageChange(onPageChangeListener)
             */
        }catch (Exception e){
            Log.i(TAG,  e.toString());
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
   //     verDetalleBoletaPdf();
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
            pdfView.fromAsset("sample.pdf")
                    .defaultPage(1)
                    .showMinimap(false)
                    .enableSwipe(true)
                    .load();
            return rootView;
        }
    }

}
