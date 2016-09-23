package com.tincio.visualizarboletas.presentation.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.services.CULUsuarioDatos;
import com.tincio.visualizarboletas.presentation.view.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoginView{

    @Bind(R.id.btn_login)
    TextView btnLogin;
    @Bind(R.id.input_user)
    TextView txtUsuario;
    @Bind(R.id.input_clave)
    TextView txtClave;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NavigationMenuActivity.class));
            }
        });
    }

    @OnClick(R.id.btn_login)
    void logueo(){
        try{

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void getUsuarioLogueado(CULUsuarioDatos userLogueado) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {
        progress = Utils.showProgressDialog(getActivity());
    }

    @Override
    public void closeLoading() {
        if(progress!=null)
            progress.dismiss();
    }
}
