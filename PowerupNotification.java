import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The notification that pops up on the screen when the player has picked up a powerup
 * 
 * @author (Jaylen Cheung) 
 * @version (2.0: 01/22/2022)
 */
public class PowerupNotification extends Actor
{   
    //declaring the powerup notification's dimensions and image variables
    private GreenfootImage image;
    public static final int PU_NOTIF_WIDTH = GameWorld.WORLD_WIDTH;
    public static final int PU_NOTIF_HEIGHT = GameWorld.WORLD_HEIGHT / 12;
    public static final Font PU_NOTIF_FONT = new Font("Courier New", true, true, PU_NOTIF_HEIGHT * 2 / 3);
    
    //initializing constants
    public static final int POPUP_TIME = 60;
    
    //declaring instance variables
    private int currTime = 0;
    
    /**
     * PowerupNotification Constructor
     *
     * @param powerup the powerup represented by the powerup notification
     */
    public PowerupNotification(Powerup powerup){
        //drawing the powerup notification
        image = new GreenfootImage(PU_NOTIF_WIDTH + 1, PU_NOTIF_HEIGHT + 1);
        drawPowerupNotification(powerup);
        setImage(image);
    }
    
    /**
     * Act Method
     * 
     * Updates the amount of time that the notification has been up, and clears the notification when it has reached its time
     */
    public void act() 
    {
        //clears the notification after it reached its time
        currTime++;
        if(currTime == POPUP_TIME) getWorld().removeObject(this);
    }
    
    /**
     * Method to draw the powerup notification
     *
     * @param powerup the powerup represented by the powerup notification
     */
    public void drawPowerupNotification(Powerup powerup){
        image.setColor(powerup.getColor());
        image.setFont(PU_NOTIF_FONT);
        image.setTransparency(150);
        image.fillRect(0, 0, image.getWidth(), image.getHeight());
        image.setTransparency(255);
        image.setColor(Color.BLACK);
        image.drawString(powerup.toString(), (image.getWidth() - (int)(powerup.toString().length() * PU_NOTIF_FONT.getSize() * 0.58)) / 2, (image.getHeight() + PU_NOTIF_FONT.getSize() / 2) / 2);
    }
}