/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excalibur.porsi2022;

/**
 *
 * @author asus
 */
public class Store {
    private String owner;
    private Accounting books;
    private String booksFileName;
    private String inventoryFileName;
    
    public Store(){
    
    }
    
    public void setOwner(String owner){
        this.owner=owner;
    }
    
    public String getOwner(){
        return owner;
    }
    
    public void setBooks(Accounting books){
        this.books=books;
    }
    
    public Accounting getBooks(){
        return books;
    }
    
    public void setBooksFileName(String booksFileName){
        this.booksFileName=booksFileName;
    }
    
    public String getBooksFileName(){
        return booksFileName;
    }
    
    public void setInventoryFileName(String inventoryFileName){
        this.inventoryFileName=inventoryFileName;
    }
    
    public String getInventoryFileName(){
        return inventoryFileName;
    }
}
