import greenfoot.*;

public class Saileach extends Hero {
    private GreenfootImage[] idle;
    private int barWidth = 60;
    private int barHeight = 4;
    private int currentFill = 0;
    private int maxFill = 1000;
    private GreenfootImage indicatorBar;
    private long lastFrame2 = System.nanoTime();
    private long deltaTime2;

    public Saileach() {
        idle = importSprites("sai", 8);
        maxHp = 60;
        hp = maxHp;
        indicatorBar = new GreenfootImage(barWidth, barHeight);
        indicatorBar.setColor(Color.GRAY);
        indicatorBar.fillRect(0, 0, barWidth, barHeight);
    }

    public void update() {
        // Logika lainnya di sini
        animate(idle, 200, true);
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
        if (currentFill >= maxFill) {
            produceEnergy();
            currentFill = 0;
        }
    }

    public void hit(int dmg) {
        if (isLiving()) {
            hitFlash(idle, "sai");
        }
        hp = hp - dmg;
    }

    public void produceEnergy() {
        MyWorld.addObject(new Energy(), getX(), getY() - 10);
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
