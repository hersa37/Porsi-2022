/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excalibur.porsi2022;

import excalibur.porsi2022.accounting.people.*;
import excalibur.porsi2022.accounting.*;
import excalibur.porsi2022.inventory.Inventory;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Store {
    private Owner owner;
    private Accounting accountingBook;
    private Inventory inventory;
    private ArrayList<Customer> customers;
    private ArrayList<Supplier> suppliers;
    private String accountingBookFileName;
    private String inventoryFileName;
    
    public Store(){
        customers=new ArrayList<>();
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
    
    public Customer findCustomer(String nameOrID){
        for(Customer customer:customers) {
            if (customer.getName().equals(nameOrID) || customer.getName().equals(nameOrID)){
                return customer;
            }
        }
        return null;
    }
    
    public boolean isCustomerInList(Customer customer){
        return customers.contains(customer);
    }
    
    public Customer getCustomerAt(int index){
        return customers.get(index);
    }
    
    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(ArrayList<Supplier> suppliers) {
        this.suppliers=suppliers;
    }
    
    public String getAccountingBookFileName() {
        return accountingBookFileName;
    }

    public void setAccountingBookFileName(String accountingBookFileName) {
        this.accountingBookFileName=accountingBookFileName;
    }

    public String getInventoryFileName() {
        return inventoryFileName;
    }

    public void setInventoryFileName(String inventoryFileName) {
        this.inventoryFileName=inventoryFileName;
    }
    
    
    public void sell(TransactionSell newSell){
        accountingBook.addSale(newSell);        
    }
    
    public void buy(TransactionBuy newBuy){
        accountingBook.addPurchase(newBuy);
    }
    
       
}
