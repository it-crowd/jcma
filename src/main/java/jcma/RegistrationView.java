package jcma;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ViewScoped
@ManagedBean
public class RegistrationView implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private String password;

    private User user = new User();

// --------------------- GETTER / SETTER METHODS ---------------------

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public User getUser()
    {
        return user;
    }

// -------------------------- OTHER METHODS --------------------------

    public void register() throws NoSuchAlgorithmException
    {
        final MessageDigest md5 = MessageDigest.getInstance("MD5");
        user.setPasswordDigest(new String(md5.digest(password.getBytes())));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been successfully registered: " + user));
        user = new User();
    }
}
