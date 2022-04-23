/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import excalibur.porsi2022.accounting.people.People;
import excalibur.porsi2022.inventory.Inventory;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class for the different types of transactions
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Transaction implements Serializable {
    private final People person;
    protected String id;
    protected Inventory products;
    protected int totalCost;
    protected int paid;
    protected LocalDate date;


    public Transaction(People person, Inventory products, int paid) {
        this.person = person;
        this.products = products;
        this.paid = paid;
        this.date = LocalDate.now();
        totalCost = getTotalCost();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Inventory getProducts() {
        return products;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getTotalCost() {
        return products.getTotalPrice();
    }

    public int getPaymentRemain() {
        return totalCost - paid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String toString(String peopleType, Inventory products) {
        return "\n{ID: " + id
                + "\n" + peopleType + ": " + person
                + "\nBarang-barang: \n" + products.toString()
                + "=================================================\n"
                + "Harga total\t\t:\t" + LocaleFormatting.currency(products.getTotalPrice())
                + "\nDibayar\t\t\t:\t" + LocaleFormatting.currency(paid)
                + "\nSisa Pembayaran\t\t:\t" + LocaleFormatting.currency(getPaymentRemain())
                + "\n\n";

    }

    public String toStringCash() {
        return date + "\t" + LocaleFormatting.currency(paid);
    }
}
