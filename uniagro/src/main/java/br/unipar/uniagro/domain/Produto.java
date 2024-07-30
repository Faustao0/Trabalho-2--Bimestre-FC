package br.unipar.uniagro.domain;

import br.unipar.uniagro.enums.ClasseProdutoEnum;
import br.unipar.uniagro.enums.StatusProdutoEnum;
import java.math.BigDecimal;

public class Produto extends BaseEntity {
    
    private String nome;
    private BigDecimal vlrPreco;
    private ClasseProdutoEnum classe;
    private StatusProdutoEnum status;
    private Marca marca;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getVlrPreco() {
        return vlrPreco;
    }

    public void setVlrPreco(BigDecimal vlrPreco) {
        this.vlrPreco = vlrPreco;
    }

    public ClasseProdutoEnum getClasse() {
        return classe;
    }

    public void setClasse(ClasseProdutoEnum classe) {
        this.classe = classe;
    }

    public StatusProdutoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusProdutoEnum status) {
        this.status = status;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", vlrPreco=" + vlrPreco + ", classe=" + classe + ", status=" + status + ", marca=" + marca + '}'+ super.toString();
    }
}
