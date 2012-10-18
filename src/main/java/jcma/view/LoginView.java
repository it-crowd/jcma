package jcma.view;


import jcma.data.UserListProducer;
import jcma.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@SessionScoped
@ManagedBean
public class LoginView implements Serializable {

    private String password;
    private String userName;
    private boolean logged = false;
    private String include = "login.xhtml";
    private String response = "Welcome Unknown!";
    private String filterUserName = "";
    private String filterName = "";
    private String filterLastName = "";;
    private int filterGreater = 0;
    private int filterLess = 50;
    private String filterGender = "";;
    List<String> genders = new ArrayList();

    @Inject
    private UserListProducer userListProducer;

    private List<User> userList;
    private List<User> users;

    // --------------------- GETTER / SETTER METHODS ---------------------


    public List<String> getGenders() {

        if(!genders.isEmpty()) genders.clear();
        genders.add("male");
        genders.add("female");
        genders.add("");
        return genders;
    }

    public String getFilterGender() {
        return filterGender;
    }

    public void setFilterGender(String filterGender) {
        this.filterGender = filterGender;
    }

    public int getFilterLess() {
        return filterLess;
    }

    public void setFilterLess(int filterLess) {
        this.filterLess = filterLess;
    }

    public int getFilterGreater() {
        return filterGreater;
    }

    public void setFilterGreater(int filterGreater) {
        this.filterGreater = filterGreater;
    }

    public String getFilterLastName() {
        return filterLastName;
    }

    public void setFilterLastName(String filterLastName) {
        this.filterLastName = filterLastName;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterUserName() {
        return filterUserName;
    }

    public void setFilterUserName(String filterUserName) {
        this.filterUserName = filterUserName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getLogged() {
        return logged;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getInclude() {
        return include;
    }
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    // -------------------------- OTHER METHODS --------------------------

    public void setFilter() {

        if(!users.isEmpty()) users = new ArrayList<User>();

        for(User user: userList) {
            int age = user.getAge();

            if (user.getUserName().contains(filterUserName)
                    && user.getName().contains(filterName) && user.getLastName().contains(filterLastName)
                    && age > filterGreater && age < filterLess )   {

//                filterGender.replaceAll("\\s+","");
//                user.getGender().replaceAll("\\s+","");
                if (filterGender != null && filterGender.equals(user.getGender())) {
                    users.add(user);
                }
                else if (filterGender == null || filterGender.equals("")) {
                    users.add(user);
                }

            }

        }


    }

    public void login() {

        if (!logged) {

            if (userList == null) {
                userList = userListProducer.getUsers();
                users = userList;
            }

            if (userName.equals("admin") && password.equals("admin")) {

                include = "admin.xhtml";
                response = "Welcome " + userName + "!";
                logged = true;
            } else  {

                boolean check = false;
                if (userList.isEmpty()) System.out.println("########## EMPTY");
                for(User user: userList) {
                    if (user.getUserName().equals(userName) && user.getPasswordDigest().equals(password))
                        check = true;
                }

                if (check) {

                    include = "user.xhtml";
                    response = "Welcome " + userName + "!";
                    logged = true;
                }
                else {

                    response = "Wrong username or password!!!";
                }

            }

        } else {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            logged = false;
        }

    }


}
