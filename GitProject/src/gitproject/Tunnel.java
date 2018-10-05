/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lhassler
 */
public class Tunnel extends JPanel implements MouseListener {
    private int mouseX, mouseY = 0;
    int spin = 0;

    public Tunnel(int width, int height) {
        this.setBackground(Color.white);
        this.setSize(width, height);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run() {
              if (spin<90)
              {
                  spin += 1;
              }
              else
              {
                  spin = 0;
              }
            }
        }, 20, 20);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.green);
        g.drawLine(mouseX, mouseY, mouseX+50, mouseY+300);
        g.setColor(Color.black);
        for (int i=0; i<360; i++)
        {
            if(i%10 == 0)
            {
                g.drawLine(mouseX, mouseY, mouseX+(int)(Math.sin(Math.toRadians(i+spin))*100), mouseY+(int)(Math.cos(Math.toRadians(i+spin))*100));
                g.setColor(Color.white);
                g.fillOval(mouseX+(int)(Math.sin(Math.toRadians(i+spin))*100)-5, mouseY+(int)(Math.cos(Math.toRadians(i+spin))*100)-5, 10, 10);
                g.setColor(Color.black);
                g.drawOval(mouseX+(int)(Math.sin(Math.toRadians(i+spin))*100)-5, mouseY+(int)(Math.cos(Math.toRadians(i+spin))*100)-5, 10, 10);
            }   
        }
        g.setColor(Color.white);
        g.fillOval(mouseX-20, mouseY-20, 40, 40);
        g.setColor(Color.black);
        g.drawOval(mouseX-20, mouseY-20, 40, 40);
        repaint();
    }

    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tunnel");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setLayout(null); 
        Tunnel tPanel = new Tunnel(frame.getWidth(),frame.getHeight());
        frame.add(tPanel);
        frame.addMouseListener(tPanel);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        mouseX = me.getX()-5;
        mouseY = me.getY()-25;
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
