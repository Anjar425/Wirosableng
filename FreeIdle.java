import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Basic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FreeIdle extends IdleEnemies
{
    /**
     * Act - do whatever the Basic wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] idle;
    public FreeIdle() {
        idle = importSprites("freeidle", 8);
        
    }
    
    public void act()
    {
        animate(idle, 250, true);
        // Add your action code here.
    }
}
