import java.util.*;  //Scanner
import java.io.*;  //System

public class SK_Song
{
   private String myName;
   private int myLength;
   
   private SK_Note[] myNotes;
   
   public SK_Song(String name)
   {
      myName = name;
      
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("Resources/"+myName+".txt"));
      }
      catch(FileNotFoundException e)
      {
         System.out.println("FileNotFoundException: Resources/"+myName+".txt");
      }
      
      myLength = infile.nextInt();
      myNotes = new SK_Note[myLength];
      for(int k = 0; k < myLength; k++)
      {
         myNotes[k] = new SK_Note(infile.nextInt(), infile.nextInt(), infile.nextInt(), infile.nextInt(), infile.nextInt());
      }
      infile.close();
   }
   
   public SK_Note[] getNotes()
   {
      return myNotes;
   }
   
   public int getLength()
   {
      return myLength;
   }
   
   public String getName()
   {
      return myName;
   }
}