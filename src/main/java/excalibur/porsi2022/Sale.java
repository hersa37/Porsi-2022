/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;

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
    
    public Sale(Customer customer, TransactionDetail[] products, int total, int paid){
        this.customer=customer;
        this.products=products;
        this.total=total;
        this.paid=paid;
        this.date=LocalDate.now();
        this.id=getUniqueID();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer=customer;
    }
    
    private String getUniqueID(){
        return "pur_"+String.format("%05d", uniqueID++);
    }
}
