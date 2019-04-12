/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    // general menu bar
    private JMenuBar mMenuBar = new JMenuBar();
    
    // file bar
    private JMenu mMenuFile = new JMenu("File");
    private JMenuItem mMenuItemOpenFile = new JMenuItem("Open File");
    private JMenuItem mMenuItemSaveFile = new JMenuItem("Save File");
    private JMenuItem mMenuItemExit = new JMenuItem("Exit");
    
    // edit bar
    private JMenu mMenuEdit = new JMenu("Edit");
    private JMenuItem mMenuItemCropSelection = new JMenuItem("Crop Selection");
    
    // image
    private JFileChooser mFileChooser = new JFileChooser();
    private PicturePanel mPicturePanel = new PicturePanel();
    private BufferedImage mImg;
    
    // Main method
    public static void main(String[] args)
    {
        new PictureCropper();
    }
    
    // Constructor
    public PictureCropper()
    {
        initialize();
    }
    
    // Initialisation
    private void initialize()
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
        
        // adds general menu bar
        mFrame.add(mMenuBar);
        
        // adds file bar to menu bar
        mMenuBar.add(mMenuFile);
        mMenuFile.add(mMenuItemOpenFile);
        mMenuItemOpenFile.addActionListener(this);
        mMenuFile.add(mMenuItemSaveFile);
        mMenuItemSaveFile.addActionListener(this);
        mMenuFile.add(mMenuItemExit);
        mMenuItemExit.addActionListener(this);
        
        // adds edit bar to menu bar
        mMenuBar.add(mMenuEdit);
        mMenuEdit.add(mMenuItemCropSelection);
        mMenuItemCropSelection.addActionListener(this);
        
        // set general menu bar
        mFrame.setJMenuBar(mMenuBar);
        
        // only allow images in file chooser
        mFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        mFileChooser.setAcceptAllFileFilterUsed(false);
    }

    // Actions
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // Open File
        if (mMenuItemOpenFile == ae.getSource())
        {
            if (JFileChooser.APPROVE_OPTION == mFileChooser.showOpenDialog(mFrame))
            {
                try {
                    mImg = ImageIO.read(mFileChooser.getSelectedFile());
                } catch (IOException ex) {
                    Logger.getLogger(PictureCropper.class.getName()).log(Level.SEVERE, null, ex);
                }
                drawPanel();
            }
            else
            {
                JOptionPane.showMessageDialog(mFrame, "No File selected");
            }
        }
        
        // Save File
        if (mMenuItemSaveFile == ae.getSource())
        {
            if (null != mImg)
            {
                mFileChooser.setSelectedFile(new File(".jpg"));
                if (JFileChooser.APPROVE_OPTION == mFileChooser.showSaveDialog(mFrame))
                {
                    try {
                        ImageIO.write(mImg, "jpg", mFileChooser.getSelectedFile());
                    } catch (IOException ex) {
                        Logger.getLogger(PictureCropper.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
            }
        }
        
        // Exit
        if (mMenuItemExit == ae.getSource())
        {
            mFrame.dispose();
        }
        
        // CropSelection
        if (mMenuItemCropSelection == ae.getSource())
        {
            if (null != mPicturePanel.ptOne && null != mPicturePanel.ptTwo && null != mImg)
            {
                mImg = mImg.getSubimage(mPicturePanel.rectX, mPicturePanel.rectY, mPicturePanel.sizeX, mPicturePanel.sizeY);
                mPicturePanel.ptOne = null;
                mPicturePanel.ptTwo = null;
                drawPanel();
            }
        }
    }

    private void drawPanel() {
        mPicturePanel.setSize(mImg.getWidth(),mImg.getHeight());
        mFrame.setSize(mImg.getWidth()+6,mImg.getHeight()+52);
        mPicturePanel.bi = mImg;
    }
}

class PicturePanel extends JPanel implements MouseListener
{
    public BufferedImage bi;
    public Point ptOne, ptTwo;
    public int rectX, rectY, sizeX, sizeY;
    
    public PicturePanel()
    {
        this.addMouseListener(this);
    }
        
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
        
        if (null != ptOne && null != ptTwo)
        {
            calcRect();
            g.drawRect(rectX, rectY, sizeX, sizeY);
        }
    }

    private void calcRect() {
        if (ptOne.x <= ptTwo.x)
        {
            rectX = ptOne.x;
        }
        else
        {
            rectX = ptTwo.x;
        }
        if (ptOne.y <= ptTwo.y)
        {
            rectY = ptOne.y;
        }
        else
        {
            rectY = ptTwo.y;
        }
        sizeX = Math.abs(ptTwo.x-ptOne.x);
        sizeY = Math.abs(ptTwo.y-ptOne.y);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //JOptionPane.showMessageDialog(this, "X: "+me.getX()+ " Y: " + me.getY());
    }

    @Override
    public void mousePressed(MouseEvent me) {
        ptOne = new Point(me.getX(), me.getY());
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        ptTwo = new Point(me.getX(), me.getY());
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}