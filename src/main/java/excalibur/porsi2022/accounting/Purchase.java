/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import excalibur.porsi2022.accounting.people.Supplier;
import java.time.LocalDate;
import excalibur.porsi2022.inventory.*;
/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Purchase extends Transaction {
    private Supplier supplier;
    private static int uniqueID=0;
    
    public Purchase(Supplier supplier, Product[] products, int paid){
        super(products, paid);
        this.supplier=supplier;        
        this.date=LocalDate.now();
        id=getUniqueID();
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier=supplier;
    }
    
    private String getUniqueID(){
        return "pur_"+String.format("%05d", uniqueID++);
    }
    
    @Override
    public String toString(){
        return "{ID: "+id
                +"; Supplier: "+supplier
                +"; Products: "+super.toString(products)
                +"; Total payment: "+totalCost
                +"; Total paid: "+paid
                +"}";
    }
    
}
