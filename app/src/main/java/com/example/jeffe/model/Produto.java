package com.example.jeffe.model;


public class Produto {
    private Integer id;
    private Integer empresa;
    private Integer grupo;
    private Integer subgrupo;
    private String  descricao;
    private String  apelido;
    private String  situacao;
    private Double  pesoLiquido;
    private String  classificacao;
    private String  gtin;
    private String  colecao;

    public Produto(){

    }

    public Produto(Integer id,Integer empresa,Integer grupo,Integer subgrupo,String  descricao,
                   String  apelido,String  situacao,Double  pesoLiquido,String  classificacao,
                   String  gtin,String  colecao){
       this.id =  id;
       this.empresa =  empresa;
       this.grupo =  grupo;
       this.subgrupo =  subgrupo;
       this.descricao =  descricao;
       this.apelido =  apelido;
       this.situacao =  situacao;
       this.pesoLiquido =  pesoLiquido;
       this.classificacao =  classificacao;
       this.gtin =  gtin;
       this.colecao =  colecao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Integer getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(Integer subgrupo) {
        this.subgrupo = subgrupo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Double getPeso_liquido() {
        return pesoLiquido;
    }

    public void setPeso_liquido(Double peso_liquido) {
        this.pesoLiquido = peso_liquido;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getColecao() {
        return colecao;
    }

    public void setColecao(String colecao) {
        this.colecao = colecao;
    }
}
