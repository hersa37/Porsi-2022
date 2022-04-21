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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author echa
 */
public class Porsi2022 {

    public static void main(String[] args) {
 
        
//        Products_Buy inv=new Products_Buy();
//        inv.addStock(new BerasBuy(15));
//        inv.addStock(new MinyakBuy(23)); 
//        inv.addStock(new BerasBuy(26));
//        
//        Products_Sell sale=new Products_Sell();
//        sale.addStock(new BerasSell(2));
//        sale.addStock(new MinyakSell(2));
//        sale.addStock(new GulaSell(46));
//        sale.reduceStock(new GulaSell(3));
//        FileManagement.write(inv, "inventory.txt");
//        System.out.println(inv);
//        
//        System.out.println(sale.priceList());
//        System.out.println(sale);
//        
//        TransactionSell acc_sell=new TransactionSell(new Customer("Echa", "08989999", "Bale"), sale, 42069);
//        System.out.println(acc_sell);
//        
//        
//        Accounting book=new Accounting();
//        book.addSale(acc_sell);
//        System.out.println(book);
//===================================================================================================================================
        Scanner input=new Scanner(System.in);
        Store toko=new Store();
        toko.addCustomer(new Customer("ecah", "123", "bse"));
        int inp;
        do{
            System.out.println("1. Penjualan\n2. Pembelian");
            inp=input.nextInt();
        } while (inp!=1 && inp!=2);
        switch(inp){
            case 1:
                penjualan(toko);
            case 2:;
        }
        
        
    }
    
    public static void penjualan(Store toko){
        Scanner input=new Scanner(System.in);
        int inp;     
        System.out.println("1. Customer baru\n2. Customer lama");
        inp=input.nextInt();
        TransactionSell transactionSell;
        switch(inp){
            case 1:
                Customer newCustomer=customerBaru(toko);
                transactionSell=inputSaleItems(newCustomer);
                toko.sell(transactionSell);
                break;
            case 2:
                Customer oldCustomer;
                do{
                    System.out.println("Cari nama/ID");
                    oldCustomer=toko.findCustomer(input.next());
                    if(oldCustomer==null){
                        System.out.println("Buat masukkan baru? (y/n): ");
                        if(input.next().equals("y")){
                            oldCustomer=customerBaru(toko);
                        }
                    }
                }while(oldCustomer==null);
                transactionSell=inputSaleItems(oldCustomer);
                toko.sell(transactionSell);
                break;
        }
    }
        
    public static Customer customerBaru(Store toko){
        Customer newCustomer;
        Scanner input=new Scanner(System.in);
        do{
            System.out.print("Nama\t:");
            String name=input.next();
            System.out.print("No.Telp\t:");
            String phone=input.next();
            System.out.print("Alamat\t:");
            String address=input.next();
            newCustomer=new Customer(name, phone, address);

            System.out.println("Apakah data sudah benar?(y/n)");
        } while(!input.next().equals("y"));
        toko.addCustomer(newCustomer);
        return newCustomer;
    }
    
    public static TransactionSell inputSaleItems(Customer customer){
        Scanner input=new Scanner(System.in);
        Products_Sell products_Sell=new Products_Sell();
        TransactionSell transactionSell;
        System.out.println("\n"+products_Sell.priceList());

        do{
            System.out.println("Jumlah barang pembelian:");

            System.out.print("Beras\t: ");        
            products_Sell.addStock(new BerasSell(input.nextInt()));
            System.out.print("Garam\t: ");
            products_Sell.addStock(new GaramSell(input.nextInt()));
            System.out.print("Gula\t: ");
            products_Sell.addStock(new GulaSell(input.nextInt()));
            System.out.print("Minyak\t: ");
            products_Sell.addStock(new MinyakSell(input.nextInt()));
            System.out.print("Terigu\t: ");
            products_Sell.addStock(new TeriguSell(input.nextInt()));

            System.out.println("Total:"+LocaleFormatting.currency(products_Sell.getTotalPrice()));

            System.out.println("Pembayaran: ");
            int payment=input.nextInt();
            transactionSell=new TransactionSell(customer, products_Sell, payment);
            System.out.println(transactionSell);

            System.out.print("Apakah data benar? (y/n):");
        } while(!input.next().equals("y"));
        
        return transactionSell;
    }
    
}
