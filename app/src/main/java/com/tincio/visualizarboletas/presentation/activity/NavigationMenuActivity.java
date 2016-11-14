package com.tincio.visualizarboletas.presentation.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.services.WWOEmpresa;
import com.tincio.visualizarboletas.presentation.adapter.OpcionMenuAdapter;
import com.tincio.visualizarboletas.data.model.OpcionMenu;
import com.tincio.visualizarboletas.presentation.fragment.BusquedaBoletasFragment;
import com.tincio.visualizarboletas.presentation.fragment.CambioClaveFragment;
import com.tincio.visualizarboletas.presentation.fragment.ListadoBoletasFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NavigationMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.rcv_opc_menu)
    RecyclerView rcvMenu;
    RecyclerView.LayoutManager layoutManager;

    OpcionMenuAdapter adapterMenu;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    SharedPreferences prefs;

    MenuView.ItemView item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the FirebaseAnalytics instance.
        //
        setContentView(R.layout.activity_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        changeFragment(new ListadoBoletasFragment(), "NOTAG");
        prefs = getSharedPreferences("preferences", MODE_PRIVATE);
        setTitle("Mis Boletas " + prefs.getString("sucursal", ""));
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            layoutManager = new LinearLayoutManager(getApplicationContext());
            rcvMenu.setLayoutManager(layoutManager);
            ///llamar a opciones de menu
            List<OpcionMenu> lista = new ArrayList<>();
            OpcionMenu mOpcion = new OpcionMenu(1,"Mis Boletas","men_ico_services_off");
            lista.add(mOpcion);
            lista.add(new OpcionMenu(2,"Buscar","fav_ico_find"));
            lista.add(new OpcionMenu(3,"Cambiar clave","gen_ico_token"));
            lista.add(new OpcionMenu(4,"Cerrar Sesion","men_ico_services_off"));
            showListOpcionMenu(lista);
            adapterMenu.setOnItemClickLIstener(new OpcionMenuAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(OpcionMenu opcionMenu) {
                    getCaseFragment(opcionMenu.getId());
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void getCaseFragment(int idFragment) {
        try {
            switch (idFragment) {
                case 1:
                    changeFragment(new ListadoBoletasFragment(), ListadoBoletasFragment.TAG);
                    break;
                case 2:
                    changeFragment(new BusquedaBoletasFragment(), BusquedaBoletasFragment.TAG);
                    break;
                case 3:
                    changeFragment(new CambioClaveFragment(), CambioClaveFragment.TAG);
                    break;
                case 4:
                    new AlertDialog.Builder(this)
                            .setTitle("Cerrar Sesión")
                            .setMessage("¿Esta seguro de salir?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(NavigationMenuActivity.this, MainActivity.class));
                                    finish();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeFragment(Fragment fragment, String TAG) {
        try {
            FragmentTransaction Ft = getSupportFragmentManager().beginTransaction();
            Ft.replace(R.id.frame_base, fragment, TAG);
            if(!TAG.equals("NOTAG")){
                Ft.addToBackStack(TAG);
            }
            Ft.commit();
            getSupportActionBar().setTitle(TAG.equals("NOTAG")?"Mis Boletas":TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_download) {
           *//* Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);*//*
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showListOpcionMenu(List<OpcionMenu> listOpcionMenu) {
        adapterMenu = new OpcionMenuAdapter(listOpcionMenu);
        rcvMenu.setAdapter(adapterMenu);
    }
}
