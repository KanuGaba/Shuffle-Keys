import javax.swing.*;  //JFrame, JPanel, JButton, ImageIcon, Timer
import javax.swing.event.*;  //KeyAdapter
import java.awt.*;  //Dimension, Toolkit, CardLayout, Image, Font, Color, Graphics
import java.awt.geom.*;  //ActionListener
import java.awt.event.*;  //ActionListener
import java.awt.image.*;  //Image, BufferedImage
import java.io.*;  //System
import java.applet.*;  //Applet, AudioClip
import java.net.URL;  //URL
import java.util.*;  //Scanner

public class SK_Panel_Game extends JPanel
{
   //GRAPHICS RESOURCES
   private final ImageIcon GAME_BG = new ImageIcon("Resources/GAME_BG.jpg");
   
   private final ImageIcon GAME_UF = new ImageIcon("Resources/UpFixed.png");
   private final ImageIcon GAME_DF = new ImageIcon("Resources/DownFixed.png");
   private final ImageIcon GAME_LF = new ImageIcon("Resources/LeftFixed.png");
   private final ImageIcon GAME_RF = new ImageIcon("Resources/RightFixed.png");
   
   private final ImageIcon GAME_U = new ImageIcon("Resources/Up.png");
   private final ImageIcon GAME_D = new ImageIcon("Resources/Down.png");
   private final ImageIcon GAME_L = new ImageIcon("Resources/Left.png");
   private final ImageIcon GAME_R = new ImageIcon("Resources/Right.png");
      
   //FONT RESOURCES
   private final Font FONT_TITLE = new Font("Castellar", Font.BOLD, 70);
   private final Font FONT_BUTTON = new Font("Baskerville", Font.BOLD, 55);
   
   //PROGRAM ENGINE
   private SK_ControlPanel myControlPanel;
   private javax.swing.Timer myTimer;
   private BufferedImage myBuffer;
   private Graphics2D myImage;
   
   private SK_KeyboardInput myKeyboardInput;
   private SK_AudioPlayer myAudioPlayer;
   private AudioClip myAudio;
   
   //GAME ENGINE
   private int myPointer, perfect, good, bad, miss;
   private long myStartTime;
   private SK_Song mySong;
   private int myScore;
   private int[] myResults; //[perfect, good, bad]
   private final long THRESHOLD = 400;
      
   //CONSTRUCTOR - GAME PANEL
   public SK_Panel_Game(SK_ControlPanel pane)
   {
      //CONSTRUCTOR - PROGRAM ENGINE
      myControlPanel = pane;
      
      myBuffer = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_RGB);
      myImage = (Graphics2D)myBuffer.getGraphics();
      myImage.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
         							 RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      myTimer = new javax.swing.Timer(50, new SK_GraphicsRepaint());
      
      myKeyboardInput = new SK_KeyboardInput();
      this.addKeyListener(myKeyboardInput);
      this.setFocusable(true);
      
      String mySongName = "Song"; //////////
      mySong = new SK_Song(mySongName);
      myAudioPlayer = new SK_AudioPlayer();
      myAudio = myAudioPlayer.getClip("Resources/"+mySongName+".wav");
      myAudioPlayer.run();
         
      setLayout(null);
      
      //CONSTRUCTOR - GAME ENGINE
      myPointer = 0;
      myStartTime = System.currentTimeMillis();
      myScore = 0;
      myResults = new int[3];
      
