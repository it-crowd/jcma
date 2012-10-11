/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gielczowski.homework1.util;

import com.gielczowski.homework1.view.LoginBean;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rafal
 */
public class CredentialsPhaseListener implements PhaseListener {

    private static final Logger LOGGER = Logger.getGlobal();

    private LoginBean loginBean;

    public void afterPhase(PhaseEvent pe) {

        FacesContext ctx = pe.getFacesContext();
        LOGGER.log(Level.INFO, "View ID:" + ctx.getViewRoot().getViewId());
        
        boolean isRestricted = ctx.getViewRoot().getViewId().contains("admin");
        
        if (isRestricted) {
            
            //get loginBean instance
            loginBean = ctx.getApplication().evaluateExpressionGet(ctx, "#{loginBean}", LoginBean.class);
            LOGGER.log(Level.INFO, "Logged user:" + loginBean.getLogin());

            //if logged user isn't "admin"
            if (!loginBean.isLoggedIn() || !"admin".equals(loginBean.getLogin())) {

                HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
                try {
                    response.sendError(503);//send 503 Unauthorized
                } catch (IOException ex) {
                    Logger.getLogger(CredentialsPhaseListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void beforePhase(PhaseEvent pe) {
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
