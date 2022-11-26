import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The normal enemy (grey zombie)
 * 
 * @author (Edison Lim) 
 * @version (2.0: 01/22/2022)
 */
public class NormalEnemy extends Enemy
{
    //declaring the normal enemy's dimensions and image to reference width and height
    private static GreenfootImage refImage = new GreenfootImage("Enemies/NormalEnemySprites/Move/saunter0.png");
    public static final int NORM_WIDTH = refImage.getWidth() * 4 / 3;
    public static final int NORM_HEIGHT = refImage.getHeight() * 4 / 3;
    
    //initializing constants
    public static final int NORM_SCORE = 50; //score the player gets after killing this enemy
    public static final int NORM_HP = 4;
    public static final int NORM_DMG = 3;
    public static final int NORM_SPEED = 1;
    public static final int NORM_ATTACK_DURATION = 30;
    public static final int NORM_ATTACK_DELAY = 30;
    public static final int NORM_DMG_SPRITE_NUM = 11;
    public static final int NORM_MOVE_ACT = 1;
    
    //declaring sprites
    private GreenfootImage[] normMovingSprites = new GreenfootImage[32];
    private GreenfootImage[] normAttackingSprites = new GreenfootImage[20];
    
    /**
     * NormalEnemy Constructor
     */
    public NormalEnemy(){
        super();
        
        //initializing sprites
        for (int i = 0; i < normMovingSprites.length; i++) {
            normMovingSprites[i] = new GreenfootImage("Enemies/NormalEnemySprites/Move/saunter" + i + ".png");
            normMovingSprites[i].scale(NORM_WIDTH, NORM_HEIGHT);
        }
        for (int i = 0; i < normAttackingSprites.length; i++) {
            normAttackingSprites[i] = new GreenfootImage("Enemies/NormalEnemySprites/Attack2/attack02_" + i + ".png");
            normAttackingSprites[i].scale(NORM_WIDTH, NORM_HEIGHT);
        }
        setImage(normMovingSprites[0]); //setting the image of the normal enemy
        
        //setting variables
        score = NORM_SCORE;
        currHP = NORM_HP;
        dmg = NORM_DMG;
        speed = NORM_SPEED;
        attackduration = NORM_ATTACK_DURATION;
        attackdelay = NORM_ATTACK_DELAY;
        dmgSpriteNum = NORM_DMG_SPRITE_NUM;
        moveAct = NORM_MOVE_ACT;
        movingSprites = normMovingSprites;
        attackingSprites = normAttackingSprites;
        attackSounds = new GreenfootSound[1];
        for(int i = 0; i < attackSounds.length; i++) attackSounds[i] = new GreenfootSound("ZombieSoundEffects/NormalPunch.wav");
    }
    
    /**
     * Act Method
     */
    public void act() 
    {
        super.act();
    }
    
    /**
     * Method to draw thhe normal enemy
     *
     * @param width the width of the normal enemy
     * @param height the height of the normal enemy
     * @return the normal enemy's image
     */
    private GreenfootImage drawNormalEnemy(int width, int height){
        GreenfootImage image = new GreenfootImage(width + 1, height + 1);
        image.setColor(Color.BLUE);
        int[] xVertices = {0, width, 0};
        int[] yVertices = {0, height / 2, height};
        image.fillPolygon(xVertices, yVertices, 3);
        return image;
    }
}