package jcma.user.view;

import org.jboss.seam.international.status.Messages;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RegisterView implements Serializable {
// ------------------------------ FIELDS ------------------------------

    @Inject
    private Messages messages;

// -------------------------- OTHER METHODS --------------------------

    public String register()
    {
        messages.warn("This function is currently not available.");
        return "failure";
    }
}
