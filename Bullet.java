import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The bullet that is fired from the player's weapon
 * 
 * @author (Edison Lim) 
 * @version (2.0: 01/22/2022)
 */
public class Bullet extends SuperSmoothMover
{
    //declaring the bullet's dimensions and image variables
    private static GreenfootImage image = new GreenfootImage("Player/RifleSprites/Bullet.png");
    public static final int BULLET_WIDTH = image.getWidth() / 8;
    public static final int BULLET_HEIGHT = image.getHeight() / 8;
    
    //initializing constants
    public static final int SPEED = 25;
    
    //declaring actors
    Player player;
    
    //declaring instance variables
    private Class targetClass = Enemy.class; //target class tracking (from Mr. Cohen)
    private int dmg;
    
    /**
     * Bullet Constructor
     */
    public Bullet(){
        //setting the image of the bullet
        image.scale(BULLET_WIDTH, BULLET_HEIGHT);
        setImage(image);
    }
    
    /**
     * Act Method
     * 
     * Moves the bullet forward, and removes it from the world if it has reached the edge of the world or if it has hit and enemy
     */
    public void act() 
    {
        //updating the bullet's damage
        player = getWorld().getObjects(Player.class).get(0); //(from Mr. Cohen)
        dmg = player.getDmg();
        move(SPEED);
        if(collisionDetection() || isAtEdge()) getWorld().removeObject(this); //removes bullet if when it hits an enemy or leaves the screen
    }
    
    /**
     * Method to check if the bullet has collided with an enemy, and deals damage respectively (from Mr. Cohen)
     *
     * @return true if the bullet as collided with an enemy, and false otherwise
     */
    private boolean collisionDetection(){
        Actor target = getOneIntersectingObject(targetClass);
        if(target != null){
            Enemy enemy = (Enemy) target;
            enemy.dealDmg(dmg);
            return true;
        }
        return false;
    }
}