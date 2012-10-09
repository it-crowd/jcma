/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gielczowski.homework1.view;

import com.gielczowski.homework1.model.AppUser;
import com.gielczowski.homework1.util.AppUserRepository;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author rafal
 */

@ManagedBean
@SessionScoped
public class LoginBean {
    
    private AppUser loggedUser;
    
    private String login;
    private String password;
    private boolean loggedIn;
    
    @ManagedProperty(value="#{appUserRepository}")
    private AppUserRepository userRepository;

    public LoginBean() {
        this.login = "";
        this.password = "";
        loggedIn=false;
    }
    
    public void checkLogin(){
        if(userRepository.checkUser(this.login, this.password)){
            this.loggedUser=userRepository.getUser(login);
            loggedIn=true;
        }else{
            loggedIn=false;
            this.loggedUser=null;
            this.login="";
            this.password="";
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Wrong login or password!"));
        }
    }
    
    public void logOut(){
        this.loggedIn=false;
        this.loggedUser=null;
        this.login="";
        this.password="";
    }

    public AppUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(AppUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public AppUserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
