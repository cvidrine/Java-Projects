/*
 * File: Hangman.java
 * This file plays the Hangman game. 
 */
 
//import acm.graphics.*;
import acm.program.*;
import acm.util.*;
 
//import java.io.Console;
//import java.awt.*;
import java.util.ArrayList;
 
//import javax.xml.stream.events.Characters;
 
public class Hangman extends ConsoleProgram {
    //The lexicon used for the game
    private HangmanLexicon lexicon;
    //The canvas used for the game.
    private HangmanCanvas canvas;
    //rgen used to randomly choose a word from the lexicon
    private RandomGenerator rgen = RandomGenerator.getInstance();
    //The random word pulled from the lexicon.
    private String word;
    //The String that shows the players current progress.
    private String result;
    // Int that holds how many guesses the player has remaining.
    private int guesses;
    //ArrayList that holds all of the players correct guesses so that guessing the same correct letter twice doesn't take a turn away.
    private ArrayList<Character> correctGuessList = new ArrayList<Character>();
    /*
     * Builds and adds the canvas.
     */
    public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
    }
 
    /* 
     * Adds the lexicon, chooses a word, builds a string of dashes the length of the chosen word, initializes the guesses, then runs takeGuesses.
     */
    public void run() {
        bool playing = true;
        while(true){
          canvas.reset();
          lexicon = new HangmanLexicon();
          chooseWord();
          result = "";
          guesses = 8;
          for(int i=0; i<word.length(); i++){
              result = result + "-";
          }
          println("Lets Play Hangman!");
          takeGuesses();
          playing = playAgain();
    }
    /*
     * Grabs a random word from the lexicon to use.
     */
    private void chooseWord(){
        int index = rgen.nextInt(0, lexicon.getWordCount()-1);
        word = lexicon.getWord(index);
    }
 
    /*
     * While the player still has guesses left and hasn't completely guessed the word, takeGuesses runs a loop that shows them how their word looks and
     * how many turns they have left, then takes in their guess. If their guess is only one character, then it runs checkForMatch.
     */
    private void takeGuesses(){
        while (guesses != 0 && result.equals(word)==false){
            println("The word now looks like this: "+result);
            if (guesses >1){
                println("You have "+guesses+" turns left.");
            } else{
                println("You have "+guesses+" turn left! Don't kill him..");
            }
            String str = "";
            str = readLine("Your Guess: ");
            if (str.length()> 1 || str.equals("")){
                println("That guess is illegal. Please input a valid guess.");
            }else{
                char guess = str.charAt(0);
                guess = Character.toUpperCase(guess);
                checkForMatch(guess);
            }
            canvas.displayWord(result);
        }
        if (guesses ==0){
            println("You're completely hung");
            println("The word was: "+word);
            println("You lose.");
        } else if (result.equals(word)){
            println("You guessed the word: "+result);
            println("You win.");
        }
 
    }
    /*
     * Updates the result if the users guess was correct, or subtracts a guess if it was incorrect. If the user guesses a correct guess again, it 
     * functions as a correct guess
     */
    private void checkForMatch(char guess) {
        String previousResult = buildPreviousResult();
        for(int i=0; i<word.length(); i++){
            if (word.charAt(i)==guess){
                result = result.substring(0, i)+guess+result.substring(i+1);
            }
        } 
        if (result.equals(previousResult) && !correctGuessList.contains(guess)){
            guesses--;
            println("There are no "+guess+"'s in the word.");
            canvas.noteIncorrectGuess(guess);
        } else {
            println("That guess is correct");
            if(!correctGuessList.contains(guess)){
                correctGuessList.add(guess);
            }
        }
    }
    /*
     * Builds the previous result so that it can be checked against the result after the user guess. If they match, then the user's guess did not affect the
     * string.
     */
    private String buildPreviousResult() {
        String previousResult ="";
        for(int i=0; i<result.length(); i++){
            previousResult = previousResult+result.charAt(i);
        }
        return previousResult;
    }
    /*
     * Ask the player if they want to play again. If they do, the game is reset and restarts.
     */
    private bool playAgain(){
        int decision = readInt("Enter one to play again, or any other number to terminate the game.");
        if(decision == 1) {
            canvas.reset();  
            correctGuessList.clear();
            return true;
        }
        System.exit(0);
    }
 
 
}
