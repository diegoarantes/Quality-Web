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
@Table(name = "arquivos_auditoria")
public class ArquivosAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdArquivosAuditoria")
    private Integer idArquivosAuditoria;

    @Column(name = "Caminho", nullable = false)
    private String caminho;

    @ManyToOne(optional = false)//1 para Muitos
    @JoinColumn(name = "IdAuditoria")
    private Auditoria auditoria;

    public Integer getIdArquivosAuditoria() {
        return idArquivosAuditoria;
    }

    public void setIdArquivosAuditoria(Integer idArquivosAuditoria) {
        this.idArquivosAuditoria = idArquivosAuditoria;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.idArquivosAuditoria != null ? this.idArquivosAuditoria.hashCode() : 0);
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
        final ArquivosAuditoria other = (ArquivosAuditoria) obj;
        if (this.idArquivosAuditoria != other.idArquivosAuditoria && (this.idArquivosAuditoria == null || !this.idArquivosAuditoria.equals(other.idArquivosAuditoria))) {
            return false;
        }
        return true;
    }

}
