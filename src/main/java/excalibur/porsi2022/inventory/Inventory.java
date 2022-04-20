/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.inventory;

import java.io.Serializable;


/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Inventory implements Serializable {
    private final Product[] stock;
    private final int ITEM_TYPES=5;
    
    public Inventory(){
        stock=new Product[ITEM_TYPES];
    }
    
    public Product[] getStock(){
        return stock;
    }
    
    public void addStock(Product product){
        for(int i=1;i<ITEM_TYPES;i++){
            if(stock[i]==null){
                stock[i]=product;
                break;
            } 
            else if(stock[i].getClass().equals(product.getClass())){
                stock[i].addAmount(product.getAmount());
                break;
            }            
        }       
    }
    
    public void reduceStock(Product product){
        for(int i=0;i<ITEM_TYPES;i++){
            if (stock[i]==null){
                stock[i]=product;
                break;
            }      
            else if(stock[i].getClass().equals(product.getClass())){
                stock[i].reduceAmount(product.getAmount());
                break;
            }
        }
    }
    
    @Override
    public String toString(){
        String print="{ Inventory\n";
        for(int i=0;i<ITEM_TYPES;i++){
            if(stock[i]==null){
                print+="";
            } else print+=stock[i].toString()+"\n";
        }
        print+="}";
        return print;
    }

}
