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
    
    private String product_id;
    private ProductType type;
    private String unit;
    private int price;
    
    public Product(ProductType type){
        this.type=type; 
    }
    
    public ProductType getType(){
        return type;
    }
    
    public void setProduct_id(String product_id){
        this.product_id = product_id;
    }
    
    public String getProduct_id(){
        return product_id;
    }
    
    public void setUnit(String unit){
        this.unit = unit;
    }
    
    public String getUnit(){
        return unit;
    }
    
    public void setPrice(int price){
        this.price=price;
    }
    
    public int getPrice(){
        return price;
    }
}
