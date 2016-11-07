package com.tincio.visualizarboletas.presentation.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.request.UserRequest;
import com.tincio.visualizarboletas.data.services.WRHUsuarioDatos;
import com.tincio.visualizarboletas.data.services.WWOEmpresa;
import com.tincio.visualizarboletas.presentation.VisualizarBoletasApplication;
import com.tincio.visualizarboletas.presentation.adapter.EmpresasAdapter;
import com.tincio.visualizarboletas.presentation.presenter.LoginPresenter;
import com.tincio.visualizarboletas.presentation.util.Utils;
import com.tincio.visualizarboletas.presentation.view.LoginView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoginView, AdapterView.OnItemSelectedListener{

    @Bind(R.id.btn_login)
    TextView btnLogin;
    @Bind(R.id.input_user)
    TextView txtUsuario;
    @Bind(R.id.input_clave)
    TextView txtClave;
    @Bind(R.id.spinner_empresa)
    Spinner spinnerEmpresa;
    ProgressDialog progress;
    LoginPresenter presenter;
    String TAG = MainActivity.class.getSimpleName();

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ArrayList<WWOEmpresa> listaEmpresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        presenter = new LoginPresenter(this);
        preferences = getSharedPreferences(getString(R.string.preferences_app), MODE_PRIVATE);
        if(VisualizarBoletasApplication.getEmpresas()==null){
            listaEmpresa = getIntent().getParcelableArrayListExtra("listaEmpresa");
        }else{
            listaEmpresa = VisualizarBoletasApplication.getEmpresas();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        EmpresasAdapter adapter = new EmpresasAdapter(this,R.layout.row_spinner_empresa, listaEmpresa);
        spinnerEmpresa.setAdapter(adapter);
        spinnerEmpresa.setOnItemSelectedListener(this);
        checkSelectionEmpresa();
    }

    void checkSelectionEmpresa(){
        for (int i =0; i<listaEmpresa.size();i++){
            if(listaEmpresa.get(i).id_empresa.equals(String.valueOf(preferences.getInt(getString(R.string.preferences_idempresa),0))))
            {
                spinnerEmpresa.setSelection(i);
            }
        }
    }
    @OnClick(R.id.btn_login)
    void logueo(){
        try{
            UserRequest request = new UserRequest();
            request.setCodAplicacion(1);
            request.setIdEmpresa(preferences.getInt(getString(R.string.preferences_idempresa),1));
            request.setUsername(txtUsuario.getText().toString());
            request.setClave(txtClave.getText().toString());
            request.setIp(Utils.getIp(this));
            request.setKeyIos("");
            request.setKeyAndroid("");
            presenter.logueoUser(request);
            savePreferences();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void savePreferences(){
        editor = preferences.edit();
        editor.putString(getString(R.string.preferences_user), txtUsuario.getText().toString());
        editor.putString(getString(R.string.preferences_pass), txtClave.getText().toString());
        editor.commit();
    }
    @Override
    public void getUsuarioLogueado(WRHUsuarioDatos userLogueado) {
        if(userLogueado.resultado){
            startActivity(new Intent(this,NavigationMenuActivity.class));
        }else{
            Toast.makeText(this, "Usuario y/o clave incorrectos", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        editor = preferences.edit();
        editor.putInt(getString(R.string.preferences_idempresa), Integer.parseInt(this.listaEmpresa.get(i).id_empresa));
        editor.commit();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
