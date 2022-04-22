/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import java.io.Serializable;
import java.time.LocalDate;
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
    private HashMap<String,TransactionBuy> purchase;
    private HashMap<String,TransactionSell> sale;
    private HashMap<LocalDate,Transaction> ownerCashFlow;
    private int moneyOwned;
    
    public Accounting(){
        purchase=new HashMap<>();
        sale=new HashMap<>();
        ownerCashFlow=new HashMap<>();
    }

    public int getMoneyOwned() {
        return moneyOwned;
    }

    public void setMoneyOwned(int moneyOwned) {
        this.moneyOwned=moneyOwned;
    }
    
    public HashMap<String, TransactionBuy> getPurchase() {
        return purchase;
    }

    public void setPurchase(HashMap<String, TransactionBuy> purchase) {
        this.purchase=purchase;
    }

    public HashMap<String, TransactionSell> getSale() {
        return sale;
    }

    public void setSale(HashMap<String, TransactionSell> sale) {
        this.sale=sale;
    }
    
    
    public void addPurchase(TransactionBuy purchase){
        this.purchase.put(purchase.getId(), purchase);
        moneyOwned-=purchase.getPaid();
    }   
        
    public TransactionBuy getPurchaseAt(String purchase_id){
        return purchase.get(purchase_id);
    }
    
    public void addSale(TransactionSell sale){
        this.sale.put(sale.getId(), sale);
        moneyOwned+=sale.getPaid();
        
    }

    public HashMap<LocalDate, Transaction> getOwnerCashFlow() {
        return ownerCashFlow;
    }

    public void setOwnerCashFlow(HashMap<LocalDate, Transaction> ownerCashFlow) {
        this.ownerCashFlow=ownerCashFlow;
    }
    
    public void ownerAddMoney(Transaction transaction){
        ownerCashFlow.put(transaction.getDate(), transaction);
        moneyOwned+=transaction.getPaid();
    }
    
    public void ownerTakeMoney(Transaction transaction){
        ownerCashFlow.put(transaction.getDate(), transaction);
        moneyOwned+=transaction.getPaid();
    }
    
    public TransactionSell getSaleAt(String sale_id){
        return sale.get(sale_id);
    }
    
//    public List<TransactionBuy> getPurchaseUnpaid(){
//        List<TransactionBuy> purchaseUnpaid=new ArrayList<>();
//        for(Entry<String, TransactionBuy> unpaid:purchase.entrySet()){
//            if(!unpaid.getValue().isPaid()){
//                purchaseUnpaid.add(unpaid.getValue());
//            }
//        }
//        return purchaseUnpaid;
//    }
//    
//    public List<TransactionSell> getSaleUnpaid(){
//        List<TransactionSell> saleUnpaid=new ArrayList<>();
//        for(Entry<String, TransactionSell> unpaid:sale.entrySet()){
//            if(!unpaid.getValue().isPaid()){
//                saleUnpaid.add(unpaid.getValue());
//            }
//        }
//        return saleUnpaid;
//    }
//    
//    public int getPUnpaidAmount(){
//        int pUnpaid=0;
//        for(Entry<String, TransactionBuy> unpaid:purchase.entrySet()){
//            if(unpaid.getValue().isPaid()){
//                pUnpaid+=unpaid.getValue().getPaymentRemain();
//            }
//        }
//        return pUnpaid;
//    }
//    
//    public int getSUnpaidAmount(){
//        int sUnpaid=0;
//        for(Entry<String, TransactionSell> unpaid:sale.entrySet()){
//            if(unpaid.getValue().isPaid()){
//                sUnpaid+=unpaid.getValue().getPaymentRemain();
//            }
//        }
//        return sUnpaid;
//    }
    
    @Override
    public String toString(){
        String print="Daftar Transaksi\n";
        print+="Purcase list:";
        for(String key:purchase.keySet()){
            print+="\n"+key+":"+purchase.get(key).toString();
        }
        print+="\n\n";        
        print+="Sale list:";
        for(String key:sale.keySet()){
            print+="\nID: "+key+":\n"+sale.get(key).toString();
        }
//        +"\n"
//                +"Sale list:"+toString(sale);
        return print;
    }
    
//    public String toString(List list){
//        String print="";
//        for(Object temp:list){
//            print+=temp.toString()+"\n";
//        }
//        return print;
//    }
    
//    public String toString(HashMap<String, Transaction> hashmap){
//        String print="";
//        for(String key:hashmap.keySet()){
//            print+=key+":"+hashmap.get(key).toString()+"\n";
//        }
//        return print;
//    }
    
    
}
