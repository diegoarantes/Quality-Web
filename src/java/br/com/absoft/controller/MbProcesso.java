package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Processo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@SessionScoped
public class MbProcesso implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    Processo processo = new Processo();

    private List<Processo> processos;

    public String limpProcesso() {
        processo = new Processo();
        return editProcesso();
    }

    private String editProcesso() {
        return "processos";
    }

    public String addProcesso() {
        if (processo.getIdProcesso() == null) {
            insertProcesso();
        } else {
            updateProcesso();
        }
        processo = new Processo();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
        return null;
    }

    private void insertProcesso() {
        dao.inserir(processo);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateProcesso() {
        dao.atualizar(processo);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deleteProcesso() {
        try {
            dao.excluir(processo);
        } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Há uma ocorrência vinculada á este processo! - ["+ex.getMessage()+"]", ""));
        }
    }

    public MbProcesso() {
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public List<Processo> getProcessos() {
        processos = dao.lista(Processo.class);
        return processos;
    }

    public void setProcessos(List<Processo> processos) {
        this.processos = processos;
    }

}
