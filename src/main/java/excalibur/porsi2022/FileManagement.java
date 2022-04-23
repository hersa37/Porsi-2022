/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;

import java.io.*;

/**
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class FileManagement {

    public static void write(Object object, String fileName) {
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(object);

            System.out.println("**File saved as " + "\"" + fileName + "\"");
            o.close();
            f.close();
        } catch (FileNotFoundException ignored) {

        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

    }

    public static Object read(String fileName) {
        try {
            FileInputStream f = new FileInputStream(fileName);
            ObjectInputStream o = new ObjectInputStream(f);

            return o.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }

        return null;
    }
}
