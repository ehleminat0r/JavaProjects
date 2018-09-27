/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lhassler
 */
public class GitProject {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Frame();
    }
    
}

class Frame extends JFrame implements ActionListener
{
    Timer timer = new Timer();
    JButton[][] buttons = new JButton[10][1];
    List<Ball> balls = new ArrayList<>(); 
    Panel pan = new Panel(balls);
    
    public Frame()
    {
        super("Test");
        setVisible(true);
        setSize(406, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null); 
        setLocation(new Point(100,100));
        this.add(pan);
        pan.setSize(380, 360);
        pan.setLocation(10, 50);
        initialize();
    }
    
    void initialize()
    {
        // Adding Buttons
        for (int i=0; i<buttons.length; i++)
        {
            for (int j=0; j<buttons[0].length; j++)
            {
                buttons[i][j] = new JButton(Integer.toString(i+1));
                buttons[i][j].setMargin(new Insets(0, 0, 0, 0));            // Entfernt Beschränkung wo Text im JButton stehen darf
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 16));   // legt Schriftart fest
                buttons[i][j].setBounds(i*40, j*40, 40, 40);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setActionCommand(Integer.toString(i)+Integer.toString(j));    // Aktionlistener mit Position als Übergabe z.B. "05"
                this.add(buttons[i][j]);
            }
        }
        
        // Adding first 10 Balls
        for (int i=0; i<10; i++)
        {
            balls.add(new Ball());
            balls.get(i).pt = new Point(i*38, 0);
            balls.get(i).speedx = (int)(Math.random()*10)-5;
            balls.get(i).speedy = (int)(Math.random()*10)-5;
            balls.get(i).size = 30;
        }
        
        // Adding Ball move time
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkBorder();
            }
        }, 20, 20);
        
        this.repaint();
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
            if (balls.get(i).pt.x > pan.getWidth()-30)
            {
                balls.get(i).speedx *= -1;
            }
            
            if (balls.get(i).pt.y < 0)
            {
                balls.get(i).speedy *= -1;
            }
            if (balls.get(i).pt.y > pan.getHeight()-30)
            {
                balls.get(i).speedy *= -1;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand())
        {
            case "00":
                balls.get(0).pt = new Point(0,0);
                break;
            case "10":
                balls.get(1).pt = new Point(38,0);
                break;
            case "20":
                balls.get(2).pt = new Point(76,0);
                break;
            case "30":
                balls.get(3).pt = new Point(114,0);
                break;    
            case "40":
                balls.get(4).pt = new Point(152,0);
                break;   
            case "50":
                balls.get(5).pt = new Point(190,0);
                break;
            case "60":
                balls.get(6).pt = new Point(228,0);
                break;
            case "70":
                balls.get(7).pt = new Point(266,0);
                break;
            case "80":
                balls.get(8).pt = new Point(304,0);
                break;
            case "90":
                balls.get(9).pt = new Point(342,0);
                break;    
        }
    }
}

class Panel extends JPanel
{
    List<Ball> balls = new ArrayList<>(); 
    
    Panel(List<Ball> balls)
    {
        this.balls = balls;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (int i=0; i<balls.size(); i++)
        {
            g.setColor(Color.black);
            g.fillOval(balls.get(i).pt.x, balls.get(i).pt.y, balls.get(i).size , balls.get(i).size);
            g.drawString(Integer.toString(i), balls.get(i).pt.x, balls.get(i).pt.y);
        }
        repaint();
    }
}
