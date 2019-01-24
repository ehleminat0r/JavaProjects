package MiscProjects;

import javax.swing.*;
import java.awt.* ;
import java.lang.Math ;

class SnowFlakePanel extends JPanel
{

  public SnowFlakePanel()
  {
    setPreferredSize( new Dimension(400, 400) );
    setBackground( Color.WHITE );    
  }  
  
  private void drawStar( Graphics gr, int x, int y, int size )
  {
    int endX ;
    int endY ;
    int a= 5;
    
    if ( size <= 2 ) return;
    
    // Six lines radiating from (x,y)
    for ( int i = 0; i<a; i++ )
    {
      endX = x + (int)(size*Math.cos( (2*Math.PI/a)*i ));
      endY = y- (int)(size*Math.sin( (2*Math.PI/a)*i ));
      gr.drawLine( x, y, endX, endY );
      drawStar( gr, endX, endY, (int)(size/2.3) );
    }
  }
         
  @Override
  public void paintComponent( Graphics gr )
  { 
    int width  = getWidth();
    int height = getHeight();
    int min;
   
    super.paintComponent( gr );
    gr.setColor( Color.BLUE );
    
    if ( height > width )
      min = height;
    else
      min = width;
      
    drawStar( gr, width/2, height/2, min/4 );
  }
}

public class SnowFlake
{
   public static void main ( String[] args )
   {
      JFrame frame = new JFrame( "Snowflake" );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      frame.getContentPane().add( new SnowFlakePanel() );
      frame.pack();
      frame.setVisible( true );
   }
}