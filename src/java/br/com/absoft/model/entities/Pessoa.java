package br.com.absoft.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    //Colunas
    @Id
    @GeneratedValue
    @Column(name = "IdPessoa", nullable = false)
    private Integer idPessoa;

    @Column(name = "Nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "Status", nullable = false)
    private char status;

    @Column(name = "TimeQualidade", nullable = false)
    private char timeQualidade;

    @Column(name = "ResponsavelSetor", nullable = false)
    private char responsavelSetor;

    @Column(name = "Usuario", nullable = false, length = 40, unique = true)
    private String usuario;

    @Column(name = "Senha", nullable = false, length = 40)
    private String senha;

    @Column(name = "Permissao", nullable = false, length = 20)
    private String permissao;

    @Column(name = "Email", nullable = false, length = 45)
    private String email;

    //Relacionamentos    
    @ManyToOne(optional = false) //Muitos para 1
    @ForeignKey(name = "PessoaSetor")
    @JoinColumn(name = "IdSetor", referencedColumnName = "IdSetor")
    private Setor setor;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY) //1 para Muitos
    @ForeignKey(name = "PessoaOcorrencia")
    private List<Ocorrencia> ocorrencias;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY) //1 para muitos
    @ForeignKey(name = "PessoaAnaliseDaCausa")
    private List<AnaliseDaCausa> analiseDasCausas;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY) //1 para muitos
    @ForeignKey(name = "PessoaAnaliseDaEficacia")
    private List<AnaliseEficacia> analisesEficacia;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY) //1 para muitos
    @ForeignKey(name = "PessoaPessoaPA")
    private List<PessoaPA> pessoasPA;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY) //1 para muitos
    @ForeignKey(name = "PessoaImplementacao")
    private List<Implementacao> implementacoes;

    //Construtor
    public Pessoa() {
        setor = new Setor();
    }

    //Getters e Setters
    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getTimeQualidade() {
        return timeQualidade;
    }

    public void setTimeQualidade(char timeQualidade) {
        this.timeQualidade = timeQualidade;
    }

    public char getResponsavelSetor() {
        return responsavelSetor;
    }

    public void setResponsavelSetor(char responsavelSetor) {
        this.responsavelSetor = responsavelSetor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Getters e Setters dos Relacionamentos
    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public List<AnaliseDaCausa> getAnaliseDasCausas() {
        return analiseDasCausas;
    }

    public void setAnaliseDasCausas(List<AnaliseDaCausa> analiseDasCausas) {
        this.analiseDasCausas = analiseDasCausas;
    }

    public List<AnaliseEficacia> getAnalisesEficacia() {
        return analisesEficacia;
    }

    public void setAnalisesEficacia(List<AnaliseEficacia> analisesEficacia) {
        this.analisesEficacia = analisesEficacia;
    }

    public List<PessoaPA> getPessoasPA() {
        return pessoasPA;
    }

    public void setPessoasPA(List<PessoaPA> pessoasPA) {
        this.pessoasPA = pessoasPA;
    }

    public List<Implementacao> getImplementacoes() {
        return implementacoes;
    }

    public void setImplementacoes(List<Implementacao> implementacoes) {
        this.implementacoes = implementacoes;
    }

    //Equals e Hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.idPessoa != null ? this.idPessoa.hashCode() : 0);
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
        final Pessoa other = (Pessoa) obj;
        if (this.idPessoa != other.idPessoa && (this.idPessoa == null || !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

}
