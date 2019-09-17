/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiscProjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author lhassler
 */
public class Minesweeper extends JFrame implements ActionListener {
    // Klassenvariablen
    Random rnd = new Random();
    static int xsize = 20;     // Spielfeldbreite
    static int ysize = 20;     // Spielfeldhöhe
    static boolean win = false;
    int BOMBAMOUNT = 15; // Bestimmt die Anzahl der Bomben (X% der Felder sind Bomben)
    
    int bombcount = 0;
    boolean isLost = false;
    int fieldsize = xsize*ysize;
    
    boolean[][] bombs = new boolean[xsize][ysize];
    final static JButton[][] buttons = new JButton[xsize][ysize];
    static JButton restart;
    Timer timer = new Timer();
    
    MouseAdapter ma = new MouseAdapter() {    public void mouseClicked(MouseEvent e) {
        boolean nomore = false;
        if (e.getButton() == 3) { // if right click
           
            if (((JButton) e.getSource()).getText() == "B")
            {
                nomore = true;
                int bcount = -1;
                for (int i=0; i<bombs.length; i++)
                    for (int j=0; j<bombs[0].length; j++)
                        if (bcount != 0 && buttons[i][j].isVisible()) {
                            // Knöpfe mit Anzahl der anliegenden Bomben beschriften
                            bcount = 0;
                            for (int xcheck = i-1; xcheck <= i+1; xcheck++) 
                                for (int ycheck = j-1; ycheck <= j+1; ycheck++) {
                                    if (xcheck < xsize && ycheck < ysize && xcheck >= 0 && ycheck >= 0)
                                        if (bombs[xcheck][ycheck] == true) 
                                            bcount++;
                                }
                            if (bcount == 0) {
                                buttons[i][j].setVisible(false);
                                checknb(i,j);
                                nomore = false;
                            }

                        }
            }
            
            if (nomore)
            {
                for (int i=0; i<bombs.length; i++)
                    for (int j=0; j<bombs[0].length; j++)
                    {
                        if (i==0 && j>0 && j < bombs[0].length-1)
                        {
                            if (!buttons[i+0][j-1].isVisible()
                                    || !buttons[i+1][j-1].isVisible()
                                    || !buttons[i+1][j+0].isVisible()
                                    || !buttons[i+0][j+1].isVisible()
                                    || !buttons[i+1][j+1].isVisible())
                            {
                                int bcount = 0;
                                for (int xcheck = i-1; xcheck <= i+1; xcheck++) 
                                    for (int ycheck = j-1; ycheck <= j+1; ycheck++)
                                    {
                                        if (xcheck < xsize && ycheck < ysize && xcheck >= 0 && ycheck >= 0)
                                            if (bombs[xcheck][ycheck] == true) 
                                                bcount++;
                                    }

                                buttons[i][j].setText(Integer.toString(bcount));
                                buttons[i][j].setBackground(null);
                                if (bcount == 0)
                                {
                                    buttons[i][j].setVisible(false);
                                    checknb(i,j);
                                }
                            }
                        }
                        if (i == bombs.length-1 && j>0 && j < bombs[0].length-1)
                        {
                            if (!buttons[i-1][j-1].isVisible()
                                    || !buttons[i+0][j-1].isVisible()
                                    || !buttons[i-1][j+0].isVisible()
                                    || !buttons[i-1][j+1].isVisible()
                                    || !buttons[i+0][j+1].isVisible())
                            {
                                int bcount = 0;
                                for (int xcheck = i-1; xcheck <= i+1; xcheck++) 
                                    for (int ycheck = j-1; ycheck <= j+1; ycheck++)
                                    {
                                        if (xcheck < xsize && ycheck < ysize && xcheck >= 0 && ycheck >= 0)
                                            if (bombs[xcheck][ycheck] == true) 
                                                bcount++;
                                    }

                                buttons[i][j].setText(Integer.toString(bcount));
                                buttons[i][j].setBackground(null);
                                if (bcount == 0)
                                {
                                    buttons[i][j].setVisible(false);
                                    checknb(i,j);
                                }
                            }
                        }
                        if (j==0 && i>0 && i < bombs.length-1)
                        {
                            if (!buttons[i-1][j+0].isVisible()
                                    || !buttons[i+1][j+0].isVisible()
                                    || !buttons[i-1][j+1].isVisible()
                                    || !buttons[i+0][j+1].isVisible()
                                    || !buttons[i+1][j+1].isVisible())
                            {
                                int bcount = 0;
                                for (int xcheck = i-1; xcheck <= i+1; xcheck++) 
                                    for (int ycheck = j-1; ycheck <= j+1; ycheck++)
                                    {
                                        if (xcheck < xsize && ycheck < ysize && xcheck >= 0 && ycheck >= 0)
                                            if (bombs[xcheck][ycheck] == true) 
                                                bcount++;
                                    }

                                buttons[i][j].setText(Integer.toString(bcount));
                                buttons[i][j].setBackground(null);
                                if (bcount == 0)
                                {
                                    buttons[i][j].setVisible(false);
                                    checknb(i,j);
                                }
                            }
                        }
                        if (j== bombs[0].length-1 && i>0 && i < bombs.length-1)
                        {
                            if (!buttons[i-1][j-1].isVisible()
                                    || !buttons[i+0][j-1].isVisible()
                                    || !buttons[i+1][j-1].isVisible()
                                    || !buttons[i-1][j+0].isVisible()
                                    || !buttons[i+1][j+0].isVisible())
                            {
                                int bcount = 0;
                                for (int xcheck = i-1; xcheck <= i+1; xcheck++) 
                                    for (int ycheck = j-1; ycheck <= j+1; ycheck++)
                                    {
                                        if (xcheck < xsize && ycheck < ysize && xcheck >= 0 && ycheck >= 0)
                                            if (bombs[xcheck][ycheck] == true) 
                                                bcount++;
                                    }

                                buttons[i][j].setText(Integer.toString(bcount));
                                buttons[i][j].setBackground(null);
                                if (bcount == 0)
                                {
                                    buttons[i][j].setVisible(false);
                                    checknb(i,j);
                                }
                            }
                        }
                        if (i>0 && i < bombs.length-1 && j>0 && j < bombs[0].length-1)
                        {
                            
                            if (!buttons[i-1][j-1].isVisible()
                                    || !buttons[i+0][j-1].isVisible()
                                    || !buttons[i+1][j-1].isVisible()
                                    || !buttons[i-1][j+0].isVisible()
                                    || !buttons[i+1][j+0].isVisible()
                                    || !buttons[i-1][j+1].isVisible()
                                    || !buttons[i+0][j+1].isVisible()
                                    || !buttons[i+1][j+1].isVisible())
                            {
                                int bcount = 0;
                                for (int xcheck = i-1; xcheck <= i+1; xcheck++) 
                                    for (int ycheck = j-1; ycheck <= j+1; ycheck++)
                                    {
                                        if (xcheck < xsize && ycheck < ysize && xcheck >= 0 && ycheck >= 0)
                                            if (bombs[xcheck][ycheck] == true) 
                                                bcount++;
                                    }

                                buttons[i][j].setText(Integer.toString(bcount));
                                buttons[i][j].setBackground(null);
                                if (bcount == 0)
                                {
                                    buttons[i][j].setVisible(false);
                                    checknb(i,j);
                                }
                            }
                        }
                    }
            }
            
            if (((JButton) e.getSource()).getText() == "")
            {
                ((JButton) e.getSource()).setText("B");
                ((JButton) e.getSource()).setBackground(Color.blue);
            }
        } 
    }};
    
