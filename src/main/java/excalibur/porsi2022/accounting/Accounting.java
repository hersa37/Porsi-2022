/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class to store all the transactions of a store
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Accounting implements Serializable {
    private static int index = 0;
    private final LinkedHashMap<String, TransactionBuy> purchase; //Inbound transaction
    private final LinkedHashMap<String, TransactionSell> sale; //Outbound transaction
    private final LinkedHashMap<Integer, Transaction> ownerCashFlow; //List of times owner takes/adds money
    private int moneyOwned;

    public Accounting() {
        purchase = new LinkedHashMap<>();
        sale = new LinkedHashMap<>();
        ownerCashFlow = new LinkedHashMap<>();
    }

    private static int getIndex() {
        return index++;
    }

    public int getMoneyOwned() {
        return moneyOwned;
    }

    public LinkedHashMap<String, TransactionBuy> getPurchase() {
        return purchase;
    }

    public LinkedHashMap<String, TransactionSell> getSale() {
        return sale;
    }

    /**
     * Adds a transaction to the list. Uses transaction ID as key
     *
     * @param purchase the transaction to be added
     */
    public void addPurchase(TransactionBuy purchase) {
        this.purchase.put(purchase.getId(), purchase);
        moneyOwned -= purchase.getPaid();
    }

    /**
     * Adds a transaction to the list. Uses transaction ID as key
     *
     * @param sale the transaction to be added
     */
    public void addSale(TransactionSell sale) {
        this.sale.put(sale.getId(), sale);
        moneyOwned += sale.getPaid();

    }

    public String getOwnerCashFlowString() {
        String print = "\nTanggal\t\tJumlah";
        for (Map.Entry<Integer, Transaction> tr : ownerCashFlow.entrySet()) {
            print += "\n" + tr.getValue().toStringCash();
        }
        return print;
    }

    /**
     * Method to allow owner to add money without any purchase. Uses date as key
     *
     * @param transaction
     */
    public void ownerAddMoney(Transaction transaction) {
        ownerCashFlow.put(getIndex(), transaction);
        moneyOwned += transaction.getPaid();
    }

    /**
     * Method to allow owner to take money without any purchase. Uses date as key
     *
     * @param transaction
     */
    public void ownerTakeMoney(Transaction transaction) {
        ownerCashFlow.put(getIndex(), transaction);
        moneyOwned += transaction.getPaid();
    }

    @Override
    public String toString() {
        String print = "Daftar Transaksi\n";
        print += "Purcase list:";
        for (String key : purchase.keySet()) {
            print += "\n" + key + ":" + purchase.get(key).toString();
        }
        print += "\n\n";
        print += "Sale list:";
        for (String key : sale.keySet()) {
            print += "\nID: " + key + ":\n" + sale.get(key).toString();
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
