/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.suport;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.TipoDocumento;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author diego
 */
@Named
@RequestScoped
public class BbTipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
       
    @EJB
    DAOGenerico dao;
    
    private List<TipoDocumento> tipos;

    public List<TipoDocumento> getTipos() {
        return dao.lista(TipoDocumento.class);
    }

    public void setTipos(List<TipoDocumento> tipos) {
        this.tipos = tipos;
    }
    
    
    
}
