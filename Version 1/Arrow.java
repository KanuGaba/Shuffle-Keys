import java.awt.*;
import javax.swing.*;
/*****************************************************************
	* An Arrow is an arrow in the video game Dance Dance Revolution.
   * An arrow knows how to return its radius, diameter, Y position,
   * X position, and it's speed. set its radius. It knows how to set
   * its Y position, X position, and its speed. It also knows how to
   * move and draw itself.   	 
	* @author Kanu & Arun
	* @version 6.7.2014
	****************************************************************/
public abstract class Arrow
{
   private int mydy;
   private int myYPos;
   private int myXPos;
   private int myDiameter;
   private int myRadius;
   private Color myColor;
   /************************************************************* 
   	* Constructs an Arrow with a given X Position, Y Position, speed, 
      * radius, and diameter.
   	* @param x    0
      * @param y    0
      * @param dy    7
      * @param diameter     20
      * @param radius      10
   	**************************************************************/
   public Arrow()
   {
      myYPos = 0;
      myXPos = 0;
      mydy = 7;
      myDiameter = 20;
      myRadius = 10;
   }
   /************************************************************* 
   	* Constructs an Arrow with initial X Position, Y Position, speed, 
      * radius, and diameter specified by x, y, dy, diameter, and radius.
   	* @param x    initial X Position
      * @param y    initial Y Position
      * @param dy    initial Speed
      * @param diameter     initial Diameter
      * @param radius      initial Radius
   	**************************************************************/
   public Arrow(int x, int y, int dy, int diameter, int radius)
   {
      myXPos = x;
      myYPos = y;
      mydy = dy;
      myDiameter = diameter;
      myRadius = radius;
   }
   /*************************************************************** 
   	* Returns the arrow's Y position
      * @return	 myYPos
   	**************************************************************/
   public int getYPos()
   {
      return myYPos;
   }
   /*************************************************************** 
   	* Returns the arrow's X position
      * @return	 myXPos
   	**************************************************************/
   public int getXPos()
   {
      return myXPos;
   }
   /*************************************************************** 
   	* Returns the arrow's radius
      * @return	 myRadius
   	**************************************************************/
   public int getRadius() 
   {
      return myRadius;
   }
   /*************************************************************** 
   	* Returns the arrow's diameter
      * @return	 myDiameter
   	**************************************************************/
   public int getDiameter() 
   {
      return myDiameter;
   }
   /*************************************************************** 
   	* Returns the arrow's speed
      * @return	 mydy
   	**************************************************************/
   public int getdy()
   {
      return mydy;
   }
   /***************************************************************
   	* Sets the Y position to the input number.
   	* @param y	 assigns y to myYPos
   	**************************************************************/
   public void setYPos(int y)
   {
      myYPos = y;
   }
   /***************************************************************
   	* Sets the X position to the input number.
   	* @param x	 assigns x to myXPos
   	**************************************************************/

   public void setXPos(int x)
   {
      myXPos = x;
   }
   /***************************************************************
   	* Sets the speed to the input number.
   	* @param x	 assigns x to mydy
      **************************************************************/

   public void setdy(int x)
   {
      mydy = x;
   }
   /***************************************************************
   	* Enables the arrow to move at the speed of its dy.
   	**************************************************************/
   public void move()
   {                    
      setYPos(getYPos() + mydy);
   }
   /***************************************************************
   	* Enables the arrow to draw itself, specified in its subclasses.
      **************************************************************/
   public abstract void draw(Graphics myBuffer);
}