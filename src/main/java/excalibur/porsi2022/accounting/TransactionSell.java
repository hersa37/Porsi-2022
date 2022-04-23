/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import excalibur.porsi2022.accounting.people.Customer;

import java.time.LocalDate;

/**
 * Class of sale transactions with items sold and customer
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class TransactionSell extends Transaction {
    private static int uniqueID = 0;

    public TransactionSell(Customer customer, Products_Sell products, int paid) {
        super(customer, products, paid);
        this.date = LocalDate.now();
        id = getUniqueID();
    }


    private String getUniqueID() {
        return "sell_" + String.format("%05d", uniqueID++);
    }

    @Override
    public String toString() {
        return super.toString("Customer", products);
    }


}
