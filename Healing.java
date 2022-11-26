import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The healing powerup: refills the player's hp
 * 
 * @author (Jaylen Cheung) 
 * @version (2.0: 01/22/2022)
 */
public class Healing extends Powerup
{
    //declaring the healing powerup's image variables
    private GreenfootImage image = new GreenfootImage("PowerupIcons/Healing.png");
    
    //initializing constants
    public static final Color HEALING_COLOR = Color.GREEN;
    
    /**
     * Healing Constructor
     *
     * @param player the player in the world
     */
    public Healing(Player player){
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
     * Method to activate the powerup benefits: refill the player's hp
     */
    public void activate(){
        player.setCurrHP(player.getMaxHP());
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
        return "Instant Healing";
    }
    
    /**
     * Method to get whether or not the powerup needs an icon that shows its timer
     *
     * @return false as the healing powerup needs no timer
     */
    public boolean needsIcon(){
        return false;
    }
    
    /**
     * Method to get the color of the powerup
     *
     * @return the powerup's color
     */
    public Color getColor(){
        return HEALING_COLOR;
    }
}
