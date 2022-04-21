/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import excalibur.porsi2022.inventory.*;
import excalibur.porsi2022.inventory.sell.*;
/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Products_Sell extends Inventory {
    
    public Products_Sell(){
        stock=new Product[ITEM_TYPES];
        stock[0]=new BerasSell(0);
        stock[1]=new GaramSell(0);
        stock[2]=new GulaSell(0);
        stock[3]=new MinyakSell(0);
        stock[4]=new TeriguSell(0);
    }
    
    @Override
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
    }

    @Override
    public String toString(){
        return super.toString("Sell");
    }
    
}
