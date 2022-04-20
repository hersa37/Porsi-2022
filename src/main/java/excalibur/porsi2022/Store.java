/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excalibur.porsi2022;

import excalibur.porsi2022.accounting.people.*;
import excalibur.porsi2022.accounting.*;
import excalibur.porsi2022.accounting.Purchase;
import excalibur.porsi2022.inventory.Inventory;
import excalibur.porsi2022.inventory.Product;

/**
 *
 * @author asus
 */
public class Store {
    private Owner owner;
    private Accounting accountingBook;
    private Inventory inventory;
    private Customer[] customers;
    private String accountingBookFileName;
    private String inventoryFileName;
    
    public Store(){
        
    }
    
    public void sell(Purchase purchase){
        accountingBook.addPurchase(purchase);
        for(int i=0;i<purchase.getProducts().length;i++){
            inventory.reduceStock(purchase.getProducts()[i]);
        }
    }
   
}
