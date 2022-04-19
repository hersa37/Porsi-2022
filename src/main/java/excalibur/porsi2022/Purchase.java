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
public class Purchase extends Transaction {
    private Supplier supplier;
    protected static int uniqueID=0;
    
    public Purchase(Supplier supplier, TransactionDetail[] products, int total, int paid){
        this.supplier=supplier;
        this.products=products;
        this.total=total;
        this.paid=paid;
        this.date=LocalDate.now();
        this.id=getUniqueID();
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
    
}
