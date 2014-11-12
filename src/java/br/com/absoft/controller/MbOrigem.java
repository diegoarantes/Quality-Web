package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Origem;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class MbOrigem implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    Origem origem = new Origem();

    private List<Origem> origens;

    public String limpOrigem() {
        origem = new Origem();
        return editOrigem();
    }

    private String editOrigem() {
        return "origens";
    }

    public String addOrigem() {
        if (origem.getIdOrigem() == null) {
            insertOrigem();
        } else {
            updateOrigem();
        }
        origem = new Origem();

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
        return null;
    }

    private void insertOrigem() {
        dao.inserir(origem);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateOrigem() {
        dao.atualizar(origem);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deleteOrigem() {
        try {
            dao.excluir(origem);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Há uma ocorrência vinculada á esta origem! - [" + ex.getMessage() + "]", ""));
        }
    }

    public MbOrigem() {
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public List<Origem> getOrigens() {
        origens = dao.lista(Origem.class);
        return origens;
    }

    public void setOrigens(List<Origem> origens) {
        this.origens = origens;
    }

}
