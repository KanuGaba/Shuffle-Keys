import java.awt.*;
import javax.swing.*;
public class FixedLeft extends Arrow
{
   private int mydy;
   private int myYPos;
   private int myXPos;
   private int myDiameter;
   private int myRadius;
   private Color myColor;
   
   public FixedLeft(int x, int y, int dy, int diameter, int radius)
   {
      super(x, y, dy, diameter, radius);
   }
   public void draw(Graphics myBuffer) 
   {
      ImageIcon left = new ImageIcon("LeftFixed.png");
      myBuffer.drawImage(left.getImage(), (int)(getXPos() - getRadius()), (int)(getYPos() - getRadius()), 50, 50, null);
   }
}