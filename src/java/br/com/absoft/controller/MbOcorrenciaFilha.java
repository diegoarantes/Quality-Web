package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Ocorrencia;
import br.com.absoft.model.entities.OcorrenciaOcorrencia;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MbOcorrenciaFilha implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;
    private Ocorrencia ocorrenciaPai = new Ocorrencia();
    private Ocorrencia ocorrenciaFilha = new Ocorrencia();

    private OcorrenciaOcorrencia ocorrencia = new OcorrenciaOcorrencia();

    private List<OcorrenciaOcorrencia> ocorrencias;

    public String limpOcorrencia() {
        ocorrencia = new OcorrenciaOcorrencia();
        return null;
    }

    public void insertOcorrencia() {
        ocorrencia.setOcorrencia(ocorrenciaPai);
        ocorrencia.setOcorrenciaFilha(ocorrenciaFilha);

        if (ocorrencia.getOcorrencia().getIdOcorrencia() == null || ocorrencia.getOcorrencia().getIdOcorrencia() == 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "É necessário efetuar a gravação da nova ocorrência antes de vincula-la a outra ocorrência!", ""));
        } else if (ocorrenciaPai.equals(ocorrenciaFilha)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Você não pode relacionar esta ocorrência com ela mesmo !", ""));
        } else {
            try {
                dao.inserir(ocorrencia);
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "A ocorrência selecionada já está vinculada!", ""));
            }
        }
    }

    public void deleteOcorrencia() {
        try {
            dao.excluir(ocorrencia);
        } catch (Exception ex) {

        }
    }

    public MbOcorrenciaFilha() {
    }

    public OcorrenciaOcorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(OcorrenciaOcorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public List<OcorrenciaOcorrencia> getOcorrencias() {
        //Retorna a Lista pela Criteria
        ocorrencias = dao.listaCondicao(OcorrenciaOcorrencia.class, "ocorrencia.idOcorrencia = " + ocorrenciaPai.getIdOcorrencia());

        return ocorrencias;
    }

    public void setOcorrencias(List<OcorrenciaOcorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public Ocorrencia getOcorrenciaPai() {
        return ocorrenciaPai;
    }

    public void setOcorrenciaPai(Ocorrencia ocorrenciaPai) {
        this.ocorrenciaPai = ocorrenciaPai;
    }

    public Ocorrencia getOcorrenciaFilha() {
        return ocorrenciaFilha;
    }

    public void setOcorrenciaFilha(Ocorrencia ocorrenciaFilha) {
        this.ocorrenciaFilha = ocorrenciaFilha;
    }

}
