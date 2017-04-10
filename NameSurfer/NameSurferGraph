/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */
 
import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
 
public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
 
    private ArrayList<NameSurferEntry> graphedNames = new ArrayList<NameSurferEntry>();
    private int colorCounter;
 
    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        colorCounter=0;
    }
 
 
    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        graphedNames.clear();
    }
 
 
    /* Method: addEntry(entry) */
    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        graphedNames.add(entry);
    }
 
 
    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        int width = this.getWidth();
        int height = this.getHeight();
        drawGrid(width, height);
        createDecadeLabels(width, height);
        drawLines(width, height);
    }
    /*
     * Draws all the lines on the graph, leaving margin space.
     */
    private void drawGrid(int width, int height){
        int space = width/11;
        int xLocation = space;
        //Draws the vertical lines
        for(int i =0; i<10; i++){
            GLine line = new GLine(xLocation, 0, xLocation, height);
            add(line);
            xLocation+=space;
        }
        //horizontal line at the top of the screen
        GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE);
        add(topLine);
        //horizontal line at the bottom of the screen
        GLine bottomLine = new GLine(0, height-GRAPH_MARGIN_SIZE, width, height-GRAPH_MARGIN_SIZE);
        add(bottomLine);
    }
/*
 * creates all of the decade labels for the bottom of the screen.
 */
    private void createDecadeLabels(int width, int height){
        int space = width/11;
        int xLocation = 2;
        int labelText = START_DECADE;
        for(int i=0; i<NDECADES; i++){
            GLabel label = new GLabel(Integer.toString(labelText));
            add(label, xLocation, height);
            xLocation+=space;
            labelText+=10;
        }
    }
    /*
     * Runs the method to draw a graph for each name that has been entered.
     */
    private void drawLines(int width, int height){
        for(int i=0; i<graphedNames.size(); i++){
            drawOneGraph(width, height, i);
        }
    }
    /*
     * Draws the Lines and labels for one entries graph.
     */
    private void drawOneGraph(int width, int height, int index){
        int space = width/11;
        int xStart = 0;
        int xEnd = space;
        double rankLocation = height/1001.0;
        NameSurferEntry chosenName = graphedNames.get(index);
        for(int i=0; i<NDECADES-1; i++){
            double yStart = chooseY(rankLocation, chosenName, i);
            double yEnd = chooseY(rankLocation, chosenName, i+1);
            drawLabel(chosenName, xStart, yStart, chooseColor(index+colorCounter), i);
            GLine segment = new GLine(xStart, yStart, xEnd, yEnd);
            segment.setColor(chooseColor(index+colorCounter));
            add(segment);
            xStart+=space;
            xEnd+=space;
        }
    }
    /* 
     * Chooses the color for the graphed line.
     */
    private Color chooseColor(int index){
        int indexRemainder = (index)%4;
        switch(indexRemainder){
        case 0: return Color.BLACK;
        case 1: return Color.BLUE;
        case 2: return Color.RED;
        case 3: return Color.MAGENTA;
        }
        return Color.BLACK;
    }
    
    /*
     * Draws the name and rank label for each decade depending on if the rank is 0 or not.
     */
    private void drawLabel(NameSurferEntry chosenName, int xLocation, double yLocation, Color color, int decade){
        GLabel label;
        if(yLocation==this.getHeight()-GRAPH_MARGIN_SIZE){
            label = new GLabel(chosenName.getName()+ " *");
        }else{
            label = new GLabel(chosenName.getName()+" "+ chosenName.getRank(decade));
        }
        label.setColor(color);
        add(label, xLocation, yLocation);
    }
/*
 * Chooses the Y-value of the ends of the line segment depending on if the rank is 0 or another number.
 */
    private double chooseY(double rankLocation, NameSurferEntry chosenName, int index){
        if (chosenName.getRank(index) == 0){
            return this.getHeight()-GRAPH_MARGIN_SIZE;
        } else{
            return rankLocation*chosenName.getRank(index)+GRAPH_MARGIN_SIZE;
        }
    }
 
 
    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) { }
    public void componentMoved(ComponentEvent e) { }
    public void componentResized(ComponentEvent e) { update(); }
    public void componentShown(ComponentEvent e) { }
}
