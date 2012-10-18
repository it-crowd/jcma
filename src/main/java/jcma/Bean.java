package jcma;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@SessionScoped
public class Bean {
// ------------------------------ FIELDS ------------------------------

    private static Logger LOGGER = Logger.getLogger(Bean.class.getCanonicalName());

    private boolean formVisible = true;
    private boolean isLogged = false;

    private String name;

// --------------------- GETTER / SETTER METHODS ---------------------

    public String getName()
    {
        final String msg = "getName:" + name;
        LOGGER.info(msg);

        return name;
    }

    public void setName(String name)
    {
        final String msg = "setName:" + name;
        LOGGER.info(msg);

        this.name = name;
    }

    public boolean isFormVisible()
    {
        final String msg = "isFormVisible: " + formVisible;
        LOGGER.info(msg);

        return formVisible;
    }

    public void setFormVisible(boolean formVisible)
    {
        final String msg = "setFormVisible: " + formVisible;
        LOGGER.info(msg);
       
        this.formVisible = formVisible;
    }

    

// -------------------------- OTHER METHODS --------------------------

    public void sayHi()
    {
        isLogged=true;
        final String msg = "sayHi:" + name;
        LOGGER.info(msg);
    
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hi " + name));
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        Bean.LOGGER = LOGGER;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
    
}
