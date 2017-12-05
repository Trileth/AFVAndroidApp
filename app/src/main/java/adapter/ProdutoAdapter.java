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

import model.Produto;


public class ProdutoAdapter extends BaseAdapter {

    private Context context;
    private List<Produto> lista;

    public ProdutoAdapter(Context ctx, List<Produto> produtos){
        this.context = ctx;
        this.lista = produtos;
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
        Produto produto = lista.get(position);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.produtos, null);
        }

        TextView txtNome = view.findViewById(R.id.produto_lista_nome);
        txtNome.setText("Desc: "+produto.getDescricao());
        TextView txtApelido = view.findViewById(R.id.produto_lista_apelido);
        txtApelido.setText("Apel: "+produto.getApelido());

        return view;
    }
}