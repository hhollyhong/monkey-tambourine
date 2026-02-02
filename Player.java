import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Playe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int cbeat; //keeps track of current beat; 1-8
    private int eighths; //time signature
    private long tscore;
    private long score; 
    private int round; 
    private boolean isHit;
    private boolean isShake;
    private boolean turn;
    private String img;
    private String himg;
    
    public Player()
    {
        
    }
    
    public Player(int neighths, String nimg, String nhimg)
    {
        cbeat = 0; 
        eighths = neighths;
        tscore = eighths * 8;
        score = eighths * 8; //ex. in 4/4, 8 half beats x 8 turns
        round = 0;
        isHit = false;
        isShake = false;
        turn = false;
        img = nimg;
        himg = nhimg;
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() //every act is half beat
    {
        cbeat++;
        phit();
        pshake();
        if(turn == true)
        {
            checkRhythm();
        }
        if(cbeat == eighths) //not necessarily 8, if in diff time signature
        {
            cbeat = 0;
            round++; //round = 0 is intro measure
            if(turn == false && round > 1)
            {
                turn = true;
            }
            else if(turn == true)
            {
                turn = false;
            }
        }
        if(round == 17)
        {
            end();
        }
    }
    
    public void checkRhythm() //maybe better to have in leader class? 
    {
        Leader temp = getWorld().getObjects(Leader.class).get(0);
        int[] rhythm = temp.getRhythm();
        int[] rhythmhs = temp.getRhythmhs();
        
        if(rhythm[cbeat-1] != 0)
        {
            if(rhythmhs[cbeat-1] == 1 && !Greenfoot.isKeyDown("e")) //missing a note
            {
                score--;
                temp.conf();
            }
            else if(rhythmhs[cbeat-1] == 2 && !Greenfoot.isKeyDown("o"))
            {
                score--;
                temp.conf();
            }
        }
        else //rhythm[cbeat-1] == 0
        {
            if(Greenfoot.isKeyDown("e") || Greenfoot.isKeyDown("o")) //playing on a rest
            {
                score--;
                temp.conf();
            }
        }
        
        if(cbeat == rhythm.length) //if at end of array
        {
            if(score == tscore) //if perfect
            {
                temp.happy();
            }
            tscore = score;
        }
    }
    
    public void phit()
    {
        Tambourine temp = getWorld().getObjects(Tambourine.class).get(1);
        if(isHit == false && Greenfoot.isKeyDown("e"))
        {
            temp.hit();
            setImage(himg);
            isHit = true;
        }
        if(isHit == true && !Greenfoot.isKeyDown("e"))
        {
            setImage(img);
            temp.reset();
            isHit = false;
        }
    }
    
    public void pshake()
    {
        Tambourine temp = getWorld().getObjects(Tambourine.class).get(1);
        if(isShake == false && Greenfoot.isKeyDown("o"))
        {
            temp.shake();
            setImage(himg);
            isShake = true;
        }
        if(isShake == true && !Greenfoot.isKeyDown("o"))
        {
            setImage(img);
            temp.reset();
            isShake = false;
        }
    }
    
    public void end()
    {
        Greenfoot.playSound("end.mp3");
        long total = eighths * 8;
        double fscore = ((double) score/total)*100;
        Greenfoot.stop();
        getWorld().showText("SCORE: " + Math.round(fscore), 500, 300);
    }
}
