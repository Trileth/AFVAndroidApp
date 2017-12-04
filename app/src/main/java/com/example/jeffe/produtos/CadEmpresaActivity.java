package com.example.jeffe.produtos;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.EditText;

        import dao.EmpresaDAO;
        import model.Empresa;
        import util.Mask;
        import util.Mensagem;


public class CadEmpresaActivity extends Activity {

    private EditText edtRazao,edtFantasia,edtCNPJ,edtIe,edtTelefone,
            edtFax,edtCep,edtEndereco,edtBairro,edtCidade;
    private EmpresaDAO empresaDAO;
    private Empresa empresa;
    private int idempresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadempresas);

        empresaDAO = new EmpresaDAO(this);

        edtRazao      =  findViewById(R.id.edtRazao);
        edtFantasia   =  findViewById(R.id.edtFantasia);
        edtCNPJ       =  findViewById(R.id.edtCnpj);
        edtIe         =  findViewById(R.id.edtIe);
        edtTelefone   =  findViewById(R.id.edtTelefone);
        edtFax        =  findViewById(R.id.edtFax);
        edtCep        =  findViewById(R.id.edtCep);
        edtEndereco   =  findViewById(R.id.edtEndereco);
        edtBairro     =  findViewById(R.id.edtBairro);
        edtCidade     =  findViewById(R.id.edtCidade);  
        edtCep.addTextChangedListener(Mask.insert(Mask.MaskType.CEP,  edtCep));
        edtCNPJ.addTextChangedListener(Mask.insert(Mask.MaskType.CNPJ,  edtCNPJ));
        
        //Modo de edição
        idempresa = getIntent().getIntExtra("EMPRESA_ID", 0);
        if(idempresa > 0){
            Empresa model = empresaDAO.buscarEmpresaPorId(idempresa);
            edtRazao.setText(model.getRazao_social());
            edtFantasia.setText(model.getNome_fantasia());
            edtCNPJ.setText(model.getCnpj());
            edtIe.setText(model.getIe());
            edtTelefone.setText(model.getTelefone());
            edtFax.setText(model.getFax());
            edtCep.setText(model.getCep());
            edtEndereco.setText(model.getEndereco());
            edtBairro.setText(model.getBairro());
            edtCidade.setText(model.getCidade());
            setTitle("Atualizar empresa");
        }
    }

    @Override
    protected void onDestroy() {
        empresaDAO.fechar();
        super.onDestroy();
    }

    private void cadastrar(){
        boolean validacao = true;
        
        String razao   =  edtRazao.getText().toString();
        String fantasia=  edtFantasia.getText().toString();
        String cnpj    =  edtCNPJ.getText().toString();
        String ie      =  edtIe.getText().toString();
        String telefone=  edtTelefone.getText().toString();
        String fax     =  edtFax.getText().toString();
        String cep     =  edtCep.getText().toString();
        String endereco=  edtEndereco.getText().toString();
        String bairro  =  edtBairro.getText().toString();
        String cidade  =  edtCidade.getText().toString();

        if (razao == null || razao.equals("")){
            validacao = false;
            edtRazao.setError(getString(R.string.campo_obrigatorio));
        }

        if (fantasia == null || fantasia.equals("")){
            validacao = false;
            edtFantasia.setError(getString(R.string.campo_obrigatorio));
        }

        if (cnpj == null || cnpj.equals("")){
            validacao = false;
            edtCNPJ.setError(getString(R.string.campo_obrigatorio));
        }

        if (ie == null || ie.equals("")){
            validacao = false;
            edtIe.setError(getString(R.string.campo_obrigatorio));
        }

        if (telefone == null || telefone.equals("")){
            validacao = false;
            edtTelefone.setError(getString(R.string.campo_obrigatorio));
        }

        if (cep == null || cep.equals("")){
            validacao = false;
            edtCep.setError(getString(R.string.campo_obrigatorio));
        }

        if (endereco == null || endereco.equals("")){
            validacao = false;
            edtEndereco.setError(getString(R.string.campo_obrigatorio));
        }

        if (bairro == null || bairro.equals("")){
            validacao = false;
            edtBairro.setError(getString(R.string.campo_obrigatorio));
        }

        if (cidade == null || cidade.equals("")){
            validacao = false;
            edtCidade.setError(getString(R.string.campo_obrigatorio));
        }


        if(validacao){
            empresa = new Empresa();
            empresa.setRazao_social(razao);
            empresa.setNome_fantasia(fantasia);
            empresa.setIe(ie);
            empresa.setCnpj(cnpj);
            empresa.setTelefone(telefone);
            empresa.setFax(fax);
            empresa.setCep(cep);
            empresa.setEndereco(endereco);
            empresa.setBairro(bairro);
            empresa.setCidade(cidade);

            //Se for atualizar
            if(idempresa > 0){
                empresa.set_id(idempresa);
            }

            long resultado = empresaDAO.salvarEmpresa(empresa);

            if(resultado != -1){
                if(idempresa > 0){
                    Mensagem.Msg(this, getString(R.string.mensagem_atualizar));
                }else{
                    Mensagem.Msg(this, getString(R.string.mensagem_cadastro));
                }

                finish();
                startActivity(new Intent(this, MainActivity.class));
            }else{
                Mensagem.Msg(this, getString(R.string.mensagem_erro));
            }
        }
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
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}