import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class First here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class First extends World{
    
    public First()
    {    
        super(734, 430, 1, false); 
        setBackground(new GreenfootImage("transition.png"));
        addObject(new Transition(true, new OpeningFirst(),10),288, 215);
        setPaintOrder(EndTransition.class, Transition.class);
    }
    public void act() {
        
    }
}
