/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lhassler
 */
class ThisFrame extends JFrame implements ActionListener
{
    JButton but2;
    JButton but1;
    JTextField tf1;
    JTextField tf2;
    JLabel label[] = new JLabel[20];
    
    ThisFrame(String name)
    {
        super(name);
        
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.setLayout(null); 
        
        TestForm tPanel = new TestForm(400,400);
        tPanel.setLocation(300, 100);
        tPanel.setVisible(true);
        this.add(tPanel);
        this.addMouseListener(tPanel);
        
        tf1 = new JTextField("5", 15);
        this.add(tf1);
        tf1.setLocation(10, 10);
        tf1.setSize(30, 20);
        tf2 = new JTextField("5", 15);
        this.add(tf2);
        tf2.setLocation(10, 40);
        tf2.setSize(30, 20);
        but1 = new JButton("!");
        this.add(but1);
        but1.setLocation(50, 10);
        but1.setSize(40, 20);
        but1.addActionListener(this);
        but2 = new JButton("!");
        this.add(but2);
        but2.setLocation(50, 40);
        but2.setSize(40, 20);
        but2.addActionListener(this);
        
        JLabel jl = new JLabel("tada12");
        this.add(jl);
        jl.setLocation(20, 80);
        jl.setSize(50, 10);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.but1)
        {
            for (int i=0; i<Integer.parseInt(tf1.getText());i++)
            {
                label[i] = new JLabel(Integer.toString(i));
                this.add(label[i]);
                label[i].setLocation(120, i*30+20);
                label[i].setSize(50, 10);
            }
        }
        if (ae.getSource() == this.but2)
        {
            System.out.println("b");
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

public class TestForm extends JPanel implements MouseListener {
    private int mouseX, mouseY = 0;
    int spin = 0;
    private static Random rnd = new Random();

    public TestForm(int width, int height) {
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
        g.fillRect(mouseX-3, mouseY, 6, 150);
        g.setColor(Color.black);
        for (int i=0; i<360; i++)
        {
            if(i%10 == 0)
            {
                g.drawLine(mouseX, mouseY, mouseX+(int)(Math.sin(Math.toRadians(i+spin))*50), mouseY+(int)(Math.cos(Math.toRadians(i+spin))*50));
                g.setColor(Color.white);
                g.fillOval(mouseX+(int)(Math.sin(Math.toRadians(i+spin))*50)-5, mouseY+(int)(Math.cos(Math.toRadians(i+spin))*50)-5, 10, 10);
                g.setColor(Color.black);
                g.drawOval(mouseX+(int)(Math.sin(Math.toRadians(i+spin))*50)-5, mouseY+(int)(Math.cos(Math.toRadians(i+spin))*50)-5, 10, 10);
            }   
        }
        g.setColor(Color.white);
        g.fillOval(mouseX-20, mouseY-20, 40, 40);
        g.setColor(Color.black);
        g.drawOval(mouseX-20, mouseY-20, 40, 40);
        for (int i=0; i<50; i++)
        {
            g.drawLine(i*2, 0, i*2, fibo(i)/5000);
        }
        repaint();
    }
    
    public static int fibo(int number)
    {
        int a = 0;
        int b = 1;
        int tmp = 0;
         
        for(int i = 0; i < number; i++)
        {
            tmp = a + b;
            a = b;
            b = tmp;
        }
        System.out.println(tmp);
        return tmp;
    }
    
    List list;
    
    public static void main(String[] args) {
        ThisFrame frame = new ThisFrame("Test");
        List list = new List();
        frame.add(list);
        ArrayList<Integer> aList = new ArrayList<>();
        for (int i=0; i<100; i++)
        {
            aList.add(i<<1);
            //aList.add(rnd.nextInt(100));
            list.add(String.format("%08d", Integer.parseInt(Integer.toBinaryString(aList.get(i)))));
        }
        list.setBounds(100, 100, 200, 200);
        list.select(2);
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("test");
                list.add("a");
                
            }
        }, 500, 500);
        
        

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        mouseX = me.getX()-5-500;
        mouseY = me.getY()-25-300;
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
