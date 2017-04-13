package br.com.absoft.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "origem")
public class Origem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdOrigem", nullable = false)
    private Integer idOrigem;

    @Column(name = "Origem", nullable = false, length = 45)
    private String origem;

    //Construtor
    public Origem() {
    }

    //Getters e Setters
    public Integer getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(Integer idOrigem) {
        this.idOrigem = idOrigem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    //Equals e Hashcode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.idOrigem != null ? this.idOrigem.hashCode() : 0);
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
        final Origem other = (Origem) obj;
        if (this.idOrigem != other.idOrigem && (this.idOrigem == null || !this.idOrigem.equals(other.idOrigem))) {
            return false;
        }
        return true;
    }

}
