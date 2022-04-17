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
    private String id;
    private TransactionDetail[] products;
    private int total;
    private boolean isPaid;
    private LocalDate date;
}
