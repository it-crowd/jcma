package jcma.dao;

import jcma.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: piotrek
 * Date: 17.10.12
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */

@Stateful
@Model
public class UserDAO {
    @SuppressWarnings("unused")
    @Inject
    private Event<User> memberEventSrc;

    @SuppressWarnings("unused")
    @Inject
    private EntityManager em;

    private User newUser;

    @Produces
    @Named
    public User getNewUser()
    {
        return newUser;
    }

    @PostConstruct
    public void initNewMember() throws NoSuchAlgorithmException
    {
        newUser = new User();
    }

    public void addMembers() throws NoSuchAlgorithmException, ParseException
    {
        newUser = getNewUser();
        String password = "hello";
        Date birthDate;
        String[] names = {"Peter", "Lois", "Chris", "Meg", "Stewie", "Brian"};
        String[] lastNames = {"Griffin", "Griffin", "Griffin", "Griffin", "Griffin", "Griffin"};
        String[] birthDates = {"1973-01-18 00:00:00.0", "1974-01-18 00:00:00.0", "1993-01-18 00:00:00.0", "1995-01-18 00:00:00.0", "2011-01-18 00:00:00.0", "1991-01-18 00:00:00.0"};
        String job = "family";
        String[] genders = {"male", "female", "male", "female", "male", "male"};
        String country = "us";
        String email = "familyguy@fox.tv";

        int i = 0;
        for (String name : names) {

            newUser.setName(name);
            newUser.setLastName(lastNames[i]);
            birthDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(birthDates[i]);
            newUser.setBirthDate(birthDate);
            newUser.setJob(job);
            newUser.setGender(genders[i]);
            newUser.setCountry(country);
            newUser.setEmail(email);
            newUser.setUserName(name.toLowerCase());
            newUser.setPassword(password);
            try {
                register();
            }
            catch (Exception e) {
                System.out.println("####################" + e);
            }
            i++;

        }

    }


    public void register() throws NoSuchAlgorithmException
    {

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been successfully registered: " + user));
        em.persist(newUser);
        memberEventSrc.fire(newUser);
        initNewMember();
    }


}
