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

//CONTROL PANEL
public class SK_ControlPanel extends JPanel
{
   private CardLayout myLayout;
   
   //CONSTRUCTOR - CONTROL PANEL
   public SK_ControlPanel()
   {
      myLayout = new CardLayout();
      setLayout(myLayout);
         
      add(new SK_Panel_Title(this), "TITLE");
      add(new SK_Panel_Game(this), "GAME");
      
      myLayout.show(this, "GAME"); /////////
      SK_Song song = new SK_Song("song");
   }

   public CardLayout getMyLayout()
   {
      return myLayout;
   }
}