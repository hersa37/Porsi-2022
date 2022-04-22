/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;

import excalibur.porsi2022.accounting.people.Owner;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Store nw=new Store("Echa", new Owner("s", "ds", "Sad"));
        System.out.println(nw.getInventory().toString());
    }

}
