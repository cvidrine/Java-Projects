/*
 * File: Yahtzee.java
 */
 
import java.util.ArrayList;
 
import acm.io.*;
import acm.program.*;
import acm.util.*;
 
public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

    /* Private instance variables */
    private int nPlayers;
    private String[] playerNames;
    private YahtzeeDisplay display;
    private RandomGenerator rgen = new RandomGenerator();
    private int[] dice;
    private ArrayList<ArrayList<Integer>> scoreBoardArray = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> upperScores = new ArrayList<Integer>();
    private ArrayList<Integer> lowerScores = new ArrayList<Integer>();
    private CategoryCheckClass categoryChecker = new CategoryCheckClass(); 
 
    public static void main(String[] args) {
        new Yahtzee().start(args);
    }
 
    public void run() {
        IODialog dialog = getDialog();
        nPlayers = dialog.readInt("Enter number of players");
        playerNames = new String[nPlayers];
        for (int i = 1; i <= nPlayers; i++) {
            playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
        }
        display = new YahtzeeDisplay(getGCanvas(), playerNames);
        playGame();
    }
    /*
     * Builds array that keeps track of all player totals, an ArrayList of ArrayList that holds what categories each player
     * has already chosen, and two ArrayList that will hold each players upper and lower Scores.  Runs through all 13
     *  rounds for every player. Displays initial text and runs both rerolls and updates the total. Calls all methods necessary 
     *      to run game.
     */
    private void playGame() {
        /*
         * initializes arrays used throughout the whole game.
         */
        int[] totals = new int[nPlayers];
        for(int i=0; i<nPlayers; i++){
            ArrayList<Integer> previousCategories = new ArrayList<Integer>();
            scoreBoardArray.add(previousCategories);
        }
        for(int i=0; i<nPlayers; i++){
            upperScores.add(0);
            lowerScores.add(0);
        }
        /*
         * Runs through all the rounds of the game.
         */
        for(int i = 0; i<13; i++){
            for(int j=0; j<nPlayers; j++){
                display.printMessage(playerNames[j] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
                display.waitForPlayerToClickRoll(j+1);
                dice = rollDice();
                display.displayDice(dice);
                reroll();
                totals[j]+= categorySelection(j+1);
                display.updateScorecard(TOTAL, j+1, totals[j]);
            }
        }
        /*
         * End of Game code to determine upper bonus, update the total, and pick the winner
         */
        for(int i=0; i<nPlayers; i++){
            display.updateScorecard(UPPER_SCORE, i+1, upperScores.get(i));
            display.updateScorecard(LOWER_SCORE, i+1, lowerScores.get(i));
            if (upperScores.get(i) >= 63){
                display.updateScorecard(UPPER_BONUS, i+1, 35);
                totals[i]+=35;
                display.updateScorecard(TOTAL, i+1, totals[i]);
            } else{
                display.updateScorecard(UPPER_BONUS, i+1, 0);
            }
        }
        int winnerIndex = 0;
        for(int i=0; i<nPlayers; i++){
            if (totals[i]>totals[winnerIndex]){
                winnerIndex=i;
            }   
        }
        display.printMessage("Congratulations, " + playerNames[winnerIndex] + ", you're the winner with a total score of "+totals[winnerIndex] + "!");
    }
    /*
     * Creates 5 random values for the dice array. Assigns 5 first values to dice.
     */
    private int[] rollDice(){
        int[] diceNumbers = new int[5];
        for(int i=0; i<5; i++){
            diceNumbers[i] = rgen.nextInt(1, 6);
        }
        return diceNumbers;
    }
    /*
     * Allows the user to select which die to reroll and then reroll them twice.
     */
    private void reroll(){
        for(int i=0; i<2; i++){
            display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
            display.waitForPlayerToSelectDice();
            for(int j=0; j<5; j++){
                if(display.isDieSelected(j)){
                    rerollOneDie(j);
                }
            }
            display.displayDice(dice);
        }
    }
    /*
     * Rerolls one die at the index that the user selected. Is called for every dice the user selected.
     */
    private void rerollOneDie(int index){
        dice[index] = rgen.nextInt(1, 6);
    }
    /*
     * Adds the points to the scorecard depending on the category chosen, then returns the amount to add to the total. Also
     * adds to the value of the players lower Score and total Score.
     */
    private int categorySelection(int player){
        int sum = 0;
        while(true){
            display.printMessage("Select a category for this roll");
            int category = display.waitForPlayerToSelectCategory();
            if(category == UPPER_SCORE || category == UPPER_BONUS || category == LOWER_SCORE || category == TOTAL) continue;
            if(scoreBoardArray.get(player-1).indexOf(category)!= -1){
                display.printMessage("That category has already been chosen ya weenie!");
                pause(2000);
                continue;
            }
            if(category==ONES || category==TWOS || category==THREES || category==FOURS || category == FIVES || category == SIXES){
                sum = summationLoop(category);
                display.updateScorecard(category, player, sum);
                scoreBoardArray.get(player-1).add(category);
                upperScores.set(player-1, upperScores.get(player-1)+sum);
                return sum;
            } else if (category==THREE_OF_A_KIND && categoryChecker.checkCategory(dice, THREE_OF_A_KIND)){
                sum = addAll();
                display.updateScorecard(THREE_OF_A_KIND, player, sum);
                scoreBoardArray.get(player-1).add(category);
                lowerScores.set(player-1, lowerScores.get(player-1)+sum);
                return sum;
            } else if (category==FOUR_OF_A_KIND && categoryChecker.checkCategory(dice, FOUR_OF_A_KIND)){
                sum = addAll();
                display.updateScorecard(FOUR_OF_A_KIND, player, sum);
                scoreBoardArray.get(player-1).add(category);
                lowerScores.set(player-1, lowerScores.get(player-1)+sum);
                return sum;
            } else if (category==FULL_HOUSE && categoryChecker.checkCategory(dice, FULL_HOUSE)){
                display.updateScorecard(FULL_HOUSE, player, 25);
                scoreBoardArray.get(player-1).add(category);
                lowerScores.set(player-1, lowerScores.get(player-1)+sum);
                return 25;
            } else if (category==SMALL_STRAIGHT && categoryChecker.checkCategory(dice, SMALL_STRAIGHT)){
                display.updateScorecard(SMALL_STRAIGHT, player, 30);
                scoreBoardArray.get(player-1).add(category);
                lowerScores.set(player-1, lowerScores.get(player-1)+sum);
                return 30;
            } else if (category==LARGE_STRAIGHT && categoryChecker.checkCategory(dice, LARGE_STRAIGHT)){
                display.updateScorecard(LARGE_STRAIGHT, player, 40);
                scoreBoardArray.get(player-1).add(category);
                lowerScores.set(player-1, lowerScores.get(player-1)+sum);
                return 40;
            } else if (category==YAHTZEE && categoryChecker.checkCategory(dice, YAHTZEE)){
                display.updateScorecard(YAHTZEE, player, 50);
                scoreBoardArray.get(player-1).add(category);
                lowerScores.set(player-1, lowerScores.get(player-1)+sum);
                return 50;
            } else if(category == CHANCE){
                sum = addAll();
                display.updateScorecard(CHANCE, player, sum);
                scoreBoardArray.get(player-1).add(category);
                lowerScores.set(player-1, lowerScores.get(player-1)+sum);
                return sum;
            } else {
                display.updateScorecard(category, player, 0);
                scoreBoardArray.get(player-1).add(category);
                return 0;
            }
        }
    }
    /*
     * Adds all the dice together. Used for the categories chance, four of a kind, and three of a kind.
     */
    private int addAll(){
        int result = 0;
        for (int i=0; i<5; i++){
            result+=dice[i];
        }
        return result;
    }
 
    /*
     * Does the actual summation for the given case in the addSpecific method.
     */
    private int summationLoop(int category){
        int result = 0;
        for (int i=0; i<5; i++){
            if(dice[i]==category){
                result+=dice[i];
            }
        }
        return result;
    }
 
}
