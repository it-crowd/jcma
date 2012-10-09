/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gielczowski.homework1.util;

import com.gielczowski.homework1.model.AppUser;
import com.gielczowski.homework1.model.GenderEnum;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author rafal
 * Pseudo DAO class with AppUsers
 */
@ManagedBean
@SessionScoped
public class AppUserRepository {

    List<AppUser> users;

    public AppUserRepository() {
        users=new ArrayList<AppUser>();
    }
    
    public boolean checkUser(String login, String password){
        for(AppUser a : users){
            if(a.getLogin().equals(login) && a.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    
    public AppUser getUser(String login){
        for(AppUser a : users){
            if(a.getLogin().equals(login)){
                return a;
            }
        }
        return null;
    }

    public List<AppUser> getAllUsers() {
        
        /*
         * if no registered users, add examples:
         */
        if (users.isEmpty()) {
            AppUser user = new AppUser("smith", "password", "Adam", "Smith",
                    "Lorem ipsum lol", "smith@smith.com", GenderEnum.MAN,
                    Calendar.getInstance().getTime(), true, new ArrayList<String>());
            this.users.add(user);
            
            user = new AppUser("kowalski", "password", "Jan", "Kowalski",
                    "Lorem ipsum dolor sit amet", "kowalski@smith.com", GenderEnum.MAN,
                    Calendar.getInstance().getTime(), true, new ArrayList<String>());
            this.users.add(user);
            
            List<String> f = new ArrayList<String>();
            f.add("Jan Kowalski");
            
            user = new AppUser("kowalska", "password", "Joanna", "Kowalska",
                    "Lorem ipsum dolor sit amet", "kowalski@smith.com", GenderEnum.WOMAN,
                    Calendar.getInstance().getTime(), false, f);
            this.users.add(user);
            
            List<String> ff = new ArrayList<String>();
            ff.add("Joanna Kowalska");
            user = new AppUser("nowak", "password", "Ewa", "Nowak",
                    "Lorem ipsum dolor sit amet", "nowak@nowak.com", GenderEnum.WOMAN,
                    Calendar.getInstance().getTime(), false, ff);
            this.users.add(user);
            
        }
        
        return users;
    }
    
    public List<AppUser> getUsers(int start, int end){
        try{
            List<AppUser> result = users.subList(start, end);
            Logger.getLogger("Repository").log(Level.INFO, "Returning user list size: "+users.size());
            return result;
        }catch(IndexOutOfBoundsException e){
            return users;
        }
    }
    
    public void addUserToRepository(AppUser a){
        users.add(a);
    }
}
