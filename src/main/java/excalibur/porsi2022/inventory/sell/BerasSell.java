/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.inventory.sell;

import excalibur.porsi2022.inventory.buy.BerasBuy;

/**
 * Extends product of the same type, but made different so it has the same
 * props while having a different price
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class BerasSell extends BerasBuy {

    public BerasSell(int amount) {
        super(amount);
        super.setPricePerUnit(13000);
    }
}
