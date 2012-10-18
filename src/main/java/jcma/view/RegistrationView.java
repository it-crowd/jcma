package jcma.view;

import jcma.dao.UserDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.*;

@Model
public class RegistrationView implements Serializable {
// ------------------------------ FIELDS ------------------------------

    @Inject
    private UserDAO userDAO;

    List<String> countries = new ArrayList();
    List<String> genders = new ArrayList();
    List<String> jobs = new ArrayList();

    private boolean popupConfirm = false;
    private boolean popupInfo = false;

// --------------------- GETTER / SETTER METHODS ------------------
    public List getGenders() {

        if (!genders.isEmpty()) genders.clear();
        genders.add("male");
        genders.add("female");

        return genders;
    }

    public List getCountries() {

        if (!countries.isEmpty()) countries.clear();
        countries.add("pl");
        countries.add("us");
        return countries;
    }

    public List getJobs() {

        if (!jobs.isEmpty()) jobs.clear();
        jobs.add("it");
        jobs.add("cos");
        return jobs;
    }
    public boolean isPopupConfirm() {
        return popupConfirm;
    }

    public void setPopupConfirm(boolean popupConfirm) {
        this.popupConfirm = popupConfirm;
    }

    public boolean isPopupInfo() {
        return popupInfo;
    }

    public void setPopupInfo(boolean popupInfo) {
        this.popupInfo = popupInfo;
    }


// -------------------------- OTHER METHODS --------------------------

    public void cancel()
    {
        popupConfirm = true;
    }

    public void proceedWithoutSave()
    {
        popupConfirm = false;

    }

    public void register() throws NoSuchAlgorithmException, ParseException {


        userDAO.register();
        popupInfo = true;

    }


}
