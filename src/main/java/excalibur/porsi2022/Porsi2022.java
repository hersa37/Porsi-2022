/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;

import excalibur.porsi2022.accounting.*;
import excalibur.porsi2022.accounting.people.Customer;
import excalibur.porsi2022.accounting.people.Owner;
import excalibur.porsi2022.accounting.people.Supplier;

import java.util.Scanner;

/**
 * @author echa
 */
public class Porsi2022 {

    public static void main(String[] args) {
        int inp;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("================================================" +
                    "\n                 Sistem Informasi               " +
                    "\n                   -excalibur-                  " +
                    "\n================================================");
            System.out.println("\n                      -Menu-                    ");
            System.out.println("1. Load Toko\n2. Buat Toko Baru");
            System.out.print("Input: ");
            inp = input.nextInt();
        } while (inp != 1 && inp != 2);
        Store toko = null;
        switch (inp) {
            case 1:
                do {
                    do {
                        System.out.println(" \t\t  # Load Toko #\n");
                        System.out.print("Cari nama toko\t:");
                        String namaToko = input.next();
                        toko = (Store) FileManagement.read(namaToko);
                        if (toko == null) {
                            System.out.print("Toko tidak ditemukan\n"
                                    + "Buat toko baru? (y/n):");
                            if (input.next().equals("y")) {
                                toko = tokoBaru();
                                FileManagement.write(toko, toko.getFileName());
                            } else {
                                System.out.print("Lanjut? (y/n):");
                                if (input.next().equals("n")) {
                                    System.exit(0);
                                }
                            }
                        }
                    } while (toko == null);
                    System.out.println(toko);
                    System.out.print("\nApakah benar? (y/n):");
                } while (!input.next().equals("y"));
                break;
            case 2:
                toko = tokoBaru();
                FileManagement.write(toko, toko.getFileName());
        }
        //Toko is loaded
        int opsi;
        do {
            do {
                System.out.println("\n                    -Menu Utama-                ");
                System.out.println("1. Penjualan\n"
                        + "2. Pembelian\n"
                        + "3. Daftar harga barang\n"
                        + "4. Data customer\n"
                        + "5. Data supplier\n"
                        + "6. Pembukuan\n"
                        + "7. Inventory\n"
                        + "0. Keluar");
                System.out.print("Input: ");
                opsi = input.nextInt();
                System.out.println();
            } while (opsi < 0 || opsi > 7);

            switch (opsi) {
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
                    dataSupplier(toko);
                    break;
                case 6:
                    pembukuan(toko);
                    break;
                case 7:
                    System.out.println("\n\t\t   # Inventory #\n");
                    System.out.print(toko.getInventory().toString("Invntory"));
                    break;
            }
        } while (opsi != 0);
    }

    public static Store tokoBaru() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\t  # Buat Toko Baru #\n");
        System.out.print("Nama toko\t:");
        String storeName = input.next();
        System.out.print("Nama pemilik\t:");
        String name = input.next();
        System.out.print("");
        System.out.print("No. telp\t\t:");
        String phone = input.next();
        System.out.print("Alamat toko\t:");
        String address = input.next();
        Owner pemilik = new Owner(name, phone, address);
        return new Store(storeName, pemilik);
    }

    public static void penjualan(Store toko) {
        Scanner input = new Scanner(System.in);
        int inp;
        do {
            System.out.println("\n\t\t   # Penjualan #\n");
            System.out.println("1. Customer baru"
                    + "\n2. Customer lama"
                    + "\n0. Kembali ke menu utama");
            System.out.print("Input: ");
            inp = input.nextInt();
            TransactionSell transactionSell;

            switch (inp) {
                case 1:
                    Customer newCustomer = customerBaru(toko);
                    transactionSell = inputSaleItems(toko, newCustomer);
                    if (transactionSell != null) {
                        toko.sell(transactionSell);
                        break;
                    } else {
                        System.out.println("Transaksi dibatalkan");
                        break;
                    }

                case 2:
                    Customer oldCustomer;
                    System.out.println("\n Customer Lama \n===============");
                    System.out.print("Cari nama/ID: ");
                    oldCustomer = toko.findCustomer(input.next());
                    if (oldCustomer != null) {
                        transactionSell = inputSaleItems(toko, oldCustomer);
                        if (transactionSell != null) {
                            toko.sell(transactionSell);
                            break;
                        } else {
                            System.out.println("Transaksi dibatalkan");
                            break;
                        }
                    } else {
                        System.out.println("Customer tidak ditemukan.");
                    }
                    break;
            }
        } while (inp == 1 || inp == 2);
    }

    public static Customer customerBaru(Store toko) {
        Customer newCustomer;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n Customer Baru \n===============");
            System.out.print("Nama\t:");
            String name = input.next();
            System.out.print("No.Telp\t:");
            String phone = input.next();
            System.out.print("Alamat\t:");
            String address = input.next();
            newCustomer = new Customer(name, phone, address);

            System.out.print("Apakah data sudah benar?(y/n):");
        } while (!input.next().equals("y"));
        toko.addCustomer(newCustomer);
        FileManagement.write(toko, toko.getFileName());
        return newCustomer;
    }

    public static TransactionSell inputSaleItems(Store toko, Customer customer) {
        Scanner input = new Scanner(System.in);
        Products_Sell products_Sell = new Products_Sell();
        TransactionSell transactionSell;
        System.out.println("\n" + products_Sell.priceList());
        String check = "n";
        do {
            System.out.println("\nJumlah barang penjualan:");

            System.out.print(" Beras\t: ");
            int beras = input.nextInt();
            System.out.print(" Garam\t: ");
            int garam = input.nextInt();
            System.out.print(" Gula\t: ");
            int gula = input.nextInt();
            System.out.print(" Minyak\t: ");
            int minyak = input.nextInt();
            System.out.print(" Terigu\t: ");
            int terigu = input.nextInt();
            products_Sell.setStock(beras, garam, gula, minyak, terigu);
            System.out.println("_________________________");
            System.out.println("Total\t: " + LocaleFormatting.currency(products_Sell.getTotalPrice()));
            if ((toko.isInStock(products_Sell))) {
                System.out.print("Pembayaran: ");
                int payment = input.nextInt();
                transactionSell = new TransactionSell(customer, products_Sell, payment);
                System.out.println(transactionSell);

                System.out.print("Apakah data benar? (y/n):");
                if (input.next().equals("y")) {
                    return transactionSell;
                }
            } else {
                System.out.print("Masukkan data ulang? (y/n):");
                check = input.next();
            }
        } while (check.equals("y"));
        return null;
    }

    public static void pembelian(Store toko) {
        Scanner input = new Scanner(System.in);
        int inp;
        do {
            System.out.println("\t\t   # Pembelian #\n");
            System.out.println("1. Supplier baru"
                    + "\n2. Supplier lama"
                    + "\n0. Kembali ke menu utama");
            System.out.print("Input: ");
            inp = input.nextInt();
            TransactionBuy transactionBuy;
            switch (inp) {
                case 1:
                    Supplier newSupplier = supplierBaru(toko);
                    transactionBuy = inputBuyItem(toko, newSupplier);
                    if (transactionBuy != null) {
                        toko.buy(transactionBuy);
                        break;
                    } else {
                        System.out.println("-Transaksi dibatalkan-");
                        break;
                    }
                case 2:
                    Supplier oldSupplier;

                    System.out.print("Cari nama/ID: ");
                    oldSupplier = toko.findSupplier(input.next());
                    if (oldSupplier != null) {
                        transactionBuy = inputBuyItem(toko, oldSupplier);
                        if (transactionBuy != null) {
                            toko.buy(transactionBuy);
                            break;
                        } else {
                            System.out.println("-Transaksi dibatalkan-");
                            break;
                        }
                    } else {
                        System.out.println("-Supplier tidak ditemukan-");
                    }
                    break;
            }
        } while (inp == 1 || inp == 2);
    }

    public static Supplier supplierBaru(Store toko) {
        Supplier newSupplier;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\nData Suplier Baru \n===============");
            System.out.print("Nama\t:");
            String name = input.next();
            System.out.print("No.Telp\t:");
            String phone = input.next();
            System.out.print("Alamat\t:");
            String address = input.next();
            newSupplier = new Supplier(name, phone, address);

            System.out.print("Apakah data sudah benar?(y/n):");
        } while (!input.next().equals("y"));
        toko.addSupplier(newSupplier);
        FileManagement.write(toko, toko.getFileName());
        return newSupplier;
    }

    public static TransactionBuy inputBuyItem(Store toko, Supplier supplier) {
        Scanner input = new Scanner(System.in);
        Products_Buy product_buy = new Products_Buy();
        TransactionBuy transactionBuy;
        System.out.println("\n" + product_buy.priceList());
        String check = "y";
        do {

            System.out.println("\nJumlah barang pembelian:");

            System.out.print("Beras\t: ");
            int beras = input.nextInt();
            System.out.print("Garam\t: ");
            int garam = input.nextInt();
            System.out.print("Gula\t: ");
            int gula = input.nextInt();
            System.out.print("Minyak\t: ");
            int minyak = input.nextInt();
            System.out.print("Terigu\t: ");
            int terigu = input.nextInt();
            product_buy.setStock(beras, garam, gula, minyak, terigu);
            System.out.println("Total\t:" + LocaleFormatting.currency(product_buy.getTotalPrice()));
            if (toko.isEnoughMoney(product_buy)) {
                System.out.print("Pembayaran: ");
                int payment = input.nextInt();
                transactionBuy = new TransactionBuy(supplier, product_buy, payment);
                System.out.println(transactionBuy);

                System.out.print("Apakah data benar? (y/n):");
                if (input.next().equals("y")) {
                    return transactionBuy;
                }
            } else {
                System.out.print("Masukkan data ulang? (y/n)");
                check = input.next();
            }
        } while (check.equals("y"));
        return null;
    }

    public static void daftarHarga(Store toko) {
        System.out.println("\n\t\t   # Daftar Harga Barang #\n");
        System.out.println("- Harga beli");
        System.out.println(toko.getBuyPrice());
        System.out.println("\n- Harga jual");
        System.out.println(toko.getSellPrice());
    }

    public static void pembukuan(Store toko) {
        int inp;
        do {
            System.out.println("\t\t   # Pembukuan #\n");
            System.out.println("1. Riwayat penjualan"
                    + "\n2. Riwayat pembelian"
                    + "\n3. Setor uang"
                    + "\n4. Tarik uang"
                    + "\n5. Saldo"
                    + "\n6. Riwayat penyetoran/penarikan"
                    + "\n0. Menu utama");
            Scanner input = new Scanner(System.in);
            System.out.print("Input: ");
            inp = input.nextInt();

            switch (inp) {
                case 1:
                    System.out.println("\n- Riwayat Penjualan");
                    System.out.println(toko.getAccountingBook().getSale());
                    break;
                case 2:
                    System.out.println("\n- Riwayat Pembelian");
                    System.out.println(toko.getAccountingBook().getPurchase());
                    break;
                case 3:
                    System.out.println("\n- Setor Uang");
                    System.out.print("Masukkan jumlah uang\t: ");
                    int uangM = input.nextInt();
                    toko.addMoney(uangM);
                    System.out.println("Saldo sekarang\t\t: " +
                            LocaleFormatting.currency(toko.getAccountingBook().getMoneyOwned()));
                    FileManagement.write(toko, toko.getFileName());
                    break;
                case 4:
                    int uangK;
                    String check;
                    do {
                        System.out.println("\n- Tarik Uang");
                        System.out.print("Masukkan jumlah uang\t: ");
                        uangK = input.nextInt();
                        if (toko.getAccountingBook().getMoneyOwned() >= uangK) {
                            toko.takeMoney(uangK);
                            System.out.println("Saldo sekarang\t\t: " +
                                    LocaleFormatting.currency(toko.getAccountingBook().getMoneyOwned()));
                            FileManagement.write(toko, toko.getFileName());
                            break;
                        } else {
                            System.out.println("Saldo tidak cukup. Sisa : "
                                    + LocaleFormatting.currency(toko.getAccountingBook().getMoneyOwned()));
                            System.out.print("Coba lagi? (y/n):");
                            check = input.next();
                        }

                    } while (check.equals("y"));

                    break;
                case 5:
                    System.out.println("\n- Saldo");
                    System.out.println("Saldo sekarang\t\t: " +
                            LocaleFormatting.currency(toko.getAccountingBook().getMoneyOwned()));
                    break;
                case 6:
                    System.out.println("\n- Riwayat penyetoran/penarikan");
                    System.out.println(toko.getAccountingBook().getOwnerCashFlowString());
                    break;
            }
        } while (inp >= 1 && inp <= 6);
    }

    public static void dataCustomer(Store toko) {
        Scanner input = new Scanner(System.in);
        int inp;
        do {
            System.out.println("\n\t\t   # Data Customer #\n");
            System.out.println("1. Tambah customer\n2. Hapus customer\n3. Edit customer\n4. Daftar customer\n5. Kembali");
            System.out.print("Input: ");
            inp = input.nextInt();
            switch (inp) {
                case 1:
                    System.out.println("\nTambah Customer \n===============");
                    System.out.print("Nama:");
                    String name = input.next();
                    System.out.print("No. Telp:");
                    String phone = input.next();
                    System.out.print("Alamat:");
                    String address = input.next();

                    toko.addCustomer(new Customer(name, phone, address));
                    FileManagement.write(toko, toko.getFileName());
                    break;
                case 2:
                    System.out.println("\nHapus Customer \n===============");
                    System.out.println("Nama/ID customer: ");
                    toko.removeCustomer(input.next());
                    FileManagement.write(toko, toko.getFileName());
                    break;
                case 3:
                    Customer temp;
                    String check = "n";
                    do {
                        System.out.println("\nEdit Customer \n===============");
                        System.out.println("Cari customer(nama/id) : ");
                        temp = toko.findCustomer(input.next());
                        if (temp != null) {
                            int i;
                            do {
                                System.out.println("\nGanti atribut:");
                                System.out.println("1. Nama\n"
                                        + "2. No. Telp\n"
                                        + "3. Alamat\n"
                                        + "4. ID\n"
                                        + "5. Kembali");
                                i = input.nextInt();
                                switch (i) {
                                    case 1:
                                        System.out.print("Nama baru\t\t:");
                                        temp.setName(input.next());
                                        break;
                                    case 2:
                                        System.out.print("No. Telp baru\t:");
                                        temp.setPhone(input.next());
                                        break;
                                    case 3:
                                        System.out.print("Alamat baru\t:");
                                        temp.setAddress(input.next());
                                        break;
                                    case 4:
                                        System.out.print("ID baru\t\t:");
                                        temp.setId(input.next());
                                        break;
                                }
                                FileManagement.write(toko, toko.getFileName());
                            } while (i >= 1 && i <= 4);
                        } else {
                            System.out.println("-Customer tidak ditemukan-");
                            System.out.print("Cari lagi? (y/n):");
                            check = input.next();
                            if (check.equals("n")) {
                                break;
                            }
                        }
                    } while (check.equals("y"));
                    if (check.equals("n")) {
                        break;
                    }
                    break;
                case 4:
                    System.out.println("\nDaftar Customer \n===============");
                    System.out.println(toko.listCustomer());
                    break;
                default:
            }
        } while (inp >= 1 && inp <= 4);

    }

    public static void dataSupplier(Store toko) {
        Scanner input = new Scanner(System.in);
        int inp;
        do {
            System.out.println("\n\t\t   # Data supplier #\n");
            System.out.println("1. Tambah Supplier\n"
                    + "2. Hapus supplier\n"
                    + "3. Edit supplier\n"
                    + "4. Daftar supplier\n"
                    + "5. Kembali");
            System.out.print("Input: ");
            inp = input.nextInt();
            switch (inp) {
                case 1:
                    System.out.println("\nTambah Supplier \n===============");
                    System.out.print("Nama\t\t: ");
                    String name = input.next();
                    System.out.print("No. Telp\t: ");
                    String phone = input.next();
                    System.out.print("Alamat\t: ");
                    String address = input.next();

                    toko.addSupplier(new Supplier(name, phone, address));
                    FileManagement.write(toko, toko.getFileName());
                    break;
                case 2:
                    System.out.println("\nHapus Supplier \n===============");
                    System.out.println("Nama/ID Supplier\t: ");
                    toko.removeSupplier(input.next());
                    FileManagement.write(toko, toko.getFileName());
                    break;
                case 3:
                    Supplier temp;
                    String check = "n";
                    do {
                        System.out.println("\nEdit Supplier \n===============");
                        System.out.println("Cari Supplier(nama/id) : ");
                        temp = toko.findSupplier(input.next());
                        if (temp != null) {
                            int i;
                            do {
                                System.out.println("\nGanti atribut:");
                                System.out.println("1. Nama\n"
                                        + "2. No. Telp\n"
                                        + "3. Alamat\n"
                                        + "4. ID\n"
                                        + "5. Kembali");
                                System.out.print("Input: ");
                                i = input.nextInt();
                                switch (i) {
                                    case 1:
                                        System.out.print("Nama baru\t\t:");
                                        temp.setName(input.next());
                                        break;
                                    case 2:
                                        System.out.print("No. Telp baru\t:");
                                        temp.setPhone(input.next());
                                        break;
                                    case 3:
                                        System.out.print("Alamat baru\t:");
                                        temp.setAddress(input.next());
                                        break;
                                    case 4:
                                        System.out.print("ID baru\t\t:");
                                        temp.setId(input.next());
                                        break;
                                }
                                FileManagement.write(toko, toko.getFileName());
                            } while (i >= 1 && i <= 4);
                        } else {
                            System.out.println("-Supplier tidak ditemukan-");
                            System.out.print("Cari lagi? (y/n):");
                            check = input.next();
                            if (check.equals("n")) {
                                break;
                            }
                        }
                    } while (check.equals("y"));
                    if (check.equals("n")) {
                        break;
                    }
                    break;
                case 4:
                    System.out.println("\nDaftar Supplier \n===============");
                    System.out.println(toko.listSupplier());
                    break;
                default:
            }
        } while (inp >= 1 && inp <= 4);
    }
}
