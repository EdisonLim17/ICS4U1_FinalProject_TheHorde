import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button class
 * 
 * @author (Edison Lim) 
 * @version (2.0: 01/22/2022)
 */
public class Button extends Actor
{
    //declaring the button's dimensions and image variables
    private GreenfootImage image;
    public static final int BUTTON_WIDTH = GameWorld.WORLD_WIDTH / 6;
    public static final int BUTTON_HEIGHT = GameWorld.WORLD_HEIGHT / 11;
    public static final Color hoverColor = MainMenu.MENU_TITLE_COLOR;
    public static Font labelFont = new Font("Courier New", true, false, BUTTON_HEIGHT / 2);
    private String label;
    
    //declaring instance variables
    private boolean soundPlayed = false;
    //mouse tracking
    private MouseInfo mouse;
    private boolean hovering = false;
    
    /**
     * Button Constructor
     *
     * @param str the label of the button
     */
    public Button(String str){
        //drawing the button
        image = new GreenfootImage(BUTTON_WIDTH + 1, BUTTON_HEIGHT + 1);
        label = str;
        drawButton(Color.BLACK, Color.WHITE, hoverColor);
        setImage(image);
    }
    
    /**
     * Act Method
     * 
     * Checks if the user is hovering over the button with the mouse, and updates the image of the button if they are
     */
    public void act() 
    {
        mouse = Greenfoot.getMouseInfo();
        //changes the button label's color when the mouse is hovering over it (from Mr. Cohen)
        if(Greenfoot.mouseMoved(this)){
            hovering = true;
            if(!soundPlayed){
                GreenfootSound hoverSound = new GreenfootSound("ButtonHover.wav");
                hoverSound.play();
                soundPlayed = true;
            }
            drawButton(Color.BLACK, Color.WHITE, hoverColor);
            setImage(image);
        }
        if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
            hovering = false;
            soundPlayed = false;
            drawButton(Color.BLACK, Color.WHITE, hoverColor);
            setImage(image);
        }
    }
    
    /**
     * Method to play the click sound of pressing the button, called by worlds that have buttons in them
     */
    public void playClickSound(){
        GreenfootSound clickSound = new GreenfootSound("ButtonClick.wav");
        clickSound.play();
    }
    
    /**
     * Method to draw the button
     *
     * @param backColor the color of the button
     * @param labelColor the color of the label
     * @param hoverColor the hover color of the label
     */
    private void drawButton(Color backColor, Color labelColor, Color hoverColor){
        image.clear();
        //drawing button
        image.setColor(backColor);
        image.fillOval(0, 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.fillOval(0, image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.fillOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.fillOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.setColor(labelColor);
        image.drawOval(0, 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(0, image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.setColor(backColor);
        image.fillRect((image.getWidth() - 1) / 20, 0, image.getWidth() - 1 - (image.getWidth() - 1) / 10, image.getHeight() - 1);
        image.fillRect(0, (image.getWidth() - 1) / 20, image.getWidth() - 1, image.getHeight() - 1 - (image.getWidth() - 1) / 10);
        image.setColor(labelColor);
        image.drawLine((image.getWidth() - 1) / 20, 0, image.getWidth() - 1- (image.getWidth() - 1) / 20, 0);
        image.drawLine((image.getWidth() - 1) / 20, image.getHeight() - 1, image.getWidth() - 1- (image.getWidth() - 1) / 20, image.getHeight() - 1);
        image.drawLine(0, (image.getWidth() - 1) / 20, 0, image.getHeight() - 1 - (image.getWidth() - 1) / 20);
        image.drawLine(image.getWidth() - 1, (image.getWidth() - 1) / 20, image.getWidth() - 1, image.getHeight() - 1 - (image.getWidth() - 1) / 20);
        //adding label
        image.setFont(labelFont);
        if(hovering){ //change the label color if the mouse is hovering the button
            image.setColor(hoverColor);
        }
        image.drawString(label, (image.getWidth() - (int)(label.length() * labelFont.getSize() * 0.58)) / 2, (image.getHeight() + labelFont.getSize() / 2) / 2);
    }
}