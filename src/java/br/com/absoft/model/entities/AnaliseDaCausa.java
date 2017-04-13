package br.com.absoft.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "analise_da_causa")
public class AnaliseDaCausa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdAnaliseDaCausa", nullable = false)
    private Integer idAnaliseDaCausa;

    @Column(name = "DataCadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Column(name = "Porque", nullable = false, length = 300)
    private String porque;

    @Column(name = "CausaRaiz", nullable = false)
    private char causaRaiz;

    //Relacionamentos
    @ManyToOne(optional = true) //Muitos para 1
    @JoinColumn(name = "IdPessoa", referencedColumnName = "IdPessoa")
    private Pessoa pessoa;

    @ManyToOne(optional = true) // Muitos para 1
    @JoinColumn(name = "IdOcorrencia", referencedColumnName = "IdOcorrencia")
    private Ocorrencia ocorrencia;

    //Construtor
    public AnaliseDaCausa() {
//        pessoa = new Pessoa();
//        ocorrencia = new Ocorrencia();
    }

    //Getters e Setters
    public Integer getIdAnaliseDaCausa() {
        return idAnaliseDaCausa;
    }

    public void setIdAnaliseDaCausa(Integer idAnaliseDaCausa) {
        this.idAnaliseDaCausa = idAnaliseDaCausa;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getPorque() {
        return porque;
    }

    public void setPorque(String porque) {
        this.porque = porque;
    }

    public char getCausaRaiz() {
        return causaRaiz;
    }

    public void setCausaRaiz(char causaRaiz) {
        this.causaRaiz = causaRaiz;
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
        int hash = 7;
        hash = 71 * hash + (this.idAnaliseDaCausa != null ? this.idAnaliseDaCausa.hashCode() : 0);
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
        final AnaliseDaCausa other = (AnaliseDaCausa) obj;
        if (this.idAnaliseDaCausa != other.idAnaliseDaCausa && (this.idAnaliseDaCausa == null || !this.idAnaliseDaCausa.equals(other.idAnaliseDaCausa))) {
            return false;
        }
        return true;
    }

}
