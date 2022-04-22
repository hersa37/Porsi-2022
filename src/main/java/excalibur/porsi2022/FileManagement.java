/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class FileManagement {
    
    public static void write(Object object, String fileName){
        try{
            FileOutputStream f=new FileOutputStream(new File(fileName));
            ObjectOutputStream o=new ObjectOutputStream(f);
            o.writeObject(object);
            
            System.out.println("File saved as "+"\""+fileName+"\"");
            o.close();
            f.close();
        } catch(FileNotFoundException e){
            
        } catch(IOException e){
            System.out.println("Error initializing stream");
        }
        
    }
    
    public static Object read(String fileName){
        try{
            FileInputStream f=new FileInputStream(fileName);
            ObjectInputStream o=new ObjectInputStream(f);
            
            Object object= o.readObject();
            return object;
        } catch(FileNotFoundException e){
            System.out.println("File not found");
        } catch(IOException e){
            System.out.println("Error initializing stream");
        } catch(ClassNotFoundException e){
            System.out.println("Class not found");
        }
        
       return null; 
    }
}
