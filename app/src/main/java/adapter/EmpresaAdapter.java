package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jeffe.produtos.R;

import java.util.List;

import model.Empresa;


public class EmpresaAdapter extends BaseAdapter {

    private Context context;
    private List<Empresa> lista;

    public EmpresaAdapter(Context ctx, List<Empresa> empresas){
        this.context = ctx;
        this.lista = empresas;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Empresa empresa = lista.get(position);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.empresas, null);
        }

        TextView txtNome = (TextView) view.findViewById(R.id.empresa_lista_nome);
        txtNome.setText("Desc: "+empresa.getRazao_social());
        TextView txtApelido = (TextView) view.findViewById(R.id.empresa_lista_cidade);
        txtApelido.setText("Apel: "+empresa.getCidade());

        return view;
    }
}