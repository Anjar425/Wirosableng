import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ReadySetPlant here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class LinkStart extends animatedObject {
    public GreenfootImage link;
    public GreenfootImage start;
    public boolean r = true;
    public boolean s = true;
    public boolean p = true;
    public int counter;

    public LinkStart() {
        link = new GreenfootImage("LINK.png");
        start = new GreenfootImage("START.png");
    }

    public void act() {
        if (counter < 60) {
            setImage(link);
            counter++;
        } else if (counter < 120) {
            setImage(start);
            counter++;
        } else {
            if (r) {
                r = false;
                frame = 0;
            } else if (s) {
                s = false;
                frame = 0;
            } else if (p) {
                p = false;
                getWorld().removeObject(this);
                return;
            }
        }
    }
}
