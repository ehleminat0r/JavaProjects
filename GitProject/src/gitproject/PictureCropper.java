/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author lhassler
 */
public class PictureCropper implements ActionListener, ChangeListener
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
    private JMenuItem mMenuItemReverseColor = new JMenuItem("Reverse Color");

    // paint bar
    private JMenu mMenuPaint = new JMenu("Paint");
    private JCheckBox mCheckBoxPaint = new JCheckBox("Paint");
    private JSlider mSlider = new JSlider(1,20);
    private JLabel mSliderLabel = new JLabel("Brushsize: 10");
    
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
        mMenuEdit.add(mMenuItemReverseColor);
        mMenuItemReverseColor.addActionListener(this);
        
        
        // add paint bar to menu bar
        mMenuBar.add(mMenuPaint);
        mMenuPaint.add(mCheckBoxPaint);
        mCheckBoxPaint.addActionListener(this);
        mMenuPaint.add(mSlider);
        mSlider.setPreferredSize(new Dimension(120,20));
        mSlider.addChangeListener(this);
        mMenuPaint.add(mSliderLabel);
        
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
        
        // Reverse Color
        if (mMenuItemReverseColor == ae.getSource())
        {
            if (null != mImg)
            {
                Color col;
                int red, green, blue;
                for(int i=0; i<mImg.getWidth(); i++)
                {
                    for (int j=0; j<mImg.getHeight(); j++)
                    {
                        col = new Color(mImg.getRGB(i, j));
                        // color manipulation
                        red = 255 - col.getRed();
                        green = 255 - col.getGreen();
                        blue = 255 - col.getBlue();
                        col = new Color(red, green, blue);
                        // set color
                        mImg.setRGB(i, j, col.getRGB());
                    }
                }
                
                mPicturePanel.repaint();
            }
        }
        
        // Checkbox Paint
        if (mCheckBoxPaint == ae.getSource())
        {
            if (mCheckBoxPaint.isSelected())
            {
                mPicturePanel.paint = true;
                mPicturePanel.ptOne = null;
                mPicturePanel.ptTwo = null;
                mPicturePanel.repaint();
            }
            else
            {
                mPicturePanel.paint = false;
            }
        }
    }

    private void drawPanel() {
        mPicturePanel.setSize(mImg.getWidth(),mImg.getHeight());
        mFrame.setSize(mImg.getWidth()+6,mImg.getHeight()+52);
        mPicturePanel.bi = mImg;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        if (mSlider == ce.getSource())
        {
            mSliderLabel.setText("Brushsize: " + String.format("%02d", mSlider.getValue()));
            mPicturePanel.brushSize = mSlider.getValue();
        }
    }
}

class PicturePanel extends JPanel implements MouseListener, MouseMotionListener 
{
    public BufferedImage bi;
    public Point ptOne, ptTwo;
    public int rectX, rectY, sizeX, sizeY;
    public boolean paint = false;
    public int brushSize = 10;
    
    public PicturePanel()
    {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
        
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (bi != null)
        {
            g.drawImage(bi, 0, 0, this);
            if (null != ptOne && null != ptTwo)
            {
                calcRect();
                g.drawRect(rectX, rectY, sizeX, sizeY);
            }
        }
        else
        {
            g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
            g.drawString("Picture Cropper", this.getWidth()/2 -40, this.getHeight()/2);
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
        if (rectX < 0)
        {
            rectX = 0;
        }
        if (ptOne.y <= ptTwo.y)
        {
            rectY = ptOne.y;
        }
        else
        {
            rectY = ptTwo.y;
        }
        if (rectY < 0)
        {
            rectY = 0;
        }
        sizeX = Math.abs(ptTwo.x-ptOne.x);
        if (sizeX + rectX > bi.getWidth())
        {
            sizeX = bi.getWidth() - rectX;
        }
        sizeY = Math.abs(ptTwo.y-ptOne.y);
        if (sizeY + rectY > bi.getHeight())
        {
            sizeY = bi.getHeight()- rectY;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //JOptionPane.showMessageDialog(this, "X: "+me.getX()+ " Y: " + me.getY());
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (bi != null)
        {
            if (!paint)
            {
                ptOne = new Point(me.getX(), me.getY());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (bi != null)
        {
            if (!paint)
            {
                ptTwo = new Point(me.getX(), me.getY());
                repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (bi != null)
        {
            if (!paint)
            {
                ptTwo = new Point(me.getX(), me.getY());
                repaint();    
            }
            else
            {
                for (int i=0; i< brushSize; i++)
                {
                    for (int j=0; j< brushSize; j++)
                    {
                       bi.setRGB(me.getX()-(brushSize/2)+i, me.getY()-(brushSize/2)+j, new Color(0,0,0).getRGB()); 
                    }
                }
                
                repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}