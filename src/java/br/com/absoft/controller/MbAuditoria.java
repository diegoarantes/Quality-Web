/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Auditoria;
import br.com.absoft.model.entities.Ocorrencia;
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
public class MbAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    private Auditoria auditoria = new Auditoria();

    private List<Auditoria> auditorias;

    private List<Ocorrencia> ocorrencias;

    Ocorrencia ocorrencia = new Ocorrencia();

    public MbAuditoria() {
    }

    public void addAuditoria() {
        if (auditoria.getIdAuditoria() == null) {
            insertAdutitoria();
        } else {
            updateAuditoria();
        }
        auditoria = new Auditoria();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
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

    public void vincular() {
        ocorrencia = (Ocorrencia) dao.recupera(Ocorrencia.class, ocorrencia.getIdOcorrencia()); //Recupera a ocorrencia pelo id

        ocorrencia.setAuditoria(auditoria);
        dao.atualizar(ocorrencia);

        ocorrencia = new Ocorrencia();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocorrência vinculada com sucesso!", ""));

    }

    public void desvincular() {
        ocorrencia = (Ocorrencia) dao.recupera(Ocorrencia.class, ocorrencia.getIdOcorrencia()); //Recupera a ocorrencia pelo id

        ocorrencia.setAuditoria(null);
        dao.atualizar(ocorrencia);

        ocorrencia = new Ocorrencia();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocorrência desvinculada com sucesso!", ""));
    }

    public String retonaTipo(char tip) {
        String tipo = null;

        switch (tip) {
            case 'I':
                tipo = "Interna";
                break;
            case 'E':
                tipo = "Externa";
                break;
        }
        return tipo;
    }

    public String retornaStatus(char stat) {
        String status = null;

        switch (stat) {
            case 'A':
                status = "Agendada";
                break;
            case 'E':
                status = "Em andamento";
                break;
            case 'C':
                status = "Encerrada";
                break;
        }

        return status;
    }

    public List<Ocorrencia> getOcorrencias() {
        return dao.listaCondicao(Ocorrencia.class, "auditoria.idAuditoria = " + auditoria.getIdAuditoria());
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

}
