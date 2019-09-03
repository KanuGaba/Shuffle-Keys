import java.awt.*;
import javax.swing.*;
public class FixedDown extends Arrow
{
   private int mydy;
   private int myYPos;
   private int myXPos;
   private int myDiameter;
   private int myRadius;
   private Color myColor;
   
   public FixedDown(int x, int y, int dy, int diameter, int radius)
   {
      super(x, y, dy, diameter, radius);
   }
   public void draw(Graphics myBuffer) 
   {
      ImageIcon down = new ImageIcon("DownFixed.png");
      myBuffer.drawImage(down.getImage(), (int)(getXPos() - getRadius()), (int)(getYPos() - getRadius()), 50, 50, null);
   }
}