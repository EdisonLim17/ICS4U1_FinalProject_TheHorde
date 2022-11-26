import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The unlimited ammo powerup, grants the player unlimited ammo for a duration
 * 
 * @author (Jaylen Cheung) 
 * @version (2.0: 01/22/2022)
 */
public class UnlimitedAmmo extends Powerup
{
    //declaring the unlimited ammo powerup's image variables
    private GreenfootImage image = new GreenfootImage("PowerupIcons/UnlimitedAmmo.png");
    
    //initializing constants
    public static final Color UNLIMITED_AMMO_COLOR = Color.ORANGE;
    
    /**
     * UnlimitedAmmo Constructor
     *
     * @param player the player in the world
     */
    public UnlimitedAmmo(Player player){
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
     * Method to activate the powerup benefits: refill the player's mag and grant the player unlimited ammo
     */
    public void activate(){
        player.setAmmo(player.PLAYER_MAG_SIZE);
        player.setUnlimitedAmmo(true);
    }
    
    /**
     * Method to deactivate the powerup benefits: reset the player's ammo status
     */
    public void deactivate(){
        player.setUnlimitedAmmo(false);
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
        return "Unlimited Ammo";
    }
    
    /**
     * Method to get the color of the powerup
     *
     * @return the powerup's color
     */
    public Color getColor(){
        return UNLIMITED_AMMO_COLOR;
    }
}