import greenfoot.*;

public class Hero extends animatedObject {
    public int maxHp;
    public boolean isAlive = true;
    public int hp;
    public int damage;
    public boolean opaque = false;
    public MyWorld MyWorld;

    public Hero() {

    }

    public void act() {
        if (getWorld() != null) {
            if (isLiving()) {
                update();
                if (!opaque) {
                    getImage().setTransparency(255);
                } else {
                    getImage().setTransparency(125);
                }
                displayHealthBar();
                drawIndicatorBar();
            } else {
                MyWorld = (MyWorld) getWorld();
                AudioPlayer.play(80, "herodie.mp3");

                MyWorld.board.removeHero(getXPos(), getYPos());
                MyWorld.removeObject(this);

                return;
            }
        }
    }

    public void update() {

    }

    public int getXPos() {
        return ((getX() - Board.xOffset + 30) / Board.xSpacing);
    }

    public int getYPos() {
        return ((getY() - Board.yOffset + 30) / Board.ySpacing);
    }

    @Override
    public void addedToWorld(World world) {
        MyWorld = (MyWorld) getWorld();
        MyWorld.addObject(new Dirt(), getX(), getY() + 30);
    }

    public boolean isLiving() {
        if (hp <= 0) {
            isAlive = false;
        } else {
            isAlive = true;
        }
        return isAlive;
    }

    public void hit(int dmg) {

    }

    public void drawIndicatorBar() {

    }

    public void displayHealthBar() {
        int barWidth = 60; // Width of the health bar
        int barHeight = 4; // Height of the health bar
        int healthBarWidth = (int) ((double) hp / maxHp * barWidth); // Calculate width based on current health

        GreenfootImage healthBar = new GreenfootImage(barWidth, barHeight);
        healthBar.setColor(Color.GRAY);
        healthBar.fillRect(0, 0, barWidth, barHeight);
        healthBar.setColor(Color.BLUE);
        healthBar.fillRect(0, 0, healthBarWidth, barHeight);

        int xOffset = (getImage().getWidth() - barWidth) / 2;
        int yOffset = getImage().getHeight() / 2 + barHeight - 375;

        getImage().drawImage(healthBar, xOffset, -yOffset);
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void heal(int amount) {
        hp = Math.min(hp + amount, maxHp); // Pastikan hp tidak melebihi maxHp
    }

}
