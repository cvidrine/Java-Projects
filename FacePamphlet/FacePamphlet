/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */
 
import acm.program.*;
import acm.graphics.*;
import acm.util.*;
 
import java.awt.event.*;
 
import javax.swing.*;
 
public class FacePamphlet extends Program 
implements FacePamphletConstants {
 
    private JTextField nameField, statusField, pictureField, addFriendField;
    private JButton addButton, deleteButton, lookupButton;
    private JButton changeStatusButton, changePictureButton, addFriendButton;
    private FacePamphletDatabase database;
    private FacePamphletProfile currentProfile=null;
    private FacePamphletCanvas canvas;
 
     
    /**
     * This method has the responsibility for initializing the 
     * interactors in the application, and taking care of any other 
     * initialization that needs to be performed.
     */
    public void init() {
        addNorth();
        addWest();
        statusField.addActionListener(this);
        pictureField.addActionListener(this);
        addFriendField.addActionListener(this);
        addActionListeners();
        database = new FacePamphletDatabase();
        canvas = new FacePamphletCanvas();
        add(canvas);
    }
 
    private void addNorth() {
        add(new JLabel("Name"), NORTH);
        add(nameField = new JTextField(TEXT_FIELD_SIZE), NORTH);
        add(addButton = new JButton("Add"), NORTH);
        add(deleteButton = new JButton("Delete"), NORTH);
        add(lookupButton = new JButton("Lookup"), NORTH);   
    }
 
    private void addWest() {
        add(statusField = new JTextField(TEXT_FIELD_SIZE), WEST);
        add(changeStatusButton = new JButton("Change Status"), WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(pictureField = new JTextField(TEXT_FIELD_SIZE), WEST);
        add(changePictureButton = new JButton("Change Picture"), WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(addFriendField = new JTextField(TEXT_FIELD_SIZE), WEST);
        add(addFriendButton = new JButton("Add Friend"), WEST);
    }
 
 
 
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add")) addButtonClicked();
        if(e.getActionCommand().equals("Delete")) deleteButtonClicked();
        if(e.getActionCommand().equals("Lookup")) lookupButtonClicked();
        if(e.getActionCommand().equals("Change Status") || e.getSource() == statusField) changeStatusButtonClicked();
        if(e.getActionCommand().equals("Change Picture") || e.getSource()== pictureField) changePictureButtonClicked();
        if(e.getActionCommand().equals("Add Friend") || e.getSource()==addFriendField) addFriendButtonClicked();
    }
 
    private void addButtonClicked() {
        if(!nameField.getText().equals("")){
            String newPerson = nameField.getText();
            FacePamphletProfile profile = new FacePamphletProfile(newPerson);
            if(!database.containsProfile(newPerson)){
                database.addProfile(profile);
                canvas.showMessage("New Profile Created");
                currentProfile = profile;
            } else{
                canvas.showMessage("This profile already exist");
                currentProfile = database.getProfile(newPerson);
            }
            canvas.displayProfile(currentProfile);
        }
 
    }
 
    private void deleteButtonClicked() {
        if(!nameField.getText().equals("")){
            String deletedPerson = nameField.getText().toLowerCase();
            if(database.containsProfile(deletedPerson)){
                database.deleteProfile(deletedPerson);
                canvas.removeAll();
                canvas.showMessage("Profile of " + deletedPerson + " deleted");
            } else{
                canvas.showMessage("A profile with the name " + deletedPerson+ " does not exist");
            }
            currentProfile = null;
        }
 
    }
 
    private void lookupButtonClicked() {
        if(!nameField.getText().equals("")){
            String searchedName = nameField.getText().toLowerCase();
            if(database.containsProfile(searchedName)){
                //println("Lookup: " + database.getProfile(searchedName).toString());
                canvas.showMessage("Displaying " + searchedName);
                currentProfile = database.getProfile(searchedName);
                canvas.displayProfile(currentProfile);
            }else{
                canvas.removeAll();
                canvas.showMessage("A profile with the name "+ searchedName + " does not exist.");
                currentProfile = null;
            }
        }
 
    }
 
    private void changeStatusButtonClicked() {
        if(!statusField.getText().equals("")){
            if(currentProfile != null){
                currentProfile.setStatus(statusField.getText());
                canvas.displayProfile(currentProfile);
                canvas.showMessage("Status updated to " + statusField.getText());
            }
            else{
                canvas.showMessage("Select a profile before you change a status.");
            }
            if(statusField.getText().equals("0")) System.exit(0);
        }
         
    }
 
    private void changePictureButtonClicked() {
        if(!pictureField.getText().equals("")){
            GImage image = null;
            try {
                image = new GImage(pictureField.getText());
            } catch (ErrorException ex) {
                canvas.showMessage("Unable to open image file " + pictureField.getText());
            }
            if(currentProfile == null){
                canvas.showMessage("Select a profile before you change a status.");
                } else if (image != null){  
                    currentProfile.setImage(image);
                    canvas.displayProfile(currentProfile);
                    canvas.showMessage("Picture updated");
                }
        }   
    }
 
    private void addFriendButtonClicked() {
        if(!addFriendField.getText().equals("")){
            String addedFriend = addFriendField.getText().toLowerCase();
            if(currentProfile == null){
                canvas.showMessage("Select a profile to add a friend to.");
            } else if(!database.containsProfile(addedFriend)){
                canvas.showMessage(addedFriend+ " does not exist.");
            } else if(currentProfile.addFriend(addedFriend)) {
                database.getProfile(addedFriend).addFriend(currentProfile.getName());
                canvas.showMessage(addedFriend + " added as a friend.");
                canvas.displayProfile(currentProfile);
            } else if (currentProfile.getName().equals(addedFriend.toLowerCase())){
                canvas.showMessage("You can't add yourself as a friend weenie.");
            } else{
                canvas.showMessage(currentProfile.getName()+" already has "+addedFriend+" as a friend.");
            }
        }
    }
 
}
