package upc.edu.dsa.myapplication.Activities;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import upc.edu.dsa.myapplication.Entities.VO.*;
import upc.edu.dsa.myapplication.R;
import java.util.ArrayList;

public class Extra_CardAdapter extends RecyclerView.Adapter<Extra_CardAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // Nuevo ...
        TextView nombreArticulo, idArticulo, precioArticulo, tipoArticulo, recargaHambre, recargaSalud, recargaDiversion, recargaSueno;
        ImageView imgArticulo;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.nombreArticulo = (TextView) itemView.findViewById(R.id.nombreArticulo);
            this.idArticulo = (TextView) itemView.findViewById(R.id.idArticulo);
            this.precioArticulo = (TextView) itemView.findViewById(R.id.precioArticulo);
            this.tipoArticulo = (TextView) itemView.findViewById(R.id.tipoArticulo);
            this.recargaHambre = (TextView) itemView.findViewById(R.id.recargaHambre);
            this.recargaSalud = (TextView) itemView.findViewById(R.id.recargaSalud);
            this.recargaDiversion = (TextView) itemView.findViewById(R.id.recargaDiversion);
            this.recargaSueno = (TextView) itemView.findViewById(R.id.recargaSueno);
        }
    }

    public Extra_CardAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extra_armario_cards_layout, parent, false);

        view.setOnClickListener(Activity_Pou_Armario.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int listPosition) {
        TextView idArticulo = holder.idArticulo;
        idArticulo.setText(dataSet.get(listPosition).getArticuloId());

        TextView nombreArticulo = holder.nombreArticulo;
        nombreArticulo.setText(dataSet.get(listPosition).getNombreArticulo());

        TextView precioArticulo = holder.precioArticulo;
        precioArticulo.setText(dataSet.get(listPosition).getPrecioArticulo());

        TextView tipoArticulo = holder.tipoArticulo;
        tipoArticulo.setText(dataSet.get(listPosition).getTipoArticulo());

        TextView recargaHambre = holder.recargaHambre;
        recargaHambre.setText(dataSet.get(listPosition).getRecargaHambre());

        TextView recargaSalud = holder.recargaSalud;
        recargaSalud.setText(dataSet.get(listPosition).getRecargaSalud());

        TextView recargaDiversion = holder.recargaDiversion;
        recargaDiversion.setText(dataSet.get(listPosition).getRecargaDiversion());

        TextView recargaSueno = holder.recargaSueno;
        recargaSueno.setText(dataSet.get(listPosition).getRecargaSueno());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}