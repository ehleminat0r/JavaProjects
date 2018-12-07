/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
        nests.add(new Nest(50,70));
        nests.add(new Nest(60,400));
        //nests.add(new Nest(400,420));
        //nests.add(new Nest(400,50));
        ants.add(new Ant(rnd.nextInt(400),rnd.nextInt(400),0,2,nests));
        for (int i=0; i<25;i++)
        {
            ants.add(new Ant(rnd.nextInt(400),rnd.nextInt(400),0,rnd.nextInt(5)+1,nests));
        }
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
                    ants.add(new Ant(nests.get(0).getX(),nests.get(0).getY(),0,rnd.nextInt(4)+1,nests));
                }
                for (int i=0; i<ants.size(); i++)
                {
                    ants.get(i).walk();
                }
            }
        }, 25, 25);
    }
}

class Ant
{
    private int x;
    private int y;
    int pos;
    int speed;
    int direction = 0;
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
    
    public Ant(int x, int y, int pos, int speed, List<Nest> nests)
    {
        this.x = x;
        this.y = y;
        this.pos = pos;
        this.speed = speed;
        this.nests = nests;
    }
    
    public void walk()
    {
        if (nests.get(pos).getX()>x+4)
        {
            x += speed;
            direction = 90;
        }
        else if (nests.get(pos).getX()<x)
        {
            x -= speed;
            direction = 270;
        }
        if (nests.get(pos).getY()>y+4)
        {
            y += speed;
            direction = 180;
        }
        else if (nests.get(pos).getY()<y)
        {
            y -= speed;
            direction = 0;
        }
        if (nests.get(pos).getY() < y+5 && nests.get(pos).getY() > y-5 && nests.get(pos).getX() <x+5 && nests.get(pos).getX() >x-5)
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
        BufferedImage image;
        BufferedImage au;
        BufferedImage ad;
        BufferedImage al;
        BufferedImage ar;
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        // draw nests
        try {
            image = ImageIO.read(getClass().getResource("../img/anthill.png"));
            for(int i=0; i< nests.size(); i++)
            {
                //g.fillRect(nests.get(i).getX()-15, nests.get(i).getY()-15, 30, 30);
                int save = 0;
                for (int j=0; j<ants.size();j++)
                {
                    if (ants.get(j).pos==i)
                    {
                        save++;
                    }
                }
                g.drawString(Integer.toString(save), nests.get(i).getX()-40, nests.get(i).getY()-40);

                    g.drawImage(image, nests.get(i).getX()-40, nests.get(i).getY()-30, this);

            }
        } catch (IOException ex) {
                Logger.getLogger(AntPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        // draw ants
        //image = ImageIO.read(getClass().getResource("../img/ant.png"));
        //image = scale(image,50,50);
        //image = rotateImageByDegrees(image,ants.get(i).direction);
        try {
            au = ImageIO.read(getClass().getResource("../img/ant_up.png"));
            ad = ImageIO.read(getClass().getResource("../img/ant_down.png"));
            al = ImageIO.read(getClass().getResource("../img/ant_left.png"));
            ar = ImageIO.read(getClass().getResource("../img/ant_right.png"));
        
        for(int i=0; i< ants.size(); i++)
        {
            //g.fillOval(ants.get(i).getX(), ants.get(i).getY(), 10, 10);
            if (ants.get(i).direction == 0)
            {
                g.drawImage(au, ants.get(i).getX(), ants.get(i).getY(), this);
            }
            else if (ants.get(i).direction == 90)
            {
                g.drawImage(ar, ants.get(i).getX(), ants.get(i).getY(), this);
            }
            else if (ants.get(i).direction == 180)
            {
                g.drawImage(ad, ants.get(i).getX(), ants.get(i).getY(), this);
            }
            else if (ants.get(i).direction == 270)
            {
                g.drawImage(al, ants.get(i).getX(), ants.get(i).getY(), this);
            }
        }
        } catch (IOException exe) {
                Logger.getLogger(AntPanel.class.getName()).log(Level.SEVERE, null, exe);
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

    public static BufferedImage scale(BufferedImage src, int w, int h)
    {
        BufferedImage img = 
                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int x, y;
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (y = 0; y < h; y++)
            ys[y] = y * hh / h;
        for (x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }
    
    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

         double rads = Math.toRadians(angle);
         double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
         int w = img.getWidth();
         int h = img.getHeight();
         int newWidth = (int) Math.floor(w * cos + h * sin);
         int newHeight = (int) Math.floor(h * cos + w * sin);

         BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
         Graphics2D g2d = rotated.createGraphics();
         AffineTransform at = new AffineTransform();
         at.translate((newWidth - w) / 2, (newHeight - h) / 2);

         int x = w / 2;
         int y = h / 2;

         at.rotate(rads, x, y);
         g2d.setTransform(at);
         g2d.drawImage(img, 0, 0, this);
         g2d.dispose();

         return rotated;
     }
    
    
}
