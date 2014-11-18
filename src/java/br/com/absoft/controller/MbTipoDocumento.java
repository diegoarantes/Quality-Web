/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.TipoDocumento;
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
public class MbTipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    TipoDocumento tipoDocumento = new TipoDocumento();

    private List<TipoDocumento> tiposDocumento;

    public String addTipoDocumento() {
        if (tipoDocumento.getIdTipoDocumento() == null) {
            insertTipoDocumento();
        } else {
            updateTipoDocumento();
        }

        tipoDocumento = new TipoDocumento();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
        return null;

    }

    private void insertTipoDocumento() {
        dao.inserir(tipoDocumento);
        tipoDocumento = new TipoDocumento();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateTipoDocumento() {
        dao.atualizar(tipoDocumento);
        tipoDocumento = new TipoDocumento();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deleteTipoDocumento() {
        try {
            dao.excluir(tipoDocumento);
            
            tipoDocumento = new TipoDocumento();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Há um documento vinculado á este tipo! - [" + ex.getMessage() + "]", ""));
        }
    }

    public MbTipoDocumento() {
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<TipoDocumento> getTiposDocumento() {
        return dao.lista(TipoDocumento.class);
    }

    public void setTiposDocumento(List<TipoDocumento> tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }

}
