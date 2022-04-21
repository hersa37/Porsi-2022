/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excalibur.porsi2022;

import excalibur.porsi2022.accounting.people.*;
import excalibur.porsi2022.accounting.*;
import excalibur.porsi2022.inventory.Inventory;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Store implements Serializable{
    private String storeName;
    private Owner owner;
    private Accounting accountingBook;
    private Inventory inventory;
    private String buyPrice;
    private String sellPrice;            
    private ArrayList<Customer> customers;
    private ArrayList<Supplier> suppliers;
    private String fileName;
    
    public Store(String storeName, Owner owner){
        this.storeName=storeName;
        this.owner=owner;
        accountingBook=new Accounting();
        customers=new ArrayList<>();
        suppliers=new ArrayList<>();
        inventory=new Inventory();        
        buyPrice=inventory.priceList();
        Products_Sell temp=new Products_Sell();
        sellPrice=temp.priceList();
        fileName=storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName=storeName;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setBuyPrice(Products_Buy buyPrice) {
        this.buyPrice=buyPrice.priceList();
    }

    public void setSellPrice(Products_Sell sellPrice) {
        this.sellPrice=sellPrice.priceList();
    }
    
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner=owner;
    }

    public Accounting getAccountingBook() {
        return accountingBook;
    }

    public void setAccountingBook(Accounting accountingBook) {
        this.accountingBook=accountingBook;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory=inventory;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers=customers;
    }
    
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    
    public void addSupplier(Supplier supplier){
        suppliers.add(supplier);
    }
    
    public Customer findCustomer(String nameOrID){
        for(Customer customer:customers) {
            if (customer.getName().equals(nameOrID) || customer.getName().equals(nameOrID)){
                return customer;
            }
        }
        return null;
    }
    
    public Supplier findSupplier(String nameOrID){
        for(Supplier supplier:suppliers) {
            if (supplier.getName().equals(nameOrID) || supplier.getName().equals(nameOrID)){
                return supplier;
            }
        }
        return null;
    }
    
    public boolean isCustomerInList(Customer customer){
        return customers.contains(customer);
    }
    
    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }
    
    public void setSuppliers(ArrayList<Supplier> suppliers) {
        this.suppliers=suppliers;
    }    
    
    public void sell(TransactionSell newSell){
        accountingBook.addSale(newSell);        
    }
    
    public void buy(TransactionBuy newBuy){
        accountingBook.addPurchase(newBuy);
    }
    
    public void setFileName(String fileName){
        this.fileName=fileName;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    @Override
    public String toString(){
        return owner.toString();
    }
       
}
