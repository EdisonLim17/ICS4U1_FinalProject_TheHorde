import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world the user is taken to after the player has died in the GameWorld
 * 
 * @author (Edison Lim) 
 * @version (2.0: 01/22/2022)
 */
public class EndScreen extends World
{
    //declaring world dimensions
    public static final int ENDING_WIDTH = GameWorld.WORLD_WIDTH;
    public static final int ENDING_HEIGHT = GameWorld.WORLD_HEIGHT;
    
    //declaring background variables
    private GreenfootImage bg = new GreenfootImage("Backgrounds/EndScreen.png");
    public static final Font ENDING_TITLE_FONT = MainMenu.MENU_TITLE_FONT;
    public static final Color ENDING_TITLE_COLOR = MainMenu.MENU_TITLE_COLOR;
    private String title = "You Died";
    
    //declaring actors
    private Button backButton;
    
    //declaring instance variables
    private GreenfootSound deathMusic = new GreenfootSound("BackgroundMusic/EndScreenMusic.wav");
    private GreenfootSound backgroundSound = new GreenfootSound("BackgroundMusic/ZombieEatingBackground.wav");
    
    /**
     * EndScreen Constructor
     */
    public EndScreen()
    {    
        // Create a new world with ENDING_WIDTH*ENDING_HEIGHT cells with a cell size of 1x1 pixels.
        super(ENDING_WIDTH, ENDING_HEIGHT, 1);
        
        //drawing background
        bg.scale(getWidth() + 1, getHeight() + 1);
        bg.setColor(ENDING_TITLE_COLOR);
        bg.setFont(ENDING_TITLE_FONT);
        bg.drawString(title, (getWidth() - (int)(title.length() * ENDING_TITLE_FONT.getSize() * 0.58)) / 2, getHeight() / 2);
        setBackground(bg);
        
        //adding button
        backButton = new Button("Back");
        addObject(backButton, getWidth() * 3 / 4, getHeight() * 5 / 6);
        
        deathMusic.play();
        backgroundSound.play();
    }
    
    /**
     * Act Method
     * 
     * Checks if the user clicks the 'back' button, and takes them back to the main menu if they did
     */
    public void act(){
        //takes the user back to the main menu if they pressed the button
        if(Greenfoot.mouseClicked(backButton)){
            Greenfoot.setWorld(new MainMenu());
            deathMusic.stop();
            backgroundSound.stop();
            backButton.playClickSound();
        }
    }
}