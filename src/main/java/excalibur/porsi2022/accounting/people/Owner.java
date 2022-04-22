/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting.people;

/**
 * Class for the owner with specified owner ID
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Owner extends People{
    
    private static int uniqueID=0;
    
    public Owner(String name, String phone, String address){
        super(name, phone, address);
        id=getUniqueID();
    }
    
    private static String getUniqueID(){
        return "own_"+String.format("%04d", uniqueID++);
    }
}
