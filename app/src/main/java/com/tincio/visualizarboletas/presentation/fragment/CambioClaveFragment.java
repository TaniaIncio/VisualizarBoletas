package com.tincio.visualizarboletas.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tincio.visualizarboletas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CambioClaveFragment extends Fragment {

    public static final String TAG="Cambio de clave";
    public CambioClaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cambio_clave, container, false);
    }

}
