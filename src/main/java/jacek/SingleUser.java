/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jacek;

import java.io.Serializable;


/**
 *
 * @author piotrek
 */
public class SingleUser implements Serializable{
    
    private String imie;
    private String nazwisko;

    public SingleUser() {
    }

    public SingleUser(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
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

    
    
    
}
