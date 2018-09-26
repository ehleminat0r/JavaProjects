package MiscProjects;

// Comment out the following package statement to compile separately.
// package com.javaworld.media.j2d;

/**
 * Example03 illustrates some basics of Java 2D.
 * This version is compliant with Java 1.2 Beta 3, Jun 1998.
 * Please refer to: <BR>
 * http://www.javaworld.com/javaworld/jw-07-1998/jw-07-media.html
 * <P>
 * @author Bill Day <bill.day@javaworld.com>
 * @version 1.0
 * @see java.awt.Graphics2D
 * @see java.awt.geom.AffineTransform
 * @see java.awt.geom.GeneralPath
**/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;   // Timer

public class RechteckBunt extends Frame  
{
  
    public static void main(String args[])
    {
        new RechteckBunt();

    }

    // Variablen
    int i =0;
   
    // Konstruktor
  public RechteckBunt() {
    //Title our frame.
    super("Java 2D Example03");

    //Set the size for this frame.
    setSize(400,400); 
    setResizable(false);
    super.setLocation(200, 200);

    //We need to turn on the visibility of our frame
    //by setting the Visible parameter to true.
    setVisible(true);
    
    test();
    
   new Timer(1,new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
           test();
           i++;
        }
    }).start();
    
    //Now, we want to be sure we properly dispose of resources 
    //this frame is using when the window is closed.  We use 
    //an anonymous inner class adapter for this.
    addWindowListener(new WindowAdapter() 
      {public void windowClosing(WindowEvent e) 
         {dispose(); System.exit(0);}  
      }
    );
  }

  
  public void test()
  {
      this.setTitle(Integer.toString(i));
  }
  /**
   * Here we use first-, second-, and third-order
   * curves together in our GeneralPath.
   **/
  public void paint(Graphics g) {
    //Here is how we used to draw a square with width
    //of 200, height of 200, and starting at x=50, y=50.

 
    //Let's set the Color to blue and then use the Graphics2D
    //object to do draw a rectangle, offset from the square.
    //So far, we've not done anything using Graphics2D that
    //we could not also do using Graphics.  (We are actually
    //just using Graphics2D methods inherited from Graphics.)
    
    Graphics2D g2d = (Graphics2D)g;
    g2d.setColor(Color.blue);
    g2d.drawRect(40,40,320,320);

    /*
    g2d.draw3DRect(130, 130, 250, 250, false);
    g2d.drawOval(10, 10, 100, 100);
    g2d.rotate(-0.5);

    g2d.fillOval(50, 150, 50, 100);
    g2d.rotate(0.5);
    g2d.fillOval(50, 50, 50, 100);
    */
    
    g2d.setColor(Color.BLACK);
    for (int i=0;i<=300;i++)
        for (int k=0;k<=300;k++)
            if (i%6==0)
            {
                g2d.drawLine(50+i, 50, 200, 200);
                g2d.setColor(Color.GREEN);
                g2d.drawLine(350-i, 350, 200, 200);
                g2d.setColor(Color.BLACK);
                if (k%6==0)
                {
                    g2d.setColor(Color.YELLOW);
                    g2d.drawLine(50, 50+k, 200, 200);
                        g2d.setColor(Color.BLUE);
                    g2d.drawLine(350, 350-k, 200, 200);
                }
            }
    g2d.setColor(Color.BLACK);
    for (int i=0;i<20;i++)
        g2d.drawRect(55+i*5, 55+i*5, 290-i*10, 290-i*10);
    for (int i=0;i<15;i++)
    {
        g2d.clearRect(50+(i*20), 50+(i*20), 20, 20);
        g2d.clearRect(50+(i*20), 330-(i*20), 20, 20);
    }
    
    g.setColor(Color.red);
    g.drawRect(50,50,300,300);
            //Now, let's draw another rectangle, but this time, let's
    //use a GeneralPath to specify it segment by segment.
    //This time, however, our GeneralPath will have nonlinear
    //segments, one second-order (quadratic) and the other
    //third-order (cubic).  We translate and rotate this shape
    //again, as we did before.
    
        /*
    
    GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
    path.moveTo(0.0f,0.0f);
    path.lineTo(0.0f,125.0f);
    path.quadTo(100.0f,100.0f,225.0f,125.0f);
    path.curveTo(260.0f,100.0f,130.0f,50.0f,225.0f,0.0f);
    path.closePath();

    AffineTransform at = new AffineTransform();
    at.setToRotation(-Math.PI/8.0);
    g2d.transform(at);
    at.setToTranslation(50.0f,200.0f);
    g2d.transform(at);

    g2d.setColor(Color.green);
    g2d.fill(path);
    
    g2d.translate(0, 0);
    */
  }
}
