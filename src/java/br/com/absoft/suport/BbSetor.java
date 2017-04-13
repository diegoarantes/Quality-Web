package br.com.absoft.suport;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Setor;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class BbSetor implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    DAOGenerico dao;
    
    private List<Setor> setores;
    
    public List<Setor> getSetores() {
        return dao.lista(Setor.class);
    }
    
}
