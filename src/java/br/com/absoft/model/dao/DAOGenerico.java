package br.com.absoft.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

/**
 *
 * @author Diego Arantes
 */
@Stateless
public class DAOGenerico {

    @PersistenceContext(unitName = "Quality-WebPU")
    private EntityManager em;

    @Resource(lookup = "java:/jboss/datasources/Quality")
    DataSource datasource;

    public List lista(Class classe) {
        Query q = em.createQuery("from " + classe.getSimpleName());
        return q.getResultList();
    }

    public List listaCondicao(Class classe, String condicao) {
        Query q = em.createQuery("from " + classe.getSimpleName() + " where " + condicao);
        return q.getResultList();
    }

    public void inserir(Object objeto) {
        em.persist(objeto);
    }

    public void excluir(Object objeto) throws Exception {
        if (!em.contains(objeto)) {
            objeto = em.merge(objeto);
        }
        em.remove(objeto);
    }

    public void atualizar(Object objeto) {
        em.merge(objeto);
    }

    public Object recupera(Class classe, int id) { //Era do tipo Long
        Object retornando;
        retornando = em.find(classe, id);
        return retornando;
    }

    public Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

}
