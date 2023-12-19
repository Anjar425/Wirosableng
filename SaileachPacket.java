import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SunflowerPacket here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class SaileachPacket extends HeroPacket {
    /**
     * Act - do whatever the SunflowerPacket wants to do. This method is called
     * whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SaileachPacket() {
        super(12000L, true, 50, "PocketSai");

    }

    public TransparentObject addImage() {
        TransparentObject temp = new TransparentSaileach(false);
        ((MyWorld) getWorld()).addObject(temp, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
        return temp;
    }

    public Hero getHero() {
        return new Saileach();
    }
}
