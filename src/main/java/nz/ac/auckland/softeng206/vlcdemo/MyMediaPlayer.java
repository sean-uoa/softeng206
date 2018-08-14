package nz.ac.auckland.softeng206.vlcdemo;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class MyMediaPlayer {

	private static final String NATIVE_LIBRARY_SEARCH_PATH = "/usr/lib";

    private final JFrame frame;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
	public MyMediaPlayer() {
		
		JPanel contentPane = new JPanel();
    	contentPane.setLayout(new BorderLayout());
    	
    	mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
    	contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);
    	
    	JPanel controlsPane = new JPanel();
    	JButton pauseButton = new JButton("Pause");
    	controlsPane.add(pauseButton);
    	JButton rewindButton = new JButton("Rewind");
    	controlsPane.add(rewindButton);
    	JButton skipButton = new JButton("Skip");
    	controlsPane.add(skipButton);

    	contentPane.add(controlsPane, BorderLayout.NORTH);
    	
    	frame = new JFrame("My First Media Player");
    	frame.setBounds(100, 100, 600, 400)	;
    	
    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
    	
    	frame.setContentPane(contentPane);
    	frame.setVisible(true);
    	
    	mediaPlayerComponent.getMediaPlayer().playMedia("dat/BigBuckBunny_320x180.mp4");
    	
	}
    
	public static void main(String[] args) {
		
		new NativeDiscovery().discover();  // To try and automatically locate the libvlc native libraries with the default strategies
		
		SwingUtilities.invokeLater(new Runnable() {  // Use the EDT thread
            public void run() {
            	
                new MyMediaPlayer();
                
            }
        });

	}


}
