package com.example.jeffe.produtos;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Spinner;

        import java.util.ArrayList;
        import java.util.List;

        import adapter.SpinnerGrupoAdapter;
        import dao.GrupoProdutoDAO;
        import dao.ProdutoDAO;
        import model.GrupoProduto;
        import model.Produto;


public class CadProdutoActivity extends Activity {

    private EditText  edtDescricao_produto,edtSituacao_produto ;
    private Spinner spnGrupo_produto;
    private ProdutoDAO produtoDAO;
    private GrupoProdutoDAO grupoDAO;
    private List<GrupoProduto> listagrupos = new ArrayList<>();
    private int idproduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadprodutos1);

        produtoDAO = new ProdutoDAO(this);
        grupoDAO = new GrupoProdutoDAO(this);

        edtDescricao_produto =  findViewById(R.id.produto_edtDescricao);
        spnGrupo_produto =  findViewById(R.id.produto_spnGrupo);
        edtSituacao_produto = findViewById(R.id.produto_edtSituacao);

        //Configurando Spinner Grupo
        listagrupos = grupoDAO.listarGrupoProduto();
        GrupoProduto[] grupoArr = listagrupos.toArray(new GrupoProduto[listagrupos.size()]);
        Spinner spinner = findViewById(R.id.produto_spnGrupo);
        SpinnerGrupoAdapter spinnerArrayAdapter = new SpinnerGrupoAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, grupoArr);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        //Modo de edição
        idproduto = getIntent().getIntExtra("PRODUTO_ID", 0);
        if(idproduto > 0){
            Produto model = produtoDAO.buscarProdutoPorId(idproduto);
            edtDescricao_produto.setText(model.getDescricao());
            spnGrupo_produto.setSelection(model.getGrupo()-1);
            edtSituacao_produto.setText(model.getSituacao());
            setTitle("Atualizar produto");
        }
    }

    @Override
    protected void onDestroy() {
        produtoDAO.fechar();
        super.onDestroy();
    }

    private void cadastrar(){
        boolean validacao = true;

        String descricao  =  edtDescricao_produto.getText().toString();
        String situacao =  edtSituacao_produto.getText().toString();
        GrupoProduto grupoSelecionado = (GrupoProduto) spnGrupo_produto.getSelectedItem();

        if (descricao.equals("")){
            validacao = false;
            edtDescricao_produto.setError(getString(R.string.campo_obrigatorio));
        }

        if (situacao.equals("")){
            validacao = false;
            edtSituacao_produto.setError(getString(R.string.campo_obrigatorio));
        }

        if(validacao){
            int empresa = getIntent().getIntExtra("EMP_LOGADA", 1);
            Bundle bundle = new Bundle();
            bundle.putInt("produtoEmp", empresa );
            bundle.putString("produtoDesc",descricao);
            bundle.putInt("produtoGrupo", grupoSelecionado.get_id());
            bundle.putInt("produtoId", idproduto);
            bundle.putString("produtoSit", situacao);
            Intent intent = new Intent(this, CadProduto2Activity.class);
            intent.putExtra("produtoBundle",bundle);
            finish();
            startActivity(intent);
        }
    }

    public void onContinuarClick(View view) {
         cadastrar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadprocesso, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_continuar:
                this.cadastrar();
                break;
            case R.id.action_menu_voltar:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
