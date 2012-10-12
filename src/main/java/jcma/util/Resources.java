package jcma.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {
// ------------------------------ FIELDS ------------------------------

    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext
    private EntityManager em;
}
