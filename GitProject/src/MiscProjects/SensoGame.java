/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiscProjects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author lhassler
 */
public class SensoGame extends JFrame implements ActionListener{
    // Klassen Variablen
    JButton buttonGruen = new JButton();
    JButton buttonGelb = new JButton();
    JButton buttonBlau = new JButton();
    JButton buttonRot = new JButton();
    
    JButton start = new JButton("GO");
    
    JLabel labelMusterCount = new JLabel("0");
    
    int[] muster = new int[50];
    int[] playermuster = new int[50];
    int playercount = 0;
    int mustercount = 1;
    int musterschritt = 0;
    int gameloop = 0;
    
    // Konstruktor
    public SensoGame() {
        super("Senso");
        this.setSize(306, 329);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.setLayout(null); 
        // Buttons dem Frame hinzufügen
        this.add(buttonGruen);
        this.add(buttonGelb);
        this.add(buttonBlau);
        this.add(buttonRot);
        this.add(start);
        this.add(labelMusterCount);
        // Plazieren
        buttonGruen.setBounds(100, 0, 100,100);
        buttonGelb.setBounds(0, 100, 100, 100);
        buttonBlau.setBounds(200, 100, 100, 100);
        buttonRot.setBounds(100,200, 100, 100);
        start.setBounds(120, 120, 60, 60);
        labelMusterCount.setBounds(145, 135, 30, 30);
        // Farben
        ResetButtons();
        // Knöpfe Deaktivieren
        SwitchButtonEnabled();
        // Action Listener setzen
        buttonGruen.addActionListener(this);
        buttonGelb.addActionListener(this);
        buttonBlau.addActionListener(this);
        buttonRot.addActionListener(this);
        start.addActionListener(this);
        // ActionCommands zur identifizierung welcher Knopf gedrückt wurde
        buttonGruen.setActionCommand("gruen");
        buttonGelb.setActionCommand("gelb");
        buttonBlau.setActionCommand("blau");
        buttonRot.setActionCommand("rot"); 
        start.setActionCommand("start");
        // Muster festlegen
        for (int i=0; i<50;i++)
            muster[i] = new Random().nextInt(4);
    }
    // Methoden
    
    Timer zeit = new Timer(100, new ActionListener() {   // Timer erstellen
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameloop<9) {
                switch (muster[musterschritt]) {
                case 0:
                    buttonGruen.setBackground(Color.black);
                    break;
                case 1:
                    buttonGelb.setBackground(Color.black);
                    break;
                case 2:
                    buttonBlau.setBackground(Color.black);
                    break;
                case 3:
                    buttonRot.setBackground(Color.black);
                    break;  
                }
            }   
            else
            {
                ResetButtons();
            }
            
            if (gameloop<10)
                gameloop++;
            else {
                gameloop = 0;
                if (musterschritt < mustercount)
                    musterschritt++;
                if (musterschritt == mustercount) {
                    musterschritt = 0;
                    mustercount++;
                    playercount = mustercount-1;
                    zeit.stop();
                    SwitchButtonEnabled();
                }
            }
                
        }
     });
    
     // Listener-Methode der Schnittstelle ActionListener
    @Override
    public void actionPerformed( ActionEvent evt)
    {
        if (evt.getActionCommand().equals("gruen")) {
            playermuster[mustercount-playercount-1] = 0;
            playercount--;
            CheckMuster(); 
        }
        if (evt.getActionCommand().equals("gelb")) {
            playermuster[mustercount-playercount-1] = 1;
            playercount--;
            CheckMuster(); 
        }
        if (evt.getActionCommand().equals("blau")) {
            playermuster[mustercount-playercount-1] = 2;
            playercount--;        
            CheckMuster(); 
        }
        if (evt.getActionCommand().equals("rot")) {
            playermuster[mustercount-playercount-1] = 3;
            playercount--;
            CheckMuster(); 
        }
     
        
        if (evt.getActionCommand().equals("start")) {
            zeit.start();
            start.setVisible(false);
        }
    }
    
    
    public void ResetButtons() {
        buttonGruen.setBackground(Color.green);
        buttonGelb.setBackground(Color.yellow);
        buttonBlau.setBackground(Color.blue);
        buttonRot.setBackground(Color.red);  
    }
    
    public void SwitchButtonEnabled() {
        buttonGruen.setEnabled(!buttonGruen.isEnabled());
        buttonGelb.setEnabled(!buttonGelb.isEnabled());
        buttonBlau.setEnabled(!buttonBlau.isEnabled());
        buttonRot.setEnabled(!buttonRot.isEnabled());
    }
    
    public void CheckMuster() {
        boolean check = true;
        // letzte Eingabe richtig?
        if (muster[mustercount-playercount-2] != playermuster[mustercount-playercount-2])
            check = false;
        
        if (check && (playercount==0)) {
            zeit.start();
            SwitchButtonEnabled();
            labelMusterCount.setText(Integer.toString(mustercount-1));
        }
        
        if (!check) {
            JOptionPane.showMessageDialog(null,"Du hast bei Schritt "+(mustercount-2)+" des Musters Verloren!","Senso-Game",JOptionPane.WARNING_MESSAGE);
            SwitchButtonEnabled();
            start.setVisible(true);
            for (int i=0; i<50;i++)
                muster[i] = new Random().nextInt(4);
            playercount = 0;
            mustercount = 1;
            musterschritt = 0;
            labelMusterCount.setText("0");
        }
    }
    
    
    public static void main(String[] args) {
       new SensoGame();
    }
}
