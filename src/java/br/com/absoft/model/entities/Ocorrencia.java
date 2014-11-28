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
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdOcorrencia", nullable = false)
    private Integer idOcorrencia;

    @Column(name = "Tipo", nullable = false)
    private char tipo;

    @Column(name = "Status", nullable = false)
    private char status;

    @Column(name = "Usuario", nullable = false, length = 45)
    private String usuario;

    @Column(name = "DataCadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Column(name = "DataAbertura", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAbertura;

    @Column(name = "Prazo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date prazo;

    @Column(name = "Titulo", nullable = false, length = 70)
    private String titulo;

    @Column(name = "Descricao", nullable = false, length = 1000)
    private String descricao;

    @Column(name = "DataFechamento", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dataFechamento;

    @Column(name = "Revisao", nullable = false)
    private Integer revisao;

    @Column(name = "AcaoImediata", nullable = true, length = 500)
    private String acaoImediata;

    //Relacionamentos
    @ManyToOne(optional = false) // Muitos para 1
    @ForeignKey(name = "OcorrenciaEmpresa")
    @JoinColumn(name = "IdEmpresa", referencedColumnName = "IdEmpresa")
    private Empresa empresa;

    @ManyToOne(optional = false) //Muitos para 1
    @ForeignKey(name = "OcorrenciaOrigem")
    @JoinColumn(name = "IdOrigem", referencedColumnName = "IdOrigem")
    private Origem origem;

    @ManyToOne(optional = false) //Muitos para 1
    @ForeignKey(name = "OcorrenciaPessoa")
    @JoinColumn(name = "IdPessoa", referencedColumnName = "IdPessoa")
    private Pessoa pessoa;

    @ManyToOne(optional = false) //Muitos para 1
    @ForeignKey(name = "OcorrenciaSetor")
    @JoinColumn(name = "IdSetor", referencedColumnName = "IdSetor")
    private Setor setor;

    @ManyToOne(optional = false) //Muitos para 1
    @ForeignKey(name = "OcorrenciaProcesso")
    @JoinColumn(name = "IdProcesso", referencedColumnName = "IdProcesso")
    private Processo processo;

    @ManyToOne(optional = true)
    @ForeignKey(name = "OcorrenciaAuditoria")
    @JoinColumn(name = "IdAuditoria")
    private Auditoria auditoria;

    //Construtor
    public Ocorrencia() {
        empresa = new Empresa();
        origem = new Origem();
        pessoa = new Pessoa();
        setor = new Setor();
        processo = new Processo();
 //       auditoria = new Auditoria();
    }

    //Getters e Setters
    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getRevisao() {
        return revisao;
    }

    public void setRevisao(Integer revisao) {
        this.revisao = revisao;
    }

    public String getAcaoImediata() {
        return acaoImediata;
    }

    public void setAcaoImediata(String acaoImediata) {
        this.acaoImediata = acaoImediata;
    }

    //Getters e Setters dos Relacionamentos
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    //Equals e Hashcode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (this.idOcorrencia != null ? this.idOcorrencia.hashCode() : 0);
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
        final Ocorrencia other = (Ocorrencia) obj;
        if (this.idOcorrencia != other.idOcorrencia && (this.idOcorrencia == null || !this.idOcorrencia.equals(other.idOcorrencia))) {
            return false;
        }
        return true;
    }

}
