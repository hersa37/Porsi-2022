/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.inventory.sell;

import excalibur.porsi2022.inventory.buy.MinyakBuy;

/**
 * Extends product of the same type, but made different so it has the same 
 * props while having a different price
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class MinyakSell extends MinyakBuy{

    public MinyakSell(int amount){
        super(amount);
        super.setPricePerUnit(25000);
    }    
}
