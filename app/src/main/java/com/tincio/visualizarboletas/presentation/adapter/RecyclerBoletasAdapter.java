package com.tincio.visualizarboletas.presentation.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tincio.visualizarboletas.R;
import com.tincio.visualizarboletas.data.model.Boleta;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by juan on 25/05/2016.
 */
public class RecyclerBoletasAdapter extends RecyclerView.Adapter<RecyclerBoletasAdapter.BoletaViewHolder>{

    Context context;
    List<Boleta> list;
    public RecyclerBoletasAdapter(List<Boleta> list){
        this.list= list;
    }

    @Override
    public BoletaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()  ).inflate(R.layout.linear_row_boletapago,parent, false  );
        BoletaViewHolder viewHolder = new BoletaViewHolder(view);
        return viewHolder;
    }

    Boleta opc;
    @Override
    public void onBindViewHolder(BoletaViewHolder holder, int position) {
        opc = list.get(position);
        holder.txtNombre.setText(opc.getNombre());
        String[] fechas = opc.getDate().split("-");
        holder.txtFechaDia.setText(String.valueOf(fechas[0]));
        holder.txtFechaMes.setText(String.valueOf(fechas[1]));
        holder.txtFechaAnio.setText(String.valueOf(fechas[2]));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BoletaViewHolder extends RecyclerView.ViewHolder{

        @Nullable
        @Bind(R.id.rowboletapago_txt_encabezado)
        TextView txtNombre;
        @Nullable
        @Bind(R.id.rowboletapago_txtfecha_dia)
        TextView txtFechaDia;
        @Nullable
        @Bind(R.id.rowboletapago_txtfecha_mes)
        TextView txtFechaMes;
        @Bind(R.id.rowboletapago_txtfecha_anio)
        TextView txtFechaAnio;
        @Bind(R.id.linear_row_boletas)
        LinearLayout linearRowBoleta;
        public BoletaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            linearRowBoleta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickLIstener != null) {
                        onItemClickLIstener.setOnItemClickListener(list.get(getPosition()));
                    }
                }
            });
        }
    }

    public void setList(List<Boleta> lista){
        try{
            this.list = lista;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public OnItemClickListener onItemClickLIstener;
    public interface OnItemClickListener{
        public void setOnItemClickListener(Boleta Boleta);
    }

    public void setOnItemClickLIstener(OnItemClickListener onItemClickListener){
        this.onItemClickLIstener = onItemClickListener;
    }
}
