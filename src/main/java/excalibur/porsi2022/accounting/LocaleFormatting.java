/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package excalibur.porsi2022.accounting;

import java.text.*;
import java.util.Locale;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class LocaleFormatting {
    private LocaleFormatting(){
        
    }
    
    public static String currency(int currency){
        Locale locale=new Locale("id", "ID");
        NumberFormat format=NumberFormat.getCurrencyInstance(locale);
        return format.format(currency);
    }
}
