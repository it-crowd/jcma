package com.gielczowski.homework1.view;

import com.gielczowski.homework1.model.AppUser;
import com.gielczowski.homework1.model.GenderEnum;
import com.gielczowski.homework1.util.AppUserRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author rafal
 */

@ManagedBean
@ViewScoped
public class RegistrationBacking {
    
    private AppUser newUser;
    
    private GenderEnum genderEnum;
    
    private List<AppUser> mayBeFriends;
    
    @ManagedProperty(value="#{appUserRepository}")
    private AppUserRepository userRepository;

    public RegistrationBacking() { 
        newUser = new AppUser();
        newUser.setFriends(new ArrayList<String>());
    }
    
    @PostConstruct
    private void init(){
        this.mayBeFriends = userRepository.getAllUsers();
    }

    
    public void registerAction(){
        Logger.getLogger("Registration").log(Level.INFO, this.newUser.toString());
        userRepository.addUserToRepository(newUser);
        newUser = new AppUser();
    }
    

    public AppUser getNewUser() {
        return newUser;
    }

    public void setNewUser(AppUser newUser) {
        this.newUser = newUser;
    }

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public List<AppUser> getMayBeFriends() {
        return mayBeFriends;
    }

    public void setMayBeFriends(List<AppUser> mayBeFriends) {
        this.mayBeFriends = mayBeFriends;
    }

    public AppUserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
 
}
