package br.com.absoft.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "setor")
public class Setor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSetor", nullable = false)
    private Integer idSetor;

    @Column(name = "Setor", nullable = false, length = 40)
    private String setor;

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
