
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains the lexicon that will be used for the game.
 */
 
import java.io.*;
import java.util.ArrayList;
 
import acm.util.*;
 
public class HangmanLexicon {
 
    private ArrayList<String> lexiconArray = new ArrayList<String>();
    public HangmanLexicon(){
        try {
            BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
            while (true){
                String line = rd.readLine();
                if (line==null) break;
                lexiconArray.add(line);
            }
            rd.close();
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            throw new ErrorException(ex);
        }
    }
 
    /** Returns the number of words in the lexicon. */
    public int getWordCount() {
        return lexiconArray.size();
    }
 
    /** Returns the word at the specified index. */
    public String getWord(int index) {
        return lexiconArray.get(index);
    };
}
