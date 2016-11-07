package com.tincio.visualizarboletas.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.services.WWOEmpresa;

import java.util.List;

/**
 * Created by juan on 25/05/2016.
 */
public class EmpresasAdapter extends ArrayAdapter<WWOEmpresa>{

    Context context;
    List<WWOEmpresa> list;

    public EmpresasAdapter(Context context, int resource,List<WWOEmpresa> lista) {
        super(context, resource,lista);
        this.list = lista;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        if(row == null)
        {
            //inflate your customlayout for the textview
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(R.layout.row_spinner_empresa, parent, false);
        }
        //put the data in it
        String item = list.get(position).nomb_emp;
        TextView nombre = (TextView) row.findViewById(R.id.txt_empresa);
        nombre.setText(item);
        return row;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null)
        {
            //inflate your customlayout for the textview
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(R.layout.row_spinner_empresa, parent, false);
        }
        //put the data in it
        String item = list.get(position).nomb_emp;
        TextView nombre = (TextView) row.findViewById(R.id.txt_empresa);
        nombre.setText(item);
        return row;
    }
}
