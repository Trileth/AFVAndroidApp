package model;

public class GrupoProduto {
    private Integer  _id         ;
    private String   descricao   ;
    private Double   perc_desc   ;
    private Integer  complemento ;

    public GrupoProduto(Integer id,String descricao,Double perc_desc,Integer  complemento){
        this._id          =  id         ;
        this.descricao    =  descricao   ;
        this.perc_desc    =  perc_desc   ;
        this.complemento  =  complemento ;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPerc_desc() {
        return perc_desc;
    }

    public void setPerc_desc(Double perc_desc) {
        this.perc_desc = perc_desc;
    }

    public Integer getComplemento() {
        return complemento;
    }

    public void setComplemento(Integer complemento) {
        this.complemento = complemento;
    }
}
