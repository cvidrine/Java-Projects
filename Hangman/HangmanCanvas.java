/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */
 
import acm.graphics.*;
 
public class HangmanCanvas extends GCanvas {
 
/** Resets the display so that only the scaffold appears */
    public void reset() {
        removeAll();
        incorrect = "";
        bodyPart =0;
        createScaffold();
    }
 
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
    public void displayWord(String word) {
        if(getElementAt(15, getHeight()-50)!=null){
        remove(wordDisplay);
        }
        wordDisplay = new GLabel(word);
        wordDisplay.setFont("Courier New-bold-24");
        add(wordDisplay, 15, getHeight()-50);
    }
 
/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
    public void noteIncorrectGuess(char letter) {
        incorrect = incorrect+letter;
        GLabel incorrectGuesses = new GLabel(incorrect);
        incorrectGuesses.setFont("Courier New-bold-20");
        add(incorrectGuesses, 15, getHeight()-25);
        bodyPart++;
        addBodyPart(bodyPart);
    }
    /*
     * Builds the scaffold
     */
    private void createScaffold(){
        GLine scaffold = new GLine(getWidth()/2-BEAM_LENGTH, (getHeight()+SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER, getWidth()/2-BEAM_LENGTH,(getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER);
        add(scaffold);
        GLine beam = new GLine(getWidth()/2-BEAM_LENGTH,(getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER, getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER);
        add(beam);
        GLine rope = new GLine(getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER, getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH);
        add(rope);
    }
    /*
     * Adds another body part every time the player has an incorrect guess.
     */
    private void addBodyPart(int bodyPart){
        if (bodyPart ==1){
            //The head
            GOval head = new GOval (HEAD_RADIUS*2, HEAD_RADIUS*2);
            add(head, getWidth()/2-HEAD_RADIUS, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH);
        } else if(bodyPart==2){
            //The body
            GLine body = new GLine(getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS, getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH);
            add(body);
        } else if (bodyPart==3){
            //The Left Arm
            GLine upperLeftArm = new GLine(getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD, getWidth()/2-UPPER_ARM_LENGTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD);
            GLine lowerLeftArm = new GLine(getWidth()/2-UPPER_ARM_LENGTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD, getWidth()/2-UPPER_ARM_LENGTH,(getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
            add(upperLeftArm);
            add(lowerLeftArm);
        } else if (bodyPart==4){
            //The Right Arm
            GLine upperRightArm = new GLine(getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD, getWidth()/2+UPPER_ARM_LENGTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD);
            GLine lowerRightArm = new GLine(getWidth()/2+UPPER_ARM_LENGTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD, getWidth()/2+UPPER_ARM_LENGTH,(getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
            add(upperRightArm);
            add(lowerRightArm);
        } else if (bodyPart==5){
            //The left Leg
            GLine leftHip = new GLine(getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH, getWidth()/2-HIP_WIDTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH);
            GLine leftLeg = new GLine(getWidth()/2-HIP_WIDTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH,getWidth()/2-HIP_WIDTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
            add(leftHip);
            add(leftLeg);
        } else if (bodyPart==6){
            //The right leg
            GLine rightHip =new GLine(getWidth()/2, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH, getWidth()/2+HIP_WIDTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH);
            GLine rightLeg = new GLine(getWidth()/2+HIP_WIDTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH,getWidth()/2+HIP_WIDTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
            add(rightHip);
            add(rightLeg);
        } else if (bodyPart==7){
            //The left Foot
            GLine leftFoot = new GLine(getWidth()/2-HIP_WIDTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH,getWidth()/2-HIP_WIDTH-FOOT_LENGTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
            add(leftFoot);
        } else if (bodyPart==8){
            //The Right Foot
            GLine rightFoot = new GLine(getWidth()/2+HIP_WIDTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH,getWidth()/2+HIP_WIDTH+FOOT_LENGTH, (getHeight()-SCAFFOLD_HEIGHT)/2-SCAFFOLD_OFF_CENTER+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
            add(rightFoot);
        }
    }
 
/* Constants for the simple version of the picture (in pixels) */
    private static final int SCAFFOLD_HEIGHT = 360;
    private static final int BEAM_LENGTH = 144;
    private static final int ROPE_LENGTH = 18;
    private static final int HEAD_RADIUS = 36;
    private static final int BODY_LENGTH = 144;
    private static final int ARM_OFFSET_FROM_HEAD = 28;
    private static final int UPPER_ARM_LENGTH = 72;
    private static final int LOWER_ARM_LENGTH = 44;
    private static final int HIP_WIDTH = 36;
    private static final int LEG_LENGTH = 108;
    private static final int FOOT_LENGTH = 28;
    private static final int SCAFFOLD_OFF_CENTER = 50;
    private String incorrect = "";
    private GLabel wordDisplay;
    private int bodyPart =0;
 
}
