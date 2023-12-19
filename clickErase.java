import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class clickShovel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class clickErase extends SmoothMover
{
    /**
     * Act - do whatever the clickShovel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    MyWorld MyWorld;
    Hero lastHero = null;
    
    public void addedToWorld(World world) {
        MyWorld = (MyWorld)getWorld();
    }
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            setLocation(mouse.getX(), mouse.getY());
            
    
            if (mouse.getX() < HeroBank.x1 || mouse.getX() > HeroBank.x2 || mouse.getY() < HeroBank.y1 || mouse.getY() > HeroBank.y2)  {
                if (lastHero != null) {
                    lastHero.opaque = false;
                }
                if (Greenfoot.mouseClicked(null)) {
                    
                    
                    MyWorld.erase.setSelected(false);
                    MyWorld.removeObject(this);
                    return;
                }
            } else {
                int x = (int)((mouse.getX()-HeroBank.x1)/HeroBank.xSpacing);
                int y = (int)((mouse.getY()-HeroBank.y1)/HeroBank.ySpacing);
                Hero current = MyWorld.board.getHero(x, y);
                if (current != null) {

                    if (lastHero != null && lastHero != current) {
                        lastHero.opaque = false;
                        lastHero = current;
                    } else {
                        lastHero = current;
                        lastHero.opaque = true;
                    }
                    
                } else {
                    if (lastHero != null) {
                        lastHero.opaque = false;
                    }
                }
                
                
                
                if (Greenfoot.mouseClicked(null)) {
                    
                    if (current != null) {
                        
                        MyWorld.board.removeHero(current.getXPos(), current.getYPos());
                        
                    } else {
                        AudioPlayer.play(60, "buttonStart.mp3");
                        
                    }
                    MyWorld.erase.setSelected(false);
                    MyWorld.removeObject(this);
                    return;
                }
            }
        }
        // Add your action code here.
    }
  
}
