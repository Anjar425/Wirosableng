import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Saria here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Saria extends Hero {
    private GreenfootImage[] idle;
    private GreenfootImage[] hit;
    private boolean test = false;
    private long lastFrame2 = System.nanoTime();
    private long deltaTime2;

    private int barWidth = 60;
    private int barHeight = 4;
    private int currentFill = 0;
    private int maxFill = 1000;
    private GreenfootImage indicatorBar;

    public Saria() {
        idle = importSprites("Sariaidle", 3);
        hit = importSprites("SariaHit", 6);
        maxHp = 730;
        hp = maxHp;

        indicatorBar = new GreenfootImage(barWidth, barHeight);
        indicatorBar.setColor(Color.GRAY);
        indicatorBar.fillRect(0, 0, barWidth, barHeight);
    }

    public void update() {
        animate(hit, 300, true);
        fillIndicatorBar();
        drawIndicatorBar();
        checkAndSkillActive();
    }

    private void fillIndicatorBar() {
        // Mengisi bar seiring waktu
        currentFill += 1;
        if (currentFill > maxFill) {
            currentFill = maxFill;
        }
    }

    private void checkAndSkillActive() {
        // Memeriksa apakah bar sudah penuh
        World world = getWorld();
        List<Hero> heroes = getObjectsInRange(9, Hero.class);
        if (currentFill >= maxFill && isLiving() && hp < maxHp) {
            healHeroes();
            currentFill = 0;
        }
        for (Hero hero : heroes) {
            if (currentFill >= maxFill && hero.isLiving() && hero.getHp() < hero.getMaxHp()) {
                healHeroes();
                currentFill = 0;
            }
        }
    }

    public void hit(int dmg) {
        if (isLiving()) {
            animate(hit, 300, true);
            hp = hp - dmg;
        }
    }

    private void healHeroes() {
        World world = getWorld();
        List<Hero> heroes = getObjectsInRange(9, Hero.class);

        for (Hero hero : heroes) {
            if (hero.isLiving() && hero.getHp() < hero.getMaxHp()) {
                int healingAmount = 150;
                hero.heal(healingAmount);
            }
        }

        // Menyembuhkan Saria sendiri jika dibutuhkan
        if (isLiving() && hp < maxHp) {
            int healingAmount = 150;
            heal(healingAmount);
        }
    }

    public void drawIndicatorBar() {
        int xOffset = (getImage().getWidth() - barWidth) / 2;
        int yOffset = getImage().getHeight() / 2 + barHeight - 379;
        indicatorBar.setColor(Color.GREEN);
        indicatorBar.fillRect(0, 0, (int) (barWidth * ((double) currentFill / maxFill)), barHeight);
        indicatorBar.setColor(Color.GRAY);
        indicatorBar.fillRect((int) (barWidth * ((double) currentFill / maxFill)), 0,
                barWidth - (int) (barWidth * ((double) currentFill / maxFill)), barHeight);
        getImage().drawImage(indicatorBar, xOffset, -yOffset);
    }
}
