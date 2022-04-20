/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import excalibur.porsi2022.inventory.Inventory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Accounting  implements Serializable{
    private HashMap<String,Purchase> purchase;
    private HashMap<String,Sale> sale;
    
    
    public Accounting(){
        purchase=new HashMap<>();
        sale=new HashMap<>();
    }

    public HashMap<String, Purchase> getPurchase() {
        return purchase;
    }

    public void setPurchase(HashMap<String, Purchase> purchase) {
        this.purchase=purchase;
    }

    public HashMap<String, Sale> getSale() {
        return sale;
    }

    public void setSale(HashMap<String, Sale> sale) {
        this.sale=sale;
    }
    
    
    public void addPurchase(Purchase purchase){
        this.purchase.put(purchase.getId(), purchase);
    }
    
    
        
    public Purchase getPurchaseAt(String purchase_id){
        return purchase.get(purchase_id);
    }
    
    public void addSale(Sale sale){
        this.sale.put(sale.getId(), sale);
        
    }
    
    public Sale getSaleAt(String sale_id){
        return sale.get(sale_id);
    }
    
    public List<Purchase> getPurchaseUnpaid(){
        List<Purchase> purchaseUnpaid=new ArrayList<>();
        for(Entry<String, Purchase> unpaid:purchase.entrySet()){
            if(!unpaid.getValue().isPaid()){
                purchaseUnpaid.add(unpaid.getValue());
            }
        }
        return purchaseUnpaid;
    }
    
    public List<Sale> getSaleUnpaid(){
        List<Sale> saleUnpaid=new ArrayList<>();
        for(Entry<String, Sale> unpaid:sale.entrySet()){
            if(!unpaid.getValue().isPaid()){
                saleUnpaid.add(unpaid.getValue());
            }
        }
        return saleUnpaid;
    }
    
    public int getPUnpaidAmount(){
        int pUnpaid=0;
        for(Entry<String, Purchase> unpaid:purchase.entrySet()){
            if(unpaid.getValue().isPaid()){
                pUnpaid+=unpaid.getValue().getPaymentRemain();
            }
        }
        return pUnpaid;
    }
    
    public int getSUnpaidAmount(){
        int sUnpaid=0;
        for(Entry<String, Sale> unpaid:sale.entrySet()){
            if(unpaid.getValue().isPaid()){
                sUnpaid+=unpaid.getValue().getPaymentRemain();
            }
        }
        return sUnpaid;
    }
    
    public String toString(List list){
        String print="";
        for(Object temp:list){
            print+=temp.toString()+"\n";
        }
        return print;
    }
    
    public String toString(HashMap<String, Transaction> hashmap){
        String print="";
        return print;
    }
}
