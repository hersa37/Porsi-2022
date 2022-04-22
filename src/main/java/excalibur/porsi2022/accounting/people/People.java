/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting.people;

import java.io.Serializable;

/**
 * A Class for different people used in the program
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class People implements Serializable{
    protected String id;
    protected String name;
    protected String phone;
    protected String address;

    protected People(String name, String phone, String address) {
        this.name=name;
        this.phone=phone;
        this.address=address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone=phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }
    
    @Override
    public String toString(){
        return "{Type: "+this.getClass().getSimpleName()
                + "; ID: "+id
                +"; Name: "+name
                +"; Phone: "+phone
                +"; Address: "+address
                +"}";
    }
    
    
}
