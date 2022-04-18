/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Stock {
    private Product product;
    private int amount;
    
    public Stock(Product product, int amount){
        this.product=product;
        this.amount=amount;
    }
    
    public void addAmount(int add){
        amount+=add;
    }
    
    public void reduceAmount(int reduce){
        amount-=reduce;
    }
    
    public Product getProduct(){
        return product;
    }
}
