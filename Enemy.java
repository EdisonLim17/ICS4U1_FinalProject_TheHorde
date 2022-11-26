import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The enemy superclass
 * 
 * @author (Edison Lim) 
 * @version (2.0: 01/22/2022)
 */
public class Enemy extends SuperSmoothMover
{
    //declaring instance variables
    protected Actor target;
    protected int score, currHP, dmg, speed, attackduration, currDuration = 0, attackdelay, currDelay = 0; //enemy stats
    protected boolean attacking = false, dmgDealt = false;
    //sprites
    protected GreenfootImage[] movingSprites;
    protected GreenfootImage[] attackingSprites;
    //sprite info
    protected int movingSpriteNum = 0, attackingSpriteNum = 0;
    protected int dmgSpriteNum, currMoveAct, moveAct;
    //sound
    protected GreenfootSound[] attackSounds;
    protected int attackSoundsIndex = 0;
    
    /**
     * Enemy Constructor
     */
    public Enemy(){
        
    }
    
    /**
     * Act Method
     * 
     * Removes the object from the world if it has died
     * Set attacking animation if it is currently attacking the player, and deal damage to the player if it hits him
     * If it is not currently attacking the player, move towards the player
     */
    public void act() 
    {
        if(currHP <= 0){
            target = getWorld().getObjects(Player.class).get(0); //(from Mr. Cohen)
            Player player = (Player) target;
            player.setScore(score); //give points to player
            
            //adding blood splatter
            BloodSplatter bloodSplatter = new BloodSplatter();
            getWorld().addObject(bloodSplatter, getX(), getY());
            
            GreenfootSound zombieDeathSound = new GreenfootSound("ZombieSoundEffects/ZombieDeath.wav");
            zombieDeathSound.play();
            
            getWorld().removeObject(this); //enemy dies
        }
        else if(attacking){
            currDuration++;
            //manages animation depending on the duration of the attack
            if((int)((double)currDuration % ((double)attackduration / attackingSprites.length)) == 0 && attackingSpriteNum < attackingSprites.length - 1) attackingSpriteNum++;
            setImage(attackingSprites[attackingSpriteNum]);
            
            target = getOneIntersectingObject(Player.class);
            //check if the enemy is at the animation frame that does dmg to the player
            if(target != null && !dmgDealt && attackingSpriteNum == dmgSpriteNum){
                Player player = (Player) target;
                player.dealDmg(dmg);
                currDelay = attackdelay;
                dmgDealt = true;
                
                attackSounds[attackSoundsIndex].play();
                attackSoundsIndex++;
                if(attackSoundsIndex >= attackSounds.length) attackSoundsIndex = 0;
            }
            //checks if the enemy has finished its attack
            if(currDuration == attackduration){
                attacking = false;
                dmgDealt = false;
                currDuration = 0;
                attackingSpriteNum = 0;
            }
        }
        else{
            target = getOneIntersectingObject(Player.class);
            //checks if the enemy can attack the player
            if(target != null && currDelay == 0){
                attacking = true;
                setImage(attackingSprites[attackingSpriteNum]);
            }
            else if(currDelay == 0){
                //turn towards the player
                target = getWorld().getObjects(Player.class).get(0); //(from Mr. Cohen)
                Player player = (Player) target;
                turnTowards(player.getX(), player.getY());
                move(speed);
                //setting moving animation
                if(currMoveAct == moveAct){
                    currMoveAct = 0;
                    movingSpriteNum++;
                    if (movingSpriteNum == movingSprites.length) {
                        movingSpriteNum = 0;
                    }
                    setImage(movingSprites[movingSpriteNum]);
                }
                else currMoveAct++;
            }
            else currDelay--;
        }
    }
    
    //setter methods
    
    /**
     * Method to deal damage to the enemy
     * 
     * Called by the bullet class when the bullet has collided with the enemy
     *
     * @param dmg the amount of damage dealt
     */
    public void dealDmg(int dmg){
        currHP -= dmg;
    }
}