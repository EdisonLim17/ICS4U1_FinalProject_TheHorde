import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The muzzle flash of the player's weapon, appears for a second when a bullet is fired
 * 
 * @author (Jaylen Cheung) 
 * @version (2.0: 01/22/2022)
 */
public class MuzzleFlash extends SuperSmoothMover
{
    //declaring the muzzle flash's dimensions and image variables
    private static GreenfootImage image = new GreenfootImage("Player/RifleSprites/MuzzleFlash.png");
    public static final int MUZZLE_FLASH_WIDTH = image.getWidth() / 3;
    public static final int MUZZLE_FLASH_HEIGHT = image.getHeight() / 3;
    
    //declaring actors
    Player player;
    
    //declaring instance variables
    private int transparency = 255;
    
    /**
     * MuzzleFlash Constructor
     */
    public MuzzleFlash(){
        //setting the muzzle flash's image
        image.scale(MUZZLE_FLASH_WIDTH, MUZZLE_FLASH_HEIGHT);
        setImage(image);
    }
    
    /**
     * Act Method
     * 
     * Follows the rifle and reduces transparency to apply the fade out effect, removes the object when it is transparent
     */
    public void act() 
    {
        //follow the rifle
        player = getWorld().getObjects(Player.class).get(0); //(from Mr. Cohen)
        setLocation(player.getX(), player.getY());
        setRotation(player.getRotation());
        move(Player.PLAYER_WIDTH / 2 + MUZZLE_FLASH_WIDTH / 3);
        setRotation(getRotation() + 90);
        move(Player.PLAYER_HEIGHT / 4);
        setRotation(getRotation() - 90);
        
        //fade out effect
        transparency -= 20;
        if (transparency <= 0) {
            getWorld().removeObject(this);
            return;
        }
        image.setTransparency(transparency);
        setImage(image);
    }    
}