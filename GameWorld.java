import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * The world where the game is played, the user is taken to this world after clicking the 'Play" button in the MainMenu
 * 
 * @author (Edison Lim) 
 * @version (2.0: 01/22/2022)
 */
public class GameWorld extends World
{
    //declaring world dimensions
    public static final int WORLD_WIDTH = 960;
    public static final int WORLD_HEIGHT = 540;
    
    //declaring background variables
    private GreenfootImage bgImage = new GreenfootImage("Backgrounds/GameWorld.jpg");
    
    //initializing constants
    public static final int START_NUM_POOL = 120; //the smaller the pool, the larger the chance to spawn an enemy
    public static final int END_NUM_POOL = 30;
    public static final int POOL_DECR_DELAY = 60; //acts between decreasing the number pool by 1
    //chance of fast or strong enemy spawning (as a percent, 0 <= sum <= 100)
    public static final int FAST_ENEMY_CHANCE = 20;
    public static final int STR_ENEMY_CHANCE = 30;
    public static final int POWERUP_FREQUENCY = 500; // 1 in x chance of spawning a powerup every act
    
    //declaring actors
    private Player player;
    
    //declaring lists for the powerups
    private ArrayList<Powerup> powerupDrops = new ArrayList<Powerup>();
    private ArrayList<Powerup> activePowerups = new ArrayList<Powerup>();
    private ArrayList<PowerupIcon> powerupIcons = new ArrayList<PowerupIcon>();
    
    //declaring instance variables
    private static GreenfootSound bgMusic = new GreenfootSound("BackgroundMusic/GameWorldMusic.wav");
    private boolean musicStarted = false;
    private int numPool,decrTimer;
    
    /**
     * GameWorld Constructor
     */
    public GameWorld()
    {   
        // Create a new world with WORLD_WIDTH * WORLD_HEIGHT cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        
        //seting background
        setBackground(bgImage);
        
        //initializing instance variables
        numPool = START_NUM_POOL;
        decrTimer = POOL_DECR_DELAY;
        
        //creating a new player at the specified location
        player = new Player();
        addObject(player, getWidth() / 2, getHeight() / 2);
    }
    
    /**
     * Act Method
     * 
     * Checks whether or not to spawn an enemy, increases enemy spawn chance, and manages powerups
     */
    public void act(){
        if(Greenfoot.getRandomNumber(numPool) == 0) spawnEnemy(); //spawns an enemy if the random number chosen from the number pool is equal to 0
        //increases the chance of enemies spawning when the timer hits 0
        if(numPool > END_NUM_POOL && decrTimer == 0) numPool--;
        decrTimer--;
        if(decrTimer < 0) decrTimer = POOL_DECR_DELAY;
        
        handlePowerups();
        
        // Start music, and prevent redundency
        if (!musicStarted) {
            bgMusic.playLoop();
            musicStarted = true;
        }
    }
    
    /**
     * Method to spawn an enemy
     * 
     * Spawns a random enemy at a random location at the edge of the screen
     */
    private void spawnEnemy(){
        //generating random numbers for the type of enemy and where to spawn it
        int rdmX = Greenfoot.getRandomNumber(getWidth());
        int rdmY = Greenfoot.getRandomNumber(getHeight());
        int rdmSide = Greenfoot.getRandomNumber(4);
        int rdmEnemy = Greenfoot.getRandomNumber(100);
        //declaring enemy variables
        Actor enemy;
        GreenfootSound zombieGroan;
        
        //chooses type of enemy
        if(rdmEnemy < FAST_ENEMY_CHANCE){
            enemy = new FastEnemy();
            zombieGroan = new GreenfootSound("ZombieSoundEffects/FastGroan.wav");
        }
        else if(rdmEnemy < FAST_ENEMY_CHANCE + STR_ENEMY_CHANCE){
            enemy = new StrongEnemy();
            zombieGroan = new GreenfootSound("ZombieSoundEffects/StrongGroan.wav");
        }
        else{
            enemy = new NormalEnemy();
            zombieGroan = new GreenfootSound("ZombieSoundEffects/NormalGroan.wav");
        }
        
        //spawns enemy at a random location at the edge of the world
        if(rdmSide == 0) addObject(enemy, rdmX, 0);
        else if(rdmSide == 1) addObject(enemy, rdmX, getHeight());
        else if(rdmSide == 2) addObject(enemy, 0, rdmY);
        else if(rdmSide == 3) addObject(enemy, getWidth(), rdmY);
        zombieGroan.play();
    }
    
