package com.tincio.visualizarboletas.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.services.WRHgetEmpresasResponse;
import com.tincio.visualizarboletas.data.services.WWOEmpresa;
import com.tincio.visualizarboletas.presentation.VisualizarBoletasApplication;
import com.tincio.visualizarboletas.presentation.presenter.EmpresasPresenter;
import com.tincio.visualizarboletas.presentation.view.SplashView;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashView{
    private ArrayList<WWOEmpresa> listaEmpresa;
    EmpresasPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        presenter = new EmpresasPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getEmpresas();
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void getEmpresas(WRHgetEmpresasResponse empresas) {
        Log.i("total",empresas.size()+"");
        listaEmpresa= new ArrayList<WWOEmpresa>(empresas);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putParcelableArrayListExtra("listaEmpresa",listaEmpresa);
        startActivity(intent);
        VisualizarBoletasApplication.setEmpresas(listaEmpresa);
        finish();
    }
}
