package jcma;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class RegistrationView implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private boolean confirmationPanelVisible;

    private Converter countryConverter = new Converter() {
        public Object getAsObject(FacesContext context, UIComponent component, String value)
        {
            return countryDAO.getCountryById(value);
        }

        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            return value == null ? null : ((Country) value).getId();
        }
    };

    @Inject
    private CountryDAO countryDAO;

    @NotNull
    @Size(min = 6)
    private String password;

    private User user = new User();

// --------------------- GETTER / SETTER METHODS ---------------------

    public Converter getCountryConverter()
    {
        return countryConverter;
    }

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

    public boolean isConfirmationPanelVisible()
    {
        return confirmationPanelVisible;
    }

// -------------------------- OTHER METHODS --------------------------

    public void cancel()
    {
        confirmationPanelVisible = true;
    }

    public List<Country> getAvailableCountries()
    {
        return new ArrayList<Country>(countryDAO.getCountries());
    }

    public void proceedWithoutSave()
    {
        confirmationPanelVisible = false;
        user = new User();
        password = null;
    }

    public void register() throws NoSuchAlgorithmException
    {
        final MessageDigest md5 = MessageDigest.getInstance("MD5");
        user.setPasswordDigest(new String(md5.digest(password.getBytes())));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been successfully registered: " + user));
        user = new User();
    }
}
