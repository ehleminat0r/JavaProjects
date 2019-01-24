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
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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
    BufferedImage img = new BufferedImage(493, 470, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics;
    Color col;
    int colSize=1;
    
    boolean keyW = false;
    boolean keyA = false;
    boolean keyS = false;
    boolean keyD = false;
    
    int controlType = 0;
    int mouseX;
    int mouseY;
    int mouseDraggedX;
    int mouseDraggedY;
    
    double x=50;
    double y=50;
    double speed = 0.1;
    int winkel = 0;
    
    public Driver()
    {
        graphics = img.createGraphics();
        graphics.setPaint ( new Color ( 255, 255, 255 ) );
        graphics.fillRect ( 0, 0, img.getWidth(), img.getHeight() );
        col = Color.BLACK;
    }
    
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
         
        // Background with set pixels
        g.drawImage(img, 0, 0, this);
        
        // black border and text
        g.setColor(Color.black);
        g.drawRect(0, 0, 493 , 470);
        g.drawString("Speed: "+String.format("%.2f", speed), 420, 35);
        if (controlType == 0)
        {
            g.drawString("(Key Q) Manual Control", 360, 20);
        }
        else if (controlType == 1)
        {
            g.drawString("(Key Q) Mouse Follow", 360, 20);
        }
        else if (controlType == 2)
        {
            g.drawString("(Key Q) Drag Follow", 360, 20);
        }
        
        // draw and rotate ant
        try {
            image = ImageIO.read(getClass().getResource("../img/ant_up.png"));
        } catch (IOException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        graphics = (Graphics2D)g;
        AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(-winkel-180), x+(image.getWidth(this)/2), y+(image.getHeight(this)/2));
        //Set our Graphics2D object to the transform
        graphics.setTransform(a);
        //Draw our image like normal
        graphics.drawImage(image, (int)x, (int)y, null);
        
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
        
        if (ke.getKeyChar() == 'q')
        {
            if (controlType == 2)
            {
                controlType = 0;
            }
            else
            {
                controlType++;
            }
        }
        else if (ke.getKeyChar() == 'e')
        {
            graphics = img.createGraphics();
            graphics.setPaint ( new Color ( 255, 255, 255 ) );
            graphics.fillRect ( 0, 0, img.getWidth(), img.getHeight() );
        }
        else if (ke.getKeyCode()== KeyEvent.VK_ENTER)
        {
            File outputfile = new File("C:\\Users\\lhassler\\Desktop\\image.png");
            try {
                ImageIO.write(img, "png", outputfile);
            } catch (IOException ex) {
                Logger.getLogger(Bubbles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        switch (ke.getKeyChar())
        {
            case '1':
                colSize=1;
                break;
            case '2':
                colSize=2;
                break;
            case '3':
                colSize=3;
                break;
        }
    }

    private void move() {
        if (keyW)
        {
            speed += 0.001;
        }
        if (keyS)
        {
            speed -= 0.001;
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

        FollowMouse();
        
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
        switch(winkel/72)
        {
            case 0:
                col = Color.red;
                break;
            case 1:
                col = Color.BLUE;
                break;
            case 2:
                col = Color.black;
                break;
            case 3:
                col = Color.GREEN;
                break;
            case 4:
                col = Color.cyan;
                break;
        }
        switch (colSize)
        {
            case 1:
                img.setRGB((int)x+13, (int)y+13, col.getRGB());
                break;
            case 2:
                img.setRGB((int)x+13, (int)y+13, col.getRGB());
                img.setRGB((int)x+13, (int)y+14, col.getRGB());
                img.setRGB((int)x+14, (int)y+13, col.getRGB());
                img.setRGB((int)x+14, (int)y+14, col.getRGB());
                break;
            case 3:
                img.setRGB((int)x+12, (int)y+12, col.getRGB());
                img.setRGB((int)x+12, (int)y+13, col.getRGB());
                img.setRGB((int)x+12, (int)y+14, col.getRGB());
                img.setRGB((int)x+13, (int)y+12, col.getRGB());
                img.setRGB((int)x+13, (int)y+13, col.getRGB());
                img.setRGB((int)x+13, (int)y+14, col.getRGB());
                img.setRGB((int)x+14, (int)y+12, col.getRGB());
                img.setRGB((int)x+14, (int)y+13, col.getRGB());
                img.setRGB((int)x+14, (int)y+14, col.getRGB());
                break;
        }
        
    }

    private void FollowMouse()
    {
        if (controlType == 1)
        {
            double x1 = Math.abs(mouseX-(x+Math.sin(Math.toRadians(winkel+2))));
            double y1 = Math.abs(mouseY-(y+Math.cos(Math.toRadians(winkel+2))));
            double z1 = Math.sqrt(Math.pow(x1, 2)+Math.pow(y1, 2));
            
            double x2 = Math.abs(mouseX-(x+Math.sin(Math.toRadians(winkel-2))));
            double y2 = Math.abs(mouseY-(y+Math.cos(Math.toRadians(winkel-2))));
            double z2 = Math.sqrt(Math.pow(x2, 2)+Math.pow(y2, 2));

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
            else if (z1<z2)
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
        }
        else if (controlType == 2)
        {
            double x1 = Math.abs(mouseDraggedX-(x+Math.sin(Math.toRadians(winkel+2))));
            double y1 = Math.abs(mouseDraggedY-(y+Math.cos(Math.toRadians(winkel+2))));
            double z1 = Math.sqrt(Math.pow(x1, 2)+Math.pow(y1, 2));
            
            double x2 = Math.abs(mouseDraggedX-(x+Math.sin(Math.toRadians(winkel-2))));
            double y2 = Math.abs(mouseDraggedY-(y+Math.cos(Math.toRadians(winkel-2))));
            double z2 = Math.sqrt(Math.pow(x2, 2)+Math.pow(y2, 2));

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
            else if (z1<z2)
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
        }
    }
    

    @Override
    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        mouseDraggedX = me.getX() -15;
        mouseDraggedY = me.getY()-40;
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
