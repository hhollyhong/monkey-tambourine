import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//stop
//intro screen
//end screen
//sound
//right/wrong animations

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board extends World
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Board()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        Greenfoot.setSpeed(30); //75 for quarter, 150 for eighth; 150 acts in 1 minute; each act takes 0.4s 
        //26: 85 
        //27: 95 for quarter; each +1 goes up 10 bpm
        //30: 143
        prepare();
    }
    
    public void prepare()
    {
        Leader monkey = new Leader(16,"monkey.png", "monkey2.png", "tap.mp3", "boink.wav", "happy.wav"); //in 4/4; 6 for 3/4 //option to choose time sig?
        addObject(monkey, 250, 200);
        Tambourine mtamb = new Tambourine("tambourine.png", "tambourine-hit.png", "tambourine-shake.png", "shake.wav", "hit.wav");
        addObject(mtamb, 430, 170);
        Tambourine utamb = new Tambourine("tambourine.png", "tambourine-hit.png", "tambourine-shake.png", "shake.wav", "hit.wav");
        addObject(utamb, 800, 450);
        Player hand = new Player(16, "player.png", "player-hit.png"); 
        addObject(hand, 600, 450);
    }
}