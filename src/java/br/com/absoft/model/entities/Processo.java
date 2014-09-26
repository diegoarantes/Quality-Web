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
@Table(name = "processo")
public class Processo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdProcesso", nullable = false)
    private Integer idProcesso;

    @Column(name = "Processo", nullable = false)
    private String processo;

    @OneToMany(mappedBy = "processo", fetch = FetchType.LAZY)// 1 para Muitos
    @ForeignKey(name = "ProcessoOcorrencia")
    private List<Ocorrencia> ocorrencias;

    public Processo() {
    }

    //Getters e Setters
    public Integer getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }

    //Getters e Setters dos Relacionamentos
    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    //Equals e Hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.idProcesso != null ? this.idProcesso.hashCode() : 0);
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
        final Processo other = (Processo) obj;
        if (this.idProcesso != other.idProcesso && (this.idProcesso == null || !this.idProcesso.equals(other.idProcesso))) {
            return false;
        }
        return true;
    }

}
