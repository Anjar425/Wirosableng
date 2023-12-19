import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Golem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Golem extends Enemies
{
    /**
     * Act - do whatever the Golem wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public GreenfootImage[] idle;
    public GreenfootImage[] walk;
    public GreenfootImage[] attack;
    public GreenfootImage[] die;
    
    public void Golem()
    {
        walk = importSprites("freeidle", 8);
        attack = importSprites("freeattack", 6);
        die = importSprites("freedie", 6);

        walkSpeed = Random.Double(11, 14);
        maxHp = 200;
        hp = maxHp;
    }
        public void update() {
        if (hp > 50) {
            if (!isEating()) {
                animate(walk, 350, true);
                move(-walkSpeed);
            } else {
                animate(attack, 200, true);
                playEating();
            }
        } else {
            if (!fallen) {
                fallen = true;
            }
            if (!isEating()) {
                animate(walk, 350, true);
                move(-walkSpeed);
            } else {
                animate(attack, 200, true);
                playEating();
            }

        }

    }
        public void hit(int dmg) {
        AudioPlayer.play(70, "hitDamage.mp3");
        if (isLiving()) {

            if (!fallen) {
                if (!eating) {
                    hitFlash(walk, "freeidle");
                } else {
                    hitFlash(attack, "freeattack");
                }
            } else {
                if (!eating) {
                    hitFlash(walk, "freeidle");
                } else {
                    hitFlash(attack, "freeattack");
                }

            }

            hp -= dmg;
        } else if (!finalDeath) {
            if (!eating) {
                hitFlash(die, "freedie");
            } else {
                hitFlash(die, "freedie");
            }
        }

    }
}
