package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Ocorrencia;
import br.com.absoft.suport.BbUsuarioLogado;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;    
import javax.faces.bean.SessionScoped;

/**
 *
 * @author diego
 */
@ManagedBean
@SessionScoped
public class MbPendencias implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    private List<Ocorrencia> aAprovacao;
    private List<Ocorrencia> aAnalise;
    private List<Ocorrencia> aPlano;
    private List<Ocorrencia> aValidacao;
    private List<Ocorrencia> aImplementacao;
    private List<Ocorrencia> aEficacia;

    public List<Ocorrencia> getaAprovacao() {
        aAprovacao = dao.listaCondicao(Ocorrencia.class, "pessoa.idPessoa = " + BbUsuarioLogado.user.getIdPessoa() + " AND status = 'A' ");
        return aAprovacao;
    }

    public void setaAprovacao(List<Ocorrencia> aAprovacao) {
        this.aAprovacao = aAprovacao;
    }

    public List<Ocorrencia> getaAnalise() {
        return aAnalise;
    }

    public void setaAnalise(List<Ocorrencia> aAnalise) {
        this.aAnalise = aAnalise;
    }

    public List<Ocorrencia> getaPlano() {
        return aPlano;
    }

    public void setaPlano(List<Ocorrencia> aPlano) {
        this.aPlano = aPlano;
    }

    public List<Ocorrencia> getaValidacao() {
        return aValidacao;
    }

    public void setaValidacao(List<Ocorrencia> aValidacao) {
        this.aValidacao = aValidacao;
    }

    public List<Ocorrencia> getaImplementacao() {
        return aImplementacao;
    }

    public void setaImplementacao(List<Ocorrencia> aImplementacao) {
        this.aImplementacao = aImplementacao;
    }

    public List<Ocorrencia> getaEficacia() {
        return aEficacia;
    }

    public void setaEficacia(List<Ocorrencia> aEficacia) {
        this.aEficacia = aEficacia;
    }

    
    
}
