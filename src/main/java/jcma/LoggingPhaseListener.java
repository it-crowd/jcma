package jcma;

import java.io.IOException;
import java.util.logging.Level;
import javax.faces.component.UIViewRoot;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class LoggingPhaseListener implements PhaseListener {
// ------------------------------ FIELDS ------------------------------

    private static Logger LOGGER = Logger.getLogger(LoggingPhaseListener.class.getCanonicalName());

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface PhaseListener ---------------------

    public void afterPhase(PhaseEvent event)
    {
        
        final LoggingBean loggingBean = (LoggingBean) event.getFacesContext()
            .getApplication()
            .createValueBinding("#{loggingBean}")
            .getValue(event.getFacesContext());
        FacesContext ctx = event.getFacesContext();
        if(!loggingBean.isLoggedIn() && ctx.getViewRoot().getViewId().startsWith("admin", 1)){
            
            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
            try {
                response.sendError(404);
            } catch (IOException ex) {
                Logger.getLogger(LoggingPhaseListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void beforePhase(PhaseEvent event)
    {
        /*
        final UIViewRoot viewRoot = event.getFacesContext().getViewRoot();
        final String viewId = viewRoot == null ? null : viewRoot.getViewId();
        final String msg = String.format("Before phase: %s; ViewId: %s", event.getPhaseId(), viewId);
        LOGGER.info(msg);
        final LoggingBean loggingBean = (LoggingBean) event.getFacesContext()
            .getApplication()
            .createValueBinding("#{loggingBean}")
            .getValue(event.getFacesContext());
        logg */
    }

    public PhaseId getPhaseId()
    {
        return PhaseId.RESTORE_VIEW;
    }
}
