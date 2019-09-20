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
public class HRS
{
    // Private Members
    private JFrame mFrame = new JFrame("HRS GUI");
    private MainPanel mMainPanel = new MainPanel();
    
    // Public Members
    public static HrsLogic hrs = new HrsLogic();
    
    // Constructor
    public HRS()
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
        mFrame.add(mMainPanel);
        mMainPanel.setSize(800, 600);
    }

    
    
    public static void main(String[] args)
    {
        HRS test = new HRS();
        
        /*
        Dog h1 = new Dog();
        Owner o1 = new Owner();
        h1.setAge(new Date());
        h1.setName("Hugo");
        h1.setOwner(o1);
        h1.setRace("Pit");
        o1.setmName("Hans");
        
        test.hrs.getJobs().add(new Job(new GregorianCalendar(2019, 1, 1),h1, "Zimmer 1"));
        
        System.out.println(test.hrs.getJobs().get(0).getDate().toZonedDateTime()); 
        */
    }
    
}
