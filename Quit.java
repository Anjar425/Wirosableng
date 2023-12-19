import greenfoot.*;

public class Quit extends Button {
    public boolean clicked = false;
    GreenfootImage[] start;
    public int counter = 0;

    public Quit() {
        super("quit1.png", "quit2.png");
        start = importSprites("quit", 2);
    }

    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        MainMenu world = (MainMenu) getWorld();
        if (clicked) {
            animate(start, 80, true);
            counter++;
            if (counter == 200) {

                update();
            }
        } else {
            if (mouse != null) {
                world.moveHitbox();
                if (this.intersects(world.hitbox)) {
                    setImage(hover);
                } else {
                    setImage(idle);
                }
                if (Greenfoot.mouseClicked(this)) {
                    clicked = true;
                    world.menutheme.stop();
                    AudioPlayer.play(90, "buttonMenu.mp3");
                }

            }
        }
        if (Greenfoot.mouseClicked(this)) {
            System.exit(0); // Mengakhiri program
        }
    }
}
