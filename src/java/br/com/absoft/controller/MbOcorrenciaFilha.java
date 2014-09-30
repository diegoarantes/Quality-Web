package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.OcorrenciaOcorrencia;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.NonUniqueObjectException;

@ManagedBean
@ViewScoped
public class MbOcorrenciaFilha implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    private OcorrenciaOcorrencia ocorrencia = new OcorrenciaOcorrencia();

    private List<OcorrenciaOcorrencia> ocorrencias;

    public String limpOcorrencia() {
        ocorrencia = new OcorrenciaOcorrencia();
        return null;
    }

    public void insertOcorrencia() {
        if (ocorrencia.getOcorrencia().getIdOcorrencia() == null || ocorrencia.getOcorrencia().getIdOcorrencia() == 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "É necessário efetuar a gravação da nova ocorrência antes de vincula-la a outra ocorrência!", ""));
        } else {
            try {
                System.out.println("------------------------------------------");
                System.err.println(ocorrencia.getOcorrencia().getDescricao());
                System.err.println(ocorrencia.getOcorrenciaFilha().getDescricao());
                dao.inserir(ocorrencia);

            } catch (NonUniqueObjectException ex) {
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
        ocorrencias = dao.listaCondicao(OcorrenciaOcorrencia.class, "ocorrencia.idOcorrencia = " + ocorrencia.getOcorrencia().getIdOcorrencia());

        return ocorrencias;
    }

    public void setOcorrencias(List<OcorrenciaOcorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

}
