/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lhassler
 */
public class Driver extends JPanel implements KeyListener{
    Image image;
    boolean keyW = false;
    boolean keyA = false;
    boolean keyS = false;
    boolean keyD = false;
    
    double x=50;
    double y=50;
    double speed = 0.1;
    int winkel = 0;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Driver");
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(100, 100);
        frame.setVisible(true);
        frame.setLayout(null);
        Driver test = new Driver();
        test.setSize(500,500);
        test.setBackground(Color.white);
        frame.add(test);
        frame.addKeyListener(test);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        move();
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 500);
        g.setColor(Color.black);
        g.drawRect(0, 0, 493 , 470);
        try {
            image = ImageIO.read(getClass().getResource("../img/ant_up.png"));
        } catch (IOException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        Graphics2D g2d = (Graphics2D)g;
        //Make a backup so that we can reset our graphics object after using it.
        //AffineTransform backup = g2d.getTransform();
        //rx is the x coordinate for rotation, ry is the y coordinate for rotation, and angle
        //is the angle to rotate the image. If you want to rotate around the center of an image,
        //use the image's center x and y coordinates for rx and ry.
        AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(-winkel-180), x+(image.getWidth(this)/2), y+(image.getHeight(this)/2));
        //Set our Graphics2D object to the transform
        g2d.setTransform(a);
        //Draw our image like normal
        g2d.drawImage(image, (int)x, (int)y, null);
        //Reset our graphics object so we can draw with it again.
        //g2d.setTransform(backup);
    
        //g.drawImage(image, (int)x, (int)y, this);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() == 'w')
        {
            keyW = true;
        }
        else if (ke.getKeyChar() == 's')
        {
            keyS = true;
        }
        
        if (ke.getKeyChar() == 'a')
        {
            keyA = true;
        }
        if (ke.getKeyChar() == 'd')
        {
            keyD = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
                if (ke.getKeyChar() == 'w')
        {
            keyW = false;
        }
        else if (ke.getKeyChar() == 's')
        {
            keyS = false;
        }
        
        if (ke.getKeyChar() == 'a')
        {
            keyA = false;
        }
        if (ke.getKeyChar() == 'd')
        {
            keyD = false;
        }
    }

    private void move() {
        if (keyW)
        {
            speed += 0.01;
        }
        if (keyS)
        {
            speed -= 0.01;
        }
        
        if (keyA)
        {
            if (winkel <= 359)
            {
                winkel += 2;
            }
            else
            {
                winkel = 0;
            }
        }
        if (keyD)
        {
            if (winkel >= 0)
            {
                winkel -=2;
            }
            else
            {
                winkel = 359;
            }
        }
        
        x = x+(Math.sin(Math.toRadians(winkel))*speed);
        y = y+(Math.cos(Math.toRadians(winkel))*speed);
        
        if (x<0)
        {
            x=0;
        }
        if (x>470)
        {
            x=470;
        }
        
        if (y<0)
        {
            y=0;
        }
        if (y>445)
        {
            y=445;
        }
    }
}
