/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;
import excalibur.porsi2022.accounting.people.Customer;
import excalibur.porsi2022.accounting.*;
import excalibur.porsi2022.accounting.people.Owner;
import excalibur.porsi2022.accounting.people.Supplier;
import excalibur.porsi2022.inventory.sell.*;
import excalibur.porsi2022.inventory.buy.*;
import java.util.Scanner;

/**
 *
 * @author echa
 */
public class Porsi2022 {

    public static void main(String[] args) {
        int inp;
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("1. Buka toko\n2. Toko baru");
            System.out.print("Input: ");
            inp=input.nextInt();
        }while(inp!=1 && inp !=2);
        Store toko=null;
        switch(inp) {
            case 1: 
                do{
                    do{
                    System.out.print("Nama toko:");
                    String namaToko=input.next();                    
                        toko=(Store) FileManagement.read(namaToko);
                    } while(toko==null);
                    System.out.println(toko);
                    System.out.println("\nApakah benar? (y/n)");
                }while(!input.next().equals("y"));
                break;
            case 2:
                System.out.println("Nama toko:");
                String storeName=input.next();
                System.out.println("Nama pemilik");
                String name=input.next();
                System.out.print("");
                System.out.println("No. telp emilik");
                String phone=input.next();
                System.out.println("Alamat pemilik");
                String address=input.next();
                Owner pemilik=new Owner(name, phone, address);
                toko=new Store(storeName,pemilik);
                FileManagement.write(toko, toko.getFileName());
        }
        
        do{
            do{
                System.out.println("\n1. Penjualan\n"
                        + "2. Pembelian\n"
                        + "3. Daftar harga barang\n"
                        + "4. Data customer\n"
                        + "5. Data supplier\n"
                        + "6. Sejarah penjualan\n"
                        + "7. Sejarah pembelian");
                System.out.print("Input: ");
                inp=input.nextInt();
                System.out.println("");
            } while (inp<1 && inp>5);

            switch(inp){
                case 1:
                    penjualan(toko); 
                    FileManagement.write(toko, toko.getFileName());
                    break;
                case 2:
                    pembelian(toko); 
                    FileManagement.write(toko, toko.getFileName());
                    break;
                case 3:
                    daftarHarga(toko); 
                    break;
                case 4:
                    System.out.println(toko.getCustomers());
                    break;
                case 5:
                    System.out.println(toko.getSuppliers());
                    break;
                case 6:
                    System.out.println(toko.getAccountingBook().getSale());
                    break;
                case 7:
                    System.out.println(toko.getAccountingBook().getPurchase());
                    break;
            }
            System.out.println("\nLanjut? (y/n)");
        } while(input.next().equals("y"));
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
            System.out.println("Jumlah barang penjualan:");

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
    
    public static void pembelian(Store toko){
         Scanner input=new Scanner(System.in);
        int inp;     
        System.out.println("1. Supplier baru\n2. Supplier lama");
        inp=input.nextInt();
        TransactionBuy transactionBuy;
        switch(inp){
            case 1:
                Supplier newSupplier=supplierBaru(toko);
                transactionBuy=inputBuyItem(newSupplier);
                toko.buy(transactionBuy);
                
                break;
            case 2:
                Supplier oldSupplier;
                do{
                    System.out.println("Cari nama/ID");
                    oldSupplier=toko.findSupplier(input.next());
                    if(oldSupplier==null){
                        System.out.println("Buat masukkan baru? (y/n): ");
                        if(input.next().equals("y")){
                            oldSupplier=supplierBaru(toko);
                        }
                    }
                }while(oldSupplier==null);
                transactionBuy=inputBuyItem(oldSupplier);
                toko.buy(transactionBuy);
                break;
        }
    }
    
    public static Supplier supplierBaru(Store toko){
        Supplier newSupplier;
        Scanner input=new Scanner(System.in);
        do{
            System.out.print("Nama\t:");
            String name=input.next();
            System.out.print("No.Telp\t:");
            String phone=input.next();
            System.out.print("Alamat\t:");
            String address=input.next();
            newSupplier=new Supplier(name, phone, address);

            System.out.println("Apakah data sudah benar?(y/n)");
        } while(!input.next().equals("y"));
        toko.addSupplier(newSupplier);
        return newSupplier;
    }
    
    public static TransactionBuy inputBuyItem(Supplier supplier){
        Scanner input=new Scanner(System.in);
        Products_Buy product_buy=new Products_Buy();
        TransactionBuy transctionBuy;
        System.out.println("\n"+product_buy.priceList());

        do{
            System.out.println("Jumlah barang pembelian:");

            System.out.print("Beras\t: ");        
            product_buy.addStock(new BerasBuy(input.nextInt()));
            System.out.print("Garam\t: ");
            product_buy.addStock(new GaramBuy(input.nextInt()));
            System.out.print("Gula\t: ");
            product_buy.addStock(new GulaBuy(input.nextInt()));
            System.out.print("Minyak\t: ");
            product_buy.addStock(new MinyakBuy(input.nextInt()));
            System.out.print("Terigu\t: ");
            product_buy.addStock(new TeriguBuy(input.nextInt()));

            System.out.println("Total:"+LocaleFormatting.currency(product_buy.getTotalPrice()));

            System.out.println("Pembayaran: ");
            int payment=input.nextInt();
            transctionBuy=new TransactionBuy(supplier, product_buy, payment);
            System.out.println(transctionBuy);

            System.out.print("Apakah data benar? (y/n):");
        } while(!input.next().equals("y"));
        
        return transctionBuy;
    }
    
    public static void daftarHarga(Store toko){
        System.out.println("Harga beli");
        System.out.println(toko.getBuyPrice());
        System.out.println("");
        System.out.println("Harga jual");
        System.out.println(toko.getSellPrice());
    }
    
}
