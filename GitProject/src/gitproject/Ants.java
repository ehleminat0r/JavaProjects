/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lhassler
 */
public class Ants
{ 
    static List<Nest> nests = new ArrayList<>();
    static List<Ant> ants = new ArrayList<>();
    static Random rnd = new Random();
    static int timervar = 0;
    
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Ants");
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(100, 100);
        frame.setVisible(true);
        frame.setLayout(null); 
        AntPanel ap = new AntPanel(nests,ants);
        frame.add(ap);
        frame.addMouseListener(ap);
        ap.setSize(frame.getWidth()-6, frame.getHeight()-29);
        nests.add(new Nest(50,30));
        nests.add(new Nest(40,400));
        //nests.add(new Nest(400,420));
        //nests.add(new Nest(400,50));
        ants.add(new Ant(rnd.nextInt(400),rnd.nextInt(400),0,nests));
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                if (timervar>0)
                {
                    timervar--;
                }
                else
                {
                    timervar = rnd.nextInt(500);
                    ants.add(new Ant(nests.get(0).getX(),nests.get(0).getY(),0,nests));
                }
                for (int i=0; i<ants.size(); i++)
                {
                    ants.get(i).walk();
                }
                
            }
        }, 2, 2);
    }
}

class Ant
{
    private int x;
    private int y;
    int pos;
    List<Nest> nests;

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
    public Ant(int x, int y, int pos, List<Nest> nests)
    {
        this.x = x;
        this.y = y;
        this.pos = pos;
        this.nests = nests;
    }
    
    public void walk()
    {
        if (nests.get(pos).getX()>x)
        {
            x++;
        }
        else if (nests.get(pos).getX()<x)
        {
            x--;
        }
        if (nests.get(pos).getY()>y)
        {
            y++;
        }
        else if (nests.get(pos).getY()<y)
        {
            y--;
        }
        if (nests.get(pos).getY()==y && nests.get(pos).getX()==x)
        {
            pos = (int)(Math.random()*(nests.size()));
        }
    }

}

class Nest
{
    private int x;
    private int y;
    
    public Nest(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
}

class AntPanel extends JPanel implements MouseListener
{
    List<Nest> nests;
    List<Ant> ants;
    
    public AntPanel(List<Nest> nests, List<Ant> ants)
    {
        this.nests = nests;
        this.ants = ants;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        
        // draw nests
        for(int i=0; i< nests.size(); i++)
        {
            g.fillRect(nests.get(i).getX()-15, nests.get(i).getY()-15, 30, 30);
        }
        
        // draw ants
        for(int i=0; i< ants.size(); i++)
        {
            g.fillOval(ants.get(i).getX(), ants.get(i).getY(), 10, 10);
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        nests.add(new Nest(me.getX()-0,me.getY()-27));
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
