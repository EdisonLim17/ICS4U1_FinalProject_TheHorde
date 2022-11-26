import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The enemy's blood splatter, appears for a few seconds after the enemy dies
 * 
 * @author (Edison Lim) 
 * @version (2.0: 01/22/2022)
 */
public class BloodSplatter extends Actor
{
    //declaring the blood splatter's dimensions and image variables
    private static GreenfootImage image = new GreenfootImage("BloodSplatter.png");
    public static final int BLOOD_SPLATTER_WIDTH = StrongEnemy.STR_WIDTH;
    public static final int BLOOD_SPLATTER_HEIGHT = BLOOD_SPLATTER_WIDTH;
    
    //initializing constants
    public static final int STARTING_TRANSPARENCY = 200;
    public static final int FADE_OUT_SPEED = 5; //decrease in transparency per act
    
    //declaring instance variables
    private int transparency = STARTING_TRANSPARENCY;
    
    /**
     * BloodSplatter Constructor
     */
    public BloodSplatter(){
        //setting the blood splatter's image
        image.scale(BLOOD_SPLATTER_WIDTH, BLOOD_SPLATTER_HEIGHT);
        setImage(image);
    }
    
    /**
     * Act Method
     * 
     * Reduces transparency to apply fade out effect, and removes the object when it is transparent
     */
    public void act() 
    {
        //fade out effect
        transparency -= FADE_OUT_SPEED;
        if (transparency <= 0) {
            getWorld().removeObject(this);
            return;
        }
        image.setTransparency(transparency);
        setImage(image);
    }    
}
