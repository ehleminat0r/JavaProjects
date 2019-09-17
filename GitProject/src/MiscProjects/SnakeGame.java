package MiscProjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Transient;
import java.util.Random;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.Timer;


import java.util.*;

class Punkt { // Punktklasse definieren
    //Klassenvariablen
    private int x;
    private int y;
    // Konstruktor
    Punkt(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // Methoden
    public void setPunktX(int x) {
        this.x = x;     
    }
    public void setPunktY(int y) {
        this.y = y;     
    }
    public int getPunktX() {
        return this.x;
    }
    public int getPunktY() {
        return this.y;
    }
}

 class Snake {
    // Klassenvariablen
    private ArrayList<Punkt> snakepart = new ArrayList<Punkt>();
    private boolean snakehit = false;
    private int snakelaenge = 5;
    private int richtung = 3; // nach rechts
    
    // Konstruktoren
    Snake() {
        snakepart.add(new Punkt(10,10));
    }
    Snake(int x, int y) {
        snakepart.add(new Punkt(x,y));
    }
    
    // Methoden
    public int snakeLength() {
        return snakepart.size();
    }
    
    public void addsnake() {
        this.snakelaenge++;
    }
    
    public void setRichtung(int richtung) {
        this.richtung = richtung;
    }
    
    public int getRichtung() {
        return richtung;
    }
    
    public int getSPX(int part) {   // SPX snakepart X
        return snakepart.get(part).getPunktX();
    }
    public int getSPY(int part) {
        return snakepart.get(part).getPunktY();
    }
    
    public void addSnakepart(int i) {
        switch (i) {
            case 0:     // Schlangenglied oben hinzufügen (w)
                if (snakepart.get(snakepart.size()-1).getPunktY() > 5 )
                    snakepart.add(new Punkt(snakepart.get(snakepart.size()-1).getPunktX(),snakepart.get(snakepart.size()-1).getPunktY()-1));
                else
                    snakepart.add(new Punkt(snakepart.get(snakepart.size()-1).getPunktX(),42));
                break;
            case 1:     // Schlangenglied unten hinzufügen (s)
                if (snakepart.get(snakepart.size()-1).getPunktY() < 42 )
                    snakepart.add(new Punkt(snakepart.get(snakepart.size()-1).getPunktX(),snakepart.get(snakepart.size()-1).getPunktY()+1));
                else
                    snakepart.add(new Punkt(snakepart.get(snakepart.size()-1).getPunktX(),5));
                break;
            case 2:     // Schlangenglied links hinzufügen (a)
                if (snakepart.get(snakepart.size()-1).getPunktX() > 1 )
                    snakepart.add(new Punkt(snakepart.get(snakepart.size()-1).getPunktX()-1,snakepart.get(snakepart.size()-1).getPunktY()));
                else
                    snakepart.add(new Punkt(38,snakepart.get(snakepart.size()-1).getPunktY()));
                break;
            case 3:     // Schlangenglied rechts hinzufügen (d)
                if (snakepart.get(snakepart.size()-1).getPunktX() < 38 )                
                    snakepart.add(new Punkt(snakepart.get(snakepart.size()-1).getPunktX()+1,snakepart.get(snakepart.size()-1).getPunktY()));
                else
                    snakepart.add(new Punkt(1,snakepart.get(snakepart.size()-1).getPunktY()));
                break;
            default:    // falsche Eingabe
                System.out.println("falsche Eingabe"); 
        }
    }
    
    public void checkHit() {
        for (int j=0; j< snakepart.size()-1;j++)
            for (int i=j+1; i< snakepart.size();i++) {             // Überprüft ob erstes Glied mit anderen kollidiert
                if ( (snakepart.get(j).getPunktX() == snakepart.get(i).getPunktX()) && (snakepart.get(j).getPunktY() == snakepart.get(i).getPunktY()) )
                    snakehit = true;         
            }   
        if (snakehit) {     // Falls es eine Kollision gibt Schlangeteile löschen
            while (snakepart.size() > 1)
                snakepart.remove(0);
            snakelaenge = 5;
            snakehit = false;
        }
    }
    
    public void delSnake() {
        while (snakepart.size() > snakelaenge)
          snakepart.remove(0);
    }
}

public class SnakeGame extends JPanel implements KeyListener {

