/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import excalibur.porsi2022.accounting.people.Customer;
import excalibur.porsi2022.inventory.*;
import java.time.LocalDate;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Sale extends Transaction{
    private Customer customer;
    private static int uniqueID=0;
    
    public Sale(Customer customer, Product[] products, int paid){
        super(products, paid);
        this.customer=customer;
        this.date=LocalDate.now();
        id=getUniqueID();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer=customer;
    }
    
    private String getUniqueID(){
        return "sale_"+String.format("%05d", uniqueID++);
    }
    
    @Override
    public String toString(){
        return "{ID: "+id
                +"; Customer: "+customer
                +"; Products: "+super.toString(products)
                +"; Total payment: "+totalCost
                +"; Total paid: "+paid
                +"}";
    }
}
