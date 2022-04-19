package excalibur.porsi2022;

/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Supplier {
    private String sup_id;
    private String sup_name;
    private String sup_phone;
    private static int uniqueID=0;
    
    public Supplier(String name, String phone){
        sup_name=name;
        sup_phone=phone;
        sup_id=getUniqueID();
    }

    public String getSup_id() {
        return sup_id;
    }

    public void setSup_id(String sup_id) {
        this.sup_id=sup_id;
    }

    public String getSup_name() {
        return sup_name;
    }

    public void setSup_name(String sup_name) {
        this.sup_name=sup_name;
    }

    public String getSup_phone() {
        return sup_phone;
    }

    public void setSup_phone(String sup_phone) {
        this.sup_phone=sup_phone;
    }
    
    private String getUniqueID(){
        return "sup_"+String.format("%03d",uniqueID++);
    }
    
    @Override
    public String toString(){
        return "{sup_id: "+sup_id
                +"; sup_name: "+sup_name
                +"; sup_phone: "+sup_phone+"}";
    }
}
