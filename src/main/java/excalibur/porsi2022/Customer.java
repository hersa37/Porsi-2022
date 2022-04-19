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
public class Customer {
    private String cust_id;
    private String cust_name;
    private String cust_address;
    private String cust_phone;
    private static int uniqueID=0;
    
    public Customer(String name, String address, String phone){
        cust_name=name;
        cust_address=address;
        cust_phone=phone;
        cust_id=getUniqueID();
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id=cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name=cust_name;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address=cust_address;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone=cust_phone;
    }
    
    private String getUniqueID(){
        return "cust_"+String.format("%03d", uniqueID++);
    }
}
