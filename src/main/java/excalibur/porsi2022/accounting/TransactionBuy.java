/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import excalibur.porsi2022.accounting.people.Supplier;
import java.time.LocalDate;
/**
 * Class for buy transactions using supplier and list of bought products
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class TransactionBuy extends Transaction {
    private static int uniqueID=0;
    
    public TransactionBuy(Supplier supplier, Products_Buy products, int paid){
        super(supplier, products, paid);   
        this.date=LocalDate.now();
        id=getUniqueID();
    }
    
    private String getUniqueID(){
        return "buy_"+String.format("%05d", uniqueID++);
    }
    
    @Override
    public String toString(){
        return super.toString("Customer", products);
    }    
}
