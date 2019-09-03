import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class InstructionPanel extends JPanel
{
   private ImageIcon piano;
   private Insets in;
   public InstructionPanel()
   {
      setLayout(null);
      piano = new ImageIcon("piano.jpg");  
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(piano.getImage(), 0, 0, getWidth(), getHeight(), null);
      g.setFont(new Font("Castellar", Font.BOLD, 25));
      g.setColor(Color.WHITE);
      g.drawString("Shuffle Keys is a game that", 275, 150);
      g.drawString("utilizes the four arrow keys.", 250, 200);
      g.drawString("Press the keys as close as possible", 225, 250);
      g.drawString("to the template arrows to score points.", 175, 300);  
   }
}