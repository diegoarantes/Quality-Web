/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.OrgaoCertificador;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author diego
 */
@Named
@SessionScoped
public class MbOrgao implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    OrgaoCertificador orgao = new OrgaoCertificador();
    List<OrgaoCertificador> orgaos;

    public MbOrgao() {
    }

    public void addOrgao() {
        if (orgao.getIdOrgaoCertificador() == null) {
            insertOrgao();
        } else {
            updateOrgao();
        }
        orgao = new OrgaoCertificador();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
    }

    public void deleteOrgao() {
        try {
            dao.excluir(orgao);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Há um auditor vinculado á este orgão! - [" + ex.getMessage() + "]", ""));
        }
    }
    
    //--------------------------

    public OrgaoCertificador getOrgao() {
        return orgao;
    }

    public void setOrgao(OrgaoCertificador orgao) {
        this.orgao = orgao;
    }

    public List<OrgaoCertificador> getOrgaos() {
        return dao.lista(OrgaoCertificador.class);
    }

    public void setOrgaos(List<OrgaoCertificador> orgaos) {
        this.orgaos = orgaos;
    }

    private void insertOrgao() {
        dao.inserir(orgao);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateOrgao() {
        dao.atualizar(orgao);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

}
