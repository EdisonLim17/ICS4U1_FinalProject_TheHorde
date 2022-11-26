import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The icon that shows what powerup is active and its timer
 * 
 * @author (Jaylen Cheung) 
 * @version (2.0: 01/22/2022)
 */
public class PowerupIcon extends Actor
{
    //declaring the powerup icon's dimensions and image variables
    private GreenfootImage image;
    public static final int PU_ICON_WIDTH = Powerup.PU_WIDTH;
    public static final int PU_ICON_HEIGHT = PU_ICON_WIDTH * 3 / 2;
    public static final Font PU_ICON_FONT = new Font("Courier New", true, false, PU_ICON_HEIGHT / 3);
    
    //declaring instance variables
    private int timeLeft;
    private Powerup powerup;
    
    /**
     * PowerupIcon Constructor
     *
     * @param powerup the powerup represented by the powerup icon
     */
    public PowerupIcon(Powerup powerup){
        this.powerup = powerup;
        timeLeft = Powerup.DURATION;
        //drawing the powerup icon
        image = new GreenfootImage(PU_ICON_WIDTH, PU_ICON_HEIGHT);
        drawPowerupIcon(PU_ICON_WIDTH, PU_ICON_HEIGHT);
    }
    
    /**
     * Act Method
     * 
     * Updates the timer on the powerup icon
     */
    public void act() 
    {
        //update the powerup icon's timer
        timeLeft--;
        drawPowerupIcon(PU_ICON_WIDTH, PU_ICON_HEIGHT);
    }
    
    //getter methods
    
    /**
     * Method to get the powerup of the powerup icon
     *
     * @return the powerup icon's powerup
     */
    public Powerup getPowerup(){
        return powerup;
    }
    
    /**
     * Method to get the amount of time left on the timer of the powerup
     *
     * @return the amount of time left on the powerup icon
     */
    public int getTimeLeft(){
        return timeLeft;
    }
    
    //setter methods
    
    /**
     * Method to set the timer of the powerup
     *
     * @param timeLeft current time left
     */
    public void setTimeLeft(int timeLeft){
        this.timeLeft = timeLeft;
    }
    
    /**
     * Method to draw the powerup icon
     *
     * @param width the width of the powerup icon
     * @param height the height of the powerup icon
     */
    private void drawPowerupIcon(int width, int height){
        image.clear();
        image.setColor(Color.BLACK);
        image.fillRect(0, 0, width, height * 2 / 3);
        image.drawImage(powerup.getImage(), 0, 0);
        image.setColor(Color.WHITE);
        image.setFont(PU_ICON_FONT);
        String text = String.valueOf(timeLeft / 60 + 1);
        image.drawString(text, (image.getWidth() - (int)(text.length() * PU_ICON_FONT.getSize() * 0.58)) / 2, image.getHeight() - PU_ICON_FONT.getSize() / 6);
        setImage(image);
    }
}
