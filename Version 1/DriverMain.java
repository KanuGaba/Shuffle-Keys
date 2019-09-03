import java.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
public class DriverMain
{
   private static MainPanel pane;
   private static final String TITLE = "title", INSTRUCTION = "instruction", ARROW = "arrow";
   public static void main(String[] args)
   {
      pane = new MainPanel();
      JFrame frame = new JFrame("Shuffle Keys");
      frame.setSize(1000, 1000);
      frame.setLocation(150, 10);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(pane);
      frame.setVisible(true);
   }  
   private static class MainPanel extends JPanel 
   {
      private BufferedImage myImage;
      private Graphics myBuffer;
      private TitlePanel title;
      private InstructionPanel instruction;
      private ArrowPanel arrow;
   	//MenuPanel things:
      private JButton play, help, back, quit, quit2;
      public MainPanel()
      {
         setLayout(new CardLayout());
         title = new TitlePanel();
         arrow = new ArrowPanel();
         instruction = new InstructionPanel();
         play = new JButton("Play");
         help = new JButton("Instructions");
         back = new JButton("Back");
         quit2 = new JButton("Quit");
         quit = new JButton("Quit");
         Insets in = getInsets();
         play.setOpaque(false);
         play.setContentAreaFilled(false);
         play.setBorderPainted(false);
         help.setOpaque(false);
         help.setContentAreaFilled(false);
         help.setBorderPainted(false);
         back.setOpaque(false);
         back.setContentAreaFilled(false);
         back.setBorderPainted(false);
         quit2.setOpaque(true);
         quit2.setContentAreaFilled(true);
         quit2.setBorderPainted(true);
         quit.setOpaque(false);
         quit.setContentAreaFilled(false);
         quit.setBorderPainted(false);
         play.setFont(new Font("Baskerville", Font.BOLD, 55));
         help.setFont(new Font("Baskerville", Font.BOLD, 55));
         back.setFont(new Font("Baskerville", Font.BOLD, 60));
         quit2.setFont(new Font("Baskerville", Font.BOLD, 35));
         quit.setFont(new Font("Baskerville", Font.BOLD, 55));
         play.setForeground(Color.BLUE);
         help.setForeground(Color.BLUE);
         back.setForeground(Color.BLUE);
         quit2.setForeground(Color.BLUE);
         quit.setForeground(Color.RED);
         play.addActionListener(new playListener());
         help.addActionListener(new helpListener());
         back.addActionListener(new backListener());
         quit2.addActionListener(new quit2Listener());
         quit.addActionListener(new quitListener());
         title.add(play);
         title.add(help);
         title.add(quit);
         instruction.add(back);
         arrow.add(quit2);
         play.setBounds(415 + in.left, 625 + in.top, 150, 100);
         help.setBounds(325 + in.left, 700 + in.top, 350, 100);
         quit.setBounds(415 + in.left, 850 + in.top, 150, 100);
         quit2.setBounds(775 + in.left, 910 + in.top, 210, 50);
         back.setBounds(775 + in.left, 850 + in.top, 200, 100);
         add(title, TITLE);
         add(arrow, ARROW);
         add(instruction, INSTRUCTION);	
      }
      public void paintComponent(Graphics g) 
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
      private class playListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            CardLayout cl = (CardLayout)(pane.getLayout());
            cl.show(pane, ARROW);
         }
      }
      private class helpListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            CardLayout cl = (CardLayout)(pane.getLayout());
            cl.show(pane, INSTRUCTION);
         }
      }
      private class backListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            CardLayout cl = (CardLayout)(pane.getLayout());
            cl.show(pane, TITLE);
         }
      }
      private class quit2Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      }
      private class quitListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      }
   }
}