package jcma;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private Date birthDate;

    private String email;

    private int luckyNumber;

    private String passwordDigest;

// --------------------- GETTER / SETTER METHODS ---------------------

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getLuckyNumber()
    {
        return luckyNumber;
    }

    public void setLuckyNumber(int luckyNumber)
    {
        this.luckyNumber = luckyNumber;
    }

    public String getPasswordDigest()
    {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest)
    {
        this.passwordDigest = passwordDigest;
    }

// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public String toString()
    {
        return "User{" +
            "birthDate=" + birthDate +
            ", email='" + email + '\'' +
            ", luckyNumber=" + luckyNumber +
            ", passwordDigest='" + passwordDigest + '\'' +
            '}';
    }
}
