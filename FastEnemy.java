import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The fast enemy (green troll)
 * 
 * @author (Edison Lim) 
 * @version (2.0: 01/22/2022)
 */
public class FastEnemy extends Enemy
{
    //declaring the fast enemy's dimensions and image variable to reference width and height
    private static GreenfootImage refImage = new GreenfootImage("Enemies/FastEnemySprites/Move/troll-move_0.png");
    public static final int FAST_WIDTH = refImage.getWidth();
    public static final int FAST_HEIGHT = refImage.getHeight();
    
    //initializing constants
    public static final int FAST_SCORE = 100; //score the player gets after killing this enemy
    public static final int FAST_HP = 8;
    public static final int FAST_DMG = 3;
    public static final int FAST_SPEED = 6;
    public static final int FAST_ATTACK_DURATION = 10;
    public static final int FAST_ATTACK_DELAY = 10;
    public static final int FAST_DMG_SPRITE_NUM = 4;
    public static final int FAST_MOVE_ACT = 0;
    
    //declaring sprites
    private GreenfootImage[] fastMovingSprites = new GreenfootImage[8];
    private GreenfootImage[] fastAttackingSprites = new GreenfootImage[8];
    
    /**
     * FastEnemy Constructor
     */
    public FastEnemy(){
        super();
        
        //initializing sprites
        for (int i = 0; i < fastMovingSprites.length; i++) {
            fastMovingSprites[i] = new GreenfootImage("Enemies/FastEnemySprites/Move/troll-move_" + i + ".png");
            fastMovingSprites[i].scale(FAST_WIDTH, FAST_HEIGHT);
        }
        for (int i = 0; i < fastAttackingSprites.length; i++) {
            fastAttackingSprites[i] = new GreenfootImage("Enemies/FastEnemySprites/Attack/troll-attack_" + i + ".png");
            fastAttackingSprites[i].scale(FAST_WIDTH, FAST_HEIGHT);
        }
        setImage(fastMovingSprites[0]); //setting the image of the fast enemy
        
        //setting variables
        score = FAST_SCORE;
        currHP = FAST_HP;
        dmg = FAST_DMG;
        speed = FAST_SPEED;
        attackduration = FAST_ATTACK_DURATION;
        attackdelay = FAST_ATTACK_DELAY;
        dmgSpriteNum = FAST_DMG_SPRITE_NUM;
        moveAct = FAST_MOVE_ACT;
        movingSprites = fastMovingSprites;
        attackingSprites = fastAttackingSprites;
        attackSounds = new GreenfootSound[2];
        for(int i = 0; i < attackSounds.length; i++) attackSounds[i] = new GreenfootSound("ZombieSoundEffects/FastStab.wav");
    }
    
    /**
     * Act Method
     */
    public void act() 
    {
        super.act();
    }
    
    /**
     * Method to draw the fast enemy
     *
     * @param width the width of the fast enemy
     * @param height the height of the fast enemy
     * @return the fast enemy's image
     */
    private GreenfootImage drawFastEnemy(int width, int height){
        GreenfootImage image = new GreenfootImage(width + 1, height + 1);
        image.setColor(Color.GREEN);
        int[] xVertices = {0, width, 0};
        int[] yVertices = {0, height / 2, height};
        image.fillPolygon(xVertices, yVertices, 3);
        return image;
    }
}