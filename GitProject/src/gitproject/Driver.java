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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
public class Driver extends JPanel implements KeyListener, MouseMotionListener  {
    Image image;
    boolean keyW = false;
    boolean keyA = false;
    boolean keyS = false;
    boolean keyD = false;
    
    int mouseX;
    int mouseY;
    
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
        frame.addMouseMotionListener(test);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        move();
        
        // follow movement
        
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 500);
        g.setColor(Color.black);
        g.drawRect(0, 0, 493 , 470);
        g.drawString("Speed: "+String.format("%.2f", speed), 420, 20);
        
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
        
        double x1 = Math.abs(mouseX-(x+Math.sin(Math.toRadians(winkel+2))));
        double y1 = Math.abs(mouseY-(y+Math.cos(Math.toRadians(winkel+2))));
        double z1 = Math.sqrt(Math.pow(x1, 2)+Math.pow(y1, 2));
        //System.out.println("z1: "+z1);
        double x2 = Math.abs(mouseX-(x+Math.sin(Math.toRadians(winkel-2))));
        double y2 = Math.abs(mouseY-(y+Math.cos(Math.toRadians(winkel-2))));
        double z2 = Math.sqrt(Math.pow(x2, 2)+Math.pow(y2, 2));
        //System.out.println("z2: "+z2);
        if (z1>z2)
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
        else
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

    @Override
    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mouseX = me.getX() -15;
        mouseY = me.getY()-40;
        if (mouseY < 5)
        {
            mouseY=5;
        }
        if (mouseY > 440)
        {
            mouseY=440;
        }
        
        if (mouseX < 5)
        {
            mouseX=5;
        }
        if (mouseX > 465)
        {
            mouseX=465;
        }
    }
}
