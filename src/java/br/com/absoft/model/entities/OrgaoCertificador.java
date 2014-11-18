/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "orgao_certificador")
public class OrgaoCertificador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdOrgaoCertificado")
    private Integer idOrgaoCertificador;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "PessoaContato")
    private String pessoaContato;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "Endereco")
    private String endereco;

    @Column(name = "NormaAuditada")
    private String normaAuditada;

    public OrgaoCertificador() {
    }

    public Integer getIdOrgaoCertificador() {
        return idOrgaoCertificador;
    }

    public void setIdOrgaoCertificador(Integer idOrgaoCertificador) {
        this.idOrgaoCertificador = idOrgaoCertificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPessoaContato() {
        return pessoaContato;
    }

    public void setPessoaContato(String pessoaContato) {
        this.pessoaContato = pessoaContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNormaAuditada() {
        return normaAuditada;
    }

    public void setNormaAuditada(String normaAuditada) {
        this.normaAuditada = normaAuditada;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.idOrgaoCertificador != null ? this.idOrgaoCertificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrgaoCertificador other = (OrgaoCertificador) obj;
        if (this.idOrgaoCertificador != other.idOrgaoCertificador && (this.idOrgaoCertificador == null || !this.idOrgaoCertificador.equals(other.idOrgaoCertificador))) {
            return false;
        }
        return true;
    }

}
