import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PeashooterPacket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChenPacket extends HeroPacket
{
    /**
     * Act - do whatever the PeashooterPacket wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public ChenPacket() {
        super(7000L, true, 100, "PocketChen");
        
    }
    
    public TransparentObject addImage() {
        TransparentObject temp = new TransparentChen(false);
        ((MyWorld)getWorld()).addObject(temp, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
        return temp;
    }
    public Hero getHero() {
        return new Chen();
    }
}
