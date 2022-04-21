/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.inventory;

import excalibur.porsi2022.inventory.buy.*;
import java.io.Serializable;


/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Inventory implements Serializable {
    protected Product[] stock;
    public static final int ITEM_TYPES=5;
    
    public Inventory(){
        stock=new Product[ITEM_TYPES];
        stock[0]=new BerasBuy(0);
        stock[1]=new GaramBuy(0);
        stock[2]=new GulaBuy(0);
        stock[3]=new MinyakBuy(0);
        stock[4]=new TeriguBuy(0);
               
    }
    
    public Product[] getStock(){
        return stock;
    }
    
    public void addStock(Product product){
        int i=0;
        switch(product.getItemType()){
            case "Beras": i=0; break;
            case "Garam": i=1; break;
            case "Gula": i=2; break;
            case "Minyak": i=3; break;
            case "Terigu": i=4; break;
        }
        stock[i].addAmount(product.getAmount());
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
    
    public void reduceStock(Product product){
        int i=0;
        switch(product.getItemType()){
            case "Beras": i=0; break;
            case "Garam": i=1; break;
            case "Gula": i=2; break;
            case "Minyak": i=3; break;
            case "Terigu": i=4; break;
        }
        if(stock[i].getAmount()>=0+stock[i].getAmount()){
            stock[i].reduceAmount(product.getAmount());
        } else {System.out.println("Not enough");
            System.out.println("Stock "+stock[i].getItemType()
                    +" has "+stock[i].getAmount()+" left");
        }
    }
    
    public String priceList(){
        String print="Price List\n===============\n"+"Barang\t"+"Harga/unit";
        for(int i=0;i<ITEM_TYPES;i++){
            print+="\n"+stock[i].getItemType()+"\t"+stock[i].getPricePerUnit();
        }        
        return print;
    }
    
    public int getTotalPrice(){
        int total=0;
        for(int i=0;i<ITEM_TYPES;i++){
            total+=stock[i].getTotalPrice();
        }
        return total;
    }
    @Override
    public String toString(){
        String print="{ Inventory\n";
        print+="Barang\t"+"Harga/unit\t"+"Jumlah\t"+"Harga total\t\n";
        for(int i=0;i<ITEM_TYPES;i++){
            print+=stock[i].toStringNoLabel()+"\n";
        }
        print+="}";
        return print;
    }
    
    public String toString(String transactionType){
        String print=transactionType+"\n";
        print+="Barang\t"+"Harga/unit\t"+"Jumlah\t"+"Harga total\t\n";
        for(int i=0;i<ITEM_TYPES;i++){
            print+=stock[i].toStringNoLabel()+"\n";
        }
        
        return print;
    }
}
