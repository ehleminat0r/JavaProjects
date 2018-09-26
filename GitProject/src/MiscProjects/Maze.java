/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiscProjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lhassler
 */
public class Maze extends JFrame implements ActionListener {
    // Klassenvariablen
    // Level 
    static int[][] lvl = new int[][]{
    { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
    { 2, 2, 2, 3, 3, 3, 2, 2, 2, 2 },
    { 2, 2, 2, 3, 0, 3, 2, 2, 2, 2 },
    { 2, 2, 2, 3, 0, 3, 3, 3, 3, 2 },
    { 2, 3, 3, 3, 1, 0, 1, 0, 3, 2 },
    { 2, 3, 0, 0, 1, 0, 3, 3, 3, 2 },
    { 2, 3, 3, 3, 3, 1, 3, 2, 2, 2 },
    { 2, 2, 2, 2, 3, 0, 3, 2, 2, 2 },
    { 2, 2, 2, 2, 3, 3, 3, 2, 2, 2 },
    { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 } };    
    
    static int[][] lvlgoal = new int[][] {
        {4, 2},
        {2, 5},
        {7, 4},
        {5, 7},
        {0, 0},
        {0, 0},
        {0, 0},
        {0, 0},
        {0, 0},
        {0, 0} };

    static int lvlgoallength = 4;    
    
    static int lvlplayerx = 5;
    static int lvlplayery = 5;      

    /////////////////////////////////////////////    
    
    JMenu gamemenulvl;
    JMenuItem gamemenuquit;
    JMenuItem helpmenuhelp;
    JMenuItem[] lvlmenu = new JMenuItem[20];
    MazePanel mazep;
    private static final int X_SIZE = 416;
    private static final int Y_SIZE = 460;
    
    int lvlcount = 1;
    String lvlname;
    
    
    // Konstruktor
    public Maze(int x, int y) {
        super("Maze");
        this.setSize(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationByPlatform(true);   // Platzierung des Fensters (nicht ganz links oben)
        this.setVisible(true);
        this.setLayout(null); 
        
    }
    
    public void Initialize(){
        mazep = new MazePanel(this.getSize().height,this.getSize().width);
        this.add(mazep);
        mazep.requestFocus();
        
        JMenuBar menu = new JMenuBar();
        JMenu gamemenu = new JMenu("Spiel");
        JMenu helpmenu = new JMenu("Hilfe");
        gamemenulvl = new JMenu("Level");
        
        // Anzahl der Levels zählen
        while(true) {
            lvlname = "lvl"+lvlcount+".txt";
            if (checklvl(lvlname))
                lvlcount++;
            else
                break;
            
        }
        lvlcount--;
        // Menü Levels hinzufügen
        for (int i=1;i<=lvlcount;i++) {
            lvlmenu[i] = new JMenuItem("Level "+i);
            lvlmenu[i].addActionListener(this);
            gamemenulvl.add(lvlmenu[i]);
        }
        // Restliches Menü
        gamemenuquit = new JMenuItem("Beenden");
        helpmenuhelp = new JMenuItem("Hilfe");    
        menu.add(gamemenu);
        menu.add(helpmenu);
        gamemenuquit.addActionListener(this);
        helpmenuhelp.addActionListener(this);
        gamemenu.add(gamemenulvl);
        gamemenu.add(gamemenuquit);
        helpmenu.add(helpmenuhelp);
        this.setJMenuBar(menu);
        this.revalidate();
    
    }


    public static void main(String[] args) {
        
       // Construction
       Maze maze = new Maze(X_SIZE, Y_SIZE);
       
       // Initialization
       maze.Initialize();
       
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for (int i=1; i<=lvlcount;i++)
            if (ae.getSource() == lvlmenu[i])
                changelvl(i); 
        if (ae.getSource() == gamemenuquit){
            System.exit(0);
        }   
        if (ae.getSource() == helpmenuhelp){
            System.out.println("Hilfe wurde angeklickt");
        }           
    }
    
    public void changelvl(int levelno) {
        this.remove(mazep);
        lvlgoallength = loadlvl("lvl"+levelno+".txt");
        mazep = new MazePanel(this.getSize().height,this.getSize().width, lvl, lvlgoal, lvlgoallength, lvlplayerx, lvlplayery);
        this.add(mazep);
        mazep.requestFocus();
        mazep.repaint();
                
    }
    
    private static boolean checklvl(String fileName) {
        File file = new File(fileName);

        if (!file.canRead() || !file.isFile())
            return false;
        else
            return true; 
    }  
    
    private static int loadlvl(String fileName) {
        int count = 0;
        int countgoals = 0;
        boolean next = false;
        File file = new File(fileName);

        if (!file.canRead() || !file.isFile())
            System.exit(0);

            BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                if (count <10 )
                    for (int i=0; i<zeile.length();i++) {
                        lvl[count][i]= Integer.parseInt(zeile.substring(i, i+1));
                    }
                if (count > 10 && !zeile.equals("")) {
                    lvlgoal[countgoals][0] = Integer.parseInt(zeile.substring(0,1));
                    lvlgoal[countgoals][1] = Integer.parseInt(zeile.substring(2,3));    
                    countgoals++;
                    
                }
                if (next) {
                    lvlplayerx = Integer.parseInt(zeile.substring(0,1));    
                    lvlplayery = Integer.parseInt(zeile.substring(2,3));
                }                
                if (count > 11 && zeile.equals("")) {
                    next = true;
                }                
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
        return countgoals-1 ;
    } 
    
}

class MazePanel extends JPanel implements KeyListener {
    // Klassenvariablen
    // Level
    int[][] gamefield = new int[][]{
    { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 },
    { 3, 0, 0, 0, 0, 0, 0, 0, 0, 3 },
    { 3, 0, 1, 0, 0, 0, 0, 0, 0, 3 },
    { 3, 0, 0, 0, 0, 1, 0, 2, 0, 3 },
    { 3, 0, 0, 0, 0, 0, 1, 2, 0, 3 },
    { 3, 0, 0, 0, 0, 1, 0, 2, 0, 3 },
    { 3, 0, 0, 0, 0, 0, 0, 2, 0, 3 },
    { 3, 0, 0, 2, 2, 2, 2, 2, 0, 3 },
    { 3, 0, 0, 0, 0, 0, 0, 0, 0, 3 },
    { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 } };
    
    // Ziel
    int[][] goal = new int[][] {
        {1, 1},
        {8, 6},
        {8, 7},
        {8, 8},
        {0, 0},
        {0, 0},
        {0, 0},
        {0, 0},
        {0, 0},
        {0, 0} };
    
    int goallength = 4;
    
    // Spieler
    int playerx = 3;
    int playery = 3;    
    
    // Konstruktor
    public MazePanel(int height,int width) {
        this.setSize(width-6, height-52);
        this.setBackground(Color.white);
        this.addKeyListener(this);
        this.setFocusable(true);
    }    
    
    // Konstruktor mit Übergabeparameter Level usw
    public MazePanel(int height,int width, int[][] lvl, int[][]lvlgoal, int lvlgoallength, int lvlplayerx, int lvlplayery) {
        gamefield = lvl;
        goal = lvlgoal;
        goallength = lvlgoallength;
        playerx = lvlplayerx;
        playery = lvlplayery;
        this.setSize(width-6, height-52);
        this.setBackground(Color.white);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setVisible(true);
        this.repaint();
    }    
    
    // Methoden
    public void move(int direction) {
        switch (direction) {
            case 1:  //w
                if ((playery > 0 && (gamefield[playery-1][playerx]<2)) && !((gamefield[playery-1][playerx]==1) && !(gamefield[playery-2][playerx] == 0)) ) {
                    playery -= 1;
                    if ((gamefield[playery][playerx] == 1) && (gamefield[playery-1][playerx] < 2)) {
                        gamefield[playery][playerx] = 0;
                        gamefield[playery-1][playerx] = 1;
                    }
                }
                break;
            case 2:  //a
                if ((playerx > 0 && (gamefield[playery][playerx-1]<2)) && !((gamefield[playery][playerx-1]==1) && !(gamefield[playery][playerx-2] == 0)) ) {
                    playerx -= 1;
                    if ((gamefield[playery][playerx] == 1) && (gamefield[playery][playerx-1] < 2)) {
                        gamefield[playery][playerx] = 0;
                        gamefield[playery][playerx-1] = 1;
                    }
                }
                break;                
            case 3:  //s
                if ((playery < gamefield.length-1 && (gamefield[playery+1][playerx]<2)) && !((gamefield[playery+1][playerx]==1) && !(gamefield[playery+2][playerx] == 0)) ) {
                    playery += 1;
                    if ((gamefield[playery][playerx] == 1) && (gamefield[playery+1][playerx] < 2)) {
                        gamefield[playery][playerx] = 0;
                        gamefield[playery+1][playerx] = 1;
                    }
                }
                break;                
            case 4:  //d
                if ((playerx < gamefield[0].length-1 && (gamefield[playery][playerx+1]<2)) && !((gamefield[playery][playerx+1]==1) && !(gamefield[playery][playerx+2] == 0)) ) {
                    playerx += 1;
                    if ((gamefield[playery][playerx] == 1) && (gamefield[playery][playerx+1] < 2)) {
                        gamefield[playery][playerx] = 0;
                        gamefield[playery][playerx+1] = 1;
                    }
                }           
        }
        // Gewinnen check
        int save = goallength;
        for (int i=0; i<goallength;i++) {
            if (gamefield[goal[i][1]][goal[i][0]] == 1)
                save--;
        }
        if (save==0)
            JOptionPane.showMessageDialog(null,"Gewonnen");
        this.repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() == 'w')
            move(1);   
        if (ke.getKeyChar() == 'a')
            move(2);
        if (ke.getKeyChar() == 's')
            move(3);   
        if (ke.getKeyChar() == 'd')
            move(4);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //
    }    
    
    @Override
    public void keyReleased(KeyEvent ke) {
        //
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Rahmen
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getSize().width-1, this.getSize().height-1);    
        
        // Spielfeld
        for (int i=0; i<gamefield.length; i++)
            for (int j=0; j<gamefield[0].length; j++) {
                g.setColor(Color.green);
                if (gamefield[j][i] == 1)
                    g.fillRect(i*40+5, j*40+5, 38, 38);
                g.setColor(Color.black);
                if (gamefield[j][i] == 2)
                    g.fillRect(i*40+5, j*40+5, 38, 38);  
                g.setColor(Color.blue);
                if (gamefield[j][i] == 3)
                    g.fillRect(i*40+5, j*40+5, 38, 38);                      
            }
        // Ziel
        g.setColor(Color.black); 
        for (int i=0; i<goallength;i++) {
            g.drawRect(goal[i][0]*40+5, goal[i][1]*40+5, 38, 38);    
        }
        
        // Spieler
        g.setColor(Color.red);
        g.fillRect(playerx*40+5, playery*40+5, 38, 38);   
    }
}