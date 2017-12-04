package model;

public class Empresa {
    private Integer _id;
    private String nome_fantasia;
    private String razao_social;
    private String endereco  ;
    private String bairro    ;
    private String cidade    ;
    private String cep       ;
    private String telefone  ;
    private String fax       ;
    private String cnpj      ;
    private String ie        ;

    public Empresa(){
        
    }
    
    public Empresa(Integer id, String nome_fantasia,String razao_social, String endereco, 
                   String bairro, String cidade, String cep       , String telefone , String fax   ,String cnpj  ,String ie){
        this._id           = id            ;
        this.nome_fantasia = nome_fantasia ;
        this.razao_social  = razao_social  ;
        this.endereco      = endereco      ;
        this.bairro        = bairro        ;
        this.cidade        = cidade        ;
        this.cep           = cep           ;
        this.telefone      = telefone      ;
        this.fax           = fax           ;
        this.cnpj          = cnpj          ;
        this.ie            = ie            ;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }
}
