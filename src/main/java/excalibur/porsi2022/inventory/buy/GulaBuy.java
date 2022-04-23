/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.inventory.buy;

import excalibur.porsi2022.inventory.Product;

/**
 * A type of product. Made so that arrays can stay ordered
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class GulaBuy extends Product {

    public GulaBuy(int amount) {
        super("Gula", "/kg", 11000, amount);
    }
}
