import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


public class Enemies extends animatedObject
{
    public boolean fallen = false;
    public boolean eating = false;
    public boolean eatOnce = false;
    public int hp;
    public int maxHp;
    public double walkSpeed;
    public MyWorld MyWorld;
    public boolean spawnHead = false;
    public Hero target;
    public int eatSpeed;
    public boolean isAlive = true;
    public GreenfootImage[] die;
    public GreenfootImage[] headless;
    public GreenfootImage[] headlesseating;
    public GreenfootImage[] fall;
    public boolean resetAnim = false;
    public boolean finalDeath = false;
    public boolean fixAnim = false;
    /**
     * Act - do whatever the Zombie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Enemies() {
        die = importSprites("freedie", 6);
    }
    public void act()
    {
        if (getWorld() != null) {
        
            if (isLiving()) {
                update();
                
            } else {
                deathAnim();            
            }
        }
    }
    
    public void update() {
        
    }
    
    public void deathAnim() {
        if (!resetAnim) {
                frame = 0;    
                resetAnim = true;
        }
        if (frame <=6) {
            if (finalDeath) {
                if (!fixAnim) {
                    fixAnim = true;
                    AudioPlayer.play(80, "freedie1.mp3", "freedie2.mp3");
                    
                    MyWorld.removeObject(this);
                    return;
                }
                
                
            } else {
                if (!spawnHead) {
                    spawnHead = true;
                }
                if (!eating) {
                    animate(die, 350, false);
                    move(-walkSpeed);
                } else {
                    animate(die, 350, false);
                }
                
            }
        } else if (!finalDeath) {
            resetAnim = false;
            finalDeath = true;
            
            for (ArrayList<Enemies> i : MyWorld.level.enemiesRow) {
                if (i.contains(this)) {
                    i.remove(this);                    
                    break;
                }
            }
            
        } 

    }
    
    public void playEating() {
        if (frame == 5 || frame == 2) {
            if (!eatOnce) {
                eatOnce = true;
                AudioPlayer.play(70, "attack.mp3", "attack2.mp3", "attack3.mp3");
                
                target.hit(10);
            } 
        } else {
            eatOnce = false;
        }
    }
    @Override
    protected void addedToWorld(World world) {
        MyWorld = (MyWorld)getWorld();
        
    }
    public boolean isLiving() {
        if (hp <=0) {
            isAlive = false;
        } else {
            isAlive = true;
        }
        return isAlive;
    }
    public void hit(int dmg) {
        
    }
    
    public void takeDmg(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            for (ArrayList<Enemies> i : MyWorld.level.enemiesRow) {
                if (i.contains(this)) {
                    i.remove(this);                    
                    break;
                }
            }
            getWorld().removeObject(this);
            return;
        }
    }
    public boolean isEating() {
        var row = MyWorld.board.Board[getYPos()];
        for (int i = 0; i < MyWorld.board.Board[0].length; i++) {
            if (row[i] != null) {
                
                if (Math.abs(row[i].getX() - getX()+5) < 35) {
    
                    eating = true;
                    target = row[i];
                    return eating;
                }
                
            }
        }
        
        eating = false;
        return eating;
    
    }
    public int getYPos() {
        return ((getY()-MyWorld.level.yOffset)/MyWorld.level.ySpacing);
    }
    public int getXPos() {
        return getX();
    }
    
}
