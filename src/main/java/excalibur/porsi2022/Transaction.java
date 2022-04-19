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
public class Transaction {
    protected String id;
    protected TransactionDetail[] products;
    protected int total;
    protected int paid;
    protected LocalDate date;

    
    protected Transaction(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public TransactionDetail[] getProducts() {
        return products;
    }

    public void setProducts(TransactionDetail[] products) {
        this.products=products;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total=total;
    }

    public boolean isPaid() {
        return total==paid;
    }
    
    public int getPaymentRemain(){
        return total-paid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date=date;
    }
    
    public void addPayment(int payment){
        if(paid<total){
            paid+=payment;
        } else System.out.println("Paid in total");
        
    }
    
    
}
