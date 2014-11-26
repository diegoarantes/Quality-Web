/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.suport;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Auditor;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author diego
 *
 */

@ManagedBean
@RequestScoped
public class BbAuditor implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    DAOGenerico dao;
    
    private List<Auditor> auditores;
    
    public BbAuditor() {
    }
    
    public List<Auditor> getAuditores() {
        return dao.lista(Auditor.class);
    }
    
}
