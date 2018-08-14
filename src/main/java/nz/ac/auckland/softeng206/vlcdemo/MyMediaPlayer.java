package nz.ac.auckland.softeng206.vlcdemo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class MyMediaPlayer {

	private static final String NATIVE_LIBRARY_SEARCH_PATH = "/usr/lib";

    private final JFrame frame;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
	public MyMediaPlayer() {
		
        frame = new JFrame("My First Media Player");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        frame.setContentPane(mediaPlayerComponent);
        
        frame.setVisible(true);

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
