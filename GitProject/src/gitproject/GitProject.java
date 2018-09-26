/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

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
    JButton[][] buttons = new JButton[10][10];
    
    public Frame()
    {
        super("Test");
        setVisible(true);
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null); 
        setLocation(new Point(100,100));
        initialize();
    }
    
    void initialize()
    {
        for (int i=0; i<10; i++)
        {
            for (int j=0; j<10; j++)
            {
                buttons[i][j] = new JButton("");
                buttons[i][j].setMargin(new Insets(0, 0, 0, 0));            // Entfernt Beschränkung wo Text im JButton stehen darf
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 16));   // legt Schriftart fest
                buttons[i][j].setBounds(i*40, j*40, 40, 40);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setActionCommand(Integer.toString(i)+Integer.toString(j));    // Aktionlistener mit Position als Übergabe z.B. "05"
                this.add(buttons[i][j]);
            }
        }
        this.repaint();
    }    

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae);
        if (ae.getActionCommand() == "a")
        {
            System.out.println("aa");
        }
    }

}
