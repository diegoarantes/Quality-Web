package br.com.absoft.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "pessoa_pa")
public class PessoaPA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdPessoaPA", nullable = false)
    private Integer idPessoaPA;

    //Relacionamentos
    @ManyToOne(optional = false)//Muitos para 1
    @ForeignKey(name = "PessoaPAPessoa")
    @JoinColumn(name = "IdPessoa", referencedColumnName = "IdPessoa")
    private Pessoa pessoa;

//    @ManyToOne(optional = false)//Muitos para 1
//    @ForeignKey(name = "PessoaPAPlanoDeAcao")
//    @JoinColumn(name = "IdPlanoAcao", referencedColumnName = "IdPlanoAcao")
//    private PlanoAcao planoAcao;

    //Construtor
    public PessoaPA() {
        pessoa = new Pessoa();
//        planoAcao = new PlanoAcao();
    }

    //Getters e Setters
    public Integer getIdPessoaPA() {
        return idPessoaPA;
    }

    public void setIdPessoaPA(Integer idPessoaPA) {
        this.idPessoaPA = idPessoaPA;
    }

    //Getters e Setters dos Relacionamentos
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

//    public PlanoAcao getPlanoAcao() {
//        return planoAcao;
//    }
//
//    public void setPlanoAcao(PlanoAcao planoAcao) {
//        this.planoAcao = planoAcao;
//    }

    //Equals e Hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.idPessoaPA != null ? this.idPessoaPA.hashCode() : 0);
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
        final PessoaPA other = (PessoaPA) obj;
        if (this.idPessoaPA != other.idPessoaPA && (this.idPessoaPA == null || !this.idPessoaPA.equals(other.idPessoaPA))) {
            return false;
        }
        return true;
    }

}