    // Konstruktor
    public Minesweeper() {
        // Fenster Eigenschaften
        super("Minesweeper");
        this.setSize(40*xsize+7, 40*ysize+30);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationByPlatform(true);   // Platzierung des Fensters (nicht ganz links oben)
        this.setVisible(true);
        this.setLayout(null);               // Nötig, da sich die Darstellung der Objekte tw. seltsam verhält
        // Bomben setzen
        for(int i=0; i<bombs.length; i++)
            for (int j=0; j<bombs[0].length; j++)
                if (rnd.nextInt(100) < BOMBAMOUNT) {
                    bombs[i][j] = true;
                    bombcount++;
                }
                else
                    bombs[i][j] = false;
        
        /*
        // Bombenfeld in Konsole zeigen
        for(int i=0; i<bombs.length; i++) {
            for (int j=0; j<bombs[0].length; j++)
                if (bombs[i][j]==true)
                    System.out.print("X ");
                else
                    System.out.print("0 ");
            System.out.println("");
        }
        */
                
        // Elemente dem Fenster hinzufügen
       for(int i=0; i<bombs.length; i++) {
           for (int j=0; j<bombs[0].length; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setMargin(new Insets(0, 0, 0, 0));            // Entfernt Beschränkung wo Text im JButton stehen darf
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 16));   // legt Schriftart fest
                buttons[i][j].setBounds(i*40, j*40, 40, 40);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setActionCommand(Integer.toString(i)+" "+Integer.toString(j));    // Aktionlistener mit Position als Übergabe z.B. "0 5"
                buttons[i][j].addMouseListener(ma);
                this.add(buttons[i][j]);
            }
       } 
       
       restart = new JButton("Restart");
       restart.setBounds(0, 40*ysize, 40*xsize, 40);
       restart.addActionListener(this);
       restart.setActionCommand("restart");
       restart.setEnabled(false);
       this.add(restart);
       
       this.repaint();  // Fenster neu zeichnen
    }
    
    public static void main(String[] args) {
        new Minesweeper();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {   // Actionlistner der bei Knopfdruck ausgeführt wird
        
        for (int i=0; i<bombs.length; i++)
            for (int j=0; j<bombs[0].length; j++)
                if (ae.getActionCommand().equals(Integer.toString(i)+" "+Integer.toString(j))) {
                    // Knöpfe mit Anzahl der anliegenden Bomben beschriften
                    int bcount = 0;
                    for (int xcheck = i-1; xcheck <= i+1; xcheck++) 
                        for (int ycheck = j-1; ycheck <= j+1; ycheck++) {
                            if (xcheck < xsize && ycheck < ysize && xcheck >= 0 && ycheck >= 0)
                                if (bombs[xcheck][ycheck] == true) 
                                    bcount++;
                        }

                    buttons[i][j].setText(Integer.toString(bcount));
                    buttons[i][j].setBackground(null);
                    if (bcount == 0) {
                        buttons[i][j].setVisible(false);
                        checknb(i,j);
                    }
                        
                    // Name = falls Bombe B + Hintergrund rot
                    if (bombs[i][j]==true) {
                        buttons[i][j].setText("B");
                        buttons[i][j].setBackground(Color.red);
                        JOptionPane.showMessageDialog(null, "Du hast leider verloren :( ", "Verloren", JOptionPane.WARNING_MESSAGE);
                        isLost = true;
                        DisableButtons();
                    }
                }
        
        if (!isLost)
        {
            // Gewinn checken
            fieldsize -= bombcount;
            for (int i=0; i<bombs.length; i++)
                for (int j=0; j<bombs[0].length; j++) {
                    if(buttons[i][j].isVisible()==false)
                        fieldsize--;
                    else if(!buttons[i][j].getText().equals("") &&  buttons[i][j].getBackground() != Color.blue)
                        fieldsize--;
                }
            if (fieldsize<=0)
            {
                if (win)
                {
                    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                }
                win = true;
                JOptionPane.showMessageDialog(null, "Du hast gewonnen :) ", "Gewonnen", JOptionPane.INFORMATION_MESSAGE);
                DisableButtons();
                restart.setText("Beenden");
                timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    doStuff();
                }
                }, 0, 25);
            }
            fieldsize = xsize*ysize;
        }
        if (ae.getActionCommand() == "restart")
        {
            fieldsize = xsize*ysize;
            bombcount = 0;
            isLost = false;
            restart.setEnabled(false);
            this.setSize(40*xsize+7, 40*ysize+30);
            for(int i=0; i<bombs.length; i++)
                for (int j=0; j<bombs[0].length; j++)
                {
                    buttons[i][j].setBounds(i*40, j*40, 40, 40);
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setVisible(true);
                    buttons[i][j].setBackground(UIManager.getDefaults().getColor("control"));
                    if (rnd.nextInt(100) < BOMBAMOUNT) {
                        bombs[i][j] = true;
                        bombcount++;
                    }
                    else
                        bombs[i][j] = false;
                }
        }
    }
    
    private void doStuff()
    {
        for (int i=0; i<bombs.length; i++)
            for (int j=0; j<bombs[0].length; j++)
            {
                //buttons[i][j].setLocation(buttons[i][j].getLocation().x+(int)(Math.random()*11)-5, buttons[i][j].getLocation().y+(int)(Math.random()*11)-5);
                buttons[i][j].setLocation(buttons[i][j].getLocation().x+(int)(Math.random()*11)-5, buttons[i][j].getLocation().y+(int)(Math.random()*10));
            }
    }
    
    private void DisableButtons()
    {
        for (int i=0; i<bombs.length; i++)
            for (int j=0; j<bombs[0].length; j++)
            {
                buttons[i][j].setEnabled(false);
                if (bombs[i][j] == true)
                {
                    buttons[i][j].setText("B");
                    buttons[i][j].setBackground(Color.red);
                }
            }
        this.setSize(40*xsize+7, 40*ysize+30+40);
        restart.setEnabled(true);
    }
    
    public void checknb(int x, int y) {     //Prüfen ob Nachbarknopf auch 0 ist
        int checkzero = 0;
        for (int i = x-1; i <= x+1; i++) 
            for (int j = y-1; j <= y+1; j++)
                if (i < xsize && j < ysize && i >= 0 && j >= 0)
                    if (bombs[i][j] == true) 
                        checkzero++;
        if (checkzero == 0) {
            buttons[x][y].setVisible(false);
            if (x-1 >= 0)
                if (buttons[x-1][y].isVisible() == true )
                    checknb(x-1,y);
            if (x+1 < xsize)
                if (buttons[x+1][y].isVisible() == true )
                    checknb(x+1,y);

            if (y-1 >= 0)
                if (buttons[x][y-1].isVisible() == true )
                    checknb(x,y-1);
            if (y+1 < ysize)
                if (buttons[x][y+1].isVisible() == true )
                    checknb(x,y+1);
        }
    }
}