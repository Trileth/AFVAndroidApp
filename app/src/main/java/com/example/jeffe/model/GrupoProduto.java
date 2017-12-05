package com.example.jeffe.model;

public class GrupoProduto {
    private Integer id;
    private String   descricao   ;
    private Double percDesc;
    private Integer  complemento ;

    public GrupoProduto(Integer id, String descricao, Double percDesc, Integer  complemento){
        this.id =  id         ;
        this.descricao    =  descricao   ;
        this.percDesc = percDesc;
        this.complemento  =  complemento ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPercDesc() {
        return percDesc;
    }

    public void setPercDesc(Double percDesc) {
        this.percDesc = percDesc;
    }

    public Integer getComplemento() {
        return complemento;
    }

    public void setComplemento(Integer complemento) {
        this.complemento = complemento;
    }
}
