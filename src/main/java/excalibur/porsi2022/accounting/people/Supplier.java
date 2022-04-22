package excalibur.porsi2022.accounting.people;

/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

/**
 * Class for supplier with its own supplier ID
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Supplier extends People{

    private static int uniqueID=0;
    
    public Supplier(String name, String phone, String address){
        super(name, phone, address);
        id=getUniqueID();
    }
    
    private static String getUniqueID(){
        return "sup_"+String.format("%04d",uniqueID++);
    }
    
}
