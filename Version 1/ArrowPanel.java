//fix the arrow detection where arrow goes past top of screen (works, just need it to check constantly)
//increase speed and make delay shorter
//change background when combo > 20?

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.io.File; 
import java.io.IOException; 
import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.FloatControl; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import javax.sound.sampled.UnsupportedAudioFileException; 
public class ArrowPanel extends JPanel
{
   private static final int FRAME = 500;
   private static final Color BACKGROUND = new Color(204, 204, 204);

   private BufferedImage myImage;
   private ImageIcon plays;
   private Graphics myBuffer;
   private ArrowLeft arrowLeft;
   private ArrowUp arrowUp;
   private ArrowDown arrowDown; 
   private ArrowRight arrowRight;
   private FixedLeft fixedLeft;
   private FixedUp fixedUp;
   private FixedDown fixedDown;
   private FixedRight fixedRight;
   private Timer t, t2, t3, t4, t5, t6, t7, t8, t9, t10;
   private JButton play, test;
   private Boolean stop1 = false;
   private Boolean stop2 = false;
   private Boolean stop3 = false;
   private Boolean stop4 = false;
   private int perfect, good, miss, combo, highcombo, score;
   private JLabel label, label1, label2, label3, label4;
   private Thread t1;
   private int time = 0;
   public ArrowPanel()
   {
      setLayout(null);
      
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      plays = new ImageIcon("plays.jpg");
      
      myBuffer.drawImage(plays.getImage(), 0, 0, FRAME, FRAME, null);
      
      fixedLeft = new FixedLeft(45, 40, 0, 20, 10);
      fixedUp = new FixedUp(110, 40, 0, 20, 10);
      fixedDown = new FixedDown(175, 40, 0, 20, 10);
      fixedRight = new FixedRight(240, 40, 0, 20, 10);
      
      arrowLeft = new ArrowLeft(45, 500, -1, 20, 10);
      arrowUp = new ArrowUp(110, 600, -1, 20, 10);
      arrowDown = new ArrowDown(175, 700, -1, 20, 10);
      arrowRight = new ArrowRight(240, 800, -1, 20, 10);
    
      t = new Timer(5, new Listener1());
      Insets in = getInsets();
      
      play = new JButton("Play");
      play.setOpaque(true);
      play.setContentAreaFilled(true);
      play.setBorderPainted(true);
      play.setFont(new Font("Baskerville", Font.BOLD, 100));
      play.setForeground(Color.BLUE);
      play.setBounds(350 + in.left, 435 + in.top, 300, 140);
      play.addActionListener(new Listener2());
      add(play);
      
      test = new JButton("Save and Quit");
      test.setOpaque(true);
      test.setContentAreaFilled(true);
      test.setBorderPainted(true);
      test.setFont(new Font("Baskerville", Font.BOLD, 25));
      test.setForeground(Color.BLUE);
      test.setBounds(775 + in.left, 860 + in.top, 210, 50);
      test.addActionListener(new Listener11());
      add(test);
   
      addKeyListener(new Key());
      setFocusable(true);
      
      label = new JLabel("Combo: " + combo);
      label.setFont(new Font("Serif", Font.BOLD, 50));
      label.setForeground(Color.ORANGE);
      label.setBounds(617 + in.left, 510 + in.top, 300, 100);
      add(label);
      
      label1 = new JLabel("ACCURACY");
      label1.setFont(new Font("Serif", Font.BOLD, 50));
      label1.setForeground(Color.WHITE);
      label1.setBounds(675 + in.left, 75 + in.top, 300, 100);
      add(label1);
      
      label2 = new JLabel("Score: " + score);
      label2.setFont(new Font("Serif", Font.BOLD, 50));
      label2.setForeground(Color.RED);
      label2.setBounds(675 + in.left, 10 + in.top, 300, 100);
      add(label2);
      
      label3 = new JLabel("Made by Kanu Gaba");
      label3.setFont(new Font("Serif", Font.ITALIC, 15));
      label3.setForeground(Color.BLACK);
      label3.setBounds(665 + in.left, 150 + in.top, 300, 200);
      add(label3);
      
      label4 = new JLabel("and Arun Bhattasali");
      label4.setFont(new Font("Serif", Font.ITALIC, 15));
      label4.setForeground(Color.BLACK);
      label4.setBounds(665 + in.left, 165 + in.top, 300, 200);
      add(label4);
   
      t2 = new Timer(10, new Listener3());
      t2.setInitialDelay(10);
      t3 = new Timer(10, new Listener4());
      t3.setInitialDelay(10);
      t4 = new Timer(10, new Listener5());
      t4.setInitialDelay(10);
      t5 = new Timer(10, new Listener6());
      t5.setInitialDelay(10);
      
      t6 = new Timer(10, new Listener7());
      t7 = new Timer(10, new Listener8());
      t8 = new Timer(10, new Listener9());
      t9 = new Timer(10, new Listener10());
      
      t10 = new Timer(1000, new Listener12());
   
   /*
      if(arrowUp.getYPos() <= -40)
      {
         arrowUp.setdy(0);
         miss++;
         if(combo > highcombo)
         highcombo = combo;
         combo = 0;
         System.out.println("5");
         label.setText("Combo: " + combo);
         label1.setText("MISS");
         label1.setForeground(Color.RED.darker());
         score = score - 1;
         label2.setText("Score: " + score);
         stop1 = true;
         int x = (int)(Math.random() * 10000 + 1000);
         t6.setInitialDelay(x);
         t6.start();
         t2.start();
      }
      if(arrowRight.getYPos() <= -40)
      {
         arrowRight.setdy(0);
         miss++;
         if(combo > highcombo)
         highcombo = combo;
         combo = 0;
         System.out.println("5");
         label.setText("Combo: " + combo);
         label1.setText("MISS");
         label1.setForeground(Color.RED.darker());
         score = score - 1;
         label2.setText("Score: " + score);
         stop2 = true;
         int x = (int)(Math.random() * 10000 + 1000);
         t7.setInitialDelay(x);
         t7.start();
         t3.start();
      }
      if(arrowLeft.getYPos() <= -40)
      {
         arrowLeft.setdy(0);
         miss++;
         if(combo > highcombo)
         highcombo = combo;
         combo = 0;
         System.out.println("5");
         label.setText("Combo: " + combo);
         label1.setText("MISS");
         label1.setForeground(Color.RED.darker());
         score = score - 1;
         label2.setText("Score: " + score);
         stop3 = true;
         int x = (int)(Math.random() * 10000 + 1000);
         t8.setInitialDelay(x);
         t8.start();
         t4.start();
      }
      if(arrowDown.getYPos() <= -40)
      {
         arrowDown.setdy(0);
         miss++;
         if(combo > highcombo)
         highcombo = combo;
         combo = 0;
         System.out.println("5");
         label.setText("Combo: " + combo);
         label1.setText("MISS");
         label1.setForeground(Color.RED.darker());
         score = score - 1;
         label2.setText("Score: " + score);
         stop4 = true;
         int x = (int)(Math.random() * 10000 + 1000);
         t9.setInitialDelay(x);
         t9.start();
         t5.start();
      } 
      */   
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   public void output(String filename) throws Exception
   {
      System.setOut(new PrintStream(new FileOutputStream(filename + ".txt")));
      System.out.println("Perfects: " + perfect);
      System.out.println("Goods: " + good);
      System.out.println("Misses: " + miss);
      System.out.println("Highest Combo: " + highcombo);
      System.out.println("Score: " + score);
   }
   private void score(Arrow arrow)
   {
      if(arrow.getYPos() > 35 && arrow.getYPos() < 45)
      {
         perfect++;
         combo++;
         System.out.println("1");
         label1.setText("PERFECT");
         score += 2;
         label2.setText("Score: " + score);
         label1.setForeground(Color.GREEN.darker());
      }
      if((arrow.getYPos() > 15 && arrow.getYPos() <= 35) || (arrow.getYPos() >= 45 && arrow.getYPos() < 70))
      {
         good++;
         combo++;
         System.out.println("2");
         label1.setText("GOOD");
         score += 1;
         label2.setText("Score: " + score);
         label1.setForeground(Color.YELLOW.darker());
      }
      if(arrow.getYPos() <= 15 || arrow.getYPos() >= 70)
      {
         miss++;
         if(combo > highcombo)
            highcombo = combo;
         combo = 0;
         System.out.println("3");
         label1.setText("MISS");
         score = score - 1;
         label2.setText("Score: " + score);
         label1.setForeground(Color.RED.darker());
      }
   }
   
   private class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode() == KeyEvent.VK_UP)
         {
            arrowUp.setdy(0);
            score(arrowUp);
            label.setText("Combo: " + combo);
            stop1 = true;
            int x = (int)(Math.random() * 10000 + 1000);
            t6.setInitialDelay(x);
            t6.start();
            t2.start();
         }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT)
         {
            arrowRight.setdy(0);
            score(arrowRight);
            label.setText("Combo: " + combo);
            stop2 = true;
            int x = (int)(Math.random() * 10000 + 1000);
            t7.setInitialDelay(x);
            t7.start();
            t3.start();
         }
         if(e.getKeyCode() == KeyEvent.VK_LEFT)
         {
            arrowLeft.setdy(0);
            score(arrowLeft);
            label.setText("Combo: " + combo);
            stop3 = true;
            int x = (int)(Math.random() * 10000 + 1000);
            t8.setInitialDelay(x);
            t8.start();
            t4.start();
         }
         if(e.getKeyCode() == KeyEvent.VK_DOWN)
         {
            arrowDown.setdy(0);
            score(arrowDown);
            label.setText("Combo: " + combo);
            stop4 = true;
            int x = (int)(Math.random() * 10000 + 1000);
            t9.setInitialDelay(x);
            t9.start();
            t5.start();
         }
      }
   }
  
   private class Listener1 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         myBuffer.setColor(BACKGROUND);    //cover the 
         myBuffer.fillRect(0,0,FRAME,FRAME);   //old ball
         myBuffer.drawImage(plays.getImage(), 0, 0, FRAME, FRAME, null);
         
         fixedUp.draw(myBuffer);
         fixedDown.draw(myBuffer);
         fixedRight.draw(myBuffer);
         fixedLeft.draw(myBuffer);
         
         if(stop1 == false)
         {
            arrowUp.move(); 
            arrowUp.draw(myBuffer); 
         }
         if(stop2 == false)
         {
            arrowRight.move(); 
            arrowRight.draw(myBuffer);            
         }
         if(stop3 == false)
         {  
            arrowLeft.move(); 
            arrowLeft.draw(myBuffer);
         }
         if(stop4 == false)
         {
            arrowDown.move(); 
            arrowDown.draw(myBuffer);  
         }
         repaint();
      }
   }
   private class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         t.start();
         t10.start();
         new AePlayWave("song.wav").start();
         play.hide();
      }
   }
   private class Listener3 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      { 
         arrowUp.setYPos(510);
         t2.stop();
      }
   }
   private class Listener4 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      { 
         arrowRight.setYPos(510);
         t3.stop();
      }
   }
   private class Listener5 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      { 
         arrowLeft.setYPos(510);
         t4.stop();
      }
   }
   private class Listener6 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      { 
         arrowDown.setYPos(510);
         t5.stop();
      }
   }
   private class Listener7 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         arrowUp.setYPos(500);
         arrowUp.setdy(-1);
         stop1 = false;
         t6.stop();
      }
   }
   private class Listener8 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         arrowRight.setYPos(500);
         arrowRight.setdy(-1);
         stop2 = false;
         t7.stop();
      }
   }
   private class Listener9 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         arrowLeft.setYPos(500);
         arrowLeft.setdy(-1);
         stop3 = false;
         t8.stop();
      }
   }
   private class Listener10 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         arrowDown.setYPos(500);
         arrowDown.setdy(-1);
         stop4 = false;
         t9.stop();
      }
   }
   private class Listener11 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         t.stop();
         if(combo > highcombo)
            highcombo = combo;
         String filename = JOptionPane.showInputDialog("Enter your first name to save your scores.");
         try {
            output(filename);
            JOptionPane.showMessageDialog(null, "Thanks for playing, your scores have been saved, GoodBye!");
            System.exit(0);
         }
         catch(Exception o) {
            System.out.println("Caught");
         }
      }
   }
   private class Listener12 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         time++;
         if(time == 180)
         {
            t.stop();
            if(combo > highcombo)
               highcombo = combo;
            String filename = JOptionPane.showInputDialog("Enter your first name to save your scores.");
            try {
               output(filename);
               JOptionPane.showMessageDialog(null, "Thanks for playing, your scores have been saved, GoodBye!");
               System.exit(0);
            }
            catch(Exception o) {
               System.out.println("Caught");
            }
         }
      }
   }
}