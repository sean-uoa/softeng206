/**
 * The demo codes are adopted from the examples originally used in 
 * http://www.ntu.edu.sg/home/ehchua/programming/java/j5e_multithreading.html
 * */

package nz.ac.auckland.softeng206.guiconcurrencydemo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class SwingWorkerCounter extends JFrame {

	/** Illustrate Unresponsive UI problem caused by "busy" Event-Dispatching Thread */
		
   private JTextField tfCount;
   private CountTask countTask;
 
   /** Constructor to setup the GUI components */
   public SwingWorkerCounter() {
	   
	   JPanel contentPane = new JPanel();
	   contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	   
	   contentPane.add(new JLabel("Counter"));
	   
	   tfCount = new JTextField("0", 10);
	   tfCount.setEditable(false);
	   contentPane.add(tfCount);
 
      JButton btnStart = new JButton("Start Counting");
      contentPane.add(btnStart);
      btnStart.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
             (countTask = new CountTask()).execute();
         }
      });
      
      JButton btnStop = new JButton("Stop Counting");
      contentPane.add(btnStop);
      btnStop.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            countTask.cancel(true);
            countTask = null;
         }
      });
      
      this.add(contentPane);
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Counter");
      setSize(300, 120);
      
      setVisible(true);
   }
   
   private class CountTask extends SwingWorker<Void, Integer> {
       @Override
       protected Void doInBackground() {
       	int count = 0;
       	for (int i = 0; i < 10000000; ++i) {
	            if (!isCancelled()) {
	            	++count;
	            }
               publish(new Integer(count));
           }
           return null;
       }

       @Override
       protected void process(List<Integer> integers) {
       	 tfCount.setText(integers.get(integers.size()-1)+"");
       }
   }
   
   public static void main(String[] args) {
	   
//	   new SimpleCounter();  // Let the constructor do the job
	   
      // Run GUI codes in Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new SimpleCounter();  // Let the constructor do the job
         }
      });
   }
}
