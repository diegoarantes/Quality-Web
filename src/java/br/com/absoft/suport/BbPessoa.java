package br.com.absoft.suport;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class BbPessoa implements Serializable {

    private static final long serialVersionUID = 1L;
       
    @EJB
    DAOGenerico dao;

    private List<Pessoa> pessoas;

    public List<Pessoa> getPessoas() {
      return dao.lista(Pessoa.class);
    }

}
