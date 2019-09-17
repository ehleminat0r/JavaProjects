/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.Files.lines;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lhassler
 */
public class Calculator implements ActionListener{
    // Private Members
    private JFrame mFrame = new JFrame("Maze");
    private GraphPanel mPanel = new GraphPanel();
    private JTextField mTextFieldFunc = new JTextField();
    private JTextField mTextFieldFuncX = new JTextField();
    private JTextField mTextFieldFuncX2 = new JTextField();
    private JButton mButtonDraw = new JButton("Draw");
    private JTextField mTextFieldZoom = new JTextField();
    
    //private boolean [][] mGraph;
    
    // Constructor

    public Calculator()
    {
        mFrame.setSize(500, 549);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setResizable(false);
        mFrame.setLocationByPlatform(true);
        mFrame.setVisible(true);
        mFrame.setLayout(null);
        mFrame.add(mPanel);
        mFrame.add(mTextFieldFuncX2);
        mTextFieldFuncX2.setBounds(0, 0, 100, 19);
        mTextFieldFuncX2.setBackground(Color.GRAY);
        mTextFieldFuncX2.setText("1");
        mFrame.add(mTextFieldFuncX);
        mTextFieldFuncX.setBounds(100, 0, 100, 19);
        mTextFieldFuncX.setBackground(Color.GRAY);
        mTextFieldFuncX.setText("0");
        mFrame.add(mTextFieldFunc);
        mTextFieldFunc.setBounds(200, 0, 100, 19);
        mTextFieldFunc.setBackground(Color.GRAY);
        mTextFieldFunc.setText("0");
        mFrame.add(mTextFieldZoom);
        mTextFieldZoom.setBounds(350, 0, 100, 19);
        mTextFieldZoom.setBackground(Color.GRAY);
        mTextFieldZoom.setText("20"); 
        mFrame.add(mButtonDraw);
        mButtonDraw.setBounds(300, 0, 50, 19);
        mButtonDraw.setMargin(new Insets(0, 0, 0, 0));
        mButtonDraw.addActionListener(this);
        
    }
    
    // Main method
    public static void main(String[] args) throws IOException
    {
        Calculator calc = new Calculator();
        calc.zeug();
    }
    
    public static int RandomNr()    
    {
        return (int)(Math.random()*1000);
    }
    
    public void zeug()
    {
        Scanner sc = new Scanner(System.in);
        int eingabe;
        
        List<Integer> list = new ArrayList<>();
        eingabe = sc.nextInt();
        while (eingabe != 0)
        {
            list.add(eingabe);
            eingabe = sc.nextInt();
        } 
        
        PrintNumbers(list);

        int big =0;
        for (int i=0; i<list.size();i++)
        {
            if (list.get(i)> big)
            {
                big = list.get(i);
            }
        }
        
        for (int i=big; i>=0; i--)
        {
            for (int j=0; j<list.size();j++)
            {
                if(list.get(j)>i )
                {
                    System.out.print("*");
                }
                else
                {
                    System.out.print(" ");
                } 
            }
            System.out.println("");
            
        }
        
        
        int max=0;
        int seeCount=0;
        for (int i=0; i<list.size();i++)
        {
            
            if(list.get(i)>max)
            {
                seeCount++;
            }
            if (list.get(i)>max)
            {
                max=list.get(i);
            }
        }
        System.out.println("See Count: "+seeCount);
        
        
        /*
        for (int i=0; i<200;i++)
        {
            try {
                TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println(i);
        }
        
        */
        
        
        int number = RandomNr();
        
        int anzahl = 10;
        do{
            anzahl--;
            eingabe = sc.nextInt();
            if (eingabe<number)
            {
                System.out.println("Nummer ist groesser");
                System.out.println(anzahl);
            }
            else if (eingabe > number)
            {
                System.out.println("Nummer ist kleiner");
                System.out.println(anzahl);
            }
            else
            {
                System.out.println("Nummer ist richtig");
            }
            
        } while ( eingabe != number && anzahl >0);

        for (int i=0;i<10;i++)
        {
            for (int k=0;k<10;k++)
            {
                if (k>0 && k<9 && i>0 && i<9)
                {
                    System.out.print(" ");
                }
                else
                {
                    System.out.print("*");
                }
                
            }
            System.out.println("");
        }
    }

    private void PrintNumbers(List<Integer> list) {
        System.out.print("Numbers: ");
        for (int i = 0; i < list.size(); i++) {
            if (i<list.size()-1)
            {
                System.out.print(list.get(i)+", ");
            }
            else
            {
                System.out.print(list.get(i));
            }
        }
        System.out.println("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == mButtonDraw)
        {
            mPanel.func = Integer.parseInt(mTextFieldFunc.getText());
            mPanel.funcX = Double.parseDouble(mTextFieldFuncX.getText());
            mPanel.funcX2 = Double.parseDouble(mTextFieldFuncX2.getText());
            mPanel.zoom = Integer.parseInt(mTextFieldZoom.getText());
            mPanel.repaint();
        }
    }
     
}

class GraphPanel extends JPanel
{
    public int func = 0;
    public double funcX = 0;
    public double funcX2 = 1;
    public int zoom = 20;
    
    public GraphPanel()
    {
        this.setBounds(0, 20, 500, 500);
        this.setBackground(Color.WHITE);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // grid
        g.setColor(Color.GRAY);
        for (int i=-250; i<250; i++)
        {
            if ((i)%(zoom) == 0)
            {
                g.drawLine(i+250, 0, i+250, 500);
                g.drawLine(0, i+250, 500, i+250);
            }
            
        }
        
        g.setColor(Color.black);
        Graphics2D g2d = (Graphics2D)g;
        int x = 0;
        int y = 0;
               
        //g2d.scale(0.1, 0.1);
        for (int i=-250; i<250; i++)
        {
            g2d.drawLine(i+250, -(int)Math.pow(i*funcX2, 2)/zoom-(int)(i*funcX)/zoom-func/zoom+250, x, y);
            x = i+250;
            y = -(int)Math.pow(i*funcX2, 2)/zoom-(int)(i*funcX)/zoom-func/zoom+250;
        }    

        // x y line
        g.drawLine(250, 0, 250, 500);
        g.drawLine(0, 250, 500, 250);
        
        g2d.setColor(Color.green);
        g2d.rotate(0.5);
        g2d.fillRoundRect(50, 50, 50, 50, 40, 20);
        g2d.rotate(-0.5);
        g2d.scale(3, 1);
        g2d.fillRoundRect(50, 50, 50, 50, 40, 20);
        //g2d.drawr
    }
    
}