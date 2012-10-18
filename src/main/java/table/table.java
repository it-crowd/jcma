/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package table;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author misiowskazzz
 */
@ManagedBean(name = "order")
@SessionScoped
public class table {

    private static final long serialVersionUID = 1L;
    private List<Order> orderArrayList;
    private boolean sortAscending = true;
    private static final Order[] orderList = {
        new Order("id02", "item1", 500.00, 6),
        new Order("id01", "item2", 1000.00, 66), 
        new Order("id04", "item3",1500.00, 666), 
        new Order("id03", "item4",2000.00, 5),
        new Order("id05", "item4",2500.00, 1)
    };

    public table() {

        orderArrayList = new ArrayList<Order>(Arrays.asList(orderList));

    }

    public List<Order> getOrderList() {

        return orderArrayList;

    }

    public String sortByOrderNo() {

        if (sortAscending) {


            Collections.sort(orderArrayList, new Comparator<Order>() {

                @Override
                public int compare(Order o1, Order o2) {

                    return o1.getIdNum().compareTo(o2.getIdNum());

                }
            });
            sortAscending = false;

        } else {


            Collections.sort(orderArrayList, new Comparator<Order>() {

                @Override
                public int compare(Order o1, Order o2) {

                    return o2.getIdNum().compareTo(o1.getIdNum());

                }
            });
            sortAscending = true;
        }

        return null;
    }

    public static class Order {

        String idNum;
        String productName;
        double price;
        int howMany;

        public Order(String idNum, String productName, double price, int howMany) {
            this.idNum = idNum;
            this.productName = productName;
            this.price = price;
            this.howMany = howMany;
        }

        public String getIdNum() {
            return idNum;
        }

        public void setIdNum(String idNum) {
            this.idNum = idNum;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getHowMany() {
            return howMany;
        }

        public void setHowMany(int howMany) {
            this.howMany = howMany;
        }
    }
}