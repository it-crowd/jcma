package jacek;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class Controler implements Serializable {

    private List<SingleUser> users;
    private String filterPattern;

    public Controler() {
        users = new ArrayList<SingleUser>();

        SingleUser temp;
        for (int i = 0; i < 5; i++) {
            temp = new SingleUser("Adam" + i, "Kowalski" + 1);
            users.add(temp);
            Logger.getLogger("JACEK:").log(Level.INFO, "USize" + users.size());
        }
        
    }
    
    private void init(){
        users = new ArrayList<SingleUser>();

        SingleUser temp;
        for (int i = 0; i < 5; i++) {
            temp = new SingleUser("Adam" + i, "Kowalski" + 1);
            users.add(temp);
            Logger.getLogger("JACEK:").log(Level.INFO, "USize" + users.size());
        }
        
    }

    public void filter() {
        List<SingleUser> newList = new ArrayList<SingleUser>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getImie().contains(filterPattern)) {
                Logger.getAnonymousLogger().log(Level.INFO, "removed " + users.get(i).getImie() + ", filterpattern:" + filterPattern);
                newList.add(users.get(i));
            }
        }
        
        users = newList;
    }
    
    public void resetList(){
        init();
        
    }

    public List<SingleUser> getUsers() {
        return users;
    }

    public void setUsers(List<SingleUser> users) {
        this.users = users;
    }

    public String getFilterPattern() {
        return filterPattern;
    }

    public void setFilterPattern(String filterPattern) {
        this.filterPattern = filterPattern;
    }
}