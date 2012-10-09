/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gielczowski.homework1.view;

import com.gielczowski.homework1.model.AppUser;
import com.gielczowski.homework1.util.AppUserRepository;
import java.util.ArrayList;
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
public class FilterTableBacking {
    
    private List<AppUser> users;
    
    @ManagedProperty(value="#{appUserRepository}")
    private AppUserRepository userRepository;
    
    private int numberOfResults;
    
    private String filterLogin;
    private String filterPassword;
    private String filterfirstName;
    private String filterlastName;
    private String filteraboutText;
    private String filteremail;
    private String filtergender;
    private String filterbirthDate;
    private boolean filterwantNewsletter;
    private String filterfriends;
    
    @PostConstruct
    private void init(){
        users=userRepository.getAllUsers();
        this.numberOfResults=users.size();
    }
    
    /**
     * value change listeners for filter fields in column headers
     */
    public void filterByLoginListener(){
        if(filterLogin.length()==0) users=userRepository.getUsers(0, numberOfResults);
        
        Logger.getLogger("filterTable").log(Level.INFO, "Filtering:"+this.filterLogin);
        List<AppUser> result = new ArrayList<AppUser>();
        this.users = userRepository.getUsers(0, numberOfResults);
        
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getLogin().contains(filterLogin)){
                result.add(users.get(i));
            }
        }  
        this.users = result;
    }
    
    public void filterByPasswordListener(){
        if(filterPassword.length()==0) {
            users=userRepository.getUsers(0, numberOfResults);
            return;
        }
        
        Logger.getLogger("filterTable").log(Level.INFO, "Filtering:"+this.filterPassword);
        List<AppUser> result = new ArrayList<AppUser>();
        this.users = userRepository.getUsers(0, numberOfResults);
        
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getPassword().contains(filterPassword)){
                result.add(users.get(i));
            }
        }
        
        this.users = result;
    }
    
    public void filterByFirstNameListener(){
        if(filterfirstName.length()==0) {
            users=userRepository.getUsers(0, numberOfResults);
            return;
        }
        
        Logger.getLogger("filterTable").log(Level.INFO, "Filtering:"+this.filterfirstName);
        List<AppUser> result = new ArrayList<AppUser>();
        this.users = userRepository.getUsers(0, numberOfResults);
        
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getFirstName().contains(filterfirstName)){
                result.add(users.get(i));
            }
        }
        
        this.users = result;
    }
    
    public void filterByLastNameListener(){
        if(filterlastName.length()==0) users=userRepository.getUsers(0, numberOfResults);
        
        Logger.getLogger("filterTable").log(Level.INFO, "Filtering:"+this.filterlastName);
        List<AppUser> result = new ArrayList<AppUser>();
        this.users = userRepository.getUsers(0, numberOfResults);
        
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getLastName().contains(filterlastName)){
                result.add(users.get(i));
            }
        }
        
        this.users = result;
    }
    
    public void filterByAboutListener(){
        if(filteraboutText.length()==0) users=userRepository.getUsers(0, numberOfResults);
        
        Logger.getLogger("filterTable").log(Level.INFO, "Filtering:"+this.filteraboutText);
        List<AppUser> result = new ArrayList<AppUser>();
        this.users = userRepository.getUsers(0, numberOfResults);
        
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getAboutText().contains(filteraboutText)){
                result.add(users.get(i));
            }
        }
        
        this.users = result;
    }
    
    public void filterByEmailListener(){
        if(filteremail.length()==0) users=userRepository.getUsers(0, numberOfResults);
        
        Logger.getLogger("filterTable").log(Level.INFO, "Filtering:"+this.filteremail);
        List<AppUser> result = new ArrayList<AppUser>();
        this.users = userRepository.getUsers(0, numberOfResults);
        
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getEmail().contains(filteremail)){
                result.add(users.get(i));
            }
        }
        
        this.users = result;
    }
    
    public void filterByBirthDateListener(){
        if(filterbirthDate.length()==0) users=userRepository.getUsers(0, numberOfResults);
        
        Logger.getLogger("filterTable").log(Level.INFO, "Filtering:"+this.filterbirthDate);
        List<AppUser> result = new ArrayList<AppUser>();
        this.users = userRepository.getUsers(0, numberOfResults);
        
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getBirthDate().toString().contains(filterbirthDate)){
                result.add(users.get(i));
            }
        }
        
        this.users = result;
    }
    
    public void filterByFriendsListener(){
        if(filterfriends.length()==0) users=userRepository.getUsers(0, numberOfResults);
        
        Logger.getLogger("filterTable").log(Level.INFO, "Filtering:"+this.filterfriends);
        List<AppUser> result = new ArrayList<AppUser>();
        this.users = userRepository.getUsers(0, numberOfResults);
        
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getFriends().toString().contains(filterfriends)){
                result.add(users.get(i));
            }
        }
        
        this.users = result;
    }
    
    public void filterByNewsListener(){     
        
        Logger.getLogger("filterTable").log(Level.INFO, "Filtering:"+this.filterwantNewsletter);
        List<AppUser> result = new ArrayList<AppUser>();
        this.users = userRepository.getUsers(0, numberOfResults);
        
        for(int i=0; i<users.size(); i++){
            if(users.get(i).isWantNewsletter() == filterwantNewsletter){
                result.add(users.get(i));
            }
        }
        
        this.users = result;
    }
    
    public void filterByGenderListener(){     
        
        Logger.getLogger("filterTable").log(Level.INFO, "Filtering:"+this.filtergender);
        List<AppUser> result = new ArrayList<AppUser>();
        this.users = userRepository.getUsers(0, numberOfResults);
        
        for(int i=0; i<users.size(); i++){
            if(filtergender.equals(users.get(i).getGender().toString())){
                result.add(users.get(i));
            }
        }
        
        this.users = result;
    }
    /*
     * END OF listeners
     */
   
   
    public void numberOfResultsChange(){
        this.users = userRepository.getUsers(0, numberOfResults);
        Logger.getLogger("filterTable").log(Level.INFO, "Field:"+this.numberOfResults);
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }

    public AppUserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getFilterLogin() {
        return filterLogin;
    }

    public void setFilterLogin(String filterLogin) {
        this.filterLogin = filterLogin;
    }

    public String getFilterPassword() {
        return filterPassword;
    }

    public void setFilterPassword(String filterPassword) {
        this.filterPassword = filterPassword;
    }

    public String getFilterfirstName() {
        return filterfirstName;
    }

    public void setFilterfirstName(String filterfirstName) {
        this.filterfirstName = filterfirstName;
    }

    public String getFilterlastName() {
        return filterlastName;
    }

    public void setFilterlastName(String filterlastName) {
        this.filterlastName = filterlastName;
    }

    public String getFilteraboutText() {
        return filteraboutText;
    }

    public void setFilteraboutText(String filteraboutText) {
        this.filteraboutText = filteraboutText;
    }

    public String getFilteremail() {
        return filteremail;
    }

    public void setFilteremail(String filteremail) {
        this.filteremail = filteremail;
    }

    public String getFiltergender() {
        return filtergender;
    }

    public void setFiltergender(String filtergender) {
        this.filtergender = filtergender;
    }

    public String getFilterbirthDate() {
        return filterbirthDate;
    }

    public void setFilterbirthDate(String filterbirthDate) {
        this.filterbirthDate = filterbirthDate;
    }

    public boolean isFilterwantNewsletter() {
        return filterwantNewsletter;
    }

    public void setFilterwantNewsletter(boolean filterwantNewsletter) {
        this.filterwantNewsletter = filterwantNewsletter;
    }

    public String getFilterfriends() {
        return filterfriends;
    }

    public void setFilterfriends(String filterfriends) {
        this.filterfriends = filterfriends;
    }

    public int getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }
    
    
    
}
