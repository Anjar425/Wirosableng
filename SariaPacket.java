import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WalnutPacket here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class SariaPacket extends HeroPacket {
    /**
     * Act - do whatever the WalnutPacket wants to do. This method is called
     * whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SariaPacket() {
        super(20000L, true, 50, "PocketSaria");
    }

    public TransparentObject addImage() {
        TransparentObject temp = new TransparentSaria(false);
        ((MyWorld) getWorld()).addObject(temp, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
        return temp;
    }

    public Hero getHero() {
        return new Saria();
    }
}
