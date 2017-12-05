package com.example.jeffe.produtos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import adapter.SpinnerGrupoAdapter;
import dao.GrupoProdutoDAO;
import dao.ProdutoDAO;
import model.GrupoProduto;
import model.Produto;
import util.Mensagem;


public class CadProduto2Activity extends Activity {

    private EditText  edtApelido,edtPeso,edtClassificacao ;
    private Spinner spnSubgrupo;
    private ProdutoDAO produtoDAO;
    private Produto produto;
    private GrupoProdutoDAO grupoDAO;
    private int idproduto;
    private String descricao;
    private String situacao;
    private int grupo;
    private int empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadprodutos2);

        produtoDAO = new ProdutoDAO(this);
        grupoDAO = new GrupoProdutoDAO(this);
        carregaValores();
    }

    private void carregaValores() {
        edtApelido = findViewById(R.id.produto_edtApelido);
        edtPeso = findViewById(R.id.produto_edtPeso);
        edtClassificacao = findViewById(R.id.produto_edtClassificacao);
        configSpinnerGrupo();

        Bundle bundle = getIntent().getBundleExtra("produtoBundle");
        //Modo de edição
        idproduto = bundle.getInt("produtoId",0);
        if(idproduto > 0){
            Produto model = produtoDAO.buscarProdutoPorId(idproduto);
            edtApelido.setText(model.getApelido());
            spnSubgrupo.setSelection(model.getSubgrupo()-1);
            edtPeso.setText(model.getPeso_liquido().toString());
            edtClassificacao.setText(model.getClassificacao());
        }
        descricao = bundle.getString("produtoDesc");
        grupo = bundle.getInt("produtoGrupo");
        situacao = bundle.getString("produtoSit");
        empresa = bundle.getInt("produtoEmp");
    }

    private void configSpinnerGrupo(){
        //Configurando Spinner Grupo
        spnSubgrupo = findViewById(R.id.produto_spnSubgrupo);
        List<GrupoProduto> listagrupos = grupoDAO.listarGrupoProduto();
        GrupoProduto[] grupoArr = listagrupos.toArray(new GrupoProduto[listagrupos.size()]);
        SpinnerGrupoAdapter arrayAdapter = new SpinnerGrupoAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, grupoArr);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnSubgrupo.setAdapter(arrayAdapter);
    }


    public void onSalvarClick(View view) {
        cadastrar();
    }


    @Override
    protected void onDestroy() {
        produtoDAO.fechar();
        super.onDestroy();
    }

    private void cadastrar(){
        produto = setValores();
        long resultado = produtoDAO.salvarProduto(produto);
        if(resultado != -1){
            if(idproduto > 0){
                Mensagem.Msg(this, getString(R.string.mensagem_atualizar));
            }else{
                Mensagem.Msg(this, getString(R.string.mensagem_cadastro));
            }
            finish();
        }else{
            Mensagem.Msg(this, getString(R.string.mensagem_erro));
        }

    }

    private Produto setValores() {
        GrupoProduto subgrupoSelecionado = (GrupoProduto) spnSubgrupo.getSelectedItem();
        String apelido  =  edtApelido.getText().toString();
        String peso =  edtPeso.getText().toString();
        String classificacao = edtClassificacao.getText().toString();
        produto = new Produto();
        produto.set_id(idproduto);
        produto.setDescricao(descricao);
        produto.setGrupo(grupo);
        produto.setEmpresa(empresa);
        produto.setSituacao(situacao);
        produto.setApelido(apelido);
        produto.setSubgrupo(subgrupoSelecionado.get_id());
        produto.setPeso_liquido(Double.valueOf(peso));
        produto.setClassificacao(classificacao);
        return produto;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastros, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_salvar:
                this.cadastrar();
                break;
            case R.id.action_menu_sair:
                Intent intent = new Intent(this, CadProdutoActivity.class);
                intent.putExtra("PRODUTO_ID", idproduto);
                intent.putExtra("EMP_LOGADA", empresa);
                finish();
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
