import java.awt.*;
import javax.swing.*;
public class ArrowLeft extends Arrow
{
   private int mydy;
   private int myYPos;
   private int myXPos;
   private int myDiameter;
   private int myRadius;
   private Color myColor;
   
   public ArrowLeft(int x, int y, int dy, int diameter, int radius)
   {
      super(x, y, dy, diameter, radius);
   }
   public void draw(Graphics myBuffer) 
   {
      ImageIcon left = new ImageIcon("Left.png");
      myBuffer.drawImage(left.getImage(), (int)(getXPos() - getRadius()), (int)(getYPos() - getRadius()), 50, 50, null);
   }
}