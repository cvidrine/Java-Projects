/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.
 */
 
 
import acm.graphics.*;
import java.awt.*;
import java.util.*;
 
public class FacePamphletCanvas extends GCanvas 
                    implements FacePamphletConstants {
     
    private GLabel name, noImage, status, friends;
    private GLabel applicationMessage= new GLabel("");
    private GRect emptyImage;
    private GImage image;     
 
     
    /** 
     * This method displays a message string near the bottom of the 
     * canvas.  Every time this method is called, the previously 
     * displayed message (if any) is replaced by the new message text 
     * passed in.
     */
    public void showMessage(String msg) {
        remove(applicationMessage);
        applicationMessage.setLabel(msg);
        applicationMessage.setFont(MESSAGE_FONT);
        add(applicationMessage, (getWidth()-applicationMessage.getWidth())/2, getHeight()-BOTTOM_MESSAGE_MARGIN);
    }
     
     
    /** 
     * This method displays the given profile on the canvas.  The 
     * canvas is first cleared of all existing items (including 
     * messages displayed near the bottom of the screen) and then the 
     * given profile is displayed.  The profile display includes the 
     * name of the user from the profile, the corresponding image 
     * (or an indication that an image does not exist), the status of
     * the user, and a list of the user's friends in the social network.
     */
    public void displayProfile(FacePamphletProfile profile) {
        removeAll();
        addName(profile);
        addImage(profile);
        addStatus(profile);
        addFriendsList(profile);
        add(applicationMessage, (getWidth()-applicationMessage.getWidth())/2,getHeight()- BOTTOM_MESSAGE_MARGIN);
        //applicationMessage(profile);
    }
 
 
    private void addName(FacePamphletProfile profile) {
        name = new GLabel(createName(profile.getName()));
        name.setFont(PROFILE_NAME_FONT);
        name.setColor(Color.BLUE);
        add(name, LEFT_MARGIN, TOP_MARGIN+name.getHeight());
    }
 
 
    private void addImage(FacePamphletProfile profile) {
        if(profile.getImage() == null){
            emptyImage = new GRect(LEFT_MARGIN, name.getY()+IMAGE_MARGIN, IMAGE_WIDTH, IMAGE_HEIGHT);
            add(emptyImage);
            noImage = new GLabel("No Image");
            noImage.setFont(PROFILE_IMAGE_FONT);
            add(noImage, LEFT_MARGIN +(IMAGE_WIDTH-noImage.getWidth())/2,emptyImage.getY()+(IMAGE_HEIGHT+noImage.getHeight())/2);
        } else{
            image = profile.getImage();
            image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
            add(image, LEFT_MARGIN, name.getY()+IMAGE_MARGIN);
        }
    }
 
 
    private void addStatus(FacePamphletProfile profile) {
        status = new GLabel("");
        if(profile.getStatus().equals("")){
            status.setLabel("No current status.");
        } else{
            status.setLabel(createName(profile.getName())+ " is " + profile.getStatus());
        }
        status.setFont(PROFILE_STATUS_FONT);
        add(status, LEFT_MARGIN, name.getY()+IMAGE_MARGIN+IMAGE_HEIGHT+STATUS_MARGIN+status.getHeight());
    }
 
 
    private void addFriendsList(FacePamphletProfile profile) {
        friends = new GLabel("Friends:", getWidth()/2,name.getY()+IMAGE_MARGIN);
        friends.setFont(PROFILE_FRIEND_LABEL_FONT);
        add(friends);
        double spacer = name.getY()+ IMAGE_MARGIN;
        Iterator<String> iterator = profile.getFriends();
        while(iterator.hasNext()){
            GLabel friend = new GLabel(createName(iterator.next()));
            friend.setFont(PROFILE_FRIEND_FONT);
            spacer+=friend.getHeight()+2;
            add(friend, getWidth()/2, spacer);
        }
    }
 
    //Creates the correct capitalization on each name (first, middle, last) before it is printed to the screen.
    private String createName(String name){
        String result = "";
        StringTokenizer tokenizer = new StringTokenizer(name);
        while(tokenizer.hasMoreTokens()){
            result = result+changeOneName(tokenizer.nextToken())+" ";
        }
         return result;
         
    }
    //Changes one Name to having an Upper case Letter to start and the rest Lower case.
    private String changeOneName(String name){
        if(name.length()==1) {
            return Character.toString(name.charAt(0)).toUpperCase();
        }
        return Character.toString(name.charAt(0)).toUpperCase()+ name.substring(1);
    }
 
     
}
