package jcma;

import jcma.domain.Post;
import jcma.domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class DatabaseSeed {
// ------------------------------ FIELDS ------------------------------

    @PersistenceContext(unitName = "jcma")
    private EntityManager entityManager;

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void onCreate()
    {
        final User author = new User("john@doe.com", "John", "Doe");
        entityManager.persist(author);
        for (int i = 1; i <= 5; i++) {
            entityManager.persist(new Post(author, "Content #" + i, "Post title #" + i));
        }
    }
}
