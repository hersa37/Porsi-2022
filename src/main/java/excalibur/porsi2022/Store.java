/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excalibur.porsi2022;

import excalibur.porsi2022.accounting.people.*;
import excalibur.porsi2022.accounting.*;
import excalibur.porsi2022.inventory.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class of the store
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
    
    /**
     * Generates store with all attributes initialized
     * @param storeName the name of the store
     * @param owner the object of the owner
     */
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
    
    /**
     * Lists all customers as a string
     * @return the string of formatted customer list
     */
    public String listCustomer(){
        String print="ID\t\tNama\tNo. telp\tAlamat" ;
        for(int i=0;i<customers.size();i++){
            print+="\n"+customers.get(i).getId()+"\t"
                    +customers.get(i).getName()+"\t"
                    +customers.get(i).getPhone()+"\t\t"
                    +customers.get(i).getAddress();
        }
        return print;
    }
    
    public void addSupplier(Supplier supplier){
        suppliers.add(supplier);
    }
    
    /**
     * Finds customer by name or ID
     * @param nameOrID the name or ID to be searched
     * @return the customer object if found, returns null if not
     */
    public Customer findCustomer(String nameOrID){
        for(Customer customer:customers) {
            if (customer.getName().equals(nameOrID) || customer.getId().equals(nameOrID)){
                return customer;
            }
        }
        return null;
    }
    
    /**
     * Removes customer based on name or ID
     * @param nameOrID name or ID to be removed
     */
    public void removeCustomer(String nameOrID){
        Customer temp=findCustomer(nameOrID);
        if(temp==null){
            System.out.println("Tidak ditemukan");
        } else {
            customers.remove(temp);
        }
    }
    
    /**
     * Finds supplier based on ID
     * @param nameOrID name or ID to be searched
     * @return the supplier object if found, null if not
     */
    public Supplier findSupplier(String nameOrID){
        for(Supplier supplier:suppliers) {
            if (supplier.getName().equals(nameOrID) || supplier.getId().equals(nameOrID)){
                return supplier;
            }
        }
        return null;
    }
    
    /**
     * Removes supplier based on name or ID
     * @param nameOrID name or ID to be removed
     */
    public void removeSupplier(String nameOrID){
        Supplier temp=findSupplier(nameOrID);
        if(temp==null){
            System.out.println("Tidak ditemukan");
        } else {
            suppliers.remove(temp);
        }
    }
//    public boolean isCustomerInList(Customer customer){
//        return customers.contains(customer);
//    }
    
    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }
    
    public void setSuppliers(ArrayList<Supplier> suppliers) {
        this.suppliers=suppliers;
    }    
    
    /**
     * Lists all saved suppliers
     * @return the supplier in a formatted string
     */
    public String listSupplier(){
        String print="ID\t\tNama\tNo. telp\tAlamat" ;
        for(int i=0;i<suppliers.size();i++){
            print+="\n"+suppliers.get(i).getId()+"\t"
                    +suppliers.get(i).getName()+"\t"
                    +suppliers.get(i).getPhone()+"\t\t"
                    +suppliers.get(i).getAddress();
        }
        return print;
    }
    
    /**
     * Checks if products requested are  in stock
     * @param products the products checked
     * @return whether items are in stock or not
     */
    public boolean isInStock(Products_Sell products){
        for(int i=0;i<Inventory.ITEM_TYPES;i++){
            if(inventory.getStock()[i].getAmount()<products.getStock()[i].getAmount()){
                System.out.println(inventory.getStock()[i].getItemType()+" tidak cukup. Sisa "
                        +inventory.getStock()[i].getAmount());
                return false;
            }    
        }
        return true;
    }
    
    /**
     * Checks if store has enough money or not based on purchase
     * @param newBuy purchase attempted 
     * @return whether there is enough money
     */
    public boolean isEnoughMoney(Products_Buy newBuy){
        if(accountingBook.getMoneyOwned()<newBuy.getTotalPrice()){
            System.out.println("Uang tidak cukup. Sisa "+
                    LocaleFormatting.currency(accountingBook.getMoneyOwned()));
            return false;
        }
        return true;
    }
    
    /**
     * Method for successful sale transactions
     * @param newSell the sale being added
     */
    public void sell(TransactionSell newSell){
        accountingBook.addSale(newSell);
        Product[] p=newSell.getProducts().getStock();
        
        inventory.reduceStock(p[0].getAmount()
                , p[1].getAmount()
                , p[2].getAmount()
                , p[3].getAmount()
                , p[4].getAmount());
    }
    
    /**
     * Method for successful purchase transactions
     * @param newBuy the purchase being added
     */
    public void buy(TransactionBuy newBuy){
        accountingBook.addPurchase(newBuy);
        Product[] p=newBuy.getProducts().getStock();
        
        inventory.addStock(p[0].getAmount()
                , p[1].getAmount()
                , p[2].getAmount()
                , p[3].getAmount()
                , p[4].getAmount());
    }
    /**
     * Adds money to the store without any sale
     * @param money the amount added
     */
    public void addMoney(int money){
        Transaction temp=new Transaction(owner, new Inventory(), money);
        temp.setPaid(money);
        accountingBook.ownerAddMoney(temp);
    }
    /**
     * Takes money from the store without any purchase
     * @param money the amount taken
     */
    public void takeMoney(int money){
        Transaction temp=new Transaction(owner, new Inventory(), money);
        temp.setPaid(-money);
        accountingBook.ownerTakeMoney(temp);
        
    }
    
    public void setFileName(String fileName){
        this.fileName=fileName;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    @Override
    public String toString(){
        return storeName+";"+owner.toString();
    }
    
    
       
}
