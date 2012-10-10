package jcma.post.view;

import jcma.Constants;
import jcma.domain.Post;
import jcma.framework.business.EntitySelected;
import jcma.framework.view.ListViewHelper;
import jcma.post.business.PostHome;
import jcma.post.business.PostList;
import org.jboss.seam.international.status.Messages;
import pl.com.it_crowd.seam.framework.EntityPersisted;
import pl.com.it_crowd.seam.framework.EntityRemoved;
import pl.com.it_crowd.seam.framework.EntityUpdated;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@ViewScoped
public class PostListView implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private Messages messages;

    private PostHome postHome;

    private PostList postList;

    private Event<Post> postSelectedEvent;

    private Map<Post, Boolean> postSelection = new HashMap<Post, Boolean>();

// --------------------------- CONSTRUCTORS ---------------------------

    /**
     * Required for WELD-001435: Normal scoped bean needs no-args constructor to be proxiable
     */
    @SuppressWarnings("UnusedDeclaration")
    public PostListView()
    {
    }

    @Inject
    public PostListView(Messages messages, PostHome postHome, PostList postList, @EntitySelected Event<Post> postSelectedEvent)
    {
        this.messages = messages;
        this.postHome = postHome;
        this.postList = postList;
        this.postSelectedEvent = postSelectedEvent;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public PostList getPostList()
    {
        return postList;
    }

    public Map<Post, Boolean> getPostSelection()
    {
        return postSelection;
    }

// -------------------------- OTHER METHODS --------------------------

    public String removeSelectedPosts()
    {
        return ListViewHelper.removeSelectedElements(postSelection, postHome, messages);
    }

    public String select(Post post)
    {
        postSelectedEvent.fire(post);
        return "success";
    }

    @PostConstruct
    private void init()
    {
        postList.setMaxResults(Constants.DEFAULT_MAX_RESULTS);
    }

    @SuppressWarnings("UnusedDeclaration")
    private void onPeriodPersisted(@Observes(notifyObserver = Reception.IF_EXISTS) @EntityPersisted Post post)
    {
        postList.refresh();
    }

    @SuppressWarnings("UnusedDeclaration")
    private void onPeriodRemoved(@Observes(notifyObserver = Reception.IF_EXISTS) @EntityRemoved Post post)
    {
        postList.refresh();
    }

    @SuppressWarnings("UnusedDeclaration")
    private void onPeriodUpdated(@Observes(notifyObserver = Reception.IF_EXISTS) @EntityUpdated Post post)
    {
        postList.refresh();
    }
}
