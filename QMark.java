import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class QMark here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QMark extends Actor
{
    int timer = 0;
    
    /**
     * Act - do whatever the QMark wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        timer++;
        if(timer == 2)
        {
            remove();
        }
    }
    
    public void remove()
    {
        getWorld().removeObject(this);
    }
}
