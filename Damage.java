import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The damage powerup: increases the player's damage for a duration
 * 
 * @author (Jaylen Cheung) 
 * @version (2.0: 01/22/2022)
 */
public class Damage extends Powerup
{
    //declaring the damage powerup's image variables
    private GreenfootImage image = new GreenfootImage("PowerupIcons/Damage.png");
    
    //initializing constants
    public static final int DMG_BOOST = 2;
    public static final Color DMG_BOOST_COLOR = Color.RED;
    
    /**
     * Damage Constructor
     *
     * @param player the player in the world
     */
    public Damage(Player player){
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
     * Method to activate the powerup benefits: increase the player's damage
     */
    public void activate(){
        player.setDmg(DMG_BOOST);
    }
    
    /**
     * Method to deactivate the powerup benefits: reset the player's damage
     */
    public void deactivate(){
        player.setDmg(-DMG_BOOST);
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
        return "Damage Boost";
    }
    
    /**
     * Method to get the color of the powerup
     *
     * @return the powerup's color
     */
    public Color getColor(){
        return DMG_BOOST_COLOR;
    }
}
