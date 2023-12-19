import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameOver extends World {
    Hitbox hitbox = new Hitbox();
    public GameOver(World restart) {
        super(733, 430, 1, false);

        addObject(hitbox, 0, 0);
        Greenfoot.setSpeed(50);
        addObject(new Retry(restart), 365, 395);
    }

    public void act() {

    }

    public void moveHitbox() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            hitbox.setLocation(mouse.getX(), mouse.getY());
        }
    }
}
