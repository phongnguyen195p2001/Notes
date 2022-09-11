package br.com.aptech.notes;

import java.util.Date;

public class Nota {
    private Integer Id;
    private String Descricao;
    private Date DataCriacao;

    public Integer getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Date getDataCriacao() {return DataCriacao;}

    public void setDataCriacao(Date novaData) { DataCriacao = novaData; }
}
