package jcma;

import jcma.framework.business.Unmanaged;
import org.jboss.solder.core.ExtensionManaged;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class EntityManagerProducer {
// ------------------------------ FIELDS ------------------------------

    private static final String PERSISTENCE_UNIT_NAME = "jcma-unit";

    @ExtensionManaged
    @ConversationScoped
    @Produces
    @PersistenceUnit(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManagerFactory emf;

    @Inject
    private Instance<EntityManagerFactory> entityManagerFactoryInstance;

// -------------------------- OTHER METHODS --------------------------

    @Unmanaged
    @Produces
    public EntityManager getStandaloneEntityManager()
    {
        return entityManagerFactoryInstance.get().createEntityManager();
    }
}
