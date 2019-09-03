import java.awt.*;
import javax.swing.*;
public class FixedRight extends Arrow
{
   private int mydy;
   private int myYPos;
   private int myXPos;
   private int myDiameter;
   private int myRadius;
   private Color myColor;
   
   public FixedRight(int x, int y, int dy, int diameter, int radius)
   {
      super(x, y, dy, diameter, radius);
   }
   public void draw(Graphics myBuffer) 
   {
      ImageIcon right = new ImageIcon("RightFixed.png");
      myBuffer.drawImage(right.getImage(), (int)(getXPos() - getRadius()), (int)(getYPos() - getRadius()), 50, 50, null);
   }
}