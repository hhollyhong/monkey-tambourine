import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Drum here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tambourine extends Actor
{
    boolean isHit;
    boolean isShake;
    private String tImage;
    private String tHitImage;
    private String tShakeImage;
    private String tHitSound;
    private String tShakeSound;
    private int timer;
    private long startTime;
    
    public Tambourine()
    {
        
    }
    
    public Tambourine(String ntImage, String ntHitImage, String ntShakeImage, String ntShakeSound, String ntHitSound)
    {
        isHit = false;
        isShake = false;
        tImage = ntImage;
        tHitImage = ntHitImage;
        tHitSound = ntHitSound;
        tShakeImage = ntShakeImage;
        tShakeSound = ntShakeSound;
        timer = 0;
        startTime = System.currentTimeMillis();
    }
    
    /**
     * Act - do whatever the Drum wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(isHit == true || isShake == true)
        {
            timer++;
        }        
        if(timer == 2)
        {
            reset();
            timer = 0;
            //System.out.println("reset image");
            isHit = false;
            isShake = false;
        }
    }
    
    public void hit()
    {
        setImage(tHitImage);
        //System.out.println("hit");
        Greenfoot.playSound(tHitSound);
        isHit = true;
    }
    
    public void shake()
    {
        setImage(tShakeImage);
        //System.out.println("shake");
        Greenfoot.playSound(tShakeSound);
        isShake = true;
    }
    
    public void reset()
    {
        setImage(tImage);
    }
}
