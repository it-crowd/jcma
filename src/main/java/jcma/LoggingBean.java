package jcma;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped//gi bez bledow teraz :D
public class LoggingBean implements Serializable{
// ------------------------------ FIELDS ------------------------------

    private StringBuilder log = new StringBuilder();
    private String login;
    private String pass;
    private boolean loggedIn;

// -------------------------- OTHER METHODS --------------------------
    
    public LoggingBean(){
        loggedIn=false;
    }
    
    public void logout(){
        loggedIn=false;
        login=null;
        pass=null;
    }
    
    public String getWelcomeMsg(){
        if(!loggedIn) {
            return "Not logged... :(";
        }
        else{
            return "Hello, "+login;
        }
    }
    
    public void loginAction(){
        if(null!=login && null!=pass){
            if(login.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")){
                loggedIn=true;
            }
        }
    }

    public StringBuilder getLog() {
        return log;
    }

    public void setLog(StringBuilder log) {
        this.log = log;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    
    
    

    public void info(String message)
    {
        log.append(message).append("\n");
    }
}
