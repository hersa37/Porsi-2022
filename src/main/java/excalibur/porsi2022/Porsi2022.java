/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;
import excalibur.porsi2022.accounting.people.Customer;
import excalibur.porsi2022.accounting.*;
import excalibur.porsi2022.inventory.sell.*;
import excalibur.porsi2022.inventory.buy.*;
import excalibur.porsi2022.inventory.*;

/**
 *
 * @author echa
 */
public class Porsi2022 {

    public static void main(String[] args) {
        Customer cust1=new Customer("Echa", "Bale", "0822");
        System.out.println(cust1);
        Product[] products=new Product[2];
        products[0]=new BerasSell(15);
        products[1]=new MinyakSell(12);
        
        Sale sale1=new Sale(cust1, products, 15000);
        
//        System.out.println(sale1);
        
        Inventory inv=new Inventory();
        inv.addStock(new BerasBuy(15));
        inv.addStock(new MinyakBuy(23)); 
        inv.addStock(new BerasBuy(26));
//        FileManagement.write(inv, "inventory.txt");
        System.out.println(inv);
//        System.out.println(FileManagement.read("inventory.txt"));
    }
}
