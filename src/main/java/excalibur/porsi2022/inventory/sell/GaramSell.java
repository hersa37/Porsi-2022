/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.inventory.sell;

import excalibur.porsi2022.inventory.buy.GaramBuy;

/**
 * Extends product of the same type, but made different so it has the same 
 * props while having a different price
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class GaramSell extends GaramBuy{
    
    public GaramSell(int amount){
        super(amount);
        super.setPricePerUnit(5000);
    }
}
