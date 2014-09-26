package br.com.absoft.model.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "ocorrencia_ocorrencia")
public class OcorrenciaOcorrencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(optional = false)
    @ForeignKey(name = "OcorrenciaOcorrencia")
    @JoinColumn(name = "IdOcorrencia", referencedColumnName = "IdOcorrencia")
    private Ocorrencia ocorrencia;

    @Id
    @ManyToOne(optional = false)
    @ForeignKey(name = "OcorrenciaOcorrenciaFilha")
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

    //Equals e HashCode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.ocorrencia != null ? this.ocorrencia.hashCode() : 0);
        hash = 71 * hash + (this.ocorrenciaFilha != null ? this.ocorrenciaFilha.hashCode() : 0);
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
        if (this.ocorrencia != other.ocorrencia && (this.ocorrencia == null || !this.ocorrencia.equals(other.ocorrencia))) {
            return false;
        }
        if (this.ocorrenciaFilha != other.ocorrenciaFilha && (this.ocorrenciaFilha == null || !this.ocorrenciaFilha.equals(other.ocorrenciaFilha))) {
            return false;
        }
        return true;
    }

}
