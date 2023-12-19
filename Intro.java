import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Intro here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Intro extends World {
    public GreenfootSound CYS = new GreenfootSound("intro.mp3");
    public int count = 0;
    public int scrollSpeed = 4;
    public int location = 0;
    public boolean started = false;
    public Enemies n = null;
    public HeroPacket[] bank = { new SaileachPacket(), new ChenPacket(), new SariaPacket() };

    public HeroBank heroBank = new HeroBank(bank);

    public Enemies[][] level1 = {
            { new Free(), n, null, null },
            { n },
            { n, null, null, null, new Free() },
            { n },
            { null, new Free(), null, new Free() },
            { new Free(), n, n, n, n, n, new Free() },
            { null, null, new Free(), new Free(), new Free() },
            { n },
            { new Free(), new Free(), new Free(), new Free(), new Free(), n, new Free() },
            { new Free(), n, null, null },
            { new Free(), new Free(), new Free(), new Free(), new Free(), n, new Free() },
            { null, new Free(), null, new Free() },
            { new Free(), new Free(), new Free(), new Free(), new Free(), n, new Free() },
            { new Free(), n, n, n, n, n, new Free() },
            { new Free(), new Free(), new Free(), new Free(), new Free(), n, new Free(), new Free(), n, n, n, n, n, new Free(), null, null, new Free(), new Free(), new Free() }
    };

    public WaveManager level = new WaveManager(23500L, level1, 15000L, true, 8,14);

    public Intro() {

        super(733, 430, 1, false);
        addObject(new FreeIdle(), 800, 200);
        addObject(new FreeIdle(), 900, 100);
        addObject(new FreeIdle(), 890, 370);
        addObject(new FreeIdle(), 822, 241);
        addObject(new FreeIdle(), 822, 241);

        CYS.setVolume(95);

    }

    public void act() {
        if (!started) {
            started = true;
            CYS.playLoop();
        }
        count++;
        bgScrollAnimate();

    }

    public void bgScrollAnimate() {
        if (count == 100) {

        }
        if (count > 100 && count < 160) {
            location -= scrollSpeed;

            scrollBGimage(location);
        } else if (count > 350 && count < 410) {
            location += scrollSpeed;

            scrollBGimage(location);
        } else if (count == 450) {
            List<IdleEnemies> idleEnemy = getObjects(IdleEnemies.class);
            for (IdleEnemies enemy : idleEnemy) {

                removeObject(enemy);
            }
        } else if (count == 500) {

            Greenfoot.setWorld(new MyWorld(CYS, level, heroBank, new Intro()));
        }

    }

    public void scrollBGimage(int offset) {
        GreenfootImage bg = getBackground();
        GreenfootImage move = new GreenfootImage("bg.png");
        bg.drawImage(move, offset, 0);

        // get all objects and move them by the offset delta value
        List<Actor> currentObjects = getObjects(null);

        for (Actor thisObject : currentObjects) {
            if (count > 100 && count < 160) {
                thisObject.setLocation(thisObject.getX() - scrollSpeed, thisObject.getY());
            } else if (count > 350 && count < 410) {
                thisObject.setLocation(thisObject.getX() + scrollSpeed, thisObject.getY());
            }

        }

    }
}
