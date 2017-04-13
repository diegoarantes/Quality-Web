package br.com.absoft.model.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ocorrencia_ocorrencia", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IdOcorrencia", "IdOcorrenciaFilha"}, name = "vinculosOcorrencia")}
//Define que o valor das colunas juntas devem ser único ex: 1 e 1 nao podera repetir 1 e 1 em outro registro
)
public class OcorrenciaOcorrencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "IdOcorrencia", referencedColumnName = "IdOcorrencia")
    private Ocorrencia ocorrencia;

    @ManyToOne(optional = true)
    @JoinColumn(name = "IdOcorrenciaFilha", referencedColumnName = "IdOcorrencia")
    private Ocorrencia ocorrenciaFilha;

    public OcorrenciaOcorrencia() {
        ocorrencia = new Ocorrencia();
        ocorrenciaFilha = new Ocorrencia();
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Ocorrencia getOcorrenciaFilha() {
        return ocorrenciaFilha;
    }

    public void setOcorrenciaFilha(Ocorrencia ocorrenciaFilha) {
        this.ocorrenciaFilha = ocorrenciaFilha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //Equals e HashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final OcorrenciaOcorrencia other = (OcorrenciaOcorrencia) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
