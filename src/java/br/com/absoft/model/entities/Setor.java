package br.com.absoft.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "setor")
public class Setor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdSetor", nullable = false)
    private Integer idSetor;

    @Column(name = "Setor", nullable = false, length = 40)
    private String setor;

    //Relacionamentos    
    @OneToMany(mappedBy = "setor", fetch = FetchType.LAZY)//1 para Muitos
    @ForeignKey(name = "SetorPessoa")
    private List<Pessoa> pessoas;

    @OneToMany(mappedBy = "setor", fetch = FetchType.LAZY)// 1 para Muitos
    @ForeignKey(name = "SetorOcorrencia")
    private List<Ocorrencia> ocorrencias;

    //Construtor
    public Setor() {
    }

    //Getters e Setters
    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    //Getters e Setters dos Relacionamentos
    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    //Equals e Hashcode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.idSetor != null ? this.idSetor.hashCode() : 0);
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
        final Setor other = (Setor) obj;
        if (this.idSetor != other.idSetor && (this.idSetor == null || !this.idSetor.equals(other.idSetor))) {
            return false;
        }
        return true;
    }

}
