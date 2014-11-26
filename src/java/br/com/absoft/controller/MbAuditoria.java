/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Auditoria;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author diego
 */
@ManagedBean
@SessionScoped
public class MbAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    private Auditoria auditoria = new Auditoria();

    private List<Auditoria> auditorias;

    public MbAuditoria() {
    }

    public void addAuditoria() {
        if (auditoria.getIdAuditoria() == null) {
            insertAdutitoria();
        } else {
            updateAuditoria();
        }
        auditoria = new Auditoria();
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public List<Auditoria> getAuditorias() {
        return dao.lista(Auditoria.class);
    }

    public void setAuditorias(List<Auditoria> auditorias) {
        this.auditorias = auditorias;
    }

    private void insertAdutitoria() {
        dao.inserir(auditoria);
        //Exibe a Mensagem de Sucesso
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateAuditoria() {
        dao.atualizar(auditoria);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

}
