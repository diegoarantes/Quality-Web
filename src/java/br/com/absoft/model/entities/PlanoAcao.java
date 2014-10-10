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
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "plano_acao")
public class PlanoAcao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdPlanoAcao", nullable = false)
    private Integer idPlanoAcao;

    @Column(name = "DataCadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Column(name = "OqFazer", nullable = true, length = 300)
    private String oqFazer;

    @Column(name = "PqFazer", nullable = true, length = 300)
    private String pqFazer;

    @Column(name = "OndeFazer", nullable = true, length = 300)
    private String ondeFazer;

    @Column(name = "QuandoFazer", nullable = true, length = 45)
    private String quandoFazer;

    @Column(name = "QuemFazer", nullable = true, length = 45)
    private String quemFazer;

    @Column(name = "ComoFazer", nullable = true, length = 500)
    private String comoFazer;

    @Column(name = "QuantoCusta", nullable = true, length = 45)
    private String quantoCusta;

    @OneToOne(optional = true) // 1 para 1
    @ForeignKey(name = "PlanoAcaoPessoaPA")
    @JoinColumn(name = "IdPessoaPA", referencedColumnName = "IdPessoaPA")
    private PessoaPA pessoaPA;

    @ManyToOne(optional = true) // Muitos para 1
    @ForeignKey(name = "PlanoAcaoOcorrencia")
    @JoinColumn(name = "IdOcorrencia", referencedColumnName = "IdOcorrencia")
    private Ocorrencia ocorrencia;

    //Construtor
    public PlanoAcao() {
//        ocorrencia = new Ocorrencia();
//        pessoaPA = new PessoaPA();
    }

    //Getters e Setters
    public Integer getIdPlanoAcao() {
        return idPlanoAcao;
    }

    public void setIdPlanoAcao(Integer idPlanoAcao) {
        this.idPlanoAcao = idPlanoAcao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getOqFazer() {
        return oqFazer;
    }

    public void setOqFazer(String oqFazer) {
        this.oqFazer = oqFazer;
    }

    public String getPqFazer() {
        return pqFazer;
    }

    public void setPqFazer(String pqFazer) {
        this.pqFazer = pqFazer;
    }

    public String getOndeFazer() {
        return ondeFazer;
    }

    public void setOndeFazer(String ondeFazer) {
        this.ondeFazer = ondeFazer;
    }

    public String getQuandoFazer() {
        return quandoFazer;
    }

    public void setQuandoFazer(String quandoFazer) {
        this.quandoFazer = quandoFazer;
    }

    public String getQuemFazer() {
        return quemFazer;
    }

    public void setQuemFazer(String quemFazer) {
        this.quemFazer = quemFazer;
    }

    public String getComoFazer() {
        return comoFazer;
    }

    public void setComoFazer(String comoFazer) {
        this.comoFazer = comoFazer;
    }

    public String getQuantoCusta() {
        return quantoCusta;
    }

    public void setQuantoCusta(String quantoCusta) {
        this.quantoCusta = quantoCusta;
    }

    //Getters e Setters dos Relacionamentos
    public PessoaPA getPessoaPA() {
        return pessoaPA;
    }

    public void setPessoaPA(PessoaPA pessoaPA) {
        this.pessoaPA = pessoaPA;
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
        int hash = 3;
        hash = 59 * hash + (this.idPlanoAcao != null ? this.idPlanoAcao.hashCode() : 0);
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
        final PlanoAcao other = (PlanoAcao) obj;
        if (this.idPlanoAcao != other.idPlanoAcao && (this.idPlanoAcao == null || !this.idPlanoAcao.equals(other.idPlanoAcao))) {
            return false;
        }
        return true;
    }

}
