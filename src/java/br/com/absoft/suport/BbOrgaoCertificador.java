/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.suport;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.OrgaoCertificador;
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
public class BbOrgaoCertificador implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    DAOGenerico dao;
    
    List<OrgaoCertificador> orgaos;
    
    public List<OrgaoCertificador> getOrgaos() {
        return dao.lista(OrgaoCertificador.class);
    }
    
}
