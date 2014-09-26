package br.com.absoft.suport;

import br.com.absoft.model.entities.Pessoa;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ManagedBean(name = "bbUsuarioLogado")
@SessionScoped
public class BbUsuarioLogado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static Pessoa user;

    @PersistenceContext(unitName = "Quality-WebPU")
    EntityManager em;

    private String login;
    SecurityContext context = SecurityContextHolder.getContext();

    public BbUsuarioLogado() {
    }

    //PODEMOS DEIXAR NOSSO BACKING BEAN MAIS ENXUTO USANDO APENAS O TRECHO DE CÓDIGO ABAIXO
    public Pessoa procuraPessoa() {
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                login = (((User) authentication.getPrincipal()).getUsername());
            }
        }

        javax.persistence.Query query = em.createQuery("from Pessoa p where p.usuario = ?");
        query.setParameter(1, login);
        
        user = (Pessoa) query.getSingleResult();
        
        return user;//(Pessoa) query.getSingleResult();
    }

    /*NO PROJETO ORIGINAL FIZEMOS DA SEGUINTE FORMA
     private Pessoa usuario;

     public BbUsuarioLogado() {
     usuario = new Pessoa();
     SecurityContext context = SecurityContextHolder.getContext();
     if(context instanceof SecurityContext){
     Authentication authentication = context.getAuthentication();
     if(authentication instanceof Authentication){
     usuario.setLogin(((User) authentication.getPrincipal()).getUsername());
     }
     }
     }

     public Pessoa procuraPessoa(){
     String login = getUsuarioLogin();
     Session session = FacesContextUtil.getRequestSession();
     Query query = session.createQuery("from Pessoa user where user.login like ?");
     query.setString(0, login);
     return (Pessoa) query.uniqueResult();
     }

     private String getUsuarioLogin() {
     return usuario.getLogin();
     }
     */
}