    @Override public void keyReleased( KeyEvent e ) {
        
    };
    @Override public void keyTyped(KeyEvent e) {
        
    };
    @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()=='a') {
            if (snake1.getRichtung() != 3)
                snake1.setRichtung(2);
        }
        if (e.getKeyChar()=='d') {
            if (snake1.getRichtung() != 2)
                snake1.setRichtung(3);
        }
        if (e.getKeyChar()=='s') {
            if (snake1.getRichtung() != 0)
                snake1.setRichtung(1);
        }
        if (e.getKeyChar()=='w') {
            if (snake1.getRichtung() != 1)
                snake1.setRichtung(0);
        }
    };
    
    // Klassen Variablen
    public int gamespeed = 150;
    
    public Snake snake1 = new Snake(10,10);

    public Punkt snack = new Punkt(5,10);     // Snack Punkte
    private boolean snackaufsnake = true;
    public int pointz = 0;
        
    // Konstruktor
    public SnakeGame() {

    }
  
    // Methoden
    public void snakeMove() {
        snake1.addSnakepart(snake1.getRichtung());
        if (snake1.getSPX(snake1.snakeLength()-1) == snack.getPunktX() && snake1.getSPY(snake1.snakeLength()-1) == snack.getPunktY() ) {
            for (int i=0; i<10; i++)
            {
                snake1.addsnake();
            }
            pointz++;
            snackaufsnake = true;
            while (snackaufsnake) {
                snack.setPunktX(1+(new Random().nextInt(18)));    // 1-18
                snack.setPunktY(5+(new Random().nextInt(18)));    // 5- 22
                snackaufsnake = false;
                for (int i=0; i< snake1.snakeLength()-1;i++) {
                    if (snake1.getSPX(i) == snack.getPunktX() && snake1.getSPY(i) == snack.getPunktY())
                        snackaufsnake = true;
                }               
            }

        }
            
        snake1.delSnake();
    }
    
    @Override
    @Transient
    public Dimension getPreferredSize() {
        return new Dimension(400,440);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
                
        g.setColor(Color.black);
  
        
        
        // Rahmen
        g.drawRect(10, 50, 380, 380);
        /*
        for (int i=0;i<40;i++) {
            g.drawLine(0, 30+(i*10), 400, 30+(i*10));
            g.drawLine(10+(i*10), 30, 10+(i*10), 400);    
        }
        */
        
        // Snake
        snake1.checkHit();  // Kollision prüfen und ggf Glied löschen
        for (int p=0; p < snake1.snakeLength(); p++) {
            if (p%2==0)
                g.setColor(Color.red);
            else
                g.setColor(Color.black);
            if (p == snake1.snakeLength()-1)
                g.setColor(Color.GRAY);
            g.fillOval(snake1.getSPX(p)*10, snake1.getSPY(p)*10, 10, 10); // Schlange zeichnen
        }
        
        // Snake Snack
        g.setColor(Color.blue);
        g.fillOval(snack.getPunktX()*10, snack.getPunktY()*10, 10, 10);
        
        // Schrift
        g.setColor(Color.black);
        g.drawString("Punkte: "+ pointz, 0, 10);
    }

    
    // Main Methode
    public static void main(String[] args)
    {
        // Test

        //
        
        final SnakeGame c = new SnakeGame();
        JFrame frame = new JFrame();
        c.setFocusable(true);
        c.addKeyListener(c);
        
        
        JLabel speedtxt = new JLabel();
        c.add(speedtxt);
        
        JSlider speed = new JSlider();
        speed.setMaximum(0);
        speed.setMaximum(300);
        speed.setValue(150);
        // Schiebebalken wird dem Panel hinzugefügt
        c.add(speed);
        
        frame.getContentPane().add(c);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
        Timer test = new Timer(100, new ActionListener()    //TimerObjekt (100ms) erstellen
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                c.repaint();
                c.snakeMove();
                c.requestFocus();   // wieder Fokus auf game setzten, dass keyboard eingabe funkioniert
            }
        } );
        
        test.start();   // Timer starten
        
        while(true) {
            
           //speed.setValue(c.gamespeed);
           speedtxt.setText(Integer.toString(speed.getValue()));
           test.setDelay(speed.getValue());
        }

        
    }
}