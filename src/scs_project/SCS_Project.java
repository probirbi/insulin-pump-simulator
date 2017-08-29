
package scs_project;

import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;  
import java.util.regex.Pattern;
import java.applet.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;








public class SCS_Project {
    
    private Timer myTimer1;
	public static final int ONE_SEC = 1000;   //time step in milliseconds
	public static final int TENTH_SEC = 100;


        private int clockTick;  	//number of clock ticks; tick can be 1.0 s or 0.1 s
	private double clockTime;
        int clockMin=0;  	//time in seconds
	private String clockTimeString, clockMinString;
        
    public SCS_Project() {    

        
                JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2)); 
       final JTextField txtTime = new JTextField(8);
       //clockTimeString ="asd";
       //txtTime.setText(clockTimeString);
       timePanel.add(txtTime);
       timePanel.setBorder(new TitledBorder("Timer"));

                clockTick = 0;  		//initial clock setting in clock ticks
		clockTime = ((double)clockTick)/10.0;
		clockTimeString = new Double(clockTime).toString();		
		//timeLbl.setText(clockTimeString);

		myTimer1 = new Timer(TENTH_SEC, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			clockTick++;
			clockTime = ((double)clockTick)/10.0;
			clockTimeString = new Double(clockTime).toString();
                        if(clockTime>60){
                           clockMin++;
                           clockTime = 0.0;
                           clockTick = 0;//(int) 0.0;
                        }
                        clockMinString =  new Integer(clockMin).toString();
			txtTime.setText("0:0"+clockMinString+":"+clockTimeString);
			//System.out.println(clockTime);
		    }
		});
                
                myTimer1.start();
                
                /////
        

       
       final JTextField textResult = new JTextField(40); 
       textResult.setEditable(false);
       
       /*JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2)); 
       final JTextField txtTime = new JTextField(8);
       //clockTimeString ="asd";
       txtTime.setText(clockTimeString);
       timePanel.add(txtTime);
       timePanel.setBorder(new TitledBorder("Timer")); */

       
        
       JPanel panelDose = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2)); 
       final JTextField txtDose = new JTextField(8); 
       JLabel lebel2 = new JLabel("mg/dL");
       txtDose.setToolTipText("Insert dose level");
       panelDose.add(txtDose);
       panelDose.add(lebel2);
       panelDose.setBorder(new TitledBorder("Dose Amount"));
       
       JPanel panelOpMode = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
       JButton buttonBegin = new JButton("Begin");
       JButton buttonClear = new JButton("Clear");
       JButton buttonAuto = new JButton("Autometic");
       JButton buttonManual = new JButton("Manual");
       
       buttonBegin.addActionListener(new ActionListener() {
       
       public void actionPerformed(ActionEvent e)
            {
                ///
                
                
                /////
                int chk, val = 0;
                chk = 0;
                String valueTxt = txtDose.getText();
                try{
                    val = Integer.parseInt(valueTxt);
                }
                catch (NumberFormatException ex)
                        {
                            chk = 1;
                            textResult.setText("Input Should be a Number");
                            textResult.setBackground(Color.CYAN);
                        }
                if (chk == 0)
                {
                   if (val<=0)   {
                       textResult.setText("Negetive input !!!!! not accepted");
                       textResult.setBackground(Color.CYAN);
                   }
                   else if (val>=1 && val<=107) {
                       textResult.setText("Normal level");
                       textResult.setBackground(Color.green);
                   }
                   else if (val>=108 && val<=126) {
                       textResult.setText("PreDiabetes level");
                       textResult.setBackground(Color.yellow);
                   }
                   else if (val>126) {
                       try {
                           textResult.setText("Diagnosis of diabetes level");
                           textResult.setBackground(Color.red);
                           ////
                           
                          File afile = new File("sound.mp3");
                           AudioInputStream astream = null;
                           try {
                               astream = AudioSystem.getAudioInputStream(afile);
                           } catch (UnsupportedAudioFileException ex) {
                               Logger.getLogger(SCS_Project.class.getName()).log(Level.SEVERE, null, ex);
                           } catch (IOException ex) {
                               Logger.getLogger(SCS_Project.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           Clip audio = null;
                           try {
                               audio = AudioSystem.getClip();
                           } catch (LineUnavailableException ex) {
                               Logger.getLogger(SCS_Project.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           audio.open(astream);
                           audio.setFramePosition(0);
                           audio.start();
                           
                           
                           /////
                       } catch (LineUnavailableException ex) {
                           Logger.getLogger(SCS_Project.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (IOException ex) {
                           Logger.getLogger(SCS_Project.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                }
                
            }
        });   
       buttonClear.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e)
            {
               textResult.setText("");
               txtDose.setText("");
               textResult.setBackground(Color.white);
            }
           
       });
       buttonBegin.setToolTipText("Click to get output");
       buttonClear.setToolTipText("Click to clear input");
       panelOpMode.add(buttonBegin);
       panelOpMode.add(buttonClear);
       panelOpMode.add(buttonAuto);
       panelOpMode.add(buttonManual);
       panelOpMode.setBorder(new TitledBorder("Operator Mode"));
       
       /*final long start;
       start = System.currentTimeMillis();
       long now = System.currentTimeMillis();
       double time1 = (now - start) / 1000.0;
       String timeConv = Double.toString(time1);
       txtTime.setText(timeConv); */
       
       JPanel panelStart = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));       
       ImageIcon icon = new ImageIcon("b3.jpg");
       JLabel lebel = new JLabel("                     Insulin Pump Simulator       ");
       Font f = new Font("Dialog", Font.PLAIN, 24);
       lebel.setFont(f);
       panelStart.add(lebel);
       panelStart.add(new JLabel(icon));
       panelStart.setBorder(new TitledBorder("Simulator"));
       
       JPanel panelOutput = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));     
       JLabel lebel3 = new JLabel("");
       //final JTextField textResult = new JTextField(40); // 1st line
       panelOutput.add(lebel3);
       panelOutput.add(textResult);
       panelOutput.setBorder(new TitledBorder("Output"));
       
       JFrame fm = new JFrame();
       fm.setTitle("Insulin Pump Project");
       fm.setSize(600, 350);
       fm.setLocation(350,120);
       fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       fm.add(panelStart, BorderLayout.BEFORE_FIRST_LINE);
       fm.add(panelDose, BorderLayout.WEST);
       fm.add(timePanel, BorderLayout.CENTER);
       fm.add(panelOpMode, BorderLayout.EAST);
       fm.add(panelOutput, BorderLayout.SOUTH);
       
       fm.setVisible(true);
    }
    
    public static void main(String[] args) {
       //MyTestFrame myTestFrame1 = new MyTestFrame();
       SCS_Project scs = new SCS_Project();
       String txtTimeVal = "";
       //MyTestFrame myTestFrame1 = new MyTestFrame();
       //new StopWatch();
    }
}

