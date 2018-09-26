/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiscProjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.Timer;

/**
 *
 * @author lhassler
 */
class Graph extends JPanel
{
    Polygon test = new Polygon();
    int secondspassed = -300;
    boolean stop = false;
    Timer myTimer = new Timer();
    TimerTask task = new TimerTask()      
    {
        public void run()
        {
            secondspassed++;
            System.out.println(secondspassed);
            repaint();
        }
    };
    
    Graph()
    {
        super();
        this.setSize(650, 200);
        this.setBackground(Color.white);
        myTimer.scheduleAtFixedRate(task, 500, 5);
        test.addPoint(20, 10);
        test.addPoint(20, 20);
        test.addPoint(10, 30);
        test.addPoint(20, 40);
        test.addPoint(20, 50);
        test.addPoint(40, 30);
    }
    
    @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawLine(100, 0, 100, 200);
            for (int i=-100; i<100; i++)
            {
                
                if ( (200-(int)(0.002*i*i)) > 0  )
                {
                    g.drawLine(i+100, 200-(int)(0.002*i*i), i+100, 200-(int)(0.002*i*i));
                }
                
                if ( (200-(int)(0.01*i*i)) > 0  )
                {
                    g.drawLine(i+100, 200-(int)(0.01*i*i), i+1+100, 200-(int)(0.01*(i+1)*(i+1)));
                }
                
                if ( (200-(int)(0.05*i*i)) > 0  )
                {
                    g.drawLine(i+100, 200-(int)(0.05*i*i), i+1+100, 200-(int)(0.05*(i+1)*(i+1)));
                    g.drawPolygon(test);
                }
            }
            for (int i=-300; i<secondspassed;i++)
            {
                if ( (181-(int)(0.002*i*i)) > 0  )
                {
                    g.drawLine(i+300, (int)(0.002*i*i)+20, i+1+300, (int)(0.002*(i+1)*(i+1)+20));
                }
                else
                {
                    g.drawOval(590, 180, 30, 30);         
                    
                    
                }

            }    
        }
    
}

public class PaintGrid extends JPanel{

    PaintGrid()
    {
        super();
        this.setSize(20, 20);
        this.setBackground(Color.white);

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame pgframe = new JFrame("PaintGrid");
        pgframe.setSize(1250, 454);
        pgframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pgframe.setResizable(false);
        pgframe.setLocationByPlatform(true);
        pgframe.setVisible(true);
        pgframe.setLayout(null);
        
        Graph graph = new Graph();
        pgframe.add(graph);
        graph.setLocation(500, 10);
        
        PaintGrid[][] pg = new PaintGrid[20][20];
        for (int i=0;i<20;i++)
        {
            for (int j=0;j<20;j++)
            {
                pg[i][j] = new PaintGrid();
                pg[i][j].setLocation(2+ (i*21), 2+(j*21));
                pgframe.add(pg[i][j]);
            }
        }
        JButton selectColor = new JButton("");
        pgframe.add(selectColor);
        selectColor.setBounds(425, 0,40, 40);       
        selectColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("dssaf");
            }
        });
        
        pgframe.repaint();
        pgframe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("X:"+e.getX());
                System.out.println("Y:"+e.getY());
                //pg[e.getX()/21][e.getY()/21-1].setBackground(Color.black);
            }
        });

        
    }
    
}
