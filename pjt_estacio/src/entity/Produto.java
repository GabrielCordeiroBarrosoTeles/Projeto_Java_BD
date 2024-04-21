package entity;

import java.sql.Date;

public class Produto {
    private int idproduto;
    private String nome;
    private int qtd;
    private Date  data_entrega;
    public int getIdproduto() {
        return idproduto;
    }
    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getQtd() {
        return qtd;
    }
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    public Date getData_entrega() {
        return data_entrega;
    }
    public void setData_entrega(Date data_entrega) {
        this.data_entrega = data_entrega;
    }
}
