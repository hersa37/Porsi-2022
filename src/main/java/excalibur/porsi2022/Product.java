/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;

import java.io.Serializable;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Product implements Serializable {
    
    private ProductType type;
    private String unit;
    private int price;
    
    public Product(ProductType type){
        this.type=type;
        
    }
    
    public ProductType getType(){
        return type;
    }
}
