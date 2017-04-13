package br.com.absoft.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "analise_eficacia")
public class AnaliseEficacia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAnaliseEficacia", nullable = false)
    private Integer idAnaliseEficacia;

    @Column(name = "Eficaz", nullable = false)
    private char eficaz;

    @Column(name = "Justificativa", nullable = false, length = 500)
    private String justificativa;

    @Column(name = "DataCadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    //Relacionamentos
    @ManyToOne(optional = false) // Muitos Para 1
    @JoinColumn(name = "IdPessoa", referencedColumnName = "IdPessoa")
    private Pessoa pessoa;

    //Relacionamentos
    @ManyToOne(optional = false) // Muitos Para 1
    @JoinColumn(name = "IdOcorrencia", referencedColumnName = "IdOcorrencia")
    private Ocorrencia ocorrencia;

    //Construtor
    public AnaliseEficacia() {
        pessoa = new Pessoa();
        ocorrencia = new Ocorrencia();

    }

    //Getters e Setters
    public Integer getIdAnaliseEficacia() {
        return idAnaliseEficacia;
    }

    public void setIdAnaliseEficacia(Integer idAnaliseEficacia) {
        this.idAnaliseEficacia = idAnaliseEficacia;
    }

    public char getEficaz() {
        return eficaz;
    }

    public void setEficaz(char eficaz) {
        this.eficaz = eficaz;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    //Getters e Setters dos Relacionamentos
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    //Equals e Hashcode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.idAnaliseEficacia != null ? this.idAnaliseEficacia.hashCode() : 0);
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
        final AnaliseEficacia other = (AnaliseEficacia) obj;
        if (this.idAnaliseEficacia != other.idAnaliseEficacia && (this.idAnaliseEficacia == null || !this.idAnaliseEficacia.equals(other.idAnaliseEficacia))) {
            return false;
        }
        return true;
    }

}
