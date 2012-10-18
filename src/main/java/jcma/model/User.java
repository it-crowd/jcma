package jcma.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
public class User implements Serializable {
// ------------------------------ FIELDS ------------------------------

    @Size(min = 3, max = 20)
    @NotNull
    @Id
    private String userName;

    @Size(min = 3, max = 20)
    @NotNull
    private String name;

    @Size(min = 3, max = 20)
    @NotNull
    private String lastName;

    @NotNull
    private String country;

    @Email
    @Size(min = 3, max = 20)
    @NotNull
    private String email;

    @Transient
    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @Transient
    @NotNull
    @Size(min = 3, max = 20)
    private String confirmPassword;

    @NotNull
    private String job;

    @NotNull
    private String gender;

    @Transient
    @NotNull
    private Date birthDate;

    private int age;

    private String passwordDigest;

    // --------------------- GETTER / SETTER METHODS ---------------------

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {

        age = DateToAge(birthDate);

        this.birthDate = birthDate;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPasswordDigest()
    {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) throws NoSuchAlgorithmException
    {
        //final MessageDigest md5 = MessageDigest.getInstance("MD5");
        //this.passwordDigest = new String(md5.digest(password.getBytes()));
        this.passwordDigest = password;

    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPassword(String password) {
        passwordDigest = password;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

// ------------------------ OTHER METHODS ------------------------

    private int DateToAge(Date birthDate) {

        int age;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if(birthDate!= null) {
            now.setTime(new Date());
            born.setTime(birthDate);
            if(born.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            if(now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR))  {
                age-=1;
            }
            return age;
        }
        return 0;
    }


}
