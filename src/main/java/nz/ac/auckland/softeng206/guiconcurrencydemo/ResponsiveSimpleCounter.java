/**
 * The demo codes are adopted from the examples originally used in 
 * http://www.ntu.edu.sg/home/ehchua/programming/java/j5e_multithreading.html
 * */

package nz.ac.auckland.softeng206.guiconcurrencydemo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ResponsiveSimpleCounter extends JFrame {

	/** Illustrate Responsive UI with the use of multithreading technique */
		
   private boolean stop = false;  // start or stop the counter
   private JTextField tfCount;
   private int count = 1;
 
   /** Constructor to setup the GUI components */
   public ResponsiveSimpleCounter() {
	   
	   JPanel contentPane = new JPanel();
	   contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	   
	   contentPane.add(new JLabel("Counter"));
	   
	   tfCount = new JTextField(count + "", 10);
	   tfCount.setEditable(false);
	   contentPane.add(tfCount);
 
      JButton btnStart = new JButton("Start Counting");
      contentPane.add(btnStart);
      btnStart.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            stop = false;
            
            // Create our own Thread to do the counting
            Thread t = new Thread() {
               @Override
               public void run() {  // override the run() to specify the running behavior
                  for (int i = 0; i < 10000000; ++i) {
                     if (stop) break;
                     tfCount.setText(count + "");
                     ++count;
                     
                     // Suspend this thread via sleep() and yield control to other threads.
                     // Also provide the necessary delay. 
                     // This is not compulsory. But when computation power is limited (e.g., single CPU core),
                     // this thread might affect the UI responsiveness.
                     try {
                        sleep(10);  // milliseconds
                     } catch (InterruptedException ex) {}
                  }
               }
            };
            t.start();  // call back run()
         }
      });
      
      JButton btnStop = new JButton("Stop Counting");
      contentPane.add(btnStop);
      btnStop.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            stop = true;  // set the stop flag
         }
      });
      
      this.add(contentPane);
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Counter");
      setSize(300, 120);
      
      setVisible(true);
   }
   
   public static void main(String[] args) {
	    
      // Run GUI codes in Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new ResponsiveSimpleCounter();  // Let the constructor do the job
         }
      });
   }
}
