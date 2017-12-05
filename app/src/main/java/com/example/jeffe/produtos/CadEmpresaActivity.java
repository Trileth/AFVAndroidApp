package com.example.jeffe.produtos;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.EditText;

        import com.google.gson.Gson;

        import br.com.concretesolutions.canarinho.validator.Validador;
        import com.example.jeffe.dao.EmpresaDAO;
        import com.example.jeffe.model.Empresa;
        import com.example.jeffe.model.ViaCepEndereco;
        import com.example.jeffe.util.HTTPManager;
        import com.example.jeffe.util.Mask;
        import com.example.jeffe.util.Mensagem;


public class CadEmpresaActivity extends Activity {

    private EditText edtRazao,edtFantasia,edtCNPJ,edtIe,edtTelefone,
            edtFax,edtCep,edtEndereco,edtBairro,edtCidade;
    private EmpresaDAO empresaDAO;
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
        edtCep.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus) try {
                    validaCep(edtCep.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        edtCNPJ.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!Validador.CNPJ.ehValido(edtCNPJ.getText().toString()))
                   bloqueiaCNPJ();
            }
        });
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

    private void bloqueiaCNPJ(){
        edtCNPJ.setText("");
        edtCNPJ.hasFocus();
        Mensagem.Msg(this,"CNPJ Inválido!");
    }

    private void validaCep(String numCep) throws Exception {
        if(numCep.length()<9){
            edtCep.setError(getString(R.string.cep_invalido));
            limpaCep();
        }
        else
        if (numCep.equals("")){
            edtCep.setError(getString(R.string.campo_obrigatorio));
            limpaCep();
        }
        else {
            ViaCepEndereco enderecoCompleto = retornaEndereco(numCep);
            if (enderecoCompleto==null) {
                edtCep.setError(getString(R.string.cep_invalido));
                limpaCep();
            }
            edtEndereco.setText(enderecoCompleto.getLogradouro());
            edtBairro.setText(enderecoCompleto.getBairro());
            edtCidade.setText(enderecoCompleto.getLocalidade());
        }

    }

    @Override
    protected void onDestroy() {
        empresaDAO.fechar();
        super.onDestroy();
    }

    private void limpaCep(){
        edtEndereco.setText("");
        edtBairro.setText("");
        edtCidade.setText("");
        edtCep.setText("");
        edtCep.hasFocus();
        Mensagem.Msg(this,"CEP Inválido!");
    }

    private void cadastrar(){
        String razao   =  edtRazao.getText().toString();
        String fantasia=  edtFantasia.getText().toString();
        String cnpj    =  edtCNPJ.getText().toString();
        String ie      =  edtIe.getText().toString();
        String telefone=  edtTelefone.getText().toString();
        String fax     =  edtFax.getText().toString();
        String numCep     =  edtCep.getText().toString();
        String endereco=  edtEndereco.getText().toString();
        String bairro  =  edtBairro.getText().toString();
        String cidade  =  edtCidade.getText().toString();
        
        if(validacao(razao, fantasia, cnpj, ie, telefone, numCep, endereco, bairro,  cidade  )){
            Empresa empresa = new Empresa();
            empresa.setRazao_social(razao);
            empresa.setNome_fantasia(fantasia);
            empresa.setIe(ie);
            empresa.setCnpj(cnpj);
            empresa.setTelefone(telefone);
            empresa.setFax(fax);
            empresa.setCep(numCep);
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
                startActivity(new Intent(this, EmpresasActivity.class));
            }else{
                Mensagem.Msg(this, getString(R.string.mensagem_erro));
            }
        }
    }
    private boolean validacao(String razao, String fantasia, String cnpj, String ie,
                              String telefone, String numCep, String endereco,
                              String bairro, String cidade){
        if (razao.equals("")){
            edtRazao.setError(getString(R.string.campo_obrigatorio));
            return false;            
        }

        if (fantasia.equals("")){
            edtFantasia.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (cnpj.equals("")){
            edtCNPJ.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (ie.equals("")){
            edtIe.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (telefone.equals("")){
            edtTelefone.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (numCep.equals("")){
            edtCep.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (endereco.equals("")){
            edtEndereco.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (bairro.equals("")){
            edtBairro.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (cidade.equals("")){
            edtCidade.setError(getString(R.string.campo_obrigatorio));
            return false;
        }
        return true;
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

    public void onSalvarClick(View view){
        this.cadastrar();
    }

    private ViaCepEndereco retornaEndereco(String numCep) throws Exception {
        HTTPManager httpManager = new HTTPManager();
        String jsonString = httpManager.getJSON("http://viacep.com.br/ws/"+numCep+"/json/",4*1000);
        System.out.println(jsonString);
        ViaCepEndereco viaCep;
        if (jsonString.contains("erro") && jsonString.contains("true")){
            viaCep = null;
        }else {
            viaCep = new Gson().fromJson(jsonString, ViaCepEndereco.class);
        }
        return viaCep;
    }

}