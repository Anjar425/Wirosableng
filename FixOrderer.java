import greenfoot.*;

/**
 * Write a description of class Timer2 here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
class FixOrderer extends Actor {
    public long deltaTime;
    public long lastFrame = System.nanoTime();
    public long currentFrame = System.nanoTime();
    public long delayTime;
    public WaveManager level;

    public FixOrder(WaveManager level, long delayTime) {
        this.delayTime = delayTime;
        this.level = level;
    }

    public void act() {
        currentFrame = System.nanoTime();
        deltaTime = (currentFrame - lastFrame) / 1000000;
        if (deltaTime > delayTime) {
            level.fixOrder();

            getWorld().removeObject(this);
            return;
        }

    }
}
