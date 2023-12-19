import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class MyWorld extends World {

    private boolean isPlaying = false;
    public boolean lose = false;
    public boolean loseOnce = false;
    public boolean winOnce = false;
    public Board board = new Board();
    public GreenfootSound inGame = new GreenfootSound("inGame.mp3");
    public GreenfootSound CYS;
    public Enemies n = null;
    public World restartWorld;
    public GreenfootSound menutheme = new GreenfootSound("menutheme.mp3");
    public FallingObject winPlant;

    public Enemies[][] level1 = {
            { null, new Free(), null, null },
            { n },
            { new Free(), null, null, null, null },
            { n },
            { null, new Free(), null, new Free() },
            { new Free() },
    };

    public HeroPacket[] bank = { new SaileachPacket(), new ChenPacket(), new SariaPacket() };
    public HeroBank heroBank = new HeroBank(bank);
    public Hitbox hitbox = new Hitbox();
    public Erase erase = new Erase();

    public WaveManager level = new WaveManager(23500L, level1, 20000L, true, 8, 18);

    public void stopped() {
        if (inGame.isPlaying()) {
            inGame.pause();
        }

    }

    public void started() {
        if (!inGame.isPlaying()) {
            inGame.playLoop();
        }
        Greenfoot.setSpeed(50);

    }

    public void moveHitbox() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            hitbox.setLocation(mouse.getX(), mouse.getY());
        }
    }

    public void finishLevel() {
        inGame.stop();
        AudioPlayer.play(70, "winmusic.mp3");

    }

    public boolean hasLost() {
        for (Enemies i : getObjects(Enemies.class)) {

            if (i.getWorld() != null && i.getX() < 125) {
                lose = true;
                return lose;
            } else {
                lose = false;
            }

        }
        return lose;
    }

    public boolean hasWon() {
        return level.hasWon();
    }

    public MyWorld(GreenfootSound CYS, WaveManager level, HeroBank heroBank, World restartWorld) {
        super(734, 430, 1, false);
        this.CYS = CYS;
        this.heroBank = heroBank;
        this.restartWorld = restartWorld;
        this.level = level;
        this.winPlant = winPlant;
        Greenfoot.setSpeed(50);
        setBackground("bg.png");
        addObject(heroBank, 0, 0);
        addObject(board, 0, 0);
        addObject(hitbox, 0, 0);
        addObject(erase, 690, 420);
        setPaintOrder(Transition.class, AHugeWave.class, LinkStart.class, EnergyCounter.class, clickErase.class,
                Erase.class, TransparentObject.class, HeroPacket.class, FallingEnergy.class, Energy.class, Dirt.class,
                Projectile.class, FallingObject.class, Enemies.class, Hero.class);

    }

    public void act() {
        if (!isPlaying) {
            addObject(level, 0, 0);
            addObject(new DelayAudio(inGame, CYS, 70, true, 2000L), 0, 0);

            level.startLevel();
            isPlaying = true;

        }
        if (!loseOnce && hasLost()) {
            inGame.stop();
            AudioPlayer.play(80, "lose.mp3");

            loseOnce = true;
            Greenfoot.delay(50);

            addObject(new Transition(false, new GameOver(restartWorld), "gameover.png", 3), 365, 215);
        } else if (!winOnce && hasWon()) {
            winOnce = true;
            inGame.stop();
            addObject(new Transition(false, new Ending(), "Congratulations.png", 1), 365, 215);
        } else {
            if (Greenfoot.isKeyDown("1")) {
                CYS.stop();
                inGame.stop();
                Greenfoot.setWorld(new Intro());

            }
        }
    }
}
