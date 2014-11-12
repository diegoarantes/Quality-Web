package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.AnaliseDaCausa;
import br.com.absoft.model.entities.Ocorrencia;
import br.com.absoft.suport.BbUsuarioLogado;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MbAnaliseDaCausa implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    private Ocorrencia ocorrencia = new Ocorrencia();
    private AnaliseDaCausa analise = new AnaliseDaCausa();

    private AnaliseDaCausa causaRaiz;

    private List<AnaliseDaCausa> analisesDaCausa;
    
    public String addAnaliseDaCausa() {

        if (analise.getIdAnaliseDaCausa() == null) {
            analise.setOcorrencia(ocorrencia);
            analise.setPessoa(BbUsuarioLogado.user);
            insertAnaliseDaCausa();
        } else {
            updateAnaliseDaCausa();
        }
        analise = new AnaliseDaCausa();
        return null;
    }

    private void insertAnaliseDaCausa() {
        analise.setDataCadastro(new Date()); //Pega a data atual
        analise.setCausaRaiz('N'); //Seta que ainda não é a causa raíz
        dao.inserir(analise);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateAnaliseDaCausa() {
        dao.atualizar(analise);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deleteAnaliseDaCausa() {
        try {
            dao.excluir(analise);
        } catch (Exception ex) {

        }
    }

    public void definirRaiz() {
        /**
         * Seta verifica se tem causa raíz e marca com 'N'
         */
        for (AnaliseDaCausa anali : analisesDaCausa) {
            if (anali.getCausaRaiz() == 'S') {
                anali.setCausaRaiz('N');
                dao.atualizar(anali);
            }
        }
        analise.setCausaRaiz('S');  //Seta como causa raíz
        dao.atualizar(analise);

        analise = new AnaliseDaCausa();
        
        ocorrencia.setStatus('P'); //Seta o status da ocorrência como Aguardando Plano de Acao
        dao.atualizar(ocorrencia); //Atualiza a ocorrência
    }

    public MbAnaliseDaCausa() {
    }

    public List<AnaliseDaCausa> getAnalisesDaCausa() {
        if (ocorrencia != null) {
            analisesDaCausa = dao.listaCondicao(AnaliseDaCausa.class, "ocorrencia.idOcorrencia = " + ocorrencia.getIdOcorrencia());
           
            /**
             * Se for encontrado a analise com a causa raíz marcada com 'S' seta na váriavel causa raíz
             */
            for (AnaliseDaCausa causa : analisesDaCausa) {
                if (causa.getCausaRaiz() == 'S') {
                    causaRaiz = causa;
                }
            }
        }
        return analisesDaCausa;
    }

    public void setAnalisesDaCausa(List<AnaliseDaCausa> analisesDaCausa) {
        this.analisesDaCausa = analisesDaCausa;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public AnaliseDaCausa getAnalise() {
        if (analise == null) {
            analise = new AnaliseDaCausa();
        }
        return analise;
    }

    public void setAnalise(AnaliseDaCausa analise) {
        this.analise = analise;
    }

    public AnaliseDaCausa getCausaRaiz() {
        return causaRaiz;
    }

    public void setCausaRaiz(AnaliseDaCausa causaRaiz) {
        this.causaRaiz = causaRaiz;
    }

    }
