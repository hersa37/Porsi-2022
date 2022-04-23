/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excalibur.porsi2022;

import excalibur.porsi2022.accounting.*;
import excalibur.porsi2022.accounting.people.Customer;
import excalibur.porsi2022.accounting.people.Owner;
import excalibur.porsi2022.accounting.people.Supplier;
import excalibur.porsi2022.inventory.Inventory;
import excalibur.porsi2022.inventory.Product;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class of the store
 *
 * @author asus
 */
public class Store implements Serializable {
    private final String storeName;
    private final Owner owner;
    private final Accounting accountingBook;
    private final String buyPrice;
    private final String sellPrice;
    private final ArrayList<Customer> customers;
    private final ArrayList<Supplier> suppliers;
    private final String fileName;
    private Inventory inventory;

    /**
     * Generates store with all attributes initialized
     *
     * @param storeName the name of the store
     * @param owner     the object of the owner
     */
    public Store(String storeName, Owner owner) {
        this.storeName = storeName;
        this.owner = owner;
        accountingBook = new Accounting();
        customers = new ArrayList<>();
        suppliers = new ArrayList<>();
        inventory = new Inventory();
        buyPrice = inventory.priceList();
        Products_Sell temp = new Products_Sell();
        sellPrice = temp.priceList();
        fileName = storeName;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public Accounting getAccountingBook() {
        return accountingBook;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Lists all customers as a string
     *
     * @return the string of formatted customer list
     */
    public String listCustomer() {
        String print = "ID\t\tNama\tNo. telp\t\tAlamat";
        for (int i = 0; i < customers.size(); i++) {
            print += "\n" + customers.get(i).getId() + "\t"
                    + customers.get(i).getName() + "\t"
                    + customers.get(i).getPhone() + "\t\t"
                    + customers.get(i).getAddress();
        }
        return print;
    }

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    /**
     * Finds customer by name or ID
     *
     * @param nameOrID the name or ID to be searched
     * @return the customer object if found, returns null if not
     */
    public Customer findCustomer(String nameOrID) {
        for (Customer customer : customers) {
            if (customer.getName().equals(nameOrID) || customer.getId().equals(nameOrID)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Removes customer based on name or ID
     *
     * @param nameOrID name or ID to be removed
     */
    public void removeCustomer(String nameOrID) {
        Customer temp = findCustomer(nameOrID);
        if (temp == null) {
            System.out.println("Tidak ditemukan");
        } else {
            customers.remove(temp);
        }
    }

    /**
     * Finds supplier based on ID
     *
     * @param nameOrID name or ID to be searched
     * @return the supplier object if found, null if not
     */
    public Supplier findSupplier(String nameOrID) {
        for (Supplier supplier : suppliers) {
            if (supplier.getName().equals(nameOrID) || supplier.getId().equals(nameOrID)) {
                return supplier;
            }
        }
        return null;
    }

    /**
     * Removes supplier based on name or ID
     *
     * @param nameOrID name or ID to be removed
     */
    public void removeSupplier(String nameOrID) {
        Supplier temp = findSupplier(nameOrID);
        if (temp == null) {
            System.out.println("Tidak ditemukan");
        } else {
            suppliers.remove(temp);
        }
    }

    /**
     * Lists all saved suppliers
     *
     * @return the supplier in a formatted string
     */
    public String listSupplier() {
        String print = "ID\t\tNama\tNo. telp\t\tAlamat";
        for (int i = 0; i < suppliers.size(); i++) {
            print += "\n" + suppliers.get(i).getId() + "\t\t"
                    + suppliers.get(i).getName() + "\t"
                    + suppliers.get(i).getPhone() + "\t\t"
                    + suppliers.get(i).getAddress();
        }
        return print;
    }

    /**
     * Checks if products requested are  in stock
     *
     * @param products the products checked
     * @return whether items are in stock or not
     */
    public boolean isInStock(Products_Sell products) {
        for (int i = 0; i < Inventory.ITEM_TYPES; i++) {
            if (inventory.getStock()[i].getAmount() < products.getStock()[i].getAmount()) {
                System.out.println(inventory.getStock()[i].getItemType() + " tidak cukup. Sisa "
                        + inventory.getStock()[i].getAmount());
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if store has enough money or not based on purchase
     *
     * @param newBuy purchase attempted
     * @return whether there is enough money
     */
    public boolean isEnoughMoney(Products_Buy newBuy) {
        if (accountingBook.getMoneyOwned() < newBuy.getTotalPrice()) {
            System.out.println("Uang tidak cukup. Sisa " +
                    LocaleFormatting.currency(accountingBook.getMoneyOwned()));
            return false;
        }
        return true;
    }

    /**
     * Method for successful sale transactions
     *
     * @param newSell the sale being added
     */
    public void sell(TransactionSell newSell) {
        accountingBook.addSale(newSell);
        Product[] p = newSell.getProducts().getStock();

        inventory.reduceStock(p[0].getAmount()
                , p[1].getAmount()
                , p[2].getAmount()
                , p[3].getAmount()
                , p[4].getAmount());
    }

    /**
     * Method for successful purchase transactions
     *
     * @param newBuy the purchase being added
     */
    public void buy(TransactionBuy newBuy) {
        accountingBook.addPurchase(newBuy);
        Product[] p = newBuy.getProducts().getStock();

        inventory.addStock(p[0].getAmount()
                , p[1].getAmount()
                , p[2].getAmount()
                , p[3].getAmount()
                , p[4].getAmount());
    }

    /**
     * Adds money to the store without any sale
     *
     * @param money the amount added
     */
    public void addMoney(int money) {
        Transaction temp = new Transaction(owner, new Inventory(), money);
        temp.setPaid(money);
        accountingBook.ownerAddMoney(temp);
    }

    /**
     * Takes money from the store without any purchase
     *
     * @param money the amount taken
     */
    public void takeMoney(int money) {
        Transaction temp = new Transaction(owner, new Inventory(), money);
        temp.setPaid(-money);
        accountingBook.ownerTakeMoney(temp);

    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return storeName + ";" + owner.toString();
    }


}
