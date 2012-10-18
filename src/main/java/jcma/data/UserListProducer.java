package jcma.data;

import jcma.model.User;

import jcma.model.User_;
import jcma.view.LoginView;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class UserListProducer {
// ------------------------------ FIELDS ------------------------------

    @Inject
    private EntityManager em;

    private List<User> users;

// --------------------- GETTER / SETTER METHODS ---------------------

    @Produces
    @Named
    public List<User> getUsers() {
        return users;
    }

// -------------------------- OTHER METHODS --------------------------

    public void onUserListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final User user) {
        retrieveAllMembers();
    }

    @PostConstruct
    public void retrieveAllMembers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
//        Object obj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginView");
//        LoginView bean = (LoginView)obj;
//        String userName = bean.getFilterUserName();
//        String name = bean.getFilterName();
//        String lastName = bean.getFilterLastName();
//        String gender = bean.getFilterGender();
//        int less = bean.getFilterLess();
//        int greater = bean.getFilterGreater();
//        criteria.where(cb.and(cb.like(user.get(User_.userName), "%" + userName + "%")),
//                cb.like(user.get(User_.name), "%" + name + "%"), cb.like(user.get(User_.lastName), "%" + lastName + "%"),
//                cb.like(user.get(User_.gender), gender), cb.lessThan(user.get(User_.age), less),
//                cb.greaterThan(user.get(User_.age), greater));
//        users = em.createQuery(criteria).getResultList();

        criteria.select(user).orderBy(cb.asc(user.get(User_.name)));
        users = em.createQuery(criteria).getResultList();
    }


}
