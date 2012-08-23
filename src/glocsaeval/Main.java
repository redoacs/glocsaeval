/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package glocsaeval;

import edu.unam.iimas.alignment.Alignment;
import edu.unam.iimas.alignment.NonSupportedAlignmentException;
import edu.unam.iimas.alignment.glocsa.core.EventCount;
import edu.unam.iimas.alignment.glocsa.core.EventCounter;
import edu.unam.iimas.alignment.glocsa.core.GlocsaParameters;
import edu.unam.iimas.alignment.glocsa.core.GlocsaRater;
import edu.unam.iimas.alignment.glocsa.core.GlocsaRating;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xaltonalli
 */
public class Main {
    
    private static final int N_ARGUMENTS = 2;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (args.length != N_ARGUMENTS) {
            throw new RuntimeException("Incorrect number of arguments: " + args.length + ", " + N_ARGUMENTS + " are expected.");
        }
        
        try {

            //Alignment inAligment = new Alignment(new FileReader(new File(args[1])), isAminoacids);
            Alignment inAligment = new Alignment(new FileReader(new File(args[0])));
            
            GlocsaParameters glocsaParameters = new GlocsaParameters();
            GlocsaRater rater = new GlocsaRater(glocsaParameters);
            GlocsaRating rating = rater.rate(inAligment);
            EventCount eventCount = EventCounter.countEvents(inAligment);
            
            System.out.println("alignment-file-rated: \t\t\t" + args[1]);
            System.out.println();
            System.out.println(rating);
            System.out.println(eventCount);
            
            //BufferedWriter bw = new BufferedWriter(new FileWriter(new File(args[2] + ".[" + rating.getRatingValue() + "].[" + eventCount.getTotalEvents() + "]")));
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(args[1])));
            
            bw.write("alignment-file-rated: " + args[0]);
            bw.newLine();
            bw.write(rating.toString());
            bw.newLine();
            bw.write(eventCount.toString());
            bw.newLine();
            
            bw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }catch (NonSupportedAlignmentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
