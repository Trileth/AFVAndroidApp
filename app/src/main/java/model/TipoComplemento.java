package model;

public class TipoComplemento {
    private Integer _id;
    private String descricao;

    public TipoComplemento(Integer id, String descricao) {
        this._id = _id;
        this.descricao = descricao;
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

}
