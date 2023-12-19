import greenfoot.*;  

public class CreditButton extends Button
{
    public boolean clicked = false;
    GreenfootImage[] start;
    public int counter = 0;
    public CreditButton() {
        super("Credit1.png", "Credit2.png");
        start = importSprites("Credit", 2);
    }
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        MainMenu world = (MainMenu)getWorld();
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
                    AudioPlayer.play(60, "buttonMenu.mp3");
            
                }
            }
        }
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new Credit()); 
        }
    }
}
