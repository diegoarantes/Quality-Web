/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Auditor;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author diego
 */
@ManagedBean
@SessionScoped
public class MbAuditor implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    Auditor auditor = new Auditor();

    List<Auditor> auditores;

    public MbAuditor() {
    }

    public void addAuditor() {
        if (auditor.getIdAuditor() == null) {
            insertAuditor();
        } else {
            updateAuditor();
        }
        auditor = new Auditor();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
    }

    public void excluir() {
        try {
            dao.excluir(auditor);
            auditor = new Auditor();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Há uma auditoria vinculada á este auditor! - [" + ex.getMessage() + "]", ""));
            auditor = new Auditor();
        }

    }

    public Auditor getAuditor() {
        return auditor;
    }

    public void setAuditor(Auditor auditor) {
        this.auditor = auditor;
    }

    public List<Auditor> getAuditores() {
        return dao.lista(Auditor.class);
    }

    public void setAuditores(List<Auditor> auditores) {
        this.auditores = auditores;
    }

    private void insertAuditor() {
        dao.inserir(auditor);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateAuditor() {
        dao.atualizar(auditor);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

}
