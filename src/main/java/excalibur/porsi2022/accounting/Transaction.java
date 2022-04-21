/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import excalibur.porsi2022.accounting.people.People;
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
    private People person;
    protected Inventory products;
    protected int totalCost;
    protected int paid;
    protected LocalDate date;

    
    protected Transaction(People person, Inventory products, int paid){
        this.person=person;
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

    public Inventory getProducts() {
        return products;
    }

    public void setProducts(Inventory products) {
        this.products=products;
        totalCost=getTotalCost();
    }

    public int getTotalCost() {
        int total=0;
        for(Product stock:products.getStock()) {
            total+=stock.getTotalPrice();
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
    
    public String toString(String peopleType, Inventory products){
        return"{ID: "+id
                +"\n"+peopleType+": "+person
                +"\nBarang-barang: \n"+products.toString()
                +"=================================================\n"
                +"Harga total\t\t\t"+LocaleFormatting.currency(products.getTotalPrice())
                +"\nDibayar:\t\t\t"+LocaleFormatting.currency(paid)
                +"\nSisa Pembayaran:\t\t"+LocaleFormatting.currency(getPaymentRemain());                
//        String print="{";
//        for(int i=0; i<products.getStock().length;i++){
//            print+=products.getStock()[i].toString();
//        }
//        print+="}";
//        
//        return print;
    }
}