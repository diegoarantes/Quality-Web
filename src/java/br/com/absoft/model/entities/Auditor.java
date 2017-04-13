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
/**
 *
 * @author diego
 */
@Entity
@Table(name = "auditor")
public class Auditor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAuditor")
    private Integer idAuditor;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Tipo")
    private char tipo;

    @Column(name = "Funcao")
    private String funcao;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DataCurso")
    private Date dataCurso;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "ValidadeCurso")
    private Date validadeCurso;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "Email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "IdOrgaoCertificador")
    private OrgaoCertificador orgaoCertificador;

    public Auditor() {
        orgaoCertificador = new OrgaoCertificador();
    }

    public Integer getIdAuditor() {
        return idAuditor;
    }

    public void setIdAuditor(Integer idAuditor) {
        this.idAuditor = idAuditor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Date getDataCurso() {
        return dataCurso;
    }

    public void setDataCurso(Date dataCurso) {
        this.dataCurso = dataCurso;
    }

    public Date getValidadeCurso() {
        return validadeCurso;
    }

    public void setValidadeCurso(Date validadeCurso) {
        this.validadeCurso = validadeCurso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrgaoCertificador getOrgaoCertificador() {
        return orgaoCertificador;
    }

    public void setOrgaoCertificador(OrgaoCertificador orgaoCertificador) {
        this.orgaoCertificador = orgaoCertificador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.idAuditor != null ? this.idAuditor.hashCode() : 0);
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
        final Auditor other = (Auditor) obj;
        if (this.idAuditor != other.idAuditor && (this.idAuditor == null || !this.idAuditor.equals(other.idAuditor))) {
            return false;
        }
        return true;
    }

}
