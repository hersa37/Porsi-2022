/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;
import excalibur.porsi2022.accounting.people.*;
import excalibur.porsi2022.accounting.*;
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
            System.out.println("1. Load toko\n2. Buat Toko baru");
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
                        if(toko==null){
                            System.out.println("Buat toko baru? (y/n)");
                            if(input.next().equals("y")){
                                toko=tokoBaru();
                                FileManagement.write(toko, toko.getFileName());
                            } 
                            else {
                                System.out.println("Lanjut? (y/n)");
                                if(input.next().equals("n")){
                                    System.exit(0);
                                }
                            }
                        }                        
                    } while(toko==null);
                    System.out.println(toko);
                    System.out.println("\nApakah benar? (y/n)");
                }while(!input.next().equals("y"));            
                break;
            case 2:
                toko=tokoBaru();
                FileManagement.write(toko, toko.getFileName());
        }
        //Toko is loaded
        do{
            int opsi;
            do{
                System.out.println("\n1. Penjualan\n"
                        + "2. Pembelian\n"
                        + "3. Daftar harga barang\n"
                        + "4. Data customer\n"
                        + "5. Data supplier\n"
                        + "6. Sejarah penjualan\n"
                        + "7. Sejarah pembelian");
                System.out.print("Input: ");
                opsi=input.nextInt();
                System.out.println("");
            } while (opsi<1 && opsi>5);

            switch(opsi){
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
                    dataCustomer(toko);
                    break;
                case 5:
                    System.out.println(toko.getSuppliers());
                    break;
                case 6:
                    dataCustomer(toko);
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
            int beras=input.nextInt();
            System.out.print("Garam\t: ");
            int garam=input.nextInt();
            System.out.print("Gula\t: ");
            int gula=input.nextInt();
            System.out.print("Minyak\t: ");
            int minyak=input.nextInt();
            System.out.print("Terigu\t: ");
            int terigu=input.nextInt();
            products_Sell.addStock(beras, garam, gula, minyak, terigu);

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
            int beras=input.nextInt();
            System.out.print("Garam\t: ");
            int garam=input.nextInt();
            System.out.print("Gula\t: ");
            int gula=input.nextInt();
            System.out.print("Minyak\t: ");
            int minyak=input.nextInt();
            System.out.print("Terigu\t: ");
            int terigu=input.nextInt();
            product_buy.addStock(beras, garam, gula, minyak, terigu);
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
    
    public static Store tokoBaru(){
        Scanner input=new Scanner(System.in);
        
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
        
        return new Store(storeName,pemilik);
    }
    
    public static void dataCustomer(Store toko){
        Scanner input=new Scanner(System.in);
        int inp;
        do{
            System.out.println("1. Tambah customer\n2. Hapus customer\n3. Edit customer\n4. Daftar customer\n5. Keluar");
            inp=input.nextInt();
            switch(inp){
                case 1:
                    System.out.println("Nama:");
                    String name=input.next();
                    System.out.println("No. Telp:");
                    String phone=input.next();
                    System.out.println("Alamat:");
                    String address=input.next();
                    
                    toko.addCustomer(new Customer(name, phone, address));
                    break;
                case 2:
                    System.out.println("Nama/ID customer: ");
                    toko.removeCustomer(input.next());
                    break;
                case 3:
                    Customer temp;
                    String check="n";
                    do{
                        System.out.println("Cari customer:");
                        System.out.print("Nama/ID: ");
                        temp=toko.findCustomer(input.next());
                        if(temp==null){
                            System.out.println("Customer tidak ditemukan");
                            System.out.println("Cari lagi? (y/n)");
                            check=input.next();
                            if(check.equals("n")){
                                break;
                            }
                        }                        
                    } while(check.equals("y"));
                    int i;
                    do{
                        System.out.println("Ganti atribut:");
                        System.out.println("1. Nama\n2. No. Telp\n3. Alamat\n4. ID");
                        i=input.nextInt();
                        switch(i){
                            case 1:
                                System.out.println("Nama baru:");
                                temp.setName(input.next());
                                break;
                            case 2:
                                System.out.println("No. Telp baru:");
                                temp.setName(input.next());
                                break;
                            case 3:
                                System.out.println("Alamat baru:");
                                temp.setName(input.next());
                                break;
                            case 4:
                                System.out.println("ID baru:");
                                temp.setName(input.next());
                                break;
                        }                        
                    }while(i>=1 && i<=4);
                    
                case 4:
                    System.out.println("Data Pelanggan\n==================================");
                    System.out.println(toko.getCustomers());
                    break;
                default:
            }
        }while(inp>=1 && inp<=4);
        
    }
    
}
