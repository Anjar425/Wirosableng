import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
 
/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board extends Actor
{
    
     // instance variables - replace the example below with your own
    public Hero[][] Board = new Hero[5][9]; 
    public static final int xOffset = 205;
    public static final int yOffset = 95;
    public static final int xSpacing = 65;
    public static final int ySpacing = 72;
    
    Hero Chen = new Chen();
    Hero Saria = new Saria();
    Hero Saileach = new Saileach();
    
    /**
     * Constructor for objects of class Board
     */
    public Board()
    {
        
    }
    public void placeHero(int x, int y, Hero hero) {
        if (Board[y][x] == null) {
            Board[y][x] = hero;    
            World MyWorld = getWorld();
            
            MyWorld.addObject(hero, x*xSpacing+xOffset, y*ySpacing+yOffset);
            
            if(hero instanceof Saileach){
                AudioPlayer.play(100, "saileach.mp3");  
            }else if(hero instanceof Chen){
                AudioPlayer.play(80, "chen.mp3");
            } else {
                AudioPlayer.play(80, "saria.mp3");
            }
        }
        
    }
    public Hero getHero(int x, int y) {
        return Board[y][x];
    }
    public void removeHero(int x, int y) {
        if (Board[y][x] != null) {
            getWorld().removeObject(Board[y][x]);
            Board[y][x] = null;    
        }

        AudioPlayer.play(80,"retreat.mp3");
    }
    
    public void updateBoard() {
        for (int i = 0; i < Board.length; i++) {
            for (int k = 0; k < Board[0].length; k++) {
                if (Board[i][k] != null) {
                    World MyWorld = getWorld();
                    Hero temp = Board[i][k];
                    MyWorld.addObject(temp, k*2*xSpacing+xOffset, i*2*ySpacing+yOffset);
                }
            }
        }
        
    }
    
    /**
     * Act - do whatever the Board wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
}
