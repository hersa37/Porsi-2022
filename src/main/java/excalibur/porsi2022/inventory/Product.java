/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.inventory;

import excalibur.porsi2022.accounting.LocaleFormatting;
import java.io.Serializable;

/**
 * Stores each type of product
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Product implements Serializable {
    
    private String itemType; 
    private String unit;
    private int pricePerUnit;
    private int amount;
    
    protected Product(String itemType, String unit, int price, int amount){
        this.itemType=itemType;
        this.unit=unit;
        this.pricePerUnit=price;
        this.amount=amount;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType=itemType;
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
        return "[ Barang :\t"+this.getItemType()+"\t"
                +"; Harga"+unit+":\t"+LocaleFormatting.currency(pricePerUnit)
                +"; Jumlah :\t"+amount
                +"; Harga total :\t"+LocaleFormatting.currency(getTotalPrice())
                +"]";
    }
    public String toStringNoLabel(){
        return this.getItemType()+"\t"+LocaleFormatting.currency(pricePerUnit)
                +"\t"+amount+"\t"
                +LocaleFormatting.currency(getTotalPrice());
    }
                
//        return "Barang\t"+"Harga/unit\t"+"Jumlah\t"+"Harga total\t"
                
}
