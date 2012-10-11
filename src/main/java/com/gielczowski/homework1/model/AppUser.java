/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gielczowski.homework1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rafal
 */
public class AppUser{
    
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String aboutText;
    private String email;
    private GenderEnum gender;
    private Date birthDate;
    private boolean wantNewsletter;
    private List<String> friends;
    
    
    public AppUser() {
    }

    public AppUser(String login, String password, String firstName, String lastName, String aboutText, String email, GenderEnum gender, Date birthDate, boolean wantNewsletter, List<String> friends) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.aboutText = aboutText;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.wantNewsletter = wantNewsletter;
        this.friends = friends;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAboutText() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isWantNewsletter() {
        return wantNewsletter;
    }

    public void setWantNewsletter(boolean wantNewsletter) {
        this.wantNewsletter = wantNewsletter;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 41 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 41 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 41 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 41 * hash + (this.aboutText != null ? this.aboutText.hashCode() : 0);
        hash = 41 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 41 * hash + (this.gender != null ? this.gender.hashCode() : 0);
        hash = 41 * hash + (this.birthDate != null ? this.birthDate.hashCode() : 0);
        hash = 41 * hash + (this.wantNewsletter ? 1 : 0);
        hash = 41 * hash + (this.friends != null ? this.friends.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AppUser other = (AppUser) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            return false;
        }
        if ((this.aboutText == null) ? (other.aboutText != null) : !this.aboutText.equals(other.aboutText)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if (this.gender != other.gender) {
            return false;
        }
        if (this.birthDate != other.birthDate && (this.birthDate == null || !this.birthDate.equals(other.birthDate))) {
            return false;
        }
        if (this.wantNewsletter != other.wantNewsletter) {
            return false;
        }
        if (this.friends != other.friends && (this.friends == null || !this.friends.equals(other.friends))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AppUser{" + "login=" + login + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", aboutText=" + aboutText + ", email=" + email + ", gender=" + gender + ", birthDate=" + birthDate + ", wantNewsletter=" + wantNewsletter + ", friends=" + friends + '}';
    }
    
    

    
    
}
