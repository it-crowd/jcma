package jcma;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean
public class RegistrationView implements Serializable {
// ------------------------------ FIELDS ------------------------------

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

    @ManagedProperty("#{countryDAO}")
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

    public void setCountryDAO(CountryDAO countryDAO)
    {
        this.countryDAO = countryDAO;
    }

// -------------------------- OTHER METHODS --------------------------

    public List<Country> getAvailableCountries()
    {
        return new ArrayList<Country>(countryDAO.getCountries());
    }

    public void register() throws NoSuchAlgorithmException
    {
        final MessageDigest md5 = MessageDigest.getInstance("MD5");
        user.setPasswordDigest(new String(md5.digest(password.getBytes())));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been successfully registered: " + user));
        user = new User();
    }
}
