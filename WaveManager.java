import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*; //HAHAHADIE

/**
 * Write a description of class WaveManager here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class WaveManager extends Actor {
    public long currentFrame = System.nanoTime();
    public static final int xOffset = 760;
    public static final int yOffset = 65;
    public static final int ySpacing = 73;
    public ArrayList<ArrayList<Enemies>> enemiesRow = new ArrayList<ArrayList<Enemies>>();
    public ArrayList<Enemies> row1 = new ArrayList<Enemies>();
    public ArrayList<Enemies> row2 = new ArrayList<Enemies>();
    public ArrayList<Enemies> row3 = new ArrayList<Enemies>();
    public ArrayList<Enemies> row4 = new ArrayList<Enemies>();
    public ArrayList<Enemies> row5 = new ArrayList<Enemies>();

    public long lastFrame = System.nanoTime();
    public Enemies[][] level;
    public long levelTime;
    public long waveTime;
    public long firstWave;
    public long deltaTime;
    public long deltaTime2;

    public boolean won = false;
    public World MyWorld;
    public int wave = -1;
    public boolean first = false;
    public boolean finishedSending = false;
    public int[] hugeWaves;

    public WaveManager(long timeBetweenWaves, Enemies[][] level, long firstWave, boolean first, int... hugeWaves) {
        this.level = level;
        this.levelTime = levelTime;
        this.waveTime = timeBetweenWaves;
        this.firstWave = firstWave;
        this.hugeWaves = hugeWaves;
        this.first = first;
        enemiesRow.add(row1);
        enemiesRow.add(row2);
        enemiesRow.add(row3);
        enemiesRow.add(row4);
        enemiesRow.add(row5);
    }

    public void startLevel() {
        wave = 0;
        AudioPlayer.play(80, "linkStart.mp3");
        MyWorld.addObject(new LinkStart(), 400, 230);
    }

    // Fix order cause no setPaintOrder for actors :(

    public void act() {

        if (wave != -1) {
            currentFrame = System.nanoTime();
            deltaTime = (currentFrame - lastFrame) / 1000000;
        } else {
            lastFrame = System.nanoTime();
        }
        if (wave > level.length - 1) {
            MyWorld.addObject(new finishedSending(this, 15000L), 0, 0);
            wave = -1;
        }

        if (deltaTime >= firstWave && wave != -1 && first == true) {
            checkSendWave();

            wave++;
            lastFrame = System.nanoTime();
            first = false;
        }
        if (first == false && wave != -1) {
            if ((deltaTime >= waveTime) || MyWorld.getObjects(Enemies.class).size() == 0) {
                checkSendWave();

                wave++;
                lastFrame = System.nanoTime();

            }
        }
    }

    public void checkSendWave() {
        for (int i : hugeWaves) {
            if (i == wave) {
                if (wave == level.length - 1) {
                    AudioPlayer.play(70, "hugewave.mp3");
                    finishedSending = false;
                    sendHugeWave(level[wave]);
                    MyWorld.addObject(new AHugeWave(true), 360, 215);
                    return;
                } else {
                    AudioPlayer.play(70, "hugewave.mp3");
                    finishedSending = false;
                    sendHugeWave(level[wave]);
                    MyWorld.addObject(new AHugeWave(false), 360, 215);
                    return;
                }
            }
        }
        sendWave(level[wave]);
    }

    @Override
    protected void addedToWorld(World world) {
        MyWorld = (MyWorld) getWorld();
        lastFrame = System.nanoTime();
        currentFrame = System.nanoTime();

        Progress progressBar = new Progress(this);
        getWorld().addObject(progressBar, 490, 25);

    }

    public boolean hasWon() {
        if (wave == -1 && finishedSending && MyWorld.getObjects(Enemies.class).size() == 0) {
            won = true;
        } else {
            won = false;
        }
        return won;
    }

    public void sendWave(Enemies[] wave) {

        for (int i = 0; i < wave.length; i++) {
            if (i < 5) {
                if (wave[i] != null) {
                    // Send!
                    MyWorld.addObject(wave[i], xOffset, (i % 5) * ySpacing + yOffset);
                    enemiesRow.get(i % 5).add(wave[i]);
                }
            } else {

                // If more then 1 zombie per row, delay depending on how many
                if (wave[i] != null) {
                    finishedSending = false;
                    int wait = (int) (i / 5);
                    int offset = xOffset + wait * 20;
                    MyWorld.addObject(wave[i], offset, (i % 5) * ySpacing + yOffset);
                    enemiesRow.get(i % 5).add(wave[i]);

                }

                /*
                 * Deprecated
                 * if (wave[i] != null) {
                 * finishedSending = false;
                 * int wait = (int)(i/5);
                 * long delayTime = (long)(wait*4000L);
                 * Timer delay = new Timer();
                 * delay.schedule(new DelayWave(wave, i, this), delayTime);
                 * }
                 */
            }
        }
        MyWorld.addObject(new FixOrder(this, 1000L), 0, 0);
    }

    public void sendHugeWave(Enemies[] wave) {

        for (int i = 0; i < wave.length; i++) {
            if (i < 5) {
                if (wave[i] != null) {
                    MyWorld.addObject(wave[i], xOffset + 50, (i % 5) * ySpacing + yOffset);
                    enemiesRow.get(i % 5).add(wave[i]);
                }
            } else {
                if (wave[i] != null) {
                    finishedSending = false;
                    int wait = (int) (i / 5);
                    int offset = xOffset + 50 + wait * 20;
                    MyWorld.addObject(wave[i], offset, (i % 5) * ySpacing + yOffset);
                    enemiesRow.get(i % 5).add(wave[i]);

                }
            }
        }
        MyWorld.addObject(new FixOrder(this, 1000L), 0, 0);
    }

}
