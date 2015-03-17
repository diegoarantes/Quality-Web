package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Ocorrencia;
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

    private List<Ocorrencia> listaAprovacao;
    private List<Ocorrencia> listaAnalise;
    private List<Ocorrencia> listaPlano;
    private List<Ocorrencia> listaValidacao;
    private List<Ocorrencia> listaImplementacao;
    private List<Ocorrencia> listaEficacia;

    public List<Ocorrencia> getListaAprovacao() {
        listaAprovacao = dao.listaCondicao(Ocorrencia.class, "pessoa.idPessoa = " + new MbLogin().usuarioLogado().getIdPessoa() + " AND status = 'A' ");
        return listaAprovacao;
    }

    public List<Ocorrencia> getListaAnalise() {
        listaAnalise = dao.listaCondicao(Ocorrencia.class, "setor.idSetor = " + new MbLogin().usuarioLogado().getSetor().getIdSetor() + " AND status = 'C' ");
        return listaAnalise;
    }

    public List<Ocorrencia> getListaPlano() {
        listaPlano = dao.listaCondicao(Ocorrencia.class, "setor.idSetor = " + new MbLogin().usuarioLogado().getSetor().getIdSetor() + " AND status = 'P' ");
        return listaPlano;
    }

    public List<Ocorrencia> getListaValidacao() {
        listaValidacao = dao.listaCondicao(Ocorrencia.class, "setor.idSetor = " + new MbLogin().usuarioLogado().getSetor().getIdSetor() + " AND status = 'V' ");
        return listaValidacao;
    }

    public List<Ocorrencia> getListaImplementacao() {
        listaImplementacao = dao.listaCondicao(Ocorrencia.class, "setor.idSetor = " + new MbLogin().usuarioLogado().getSetor().getIdSetor() + " AND status = 'I' ");
        return listaImplementacao;
    }

    public List<Ocorrencia> getListaEficacia() {
        listaEficacia = dao.listaCondicao(Ocorrencia.class, "setor.idSetor = " + new MbLogin().usuarioLogado().getSetor().getIdSetor() + " AND status = 'E' ");
        return listaEficacia;
    }

}
