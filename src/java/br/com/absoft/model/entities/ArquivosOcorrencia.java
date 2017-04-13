/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "arquivosOcorrencia")
public class ArquivosOcorrencia implements Serializable {
   private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idArquivosOcorrencia;

    @Column(name = "Caminho", nullable = false)
    private String caminho;

    @ManyToOne(optional = false)//1 para Muitos
    @JoinColumn(name = "IdOcorrencia")
    private Ocorrencia ocorrencia;

    public ArquivosOcorrencia() {
        ocorrencia = new Ocorrencia();
    }

    public Long getIdArquivosOcorrencia() {
        return idArquivosOcorrencia;
    }

    public void setIdArquivosOcorrencia(Long idArquivosOcorrencia) {
        this.idArquivosOcorrencia = idArquivosOcorrencia;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (this.idArquivosOcorrencia != null ? this.idArquivosOcorrencia.hashCode() : 0);
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
        final ArquivosOcorrencia other = (ArquivosOcorrencia) obj;
        if (this.idArquivosOcorrencia != other.idArquivosOcorrencia && (this.idArquivosOcorrencia == null || !this.idArquivosOcorrencia.equals(other.idArquivosOcorrencia))) {
            return false;
        }
        return true;
    }

}
