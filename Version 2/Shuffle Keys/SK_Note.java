import java.util.*;  //Scanner
import java.io.*;  //System

public class SK_Note
{
   private int myTime;
   private int[] myArrows; //[LEFT 0, UP 1, DOWN 2, RIGHT 3]

   public SK_Note(int time, int left, int up, int down, int right)
   {
      myTime = time;
      
      myArrows = new int[4];
      myArrows[0] = left;
      myArrows[1] = up;
      myArrows[2] = down;
      myArrows[3] = right;
   }
   
   public int getTime()
   {  
      return myTime;
   }
   
   public int[] getArrows()
   {  
      return myArrows;
   }
   
   public void setArrows(int[] arrows)
   {
      myArrows = arrows;
   }
   
   public boolean hasArrows()
   {
      return (myArrows[0]+myArrows[1]+myArrows[2]+myArrows[3] > 0);
   }
}