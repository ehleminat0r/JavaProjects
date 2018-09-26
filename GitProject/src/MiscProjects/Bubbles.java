/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiscProjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author lhassler
 */
class Ball
{
    Point pt = new Point();
    int speedx;
    int speedy;
    int size;
}

public class Bubbles  extends JPanel implements KeyListener {
    
    List<Ball> balls = new ArrayList<>(); 
    Timer timer = new Timer();

    public Bubbles(int w, int h)
    {
        this.setBackground(Color.white);
        this.setSize(w, h);
        for (int i=0; i<200; i++)
        {
            balls.add(new Ball());
            balls.get(i).pt = new Point((int)(Math.random()*w),(int)(Math.random()*h));
            balls.get(i).speedx = (int)(Math.random()*10)-5;
            balls.get(i).speedy = (int)(Math.random()*10)-5;
            balls.get(i).size = (int)(Math.random()*20)+4;
        }
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkBorder();
            }
        }, 20, 20);
        

    }
    
    private void checkBorder()
    {
        for (int i=0; i<balls.size(); i++)
        {
            balls.get(i).pt = new Point(balls.get(i).pt.x + balls.get(i).speedx, balls.get(i).pt.y + balls.get(i).speedy);
            
            if (balls.get(i).pt.x < 0)
            {
                balls.get(i).speedx *= -1;
            }
            if (balls.get(i).pt.x > this.getWidth())
            {
                balls.get(i).speedx *= -1;
            }
            
            if (balls.get(i).pt.y < 0)
            {
                balls.get(i).speedy *= -1;
            }
            if (balls.get(i).pt.y > this.getHeight())
            {
                balls.get(i).speedy *= -1;
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.blue);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (int i=0; i<balls.size(); i++)
        {
            g.setColor(Color.white);
            g.fillOval(balls.get(i).pt.x, balls.get(i).pt.y, balls.get(i).size , balls.get(i).size);
        }
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("a");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setLayout(null); 
        Bubbles panel = new Bubbles(frame.getWidth(),frame.getHeight());
        frame.add(panel);
        frame.addKeyListener(panel);
    }
     
}
