import javax.swing.*;  //JFrame, JPanel, JButton, ImageIcon, Timer
import javax.swing.event.*;  //KeyAdapter
import java.awt.*;  //Dimension, Toolkit, CardLayout, Image, Font, Color, Graphics
import java.awt.geom.*;  //ActionListener
import java.awt.event.*;  //ActionListener
import java.awt.image.*;  //Image, BufferedImage
import java.io.*;  //System
import java.applet.*;  //Applet, AudioClip
import java.net.URL;  //URL
import java.util.*;  //Scanner

public class SK_Panel_Title extends JPanel
{
   SK_ControlPanel myControlPanel;
   
   //GRAPHICS RESOURCES
   private final ImageIcon IMAGE_TITLE_BG = new ImageIcon("Resources/Title_BG.jpg");
      
   //FONT RESOURCES
   private final Font FONT_TITLE = new Font("Castellar", Font.BOLD, 70);
   private final Font FONT_BUTTON = new Font("Baskerville", Font.BOLD, 55);
   
   //CONSTRUCTOR - TITLE PANEL
   public SK_Panel_Title(SK_ControlPanel pane)
   {
      myControlPanel = pane;
      
      setLayout(null);
      
      //SCORES BUTTON
      Insets in = getInsets();
      JButton scores = new JButton("Scores");
      scores.setOpaque(false);
      scores.setContentAreaFilled(false);
      scores.setBorderPainted(false);
      scores.setFont(FONT_BUTTON);
      scores.setForeground(Color.BLUE);
      scores.setBounds(270 + in.left, 775 + in.top, 450, 100);
      scores.addActionListener(new ScoresButtonListener());
      add(scores);
      
      
      
         // play.setFont(new Font("Baskerville", Font.BOLD, 55));
         // help.setFont(new Font("Baskerville", Font.BOLD, 55));
         // back.setFont(new Font("Baskerville", Font.BOLD, 60));
         // quit2.setFont(new Font("Baskerville", Font.BOLD, 40));
         // quit.setFont(new Font("Baskerville", Font.BOLD, 55));
         // play.setForeground(Color.BLUE);
         // help.setForeground(Color.BLUE);
         // back.setForeground(Color.BLUE);
         // quit2.setForeground(Color.BLUE);
         // quit.setForeground(Color.RED);
         // play.addActionListener(new playListener());
         // help.addActionListener(new helpListener());
         // back.addActionListener(new backListener());
         // quit2.addActionListener(new quit2Listener());
         // quit.addActionListener(new quitListener());
      //    
      //    play.setBounds(415 + in.left, 625 + in.top, 150, 100);
         // help.setBounds(325 + in.left, 700 + in.top, 350, 100);
         // quit.setBounds(415 + in.left, 850 + in.top, 150, 100);
      //    
         // title.add(play);
         // title.add(help);
         // title.add(quit);

   }
   
   //REPAINT
   public void paintComponent(Graphics g)
   {
      //DRAW BACKGROUND
      g.drawImage(IMAGE_TITLE_BG.getImage(), 0, 0, getWidth(), getHeight(), null);
      
      //DRAW TITLE
      g.setFont(FONT_TITLE);
      g.setColor(Color.WHITE);
      g.drawString("Shuffle Keys", 200, 250);
   }
   
   //SCORES BUTTON
   private class ScoresButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            String filename = JOptionPane.showInputDialog("Enter the first name of the person's scores you will like to view.");
            Scanner infile = new Scanner( new File(filename + ".txt") );
            for(int k = 0; k < 3; k++)
            {
               System.out.println(infile.nextLine());
            }
            JOptionPane.showMessageDialog(null, filename + "'s scores have been printed.");
         }
         catch(Exception o)
         {
            JOptionPane.showMessageDialog(null, "Error: File not found.");
            System.exit(0);
         }
      }
   }
}

     
