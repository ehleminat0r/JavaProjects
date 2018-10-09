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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author lhassler
 */

public class Bubbles  extends JPanel implements KeyListener {
    
    List<Ball> balls = new ArrayList<>(); 
    Timer timer = new Timer();
    BufferedImage img = new BufferedImage(795, 572, BufferedImage.TYPE_INT_RGB);
    boolean showBalls = true;

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
        balls.get(0).color = Color.WHITE;

        // check timer
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
            balls.get(balls.size()-1).color = Color.RED;

            balls.add(new Ball());
            balls.get(balls.size()-1).pt = new Point(balls.get(i).pt);
            balls.get(balls.size()-1).speedx = (int)(Math.random()*5+1)*-1;
            balls.get(balls.size()-1).speedy = (int)(Math.random()*5+1);
            balls.get(balls.size()-1).size = balls.get(i).size/2+6;
            balls.get(balls.size()-1).color = Color.GREEN;

            balls.add(new Ball());
            balls.get(balls.size()-1).pt = new Point(balls.get(i).pt);
            balls.get(balls.size()-1).speedx = (int)(Math.random()*5+1);
            balls.get(balls.size()-1).speedy = (int)(Math.random()*5+1)*-1;
            balls.get(balls.size()-1).size = balls.get(i).size/2+6;
            balls.get(balls.size()-1).color = Color.BLUE;

            balls.add(new Ball());
            balls.get(balls.size()-1).pt = new Point(balls.get(i).pt);
            balls.get(balls.size()-1).speedx = (int)(Math.random()*5+1);
            balls.get(balls.size()-1).speedy = (int)(Math.random()*5+1);
            balls.get(balls.size()-1).size = balls.get(i).size/2+6;
            balls.get(balls.size()-1).color = Color.YELLOW;
            if (i<1024)
            {
                ballsave[i] = balls.get(i);    
            }
        }

        for (int i=0; i<ballsave.length; i++)
        {
            if (ballsave[i] != null)
            {
                balls.remove(ballsave[i]);
            }
        }        
        System.out.println("Number of Ball Objects: "+balls.size());

    }
    
    private void checkBorder()
    {
        for (int i=0; i<balls.size(); i++)
        {
            // move balls
            balls.get(i).pt = new Point(balls.get(i).pt.x + balls.get(i).speedx, balls.get(i).pt.y + balls.get(i).speedy);
            // draw line
            if (balls.get(i).pt.x >= 0 && balls.get(i).pt.x <= 795 && balls.get(i).pt.y >= 0 && balls.get(i).pt.y <= 572)
            {
                if (i%4 == 0)
                {
                    img.setRGB(balls.get(i).pt.x, balls.get(i).pt.y, Color.RED.getRGB());
                }
                if (i%4 == 1)
                {
                    img.setRGB(balls.get(i).pt.x, balls.get(i).pt.y, Color.GREEN.getRGB());
                }
                if (i%4 == 2)
                {
                    img.setRGB(balls.get(i).pt.x, balls.get(i).pt.y, Color.BLUE.getRGB());
                }
                if (i%4 == 3)
                {
                    img.setRGB(balls.get(i).pt.x, balls.get(i).pt.y, Color.YELLOW.getRGB());
                }
                
            }
            // check border
            if (balls.get(i).pt.x < 0)
            {
                balls.get(i).speedx = Math.abs(balls.get(i).speedx);
            }
            if (balls.get(i).pt.x > this.getWidth()-35)
            {
                balls.get(i).speedx = -Math.abs(balls.get(i).speedx);
            }
            
            if (balls.get(i).pt.y < 0)
            {
                balls.get(i).speedy = Math.abs(balls.get(i).speedy);
            }
            if (balls.get(i).pt.y > this.getHeight()-50)
            {
                balls.get(i).speedy = -Math.abs(balls.get(i).speedy);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        // background with colored lines
        g.drawImage(img, 0, 0, this);

        // draw balls
        if (showBalls)
        {
            for (int i=0; i<balls.size(); i++)
            {
                g.setColor(balls.get(i).color);
                g.fillOval(balls.get(i).pt.x, balls.get(i).pt.y, balls.get(i).size , balls.get(i).size);
                g.drawString(Integer.toString(i), balls.get(i).pt.x, balls.get(i).pt.y);
            }
            g.setColor(Color.white);
            g.drawString("Balls enabled", 10, 20);
        }
        else
        {
            g.setColor(Color.white);
            g.drawString("Balls disabled", 10, 20);
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
        if (ke.getKeyCode()== KeyEvent.VK_1)
        {
            showBalls = !showBalls;
        }
        if (ke.getKeyCode()== KeyEvent.VK_ENTER)
        {
            File outputfile = new File("C:\\Users\\lhassler\\Desktop\\image.jpg");
            try {
                ImageIO.write(img, "jpg", outputfile);
            } catch (IOException ex) {
                Logger.getLogger(Bubbles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
