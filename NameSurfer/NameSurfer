/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */
 
import acm.program.*;
 
import java.awt.event.*;
 
import javax.swing.*;
 
public class NameSurfer extends Program implements NameSurferConstants {
 
    private JTextField nameField;
    private JButton graphButton;
    private JButton clearButton;
    private NameSurferDataBase dataBase;
    private NameSurferGraph graph;
 
    /* Method: init() */
    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        graph = new NameSurferGraph();
        add(graph);
        graph.update();
        nameField = new JTextField(20);
        graphButton = new JButton("graph");
        clearButton = new JButton("clear");
        add(new JLabel("Name"), NORTH);
        add(nameField, NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);
        addActionListeners();
        nameField.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== nameField || e.getSource()==graphButton){
            if(nameField.getText().equals("0")) System.exit(0);
            if(nameField.getText().toLowerCase().equals("clear"))typedClear();
            String name = nameField.getText();
            if(dataBase.findEntry(name) != null){
                graph.addEntry(dataBase.findEntry(name));
            }
            graph.update();
            nameField.setText("");
        } else{
            graph.clear();
            graph.update();
        }
    }
     
    private void typedClear(){
        graph.clear();
        graph.update();
        nameField.setText("");
    }
}
