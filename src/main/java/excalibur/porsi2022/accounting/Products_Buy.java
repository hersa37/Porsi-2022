/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import excalibur.porsi2022.inventory.Inventory;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Products_Buy extends Inventory{
    
    public Products_Buy(){
        super();
    }
    
    @Override
    public String toString(){
        return super.toString("Buy");
    }
}
