/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.inventory;

import excalibur.porsi2022.accounting.LocaleFormatting;
import excalibur.porsi2022.inventory.buy.*;
import java.io.Serializable;


/**
 * Stores a fixed order of products
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Inventory implements Serializable {
    protected Product[] stock;
    public static final int ITEM_TYPES=5;
    
    /**
     * Object starts with a predetermined array for easier management
     */
    public Inventory(){
        stock=new Product[ITEM_TYPES];
        stock[0]=new BerasBuy(0);
        stock[1]=new GaramBuy(0);
        stock[2]=new GulaBuy(0);
        stock[3]=new MinyakBuy(0);
        stock[4]=new TeriguBuy(0);
               
    }
    
    /**
     * Method sets the stock for each product. 
     * @param beras number of beras set
     * @param garam number of garam set
     * @param gula number of gula set
     * @param minyak number of minyak set
     * @param terigu number of terigu set
     */
    public void setStock(int beras, int garam, int gula, int minyak, int terigu){
        stock[0].setAmount(beras);
        stock[1].setAmount(garam);
        stock[2].setAmount(gula);
        stock[3].setAmount(minyak);
        stock[4].setAmount(terigu);
    }
    
    public Product[] getStock(){
        return stock;
    }
    
    /**
     * Method adds stock for each product
     * @param beras
     * @param garam
     * @param gula
     * @param minyak
     * @param terigu 
     */
    public void addStock(int beras, int garam, int gula, int minyak, int terigu){
        stock[0].addAmount(beras);
        stock[1].addAmount(garam);
        stock[2].addAmount(gula);
        stock[3].addAmount(minyak);
        stock[4].addAmount(terigu);
//        for(int i=1;i<ITEM_TYPES;i++){
//            if(stock[i]==null){
//                stock[i]=product;
//                break;
//            } 
//            else if(stock[i].getClass().equals(product.getClass())){
//                stock[i].addAmount(product.getAmount());
//                break;
//            }            
//        }       
    }
    
    public void reduceStock(int beras, int garam, int gula, int minyak, int terigu){
        stock[0].reduceAmount(beras);
        stock[1].reduceAmount(garam);
        stock[2].reduceAmount(gula);
        stock[3].reduceAmount(minyak);
        stock[4].reduceAmount(terigu);
    }
    
    /**
     * Method prints a list of the pricing for each product in a specified format
     * @return the formatted price list
     */
    public String priceList(){
        String print="Price List\n===============\n"+"Barang\t"+"Harga/unit\n";
        for(int i=0;i<ITEM_TYPES;i++){
            print+="\n"+stock[i].getItemType()+"\t"
                    +LocaleFormatting.currency(stock[i].getPricePerUnit())
                    +stock[i].getUnit();
        }        
        return print;
    }
    
    /**
     * Calculates the total price of all products combined
     * @return the total price
     */
    public int getTotalPrice(){
        int total=0;
        for(int i=0;i<ITEM_TYPES;i++){
            total+=stock[i].getTotalPrice();
        }
        return total;
    }
    
    
    
    @Override
    public String toString(){
        String print="Inventory\n";
        print+="Barang\t"+"Harga/unit\t"+"Jumlah\t"+"Harga total\t\n";
        for(int i=0;i<ITEM_TYPES;i++){
            print+=stock[i].toStringNoLabel()+"\n";
        }        
        return print;
    }
    
    public String toString(String transactionType){
        String print="\n";
        print+="Barang\t"+"Harga/unit\t"+"Jumlah\t"+"Harga total\t\n"
                + "\n";
        for(int i=0;i<ITEM_TYPES;i++){
            print+=stock[i].toStringNoLabel()+"\n";
        }        
        return print;
    }
}
