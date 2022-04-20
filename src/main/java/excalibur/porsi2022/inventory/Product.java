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
public class Product implements Serializable {

    private String unit;
    private int pricePerUnit;
    private int amount;
    
    protected Product(String unit, int price, int amount){
        this.unit=unit;
        this.pricePerUnit=price;
        this.amount=amount;
    }
    
    public void setUnit(String unit){
        this.unit = unit;
    }
    
    public String getUnit(){
        return unit;
    }
    
    public void setPricePerUnit(int pricePerUnit){
        this.pricePerUnit=pricePerUnit;
    }
    
    public int getPricePerUnit(){
        return pricePerUnit;
    }
    
    public void setAmount(int amount){
        this.amount=amount;
    }
    
    public int getAmount(){
        return amount;
    }
    
    public void addAmount(int add){
        amount+=add;
    }
    
    public void reduceAmount(int reduce){
        if(amount>=0+amount){
            amount-=reduce;
        } else amount=0;
    }
    
    public int getTotalPrice(){
        return pricePerUnit*amount;
    }
    
    @Override
    public String toString(){
        return "{ Barang: "+this.getClass().getSimpleName()
                +"; Harga"+unit+": "+pricePerUnit
                +"; Jumlah: "+amount
                +"; Harga total: "+getTotalPrice()
                +"]";
    }
}
