package com.example.jeffe.model;

public class Empresa {
    private Integer id;
    private String nomeFantasia;
    private String razaoSocial;
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
    
    public Empresa(Integer id, String nomeFantasia,String razaoSocial, String endereco,
                   String bairro, String cidade, String cep , String telefone , String fax,
                   String cnpj  ,String ie){
        this.id = id            ;
        this.nomeFantasia  = nomeFantasia ;
        this.razaoSocial   = razaoSocial  ;
        this.endereco      = endereco      ;
        this.bairro        = bairro        ;
        this.cidade        = cidade        ;
        this.cep           = cep           ;
        this.telefone      = telefone      ;
        this.fax           = fax           ;
        this.cnpj          = cnpj          ;
        this.ie            = ie            ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
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
