/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import java.time.LocalDate;
import excalibur.porsi2022.inventory.*;
import java.io.Serializable;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Transaction implements Serializable {
    protected String id;
    protected Product[] products;
    protected int totalCost;
    protected int paid;
    protected LocalDate date;

    
    protected Transaction(Product[] products, int paid){
        this.products=products;
        this.paid=paid;
        this.date=LocalDate.now();
        totalCost=getTotalCost();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products=products;
    }

    public int getTotalCost() {
        int total=0;
        for(Product product:products) {
            total+=product.getTotalPrice();
        }
        return total;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost=totalCost;
    }

    public boolean isPaid() {
        return totalCost==paid;
    }
    
    public int getPaymentRemain(){
        return totalCost-paid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date=date;
    }
    
    public void addPayment(int payment){
        if(paid<totalCost){
            paid+=payment;
        } else System.out.println("Paid in total");
        
    }
    
    public String toString(Product[] products){
        String print="{";
        for(int i=0; i<products.length;i++){
            print+=products[i].toString();
        }
        print+="}";
        
        return print;
    }
}