      //START
      myAudioPlayer.play(myAudio);
      myTimer.start();
   }
   
   //GRAPHICS PROCESSOR
   private class SK_GraphicsRepaint implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         //DRAW BACKGROUND
         myImage.drawImage(GAME_BG.getImage(), 0, 0, getWidth(), getHeight(), null);
         
         int arrowSpacing = 110;
         
         //DRAW FIXED ARROWS
         int fixedArrowX = 80; int fixedArrowY = 60;
         int fixedArrowW = 64; int fixedArrowH = 65;
         myImage.drawImage(GAME_LF.getImage(), fixedArrowX+0*arrowSpacing, fixedArrowY, fixedArrowW, fixedArrowH, null);
         myImage.drawImage(GAME_UF.getImage(), fixedArrowX+1*arrowSpacing, fixedArrowY, fixedArrowW, fixedArrowH, null);
         myImage.drawImage(GAME_DF.getImage(), fixedArrowX+2*arrowSpacing, fixedArrowY, fixedArrowW, fixedArrowH, null);
         myImage.drawImage(GAME_RF.getImage(), fixedArrowX+3*arrowSpacing, fixedArrowY, fixedArrowW, fixedArrowH, null);
            
         //UPDATE POINTER
         if(System.currentTimeMillis() - myStartTime >= mySong.getNotes()[myPointer].getTime())
            myPointer++;
            
          //TEST FOR ENDGAME  ///////////////
         if(myPointer >= mySong.getNotes().length)
         {
            myScore += (perfect * 2) + (good);
            System.out.println("Score = " + myScore);
            System.exit(0);
         }
           
         //DRAW MOVING ARROWS
         final double TIME_TO_TOP = 4000.0;
         long elapsedTime = System.currentTimeMillis() - myStartTime;
         
         int startDraw = myPointer;
         while(startDraw-1 >= 0 && elapsedTime - mySong.getNotes()[startDraw-1].getTime() < TIME_TO_TOP)
            startDraw--;
            
         int endDraw = myPointer;
         while(endDraw+1 < mySong.getLength() && mySong.getNotes()[endDraw+1].getTime() - elapsedTime < TIME_TO_TOP)
            endDraw++;
         
         for(int k = startDraw; k <= endDraw; k++)
         {
            long dt = elapsedTime - mySong.getNotes()[k].getTime();
            int dy = (int)(dt*(-1*getHeight()/TIME_TO_TOP));
         
            int[] arrows = mySong.getNotes()[k].getArrows();
         
            if(arrows[0] == 1)
               myImage.drawImage(GAME_L.getImage(), fixedArrowX+0*arrowSpacing, dy + fixedArrowY, fixedArrowW, fixedArrowH, null);
            if(arrows[1] == 1)
               myImage.drawImage(GAME_U.getImage(), fixedArrowX+1*arrowSpacing, dy + fixedArrowY, fixedArrowW, fixedArrowH, null);
            if(arrows[2] == 1)
               myImage.drawImage(GAME_D.getImage(), fixedArrowX+2*arrowSpacing, dy + fixedArrowY, fixedArrowW, fixedArrowH, null);
            if(arrows[3] == 1)
               myImage.drawImage(GAME_R.getImage(), fixedArrowX+3*arrowSpacing, dy + fixedArrowY, fixedArrowW, fixedArrowH, null); 
         }
         
                	               	
         repaint();
      }
   }
   
   //KEY PROCESSOR
   private class SK_KeyboardInput extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         long elapsedTime = System.currentTimeMillis() - myStartTime;
         
         SK_Note previousNote;
         if(myPointer > 0)
            previousNote = mySong.getNotes()[myPointer - 1];
         else
            previousNote = null;
      
         SK_Note pointerNote = mySong.getNotes()[myPointer];
         
         int keyNum;
         switch(e.getKeyCode())
         {
            case KeyEvent.VK_LEFT:  keyNum = 0;
               break;
            case KeyEvent.VK_UP:    keyNum = 1; 
               break;
            case KeyEvent.VK_DOWN:  keyNum = 2; 
               break;
            case KeyEvent.VK_RIGHT: keyNum = 3; 
               break;
            default:  
               return;
         }
         
         if(previousNote != null && previousNote.hasArrows())
         {
            long missedTime = elapsedTime-previousNote.getTime();
            if(previousNote.getArrows()[keyNum] == 1 && (missedTime <= THRESHOLD))//TOP HALF
            {
               previousNote.getArrows()[keyNum] = 0;
               if((int)missedTime < (int)(2*THRESHOLD/8)) //PERFECT
               {
                  System.out.println("Perfect");
                  perfect++;
               }
               else if((int)missedTime < (int)(2*THRESHOLD/8*3)) //GOOD
               {
                  System.out.println("Good");
                  good++;
               }
               else if((int)missedTime < (int)(2*THRESHOLD)) //BAD
               {
                  System.out.println("Bad");
                  bad++;
               }
               else if((int)missedTime < (int)(3*THRESHOLD))
               {
                  System.out.println("Miss");
                  miss++;
               }
            }
         }
         if(pointerNote != null && pointerNote.hasArrows())//BOTTOM HALF
         {
            long missedTime = pointerNote.getTime()-elapsedTime;
            if(pointerNote.getArrows()[keyNum] == 1 && (missedTime <= 2*THRESHOLD))
            {
               pointerNote.getArrows()[keyNum] = 0;
               if((int)missedTime < (int)(2*THRESHOLD/8)) //PERFECT
               {
                  System.out.println("Perfect");
                  perfect++;
               }
               else if((int)missedTime < (int)(2*THRESHOLD/8*2)) //GOOD
               {
                  System.out.println("Good");
                  good++;
               }
               else if((int)missedTime < (int)(2*THRESHOLD)) //BAD
               {
                  System.out.println("Bad");
                  bad++;
               }
               else if((int)missedTime < (int)(3*THRESHOLD))
               {
                  System.out.println("miss");
                  miss++;
               }
            }
         }
         
         
      }
   }
   
   //AUDIO PROCESSOR
   private class SK_AudioPlayer implements Runnable
   {
      public void run(){}
   	
      public AudioClip getClip(String filename)
      {
         try
         {
            java.net.URL path = SK_AudioPlayer.class.getResource(filename);
            return Applet.newAudioClip(path);
         }
         catch(Exception e)
         {
            e.printStackTrace();
            return null;
         }	
      }
   	
      public void loop(AudioClip clip)
      {
         try
         {
            clip.loop();
         }
         catch(Exception e)
         {  e.printStackTrace();  }
      }
   	
      public void play(AudioClip clip)
      {
         try
         {
            clip.play();
         }
         catch(Exception e)
         {  e.printStackTrace();  }
      }
   	
      public void stop(AudioClip clip)
      {
         try
         {
            clip.stop();
         }
         catch(Exception e)
         {  e.printStackTrace();  }
      }
   }

   //REPAINT METHOD
   public void paintComponent(Graphics g)
   {  g.drawImage(myBuffer, 0, 0, getWidth(), getHeight(), null);  }

}

     
