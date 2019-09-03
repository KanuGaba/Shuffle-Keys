import java.awt.*;
import javax.swing.*;
public class FixedUp extends Arrow
{
   private int mydy;
   private int myYPos;
   private int myXPos;
   private int myDiameter;
   private int myRadius;
   private Color myColor;
   
   public FixedUp(int x, int y, int dy, int diameter, int radius)
   {
      super(x, y, dy, diameter, radius);
   }
   public void draw(Graphics myBuffer) 
   {
      ImageIcon up = new ImageIcon("UpFixed.png");
      myBuffer.drawImage(up.getImage(), (int)(getXPos() - getRadius()), (int)(getYPos() - getRadius()), 50, 50, null);
   }
}