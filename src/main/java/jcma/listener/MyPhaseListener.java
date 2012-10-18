package jcma.listener;

import jcma.view.LoginView;
import jcma.view.RegistrationView;

import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyPhaseListener implements PhaseListener {



    public void afterPhase(PhaseEvent event)
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        Object obj = fc.getExternalContext().getSessionMap().get("loginView");
        LoginView bean = (LoginView)obj;
        if (fc.getViewRoot().getViewId().contains("admin") || fc.getViewRoot().getViewId().contains("user")) {
            if (!bean.getLogged())  {
                HttpServletResponse response = (HttpServletResponse)fc.getExternalContext().getResponse();
                try {
                    response.sendError(503);
                } catch (IOException ex) {
                    Logger.getLogger(MyPhaseListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void beforePhase(PhaseEvent event)
    {

    }

    public PhaseId getPhaseId()
    {
        return PhaseId.ANY_PHASE;
    }

}
