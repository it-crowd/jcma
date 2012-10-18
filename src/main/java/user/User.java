/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author misiowskazzz
 */
@ManagedBean
@SessionScoped
public class User {

    

    @Email
    @Size(min = 3)
    @NotNull
    private String email;
    
    
    private String name;
    private String nname;
    private String login;
    private String password;
    private String fwaus;
    private String sex;

    public String getFwaus() {
        return fwaus;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFwaus(String fwaus) {
        this.fwaus = fwaus;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    
    
}