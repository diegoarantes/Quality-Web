package br.com.absoft.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "tipodocumento")
public class TipoDocumento implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "IdTipoDocumento")
    private Integer idTipoDocumento;
    
    @Column(name = "TipoDocumento", nullable = false)
    private String tipoDocumento;
    
   
    public TipoDocumento() {
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.idTipoDocumento != null ? this.idTipoDocumento.hashCode() : 0);
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
        final TipoDocumento other = (TipoDocumento) obj;
        if (this.idTipoDocumento != other.idTipoDocumento && (this.idTipoDocumento == null || !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }
    
    
}
