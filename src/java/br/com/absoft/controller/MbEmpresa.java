package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MbEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    Empresa empresa = new Empresa();

    private List<Empresa> empresas;

    public String limpEmpresa() {
        empresa = new Empresa();
        return editEmpresa();

    }

    private String editEmpresa() {
        return "empresas";
    }

    public String addEmpresa() {
        if (empresa.getIdEmpresa() == null) {
            insertEmpresa();
        } else {
            updateEmpresa();
        }
        empresa = new Empresa();
        return null;
    }

    private void insertEmpresa() {
       dao.inserir(empresa);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateEmpresa() {
        dao.atualizar(empresa);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deleteEmpresa() {
        try {
            dao.excluir(empresa);
        } catch (Exception ex) {
            
        }
    }

    public MbEmpresa() {
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getEmpresas() {
        empresas = dao.lista(Empresa.class);
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

}
