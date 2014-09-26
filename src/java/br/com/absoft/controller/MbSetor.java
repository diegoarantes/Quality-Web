package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Setor;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
public class MbSetor implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    Setor setor = new Setor();

    private List<Setor> setores;

    public String limpSetor() {
        setor = new Setor();
        return editSetor();
    }

    public String editSetor() {
        return "setores";
    }

    public String addSetor() {
        if (setor.getIdSetor() == null || setor.getIdSetor() == 0) {
            insertSetor();
        } else {
            updateSetor();
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
        return null;
    }

    public void insertSetor() {
        dao.inserir(setor);
        setor = new Setor();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    public void updateSetor() {
        dao.atualizar(setor);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deleteSetor() {
        try {
          dao.excluir(setor);
        } catch (Exception ex) {
             FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }
    }

    public MbSetor() {
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public List<Setor> getSetores() {
        setores = dao.lista(Setor.class);
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

}
