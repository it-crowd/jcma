package jcma;

import java.io.IOException;
import java.util.logging.Level;
import javax.faces.component.UIViewRoot;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

public class LoggingPhaseListener implements PhaseListener {
// ------------------------------ FIELDS ------------------------------

    private static Logger LOGGER = Logger.getLogger(LoggingPhaseListener.class.getCanonicalName());

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface PhaseListener ---------------------

    public void afterPhase(PhaseEvent event)
    {
        final UIViewRoot viewRoot = event.getFacesContext().getViewRoot();
        final String viewId = viewRoot == null ? null : viewRoot.getViewId();
        final String msg = String.format("Before phase: %s; ViewId: %s", event.getPhaseId(), viewId);
        LOGGER.info(msg);
        final LoggingBean loggingBean = (LoggingBean) event.getFacesContext()
            .getApplication()
            .createValueBinding("#{loggingBean}")
            .getValue(event.getFacesContext());
        loggingBean.info(msg);
        
        
        final Bean bean = (Bean) event.getFacesContext()
            .getApplication()
            .createValueBinding("#{bean}")
            .getValue(event.getFacesContext());
        
        if(!bean.isIsLogged() && viewId.contains("admin")){
            
            Logger.getLogger(LoggingPhaseListener.class.getName()).log(Level.SEVERE, null, "STATE:"+bean.isIsLogged()+"and "+viewId);
            HttpServletResponse response = (HttpServletResponse) event.getFacesContext().getExternalContext().getResponse();
            try {
                response.sendError(404);
            } catch (IOException ex) {
                Logger.getLogger(LoggingPhaseListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public void beforePhase(PhaseEvent event)
    {
        
        
    }

    public PhaseId getPhaseId()
    {
        return PhaseId.RESTORE_VIEW;
    }
}
