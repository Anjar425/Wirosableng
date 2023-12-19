import greenfoot.*;

public class MenuSai extends Button {
    public boolean clicked = false;
    GreenfootImage[] start;
    public int counter = 0;

    public MenuSai() {
        super("menuSai1.png", "menuSai2.png");
        start = importSprites("menuSai", 2);
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
                    AudioPlayer.play(85, "saileach.mp3");
                }
            }
        }
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new BioSai()); // Kembali ke kelas First
        }
    }
}
