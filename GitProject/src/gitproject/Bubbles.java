/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

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

public class Bubbles  extends JPanel implements KeyListener {
    
    List<Ball> balls = new ArrayList<>(); 
    Timer timer = new Timer();

    public Bubbles(int w, int h)
    {
        this.setBackground(Color.white);
        this.setSize(w, h);
        
        // single ball
        balls.add(new Ball());
        balls.get(0).pt = new Point(380,280);
        balls.get(0).speedx = 0;
        balls.get(0).speedy = 0;
        balls.get(0).size = 80;
        
        
        // many balls
        /* 
        for (int i=0; i<200; i++)
        {
            balls.add(new Ball());
            balls.get(i).pt = new Point((int)(Math.random()*w),(int)(Math.random()*h));
            balls.get(i).speedx = (int)(Math.random()*10)-5;
            balls.get(i).speedy = (int)(Math.random()*10)-5;
            balls.get(i).size = (int)(Math.random()*20)+4;
        }
        */
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkBorder();
            }
        }, 20, 20);
    }
    
    private void splitBall()
    {
        int save = balls.size();
        Ball[] ballsave = new Ball[1024];
        for (int i=0; i<save; i++)
        {
            balls.add(new Ball());
            balls.get(balls.size()-1).pt = new Point(balls.get(i).pt);
            balls.get(balls.size()-1).speedx = (int)(Math.random()*5+1)*-1;
            balls.get(balls.size()-1).speedy = (int)(Math.random()*5+1)*-1;
            balls.get(balls.size()-1).size = balls.get(i).size/2+6;

            balls.add(new Ball());
            balls.get(balls.size()-1).pt = new Point(balls.get(i).pt);
            balls.get(balls.size()-1).speedx = (int)(Math.random()*5+1)*-1;
            balls.get(balls.size()-1).speedy = (int)(Math.random()*5+1);
            balls.get(balls.size()-1).size = balls.get(i).size/2+6;

            balls.add(new Ball());
            balls.get(balls.size()-1).pt = new Point(balls.get(i).pt);
            balls.get(balls.size()-1).speedx = (int)(Math.random()*5+1);
            balls.get(balls.size()-1).speedy = (int)(Math.random()*5+1)*-1;
            balls.get(balls.size()-1).size = balls.get(i).size/2+6;

            balls.add(new Ball());
            balls.get(balls.size()-1).pt = new Point(balls.get(i).pt);
            balls.get(balls.size()-1).speedx = (int)(Math.random()*5+1);
            balls.get(balls.size()-1).speedy = (int)(Math.random()*5+1);
            balls.get(balls.size()-1).size = balls.get(i).size/2+6;
            
            ballsave[i] = balls.get(i);
        }

        for (int i=0; i<ballsave.length; i++)
        {
            if (ballsave[i] != null)
            {
                balls.remove(ballsave[i]);
            }
            //balls.remove(i);
        }
        
        System.out.println(balls.size());
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
            if (balls.get(i).pt.x > this.getWidth()-35)
            {
                balls.get(i).speedx *= -1;
            }
            
            if (balls.get(i).pt.y < 0)
            {
                balls.get(i).speedy *= -1;
            }
            if (balls.get(i).pt.y > this.getHeight()-50)
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
            g.drawString(Integer.toString(i), balls.get(i).pt.x, balls.get(i).pt.y);
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
        splitBall();
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
