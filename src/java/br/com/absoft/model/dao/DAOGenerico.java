/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Diego Arantes
 */
@Stateless
public class DAOGenerico {

    @PersistenceContext(unitName = "Quality-WebPU")
    private EntityManager em;

    public List lista(Class classe) {
        Query q = em.createQuery("from " + classe.getSimpleName());
        return q.getResultList();
    }

    public List listaCondicao(Class classe, String condicao) {
        Query q = em.createQuery("from " + classe.getSimpleName() + " where " + condicao);
        return q.getResultList();
    }

    public void inserir(Object objeto) {
         if (!em.contains(objeto)) {
            objeto = em.merge(objeto);
        }
        em.persist(objeto);
    }

    public void excluir(Object objeto) throws Exception {
//        Method getChave = objeto.getClass().getMethod("getId", new Class[0]);
//        objeto = em.find(objeto.getClass(), getChave.invoke(objeto, new Object[0]));
        if (!em.contains(objeto)) {
            objeto = em.merge(objeto);
        }
        em.remove(objeto);
    }

    public void atualizar(Object objeto) {
        em.merge(objeto);
    }

    public Object recupera(Class classe, int id) { //Era do tipo Long
        Object retornando = null;
        retornando = em.find(classe, id);
        return retornando;
    }
}
