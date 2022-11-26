import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The powerup that spawns in the world
 * 
 * @author (Jaylen Cheung) 
 * @version (2.0: 01/22/2022)
 */
public class Powerup extends Actor
{
    //declaring the powerup's dimensions and image variables
    public static final int PU_WIDTH = GameWorld.WORLD_WIDTH / 40;
    public static final int PU_HEIGHT = PU_WIDTH;
    protected GreenfootImage image;
    
    //initializing constants
    public static final int LIFESPAN = 480; //how long the powerup lasts in the world
    public static final int DURATION = 600; //how long its effect lasts
    
    //declaring instance variables
    protected Player player;
    protected int timeLeft = LIFESPAN;
    
    /**
     * Powerup Constructor
     */
    public Powerup(){
        image = new GreenfootImage(PU_WIDTH + 1, PU_HEIGHT + 1);
    }
    
    /**
     * Act Method
     * 
     * Updates the powerup's timer and removes the powerup from the world when it hits 0
     */
    public void act(){
        timeLeft--;
        if (timeLeft == 0) getWorld().removeObject(this);
    }
    
    /**
     * Method to activate the powerup benefits
     */
    public void activate(){
        
    }
    
    /**
     * Method to deactivate the powerup benefits
     */
    public void deactivate(){
        
    }
    
    /**
     * Method to get the image of the powerup
     *
     * @return the powerup's image
     */
    public GreenfootImage getImage(){
        return null;
    }
    
    /**
     * Method to get the notification name of the powerup
     *
     * @return the powerup's notification name
     */
    public String toString(){
        return "";
    }
    
    /**
     * Method to get whether or not the powerup needs an icon that shows its timer
     *
     * @return true if the powerups needs an icon, false otherwise
     */
    public boolean needsIcon(){
        return true;
    }
    
    /**
     * Method to get the color of the powerup
     *
     * @return the powerup's color
     */
    public Color getColor(){
        return null;
    }
    
    /**
     * Method to draw the powerup
     *
     * @param width the width of the powerup
     * @param height the height of the powerup
     */
    public void draw(int width, int height){
        image.setColor(Color.BLACK);
        image.fillRect(0, 0, width, height);
        image.drawImage(getImage(), (image.getWidth() - getImage().getWidth()) / 2, (image.getHeight() - getImage().getHeight()) / 2);
        setImage(image);
    }
}
