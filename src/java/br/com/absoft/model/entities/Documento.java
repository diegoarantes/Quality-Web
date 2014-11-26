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
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "documento")
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdDocumento")
    private Integer idDocumento;

    @Column(name = "Titulo", nullable = false)
    private String titulo;

    @Column(name = "Usuario", nullable = false)
    private String usuario;

    @Column(name = "Revisao", nullable = false)
    private Integer revisao;

    @Column(name = "DataRevisao", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataRevisao;

    @Column(name = "Status", nullable = false)
    private char status;

    @Column(name = "Caminho", nullable = false)
    private String caminho;

    @Column(name = "Observacoes")
    private String observacoes;

    @ManyToOne
    @ForeignKey(name = "DocumentoTipo")
    @JoinColumn(name = "IdTipoDocumento", referencedColumnName = "IdTipoDocumento")
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @ForeignKey(name = "DocumentoSetor")
    @JoinColumn(name = "IdSetor", referencedColumnName = "IdSetor")
    private Setor setor;

    @ManyToOne
    @ForeignKey(name = "DocumentoPessoa")
    @JoinColumn(name = "IdPessoa", referencedColumnName = "IdPessoa")
    private Pessoa pessoa;

    public Documento() {
        tipoDocumento = new TipoDocumento();
        setor = new Setor();
        pessoa = new Pessoa();
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getRevisao() {
        return revisao;
    }

    public void setRevisao(Integer revisao) {
        this.revisao = revisao;
    }

    public Date getDataRevisao() {
        return dataRevisao;
    }

    public void setDataRevisao(Date dataRevisao) {
        this.dataRevisao = dataRevisao;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

     
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.idDocumento != null ? this.idDocumento.hashCode() : 0);
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
        final Documento other = (Documento) obj;
        if (this.idDocumento != other.idDocumento && (this.idDocumento == null || !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

}
