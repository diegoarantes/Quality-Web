package br.com.absoft.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "implementacao")
public class Implementacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdImplementacao", nullable = false)
    private Integer idImplementacao;

    @Column(name = "DataCadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Column(name = "Evidencia", nullable = false, length = 500)
    private String evidencia;

    //Relacionamentos
    @ManyToOne(optional = true) // Muitos para 1
    @JoinColumn(name = "IdOcorrencia", referencedColumnName = "IdOcorrencia")
    private Ocorrencia ocorrencia;

    @ManyToOne(optional = false) //Muitos para 1
    @JoinColumn(name = "IdPessoa", referencedColumnName = "IdPessoa")
    private Pessoa pessoa;

    @OneToOne// 1 para 1
    @JoinColumn(name = "IdPlanoAcao", referencedColumnName = "IdPlanoAcao")
    private PlanoAcao planoAcao;

    public Implementacao() {
        ocorrencia = new Ocorrencia();
        pessoa = new Pessoa();
        planoAcao = new PlanoAcao();
    }

    //Getters e Setters
    public Integer getIdImplementacao() {
        return idImplementacao;
    }

    public void setIdImplementacao(Integer idImplementacao) {
        this.idImplementacao = idImplementacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    //Getters e Setters dos Relacionamentos
    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PlanoAcao getPlanoAcao() {
        return planoAcao;
    }

    public void setPlanoAcao(PlanoAcao planoAcao) {
        this.planoAcao = planoAcao;
    }

    
    //Equals e hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.idImplementacao != null ? this.idImplementacao.hashCode() : 0);
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
        final Implementacao other = (Implementacao) obj;
        if (this.idImplementacao != other.idImplementacao && (this.idImplementacao == null || !this.idImplementacao.equals(other.idImplementacao))) {
            return false;
        }
        return true;
    }

}
