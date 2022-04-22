/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package inheritence;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mobil mobil=new Mobil();
        
        mobil.sound();
        
        Motor motor=new Motor();
        motor.sound();
        
       Mobil[] m=new Mobil[2];
       Kendaraan[] k=new Kendaraan[2];
       k[0]=mobil;
       k[1]=motor;
    }

}
