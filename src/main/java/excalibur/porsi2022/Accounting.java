/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;

import java.util.HashMap;
/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Accounting {
    private final HashMap<String,Transaction> transactions;
    
    
    public Accounting(){
        transactions=new HashMap<>();
    }
    
    public void addTransaction(Transaction transaction){
        transactions.put(transaction.getId(), transaction);
    }
    
    private int 
}
