package com.example.jeffe.produtos;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import adapter.ProdutoAdapter;
import dao.ProdutoDAO;
import model.Produto;
import util.Mensagem;

public class ProdutosActivity extends Activity implements
        AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private ListView lista;
    private List<Produto> produtoList;
    private ProdutoDAO produtoDAO;
    private Button btnProx;
    private Button btnAnte;
    private TextView txtPag;

    private int idposicao;
    private int empresa;
    private int pagina;
    private int qntdProdutosTotal;


    private android.app.AlertDialog alertDialog;
    private android.app.AlertDialog alertConfirmacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_produtos);

        alertDialog      = Mensagem.criarAlertDialog(this);
        alertConfirmacao = Mensagem.criarDialogConfirmacao(this);
        pagina = 0;
        btnAnte =  findViewById(R.id.btnVoltarPag);
        btnProx = findViewById(R.id.btnProxPag);
        txtPag =  findViewById(R.id.txtNumPag);

        configSpinnerProduto();
    }

    private void configSpinnerProduto() {
        Spinner spinner = findViewById(R.id.produto_spinner);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,
                R.array.filtroProdutos, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                buscaProdutos();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void carregaListaProdutos(int order, String busca){
        produtoDAO     = new ProdutoDAO(this);
        String ordem = "";
        if (order == 0) { ordem = "descricao_produto"; }
        else
        if (order == 1) { ordem = "cod_produto"; }

        empresa = getIntent().getIntExtra("emp",1);
        produtoList    = produtoDAO.listarProdutos(empresa,ordem,busca,pagina);
        qntdProdutosTotal = produtoDAO.contarProdutos(empresa,busca);
        ProdutoAdapter produtoAdapter = new ProdutoAdapter(this, produtoList);

        if(produtoList.size() > 0) {
            lista = findViewById(R.id.listaProdutos);
            lista.setAdapter(produtoAdapter);
            lista.setOnItemClickListener(this);
        } else {
            Mensagem.Msg(this, getString(R.string.mensagem_nada_encontrado));
        }
        validaBotoes();
    }

    public void onBuscarClick(View view) {
       buscaProdutos();
    }

    private void buscaProdutos(){
        TextView edtBusca = findViewById(R.id.edtBuscaProduto);
        Spinner spnOrdem = findViewById(R.id.produto_spinner);
        int position = spnOrdem.getSelectedItemPosition();
        String texto = edtBusca.getText().toString();
        carregaListaProdutos(position, texto);
    }

    public void onProximoClick(View view){
        pagina++;
        buscaProdutos();
        validaBotoes();
    }
    public void onAnteriorClick(View view){
        pagina--;
        buscaProdutos();
        validaBotoes();
    }

    private void validaBotoes() {
        if(pagina <= 0) {
            btnAnte.setVisibility(View.INVISIBLE);
        }
        else {
            btnAnte.setVisibility(View.VISIBLE);
        }

        if ((qntdProdutosTotal) < (pagina+1)*5) {
            btnProx.setVisibility(View.INVISIBLE);
        }
        else {
            btnProx.setVisibility(View.VISIBLE);
        }

        txtPag.setText(String.format("Página: %s", String.valueOf(pagina + 1)));
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_cadastrar_produto:
                Intent intent = new Intent(this, CadProdutoActivity.class);
                intent.putExtra("EMP_LOGADA", empresa);
                startActivity(intent);
                break;
            case R.id.action_trocar_empresa:
                finish();
                break;

            case R.id.action_cadastrar_empresa:
                Intent intent2 = new Intent(this, EmpresasActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = produtoList.get(idposicao).getId();
        switch (which){
            case 0:
                Intent intent = new Intent(this, CadProdutoActivity.class);
                intent.putExtra("PRODUTO_ID", id);
                intent.putExtra("EMP_LOGADA", empresa);
                startActivity(intent);
            break;
            case 1:
                alertConfirmacao.show();
                break;
                case DialogInterface.BUTTON_POSITIVE:
                     produtoList.remove(idposicao);
                     produtoDAO.removerProduto(id);
                     lista.invalidateViews();
                     Mensagem.Msg(this, getString(R.string.mensagem_excluir));
                break;
                case DialogInterface.BUTTON_NEGATIVE:
                        alertConfirmacao.dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idposicao = position;
        alertDialog.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
