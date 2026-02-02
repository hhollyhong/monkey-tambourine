import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Leader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Leader extends Actor
{
    private int cbeat; 
    private int eighths; //8 in 4/4
    private int[] rhythm; //notes
    private int[] rhythmhs; //hit or shake
    private boolean turn;
    private boolean flip;
    private String img;
    private String imgflip;
    private String tap;
    //private String boink;
    //private String happy;
    
    public Leader()
    {
        
    }
    
    public Leader(int neweighths, String nimg, String nimgflip, String ntap, String nboink, String nhappy)
    {
        cbeat = 0;
        eighths = neweighths;
        rhythm = new int[eighths];
        rhythmhs = new int[eighths];
        turn = false; //start false to give starting empty measure
        flip = false;
        img = nimg;
        imgflip = nimgflip;
        tap = ntap;
        //boink = nboink;
        //happy = nhappy;
    }
    
    /**
     * Act - do whatever the Leader wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() //every run is 16th 
    {
        cbeat++;
        //System.out.println("beat: " + cbeat);
        if(cbeat % 4 == 1) //1, 5, 9, 13
        {
            if(flip == false)
            {
                setImage(imgflip);
                Greenfoot.playSound(tap);
                flip = true;
            }
            else if(flip == true)
            {
                setImage(img);
                Greenfoot.playSound(tap);
                flip = false;
            }
        }
        if(cbeat == 1 && turn == true) //makes the rhythm on 1 
        {
            makeRhythm();
        }
        if(turn == true) //plays beats 1 - 8
        {
            playRhythm();
        }
        if(cbeat == eighths) //on beat 8
        {
            cbeat = 0;
            if(turn == true) //don't go for the next measure
            {
                turn = false;
            }
            else if(turn == false)
            {
                turn = true;
            }
        }
    }
    
    public void makeRhythm()
    {
        //8 half beats in 1 measure; 1 = eighth, 2 = quarter, 3 = dotted quarter, 4 = half
        //16 in 1 measure; 2 = eighth, 4 = quarter, 6 = dotted quarter, 8 = half
        for(int j = 0; j < rhythm.length; j++) //reset rhythm array
        {
            rhythm[j] = 0;
        }
        
        int i = 0; //what array we are looking at
        int measure = eighths; //going to decrease; how many beats available to fill
        while(measure > 0)
        {
            int note = Greenfoot.getRandomNumber(4) + 1; //1-4
            note = note * 2;
            if(measure - note >= 0 && i % 2 == 0) //if we have enough room AND we are looking at whole/half beat
            {
                rhythm[i] = note; 
                measure = measure - note;
                i = i + note;
            }
            else if(i % 2 != 0) //move to whole/half beat
            {
                i++;
                measure--;
            }
        }
        
        //for(int j = 0; j < rhythm.length; j++)
        {
            //System.out.print(rhythm[j] + ", ");
        }
        //System.out.println();
    }
    
    public void playRhythm() 
    {
        Tambourine mtamb = getWorld().getObjects(Tambourine.class).get(0);
        int sh = Greenfoot.getRandomNumber(2) + 1;
        if(rhythm[cbeat-1] != 0) //if we are playing a note
        {
            rhythmhs[cbeat-1] = sh;
            if(sh == 1)
            {
                mtamb.hit();
            }
            else if(sh == 2)
            {
                mtamb.shake();
            }
        }
        else
        {
            rhythmhs[cbeat-1] = 0;
        }
    }
    
    public int[] getRhythm()
    {
        return rhythm;
    }
    
    public int[] getRhythmhs()
    {
        return rhythmhs;
    }
    
    public void conf()
    {
        QMark q = new QMark();
        getWorld().addObject(q, 125, 100);
        //Greenfoot.playSound(boink);
    }
    
    public void happy()
    {
        Flower f = new Flower();
        getWorld().addObject(f, 120, 95);
        //Greenfoot.playSound(happy);
    }
}