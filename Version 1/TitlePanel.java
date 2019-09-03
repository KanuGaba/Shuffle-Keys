import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class TitlePanel extends JPanel
{
   private ImageIcon piano;
   private JButton scores;
   public TitlePanel()
   {
      setLayout(null);
      piano = new ImageIcon("piano.jpg");
      
      Insets in = getInsets();
      scores = new JButton("Recent Scores");
      scores.setOpaque(false);
      scores.setContentAreaFilled(false);
      scores.setBorderPainted(false);
      scores.setFont(new Font("Baskerville", Font.BOLD, 55));
      scores.setForeground(Color.GREEN);
      scores.setBounds(270 + in.left, 775 + in.top, 450, 100);
      scores.addActionListener(new Listener());
      add(scores);
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(piano.getImage(), 0, 0, getWidth(), getHeight(), null);
      g.setFont(new Font("Castellar", Font.BOLD, 70));
      g.setColor(Color.WHITE);
      g.drawString("Shuffle Keys", 200, 250);
   }
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            String filename = JOptionPane.showInputDialog("Enter the first name of the person's scores you will like to view.");
            Scanner infile = new Scanner( new File(filename + ".txt") );
            for(int k = 0; k < 5; k++)
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