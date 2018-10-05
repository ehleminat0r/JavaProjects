/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lhassler
 */
public class Tunnel extends JPanel implements MouseMotionListener {
    private int mouseX, mouseY = 0;
    
    public Tunnel(int width, int height) {
        this.setBackground(Color.white);
        this.setSize(width, height);
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mouseX = me.getX()-10;
        mouseY = me.getY()-30;
        repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        for (int i=0; i<20; i++)
        {
            g.drawLine(i*40, 0, mouseX, mouseY);
        }
        for (int i=0; i<20; i++)
        {
            g.drawLine(i*40, 600, mouseX, mouseY);
        }
        for (int i=0; i<20; i++)
        {
            g.drawLine(0, i*30, mouseX, mouseY);
        }
        for (int i=0; i<21; i++)
        {
            g.drawLine(800, i*30, mouseX, mouseY);
        }
        g.setColor(Color.white);
        g.fillOval(mouseX-100, mouseY-100, 200, 200);
        g.setColor(Color.black);
        g.drawOval(mouseX-100, mouseY-100, 200, 200);
        
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
        frame.addMouseMotionListener(tPanel);
    }
    
    

}
