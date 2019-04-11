/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author lhassler
 */
public class PictureCropper implements ActionListener
{
    // Private Members
    private JFrame mFrame = new JFrame("Picture Cropper");
    
    private JMenuBar mMenuBar = new JMenuBar();
    private JMenu mMenu = new JMenu("File");
    private JMenuItem mMenuItemOpenFile = new JMenuItem("Open File");
    private JMenuItem mMenuItemExit = new JMenuItem("Exit");
    
    private PicturePanel mPicturePanel = new PicturePanel();
    
    private JFileChooser mFileChooser = new JFileChooser();
    
    private BufferedImage mImg;
    
    // Main method
    public static void main(String[] args)
    {
        new PictureCropper();
    }
    
    // Constructor
    public PictureCropper()
    {
        Initialize();
    }
    
    // Initialisation
    private void Initialize()
    {
        mFrame.setSize(300, 350);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setResizable(false);
        mFrame.setLocationByPlatform(true);
        mFrame.setVisible(true);
        mFrame.setLayout(null); 
        
        mPicturePanel.setSize(mFrame.getSize().width-6, mFrame.getSize().height-52);
        mPicturePanel.setLocation(0, 0);
        mFrame.add(mPicturePanel);
        
        mFrame.add(mMenuBar);
        mMenuBar.add(mMenu);
        mMenu.add(mMenuItemOpenFile);
        mMenuItemOpenFile.addActionListener(this);
        mMenu.add(mMenuItemExit);
        mMenuItemExit.addActionListener(this);
        mFrame.setJMenuBar(mMenuBar);
        
        mFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        mFileChooser.setAcceptAllFileFilterUsed(false);
    }

    // Actions
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (mMenuItemOpenFile == ae.getSource())
        {
            int returnValue = mFileChooser.showOpenDialog(mFrame);
            if (JFileChooser.APPROVE_OPTION == returnValue)
            {
                try
                {
                    mImg = ImageIO.read(mFileChooser.getSelectedFile());
                    mPicturePanel.setSize(mImg.getWidth(),mImg.getHeight());
                    mFrame.setSize(mImg.getWidth()+6,mImg.getHeight()+52);
                    mPicturePanel.bi = mImg;
                }
                catch (IOException e)
                {
                    System.out.println(e.toString());
                }
            }
            else
            {
                JOptionPane.showMessageDialog(mFrame, "No File selected");
            }
        }
        if (mMenuItemExit == ae.getSource())
        {
            mFrame.dispose();
        }
    }
}

class PicturePanel extends JPanel
{
    public BufferedImage bi;
        
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (bi != null)
        {
            g.drawImage(bi, 0, 0, this);
        }
        else
        {
            g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
            g.drawString("Picture Cropper", this.getWidth()/2 -40, this.getHeight()/2);
        }
    }
}