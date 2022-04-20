/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Inventory {
    private Product product;
    private Stock[] stock;
    private Product[] products;
    
    public Inventory(){
        stock=new Stock[ProductType.values().length];
        for(int i=0;i<stock.length;i++){
            stock[i]=new Stock(new Product(ProductType.values()[i]),0);
        }
    }
    
    public Stock[] getStock(){
        return stock;
    }
    
    public void addStock(ProductType product, int amount){
        for(Stock s:stock) {
            if(s.getProduct().getType()==product) {
                s.addAmount(amount);
            }
        }
    }
    
    public void reduceStock(ProductType product, int amount){
        for(Stock s:stock){
            if(s.getProduct().getType()==product){
                s.reduceAmount(amount);
            }
        }
    }
    
    public Product[] getProducts(){
        return products;
    }
    
    public Product checkStock (String id){
        for(int i=0; i<products.length;i++){
            if(product.getProductId().equalsIgnoreCase(id)){
            }
        }
    return null;
    }
}
