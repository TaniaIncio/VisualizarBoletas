package com.tincio.visualizarboletas.presentation.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.request.UserRequest;
import com.tincio.visualizarboletas.data.services.WRHUsuarioDatos;
import com.tincio.visualizarboletas.presentation.presenter.LoginPresenter;
import com.tincio.visualizarboletas.presentation.util.Utils;
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
    LoginPresenter presenter;
    String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
      /*  btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NavigationMenuActivity.class));
            }
        });*/
    }

    @OnClick(R.id.btn_login)
    void logueo(){
        try{
            UserRequest request = new UserRequest();
            request.setCodAplicacion(1);
            request.setIdEmpresa(5);
            request.setUsername("80605606");
            request.setClave("80605606");
            request.setIp("192.168.2.365");
            request.setKeyIos("");
            request.setKeyAndroid("");
            presenter.logueoUser(request);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getUsuarioLogueado(WRHUsuarioDatos userLogueado) {
        Log.i(TAG, userLogueado.sNombres);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {
        progress = Utils.showProgressDialog(this);
    }

    @Override
    public void closeLoading() {
        if(progress!=null)
            progress.dismiss();
    }
}
