package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import model.Empresa;


public class SpinnerEmpresaAdapter extends ArrayAdapter<Empresa> {

    private Context context;
    private Empresa[] lista;

    public SpinnerEmpresaAdapter(Context context, int textViewResourceId,
                                 Empresa[] values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.lista = values;
    }

    @Override
    public int getCount(){
        return lista.length;
    }

    @Override
    public Empresa getItem(int position){
        return lista[position];
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(lista[position].getRazao_social()+" - "+lista[position].getCidade());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(lista[position].getRazao_social()+" - "+lista[position].getCidade());

        return label;
    }
}