    /**
     * Method to manage powerups
     * 
     * Spawns a random powerup on the screen if the random number matches the powerup frequency
     * Checks if the player collided with any powerups, and grants its benefits
     * Updates the powerup icons and timer
     */
    private void handlePowerups() {
        // Decide whether or not to spawn a powerup
        if (Greenfoot.getRandomNumber(POWERUP_FREQUENCY) == 0) {
            int rdnPowerup = Greenfoot.getRandomNumber(5);
            Powerup powerup;
            if (rdnPowerup == 0) powerup = new Damage(player);
            else if (rdnPowerup == 1) powerup = new FireRate(player);
            else if (rdnPowerup == 2) powerup = new Healing(player);
            else if (rdnPowerup == 3) powerup = new MovementSpeed(player);
            else powerup = new UnlimitedAmmo(player);
            powerupDrops.add(powerup);
            
            // Spawn powerup
            addObject(powerupDrops.get(powerupDrops.size() - 1), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
            GreenfootSound powerupSound = new GreenfootSound("PowerupSoundEffects/PowerupSpawn.wav");
            powerupSound.play();
        }
        
        // Handle powerup collision with player
        List<Powerup> powerupsPickedUp = player.getIntersectingObjects();
        for (Powerup i: powerupsPickedUp) {
            GreenfootSound pickupSound = new GreenfootSound("PowerupSoundEffects/PowerupPickup.wav");
            pickupSound.play();
            
            //checks if the powerup picked up is already active
            String name = i.toString();
            boolean active = false;
            for(Powerup j: activePowerups){
                if(j.toString().equals(name)){
                    active = true;
                    break;
                }
            }
            
            if(active){ //if it is active, update its timer
                for(PowerupIcon j: powerupIcons){
                    if(j.getPowerup().toString().equals(name)){
                        j.setTimeLeft(Powerup.DURATION);
                        break;
                    }
                }
            }
            else{ //if it is not active, activate it
                if(i.needsIcon()){
                    powerupIcons.add(new PowerupIcon(i));
                    activePowerups.add(i);
                }
                i.activate();
            }
            
            //display powerup notification
            PowerupNotification powerupNotif = new PowerupNotification(i);
            addObject(powerupNotif, getWidth() / 2, getHeight() / 2);
            //remove the powerup from the screen
            powerupDrops.remove(i);
            removeObject(i);
        }
        
        // Display powerup icons
        for (int i = 0; i < powerupIcons.size(); i++) {
            if (powerupsPickedUp.contains(powerupIcons.get(i).getPowerup())) {
                addObject(powerupIcons.get(i), (int)(getWidth() - Powerup.PU_WIDTH * (i + 1) * 3 / 2), WORLD_HEIGHT * 5 / 27);
            }
        }
        
        // Remove powerup icon if timer reached 0
        for (PowerupIcon i : new ArrayList<PowerupIcon>(powerupIcons)) {
            if (i.getTimeLeft() == 0) {
                removeObject(i);
                i.getPowerup().deactivate();
                activePowerups.remove(i.getPowerup());
                powerupIcons.remove(i);
            }
        }
    }
    
    /**
     * Method to end the game
     * 
     * Called by the player class, ends the game by taking the user to the end screen
     */
    public static void endGame(){
        bgMusic.stop();
        Greenfoot.setWorld(new EndScreen());
    }
}