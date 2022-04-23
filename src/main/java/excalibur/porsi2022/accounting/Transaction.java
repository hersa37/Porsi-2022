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
 * Class for the different types of transactions
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

    
    public Transaction(People person, Inventory products, int paid){
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

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid=paid;
    }
        
    public int getTotalCost() {
        return products.getTotalPrice();
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
    
    /**
     * Method made in case unpaid transactions are to be paid
     * @param payment the payment added to the transaction
     */
    public void addPayment(int payment){
        if(paid<totalCost){
            paid+=payment;
        } else System.out.println("Paid in total");
        
    }
    
    public String toString(String peopleType, Inventory products){
        return"\n{ID: "+id
                +"\n"+peopleType+": "+person
                +"\nBarang-barang: \n"+products.toString()
                +"=================================================\n"
                +"Harga total\t\t:\t"+LocaleFormatting.currency(products.getTotalPrice())
                +"\nDibayar\t\t\t:\t"+LocaleFormatting.currency(paid)
                +"\nSisa Pembayaran\t\t:\t"+LocaleFormatting.currency(getPaymentRemain())
                +"\n\n";                
//        String print="{";
//        for(int i=0; i<products.getStock().length;i++){
//            print+=products.getStock()[i].toString();
//        }
//        print+="}";
//        
//        return print;
    }
}
