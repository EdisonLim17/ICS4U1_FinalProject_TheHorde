import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The movement speed powerup: increases the player's movement speed for a duration
 * 
 * @author (Jaylen Cheung) 
 * @version (2.0: 01/22/2022)
 */
public class MovementSpeed extends Powerup
{
    //declaring the movement speed powerup's image variables
    private GreenfootImage image = new GreenfootImage("PowerupIcons/MovementSpeed.png");
    
    //initializing constants
    public static final int MS_BOOST = 2;
    public static final Color MS_BOOST_COLOR = Color.CYAN;
    
    /**
     * MovementSpeed Constructor
     *
     * @param player the player in the world
     */
    public MovementSpeed(Player player){
        image.scale(PU_WIDTH, PU_HEIGHT);
        this.player = player;
        super.draw(PU_WIDTH, PU_HEIGHT);
    }
    
    /**
     * Act Method
     */
    public void act() 
    {
        super.act();
    }    
    
    /**
     * Method to activate the powerup benefits: increase the player's movement speed
     */
    public void activate(){
        player.setSpeed(MS_BOOST);
    }
    
    /**
     * Method to deactivate the powerup benefits: reset the player's movement
     */
    public void deactivate(){
        player.setSpeed(-MS_BOOST);
    }
    
    /**
     * Method to get the image of the powerup
     *
     * @return the powerup's image
     */
    public GreenfootImage getImage(){
        return image;
    }
    
    /**
     * Method to get the notification name of the powerup
     *
     * @return the powerup's notification name
     */
    public String toString(){
        return "Movement Speed Boost";
    }
    
    /**
     * Method to get the color of the powerup
     *
     * @return the powerup's color
     */
    public Color getColor(){
        return MS_BOOST_COLOR;
    }
}
