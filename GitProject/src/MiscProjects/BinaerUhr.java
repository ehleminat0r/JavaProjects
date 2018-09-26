/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiscProjects;

import java.awt.Color;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import java.time.LocalDateTime;

/**
 *
 * @author lhassler
 */

class Uhrig extends JPanel {
    // Klassen Variablen
    public byte hours = (byte) LocalDateTime.now().getHour();
    public byte minutes = (byte) LocalDateTime.now().getMinute();
    public byte seconds = (byte) LocalDateTime.now().getSecond();
        
    Timer refreshUhr = new Timer(1000, new ActionListener() {   // Timer erstellen und Variablen aktualisieren
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds = (byte) LocalDateTime.now().getSecond();
            minutes = (byte) LocalDateTime.now().getMinute();
            hours = (byte) LocalDateTime.now().getHour();
            repaint();
        }
     });

    
    @Override
    public void paint(Graphics g) {
        g.setColor(UIManager.getDefaults().getColor("control"));    // Standart Hintergrund
        g.fillRect(0, 0, 120, 120);
        g.setColor(Color.black);
        // Stunden Bit Beschriftung
        g.drawString("16", 18, 10);
        g.drawString("8", 42, 10);
        g.drawString("4", 62, 10);
        g.drawString("2", 82, 10);
        g.drawString("1", 102, 10);
        // Minuten Bit Beschriftung
        g.drawString("32", -2, 50);        
        g.drawString("16", 18, 50);
        g.drawString("8", 42, 50);
        g.drawString("4", 62, 50);
        g.drawString("2", 82, 50);
        g.drawString("1", 102, 50);     
        // Sekunden Bit Beschriftung
        g.drawString("32", -2, 90);        
        g.drawString("16", 18, 90);
        g.drawString("8", 42, 90);
        g.drawString("4", 62, 90);
        g.drawString("2", 82, 90);
        g.drawString("1", 102, 90);
        // Stunden Rechtecke zeichnen
        if ((hours & 0B00010000) != 0)
            g.fillRect(20, 12, 10, 10);
        else
            g.drawRect(20, 12, 10, 10);
        if ((hours & 0B00001000) != 0)
            g.fillRect(40, 12, 10, 10);
        else
            g.drawRect(40, 12, 10, 10);  
        if ((hours & 0B00000100) != 0)
            g.fillRect(60, 12, 10, 10);
        else
            g.drawRect(60, 12, 10, 10);
        if ((hours & 0B00000010) != 0)
            g.fillRect(80, 12, 10, 10);
        else
            g.drawRect(80, 12, 10, 10);   
        if ((hours & 0B00000001) != 0)
            g.fillRect(100, 12, 10, 10);
        else
            g.drawRect(100, 12, 10, 10);

        // Minuten Rechtecke zeichnen
        if ((minutes & 0B00100000) != 0)
            g.fillRect(0, 52, 10, 10);
        else
            g.drawRect(0, 52, 10, 10);
        if ((minutes & 0B00010000) != 0)
            g.fillRect(20, 52, 10, 10);
        else
            g.drawRect(20, 52, 10, 10);
        if ((minutes & 0B00001000) != 0)
            g.fillRect(40, 52, 10, 10);
        else
            g.drawRect(40, 52, 10, 10);  
        if ((minutes & 0B00000100) != 0)
            g.fillRect(60, 52, 10, 10);
        else
            g.drawRect(60, 52, 10, 10);
        if ((minutes & 0B00000010) != 0)
            g.fillRect(80, 52, 10, 10);
        else
            g.drawRect(80, 52, 10, 10);   
        if ((minutes & 0B00000001) != 0)
            g.fillRect(100, 52, 10, 10);
        else
            g.drawRect(100, 52, 10, 10);   
    
        // Sekunden Rechtecke zeichnen  
        if ((seconds & 0B00100000) != 0)
            g.fillRect(0, 92, 10, 10);
        else
            g.drawRect(0, 92, 10, 10);
        if ((seconds & 0B00010000) != 0)
            g.fillRect(20, 92, 10, 10);
        else
            g.drawRect(20, 92, 10, 10);
        if ((seconds & 0B00001000) != 0)
            g.fillRect(40, 92, 10, 10);
        else
            g.drawRect(40, 92, 10, 10);  
        if ((seconds & 0B00000100) != 0)
            g.fillRect(60, 92, 10, 10);
        else
            g.drawRect(60, 92, 10, 10);
        if ((seconds & 0B00000010) != 0)
            g.fillRect(80, 92, 10, 10);
        else
            g.drawRect(80, 92, 10, 10);   
        if ((seconds & 0B00000001) != 0)
            g.fillRect(100, 92, 10, 10);
        else
            g.drawRect(100, 92, 10, 10);      
  }
}

public class BinaerUhr extends JFrame {
    // Klassen Variablen
    JLabel hoursLabel = new JLabel("Stunden");
    JLabel minutesLabel = new JLabel("Minuten");
    JLabel secondsLabel = new JLabel("Sekunden");
    static Uhrig drawPanel = new Uhrig();
        
    // Konstruktor
    public BinaerUhr() {
        super("B-Uhr");
        this.setSize(200, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.setLayout(null);
        this.add(hoursLabel);
        this.add(minutesLabel);
        this.add(secondsLabel);
        this.add(drawPanel);
        drawPanel.setBounds(75, 10, 120, 120);
        hoursLabel.setBounds(22, 20, 50, 10);
        minutesLabel.setBounds(22, 60, 50, 10);
        secondsLabel.setBounds(10, 100, 60, 10);
    }
       
    
    // Main Methode
    public static void main(String[] args) {
        new BinaerUhr();
        drawPanel.refreshUhr.start();
    }
    

}
