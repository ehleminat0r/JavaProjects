/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HRS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sun.io.Win32ErrorMode;

/**
 *
 * @author lhassler
 */
public class GUI implements ActionListener, ChangeListener
{
    // Private Members
    String[] col = {"First Name", "Last Name",  "Sport",  "# of Years",   "Vegetarian"};
    Object[][] data = {
    {"Kathy", "Smith",
     "Snowboarding", new Integer(5), new Boolean(false)},
    {"John", "Doe",
     "Rowing", new Integer(3), new Boolean(true)},
    {"Sue", "Black",
     "Knitting", new Integer(2), new Boolean(false)},
    {"Jane", "White",
     "Speed reading", new Integer(20), new Boolean(true)},
    {"Joe", "Brown",
     "Pool", new Integer(10), new Boolean(false)}
};
    
    private JFrame mFrame = new JFrame("HRS GUI");
    private DogPanel mDogPanel = new DogPanel();
    public HRS hrs = new HRS();
    
    // Constructor
    public GUI()
    {
        initialize();
    }
    
    private void initialize()
    {
        // Frame
        mFrame.setSize(900, 650);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setResizable(false);
        mFrame.setLocationByPlatform(true);
        mFrame.setVisible(true);
        mFrame.setLayout(null); 
        //
        mFrame.add(mDogPanel);
        mDogPanel.setSize(500, 450);
        mDogPanel.setmHRS(hrs);
    }

    
    
    public static void main(String[] args)
    {
        GUI test = new GUI();
        
        
        
        Dog h1 = new Dog();
        Owner o1 = new Owner();
        h1.setAge(new Date());
        h1.setName("Hugo");
        h1.setOwner(o1);
        h1.setRace("Pit");
        o1.setmName("Hans");
        
        test.hrs.getJobs().add(new Job(new GregorianCalendar(2019, 1, 1),h1, "Zimmer 1"));
        
        System.out.println(test.hrs.getJobs().get(0).getDate().toZonedDateTime()); 
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
