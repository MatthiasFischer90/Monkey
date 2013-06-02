
/* There is a monkey which can walk around on a planar grid.
 * The monkey can move one space at a time left, right, up or down. That is,
 * from (x, y) the monkey can go to (x+1, y), (x-1, y), (x, y+1), and (x, y-1).
 * Points where the sum of the digits of the absolute value of the x coordinate
 * plus the sum of the digits of the absolute value of the y coordinate are
 * lesser than or equal to 19 are accessible to the monkey. For example,
 * the point (59, 79) is inaccessible because 5 + 9 + 7 + 9 = 30, which is
 * greater than 19. Another example: the point (-5, -7) is accessible because
 * abs(-5) + abs(-7) = 5 + 7 = 12, which is less than 19. How many points can
 * the monkey access if it starts at (0, 0), including (0, 0) itself? */

package monkey;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthias Fischer
 */
public class Monkey
{

    public static void main(String[] args)
    {
        final int MAX_SUM = 19;
        
        // Umlenkung des Outputs
        try {
            PrintStream out = new PrintStream(new FileOutputStream("outputs\\output_" + MAX_SUM + ".txt"));
            System.setOut(out);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Monkey.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
         * Die Nullzeile wird so lange durchlaufen, bis ein Punkt(X, 0)
         * erreicht wird, bei dem die Quersumme von X groesser als MAX_SUM ist.
         * Der Wert dieses X ist nun der Wert, bis zu dem jede Zeile und jede
         * Spalte durchlaufen wird, da unmoeglich hinter einem Punkt(X, >0)
         * bzw. Punkt(>0, Y) ein erreichbarer Punkt sein kann.
         */
        int solution = 0;
        boolean endOfLineReached = false;
        int numberOfLines = 0;
        
        while (endOfLineReached == false) {
            String s = String.valueOf(numberOfLines);
            
            int val = determineValue(s);
            
            if (val <= MAX_SUM) {
                numberOfLines++; 
            } else {
                numberOfLines--;
                endOfLineReached = true;
            }  
        }
        solution += numberOfLines;
        
        // Zeilen
        for (int row = 1; row <= numberOfLines; row++) {
            // Spalten
            for (int column = 1; column <= (numberOfLines - row); column++)
            {
                String rowString = String.valueOf(row);
                String columnString = String.valueOf(column);
                String s = rowString + columnString;
                
                int val = determineValue(s);
                
                if (val <= MAX_SUM) {
                    System.out.print('.');
                    solution ++;
                } else {
                   System.out.print('X');
                }
            }
            System.out.println();
        }
        /* Jetzt steht das Ergebnis fuer eine Nullzeile und einen Quadranten
         * fest. Um auf die endgueltige Loesung zu kommen, muss das jetzige
         * Ergebnis noch mit 4 multipliziert werden und anschliessend das
         * Ergebnis um 1 fuer den Punkt(0, 0) erhoeht werden.
         */
        solution *= 4;
        solution ++;
        
        System.out.println(solution);
    }
    
    public static int determineValue(String s)
    {
        int val = 0;
        for (int l = 0; l < s.length(); l++) {
            val += Character.getNumericValue(s.charAt(l));
        }
        return val;
    }
}
