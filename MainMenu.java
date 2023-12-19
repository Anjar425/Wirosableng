import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainMenu extends World {
    public GreenfootSound CYS;
    Hitbox hitbox = new Hitbox();
    public static GreenfootSound menutheme = new GreenfootSound("menutheme.mp3");

    public MainMenu(GreenfootSound menutheme) {
        super(734, 430, 1, false);
        addObject(hitbox, 0, 0);
        addObject(new Wirosableng(), 355, 56);
        addObject(new Start(), 675, 387);
        addObject(new Quit(), 60, 387);
        addObject(new MenuSaria(), 253, 195);
        addObject(new MenuSai(), 469, 195);
        addObject(new MenuChen(), 353, 260);
        addObject(new CreditButton(), 138, 387);
        this.menutheme = menutheme;
        Greenfoot.setSpeed(50);
    }

    public void act() {
    }

    public void started() {
        if (!menutheme.isPlaying()) {
            menutheme.setVolume(70);
            menutheme.playLoop();
        }

    }

    public void stopped() {
        menutheme.pause();
    }

    public void moveHitbox() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            hitbox.setLocation(mouse.getX(), mouse.getY());
        }
    }

}
