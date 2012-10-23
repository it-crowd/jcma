/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nowak.jcma;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author m
 */

@ManagedBean
@ViewScoped
public class Bean {
    
    private List<String> plecLista;
    private List<String> panstwoLista;
    
    private String imie;
    private String nazwisko;
    private String email;
    private String login;
    private String haslo;
    private String plec;
    private String panstwo;
    private String wiadomosc;

    public String getWiadomosc() {
        return wiadomosc;
    }

    public void setWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
    }

    
    
    public List<String> getPlecLista() {
        plecLista = new ArrayList<String>();
        plecLista.add("Kobieta");
        plecLista.add("Mezczyzna"); 
        return plecLista;
    }

    public void setPlecLista(List<String> plecLista) {
        this.plecLista = plecLista;
    }

    public List<String> getPanstwoLista() {
        panstwoLista = new ArrayList<String>();
        panstwoLista.add("Polska");
        panstwoLista.add("USA");
        panstwoLista.add("Niemcy");
        return panstwoLista;
    }

    public void setPanstwoLista(List<String> panstwoLista) {
        this.panstwoLista = panstwoLista;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getPlec() {
        
        
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getPanstwo() {
        
        
        return panstwo;
    }

    public void setPanstwo(String panstwo) {
        this.panstwo = panstwo;
    }
    
    public void zarejestruj(){
        wiadomosc = "Zarejestrowano!!!";
    }
    
    
}
