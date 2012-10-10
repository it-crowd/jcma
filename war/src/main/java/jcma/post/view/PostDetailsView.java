package jcma.post.view;

import jcma.domain.Post;
import jcma.framework.business.EntityHome;
import jcma.framework.view.AbstractDetailsView;
import jcma.post.business.PostHome;
import org.jboss.seam.international.status.Messages;
import org.jboss.solder.logging.Logger;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class PostDetailsView extends AbstractDetailsView<Post> implements Serializable {
// ------------------------------ FIELDS ------------------------------

    public static final String OUTCOME_SUCCESS = "success";

    private PostHome postHome;

// --------------------------- CONSTRUCTORS ---------------------------

    /**
     * Required for WELD-001435: Normal scoped bean needs no-args constructor to be proxiable
     */
    @SuppressWarnings("UnusedDeclaration")
    public PostDetailsView()
    {
        super();
    }

    @Inject
    public PostDetailsView(@SuppressWarnings("CdiInjectionPointsInspection") Logger logger, Messages messages, PostHome postHome)
    {
        super(logger, messages);
        this.postHome = postHome;
    }

// -------------------------- OTHER METHODS --------------------------

    public Long getId()
    {
        return (Long) getHome().getId();
    }

    public Post getPost()
    {
        return getHome().getInstance();
    }

    public void setId(Long id)
    {
        getHome().setId(id);
    }

    @Override
    protected EntityHome<Post> getHome()
    {
        return postHome;
    }

    @Override
    protected String getOutcomeSuccess()
    {
        return OUTCOME_SUCCESS;
    }
}
