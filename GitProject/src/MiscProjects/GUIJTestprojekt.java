package MiscProjects;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class MeinFrame extends JFrame implements ActionListener
{
    JLabel label;
    JButton bChange;
    JButton krot;
    JButton kblau;
    JLabel beschr;
    
    JTextField text;
    
    // Konstruktor
    MeinFrame( String title )
    {
        super( title );                      // den Konstruktor von JFrame aufrufen
        setSize( 400, 400 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        setLayout( new FlowLayout() );       // den Layoutmanager setzen
        
        label = new JLabel("Hello Swing!");  // ein JLabel konstruieren
        add( label );                        // das Label dem JFrame hinzufügen
        bChange = new JButton("Klick mich!"); // einen JButton konstruieren
        add( bChange );                     // dem JFrame den Button hinzufügen
        // das ButtonFrame-Objekt als Listener für den JButton registrieren
        bChange.addActionListener( this ); 
        bChange.setActionCommand("anders");
        krot = new JButton("Rot");
        add (krot);
        kblau = new JButton("Blau");
        add (kblau);
        
        text = new JTextField(20);
        add (text);
        text.addActionListener(this);
        
        beschr = new JLabel("Das ist ein Text Feld!");  
        add (beschr);
        
        krot.addActionListener(this);
        kblau.addActionListener(this);
        krot.setActionCommand("rot");
        kblau.setActionCommand("blau");
    }
    // Listener-Methode der Schnittstelle ActionListener
    public void actionPerformed( ActionEvent evt)
    {
        String testtext = text.getText();
        try
        {
            int berech = Integer.parseInt(testtext);
            berech = berech*3;
            beschr.setText(String.valueOf(berech));            
        }
        catch ( Exception ex )
        {
            beschr.setText("Keine Zahl");
        }
        //setSize(450, 450);
        //getContentPane().setBackground( Color.green );
        if ( evt.getActionCommand().equals( "rot" ) )
            getContentPane().setBackground(  Color.red  ) ;
        if ( evt.getActionCommand().equals( "blau" ) )
            getContentPane().setBackground( Color.blue ) ;
        if (evt.getActionCommand().equals( "anders"))
            text.setBackground(Color.yellow);
        repaint();  // Das System auffordern den Bildschirm zu zeichnen
    }
}

    

    
        
    


public class GUIJTestprojekt
{
       
    public static void main(String[] args)
    {
    JFrame master = new JFrame("Klicken Sie, um alles zu beenden");
    JFrame temp = new JFrame("Klicken Sie, um NUR diesen Frame zu beenden");

    MeinFrame frame = new MeinFrame("Hello"); // ein MeinFrame-Objekt konstruieren  
    frame.setVisible(true);
    
    // master.setVisible( true );
    // master.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    // master.setSize( 400, 300 );

    // temp.setVisible( true );
    // temp.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE  );
    // temp.setSize( 300, 200 );
    }
    
}

