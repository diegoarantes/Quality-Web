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

/**
 *
 * @author diego
 */
@Entity
@Table(name = "auditoria")
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdAuditoria", nullable = false)
    private Integer idAuditoria;

    @Column(name = "Tipo", nullable = false)
    private char tipo;

    @Column(name = "Status", nullable = false)
    private char status;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DataInicio", nullable = false)
    private Date dataInicio;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DataFim", nullable = false)
    private Date dataFim;

    @Column(name = "Evidencias", nullable = false, length = 2000)
    private String evidencias;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IdAuditor")
    private Auditor auditor;

    public Auditoria() {
        auditor = new Auditor();
    }

    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getEvidencias() {
        return evidencias;
    }

    public void setEvidencias(String evidencias) {
        this.evidencias = evidencias;
    }

    public Auditor getAuditor() {
        return auditor;
    }

    public void setAuditor(Auditor auditor) {
        this.auditor = auditor;
    }

}
