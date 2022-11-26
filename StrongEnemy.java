import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The strong enemy (large zombie in a t-shirt)
 * 
 * @author (Edison Lim) 
 * @version (2.0: 01/22/2022)
 */
public class StrongEnemy extends Enemy
{
    //declaring the strong enemy's dimensions and image to reference width and height
    private static GreenfootImage refImage = new GreenfootImage("Enemies/StrongEnemySprites/Move/skeleton-move_0.png");
    public static final int STR_WIDTH = refImage.getWidth() / 2;
    public static final int STR_HEIGHT = refImage.getHeight() / 2;
    
    //initializing constants
    public static final int STR_SCORE = 200; //score the player gets after killing this enemy
    public static final int STR_HP = 20;
    public static final int STR_DMG = 10;
    public static final int STR_SPEED = 2;
    public static final int STR_ATTACK_DURATION = 60;
    public static final int STR_ATTACK_DELAY = 30;
    public static final int STR_DMG_SPRITE_NUM = 5;
    public static final int STR_MOVE_ACT = 2;
    
    //declaring sprites
    private GreenfootImage[] strMovingSprites = new GreenfootImage[17];
    private GreenfootImage[] strAttackingSprites = new GreenfootImage[9];
    
    /**
     * StrongEnemy Constructor
     */
    public StrongEnemy(){
        super();
        
        //initializing sprites
        for (int i = 0; i < strMovingSprites.length; i++) {
            strMovingSprites[i] = new GreenfootImage("Enemies/StrongEnemySprites/Move/skeleton-move_" + i + ".png");
            strMovingSprites[i].scale(STR_WIDTH, STR_HEIGHT);
        }
        for (int i = 0; i < strAttackingSprites.length; i++) {
            strAttackingSprites[i] = new GreenfootImage("Enemies/StrongEnemySprites/Attack/skeleton-attack_" + i + ".png");
            strAttackingSprites[i].scale(STR_WIDTH, STR_HEIGHT);
        }
        setImage(strMovingSprites[0]); //setting the image of the strong enemy
        
        //setting variables
        score = STR_SCORE;
        currHP = STR_HP;
        dmg = STR_DMG;
        speed = STR_SPEED;
        attackduration = STR_ATTACK_DURATION;
        attackdelay = STR_ATTACK_DELAY;
        dmgSpriteNum = STR_DMG_SPRITE_NUM;
        moveAct = STR_MOVE_ACT;
        movingSprites = strMovingSprites;
        attackingSprites = strAttackingSprites;
        attackSounds = new GreenfootSound[1];
        for(int i = 0; i < attackSounds.length; i++) attackSounds[i] = new GreenfootSound("ZombieSoundEffects/StrongPunch.wav");
    }
    
    /**
     * Act Method
     */
    public void act() 
    {
        super.act();
    }
    
    /**
     * Method to draw the strong enemy
     *
     * @param width the width of the strong enemy
     * @param height the height of the strong enemy
     * @return the strong enemy's image
     */
    private GreenfootImage drawStrongEnemy(int width, int height){
        GreenfootImage image = new GreenfootImage(width + 1, height + 1);
        image.setColor(Color.RED);
        int[] xVertices = {0, width, 0};
        int[] yVertices = {0, height / 2, height};
        image.fillPolygon(xVertices, yVertices, 3);
        return image;
    }
}