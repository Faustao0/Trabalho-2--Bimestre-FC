/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.uniagro.domain;

import java.util.Date;

/**
 *
 * @author andersonbosing
 */
public abstract class BaseEntity {
    
    private Integer id;
    private Date dtInsercao;
    private Date dtAtualizacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtInsercao() {
        return dtInsercao;
    }

    public void setDtInsercao(Date dtInsercao) {
        this.dtInsercao = dtInsercao;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    @Override
    public String toString() {
        return "BaseEntity{" + "id=" + id + ", dtInsercao=" + dtInsercao + ", dtAtualizacao=" + dtAtualizacao + '}';
    }
}
