package jcma.post.business;

import jcma.domain.Post;
import jcma.domain.User;
import jcma.framework.business.EntityHome;
import jcma.user.CurrentUser;
import org.jboss.seam.security.annotations.LoggedIn;
import pl.com.it_crowd.seam.framework.EntityRemoved;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PostHome extends EntityHome<Post> {
// ------------------------------ FIELDS ------------------------------

    @Inject
    @CurrentUser
    private Instance<User> currentUserInstance;

// -------------------------- OTHER METHODS --------------------------

    @LoggedIn
    @Override
    public boolean persist()
    {
        getInstance().setAuthor(currentUserInstance.get());
        return super.persist();
    }

    @Override
    public int remove(Collection<Post> elements)
    {
        if (elements.isEmpty()) {
            return 0;
        }
        final Set<Long> ids = new HashSet<Long>();
        for (Post venue : elements) {
            ids.add(venue.getId());
        }
        ids.remove(null);
        final int count = getEntityManager().createQuery("delete Post where id in (:ids)").setParameter("ids", ids).executeUpdate();
        for (Post element : elements) {
            beanManager.fireEvent(element, new AnnotationLiteral<EntityRemoved>() {
            });
        }
        return count;
    }
}
