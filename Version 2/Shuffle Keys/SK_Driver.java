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

public class SK_Driver
{
   //MAIN RUNTIME
   public static void main(String[] args)
   {
      //FRAME
      JFrame frame;
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      frame = new JFrame("Shuffle Keys");
      frame.setUndecorated(true);
      frame.setResizable(false);
      frame.setLocation(dim.width/2-512, dim.height/2-384);
      frame.setSize(1024, 768);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new SK_Panel_Title());
      frame.setVisible(true);
   }
}

   
