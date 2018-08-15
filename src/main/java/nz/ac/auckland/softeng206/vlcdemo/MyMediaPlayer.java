package nz.ac.auckland.softeng206.vlcdemo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

public class MyMediaPlayer {

	private static final String NATIVE_LIBRARY_SEARCH_PATH = "/usr/lib";

    private final JFrame frame;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
	public MyMediaPlayer() {
		
		JPanel contentPane = new JPanel();
    	contentPane.setLayout(new BorderLayout());
    	
    	mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
    	contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);
    	
    	mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(
    		    new MediaPlayerEventAdapter() {
    		    	
    		    	@Override
    		    	public void playing(MediaPlayer mediaPlayer) {
    		    	    SwingUtilities.invokeLater(new Runnable() {
    		    	        public void run() {
    		    	            frame.setTitle(String.format(
    		    	                "My First Media Player - %s",
    		    	                mediaPlayerComponent.getMediaPlayer().getMediaMeta().getTitle()
    		    	            ));
    		    	        }
    		    	    });
    		    	}
    		    	
    		    	@Override
    		    	public void finished(MediaPlayer mediaPlayer) {
    		    	    SwingUtilities.invokeLater(new Runnable() {
    		    	        public void run() {
    		    	        	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    		    	        }
    		    	    });
    		    	}

    		    	@Override
    		    	public void error(MediaPlayer mediaPlayer) {
    		    	    SwingUtilities.invokeLater(new Runnable() {
    		    	        public void run() {
    		    	            JOptionPane.showMessageDialog(
    		    	                frame,
    		    	                "Failed to play media",
    		    	                "Error",
    		    	                JOptionPane.ERROR_MESSAGE
    		    	            );
    		    	            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    		    	        }
    		    	    });
    		    	}

    		    }
    		);
    	
    	JPanel controlsPanel = new JPanel();
    	JButton pauseButton = new JButton("Pause");
    	controlsPanel.add(pauseButton);
    	JButton rewindButton = new JButton("Rewind");
    	controlsPanel.add(rewindButton);
    	JButton skipButton = new JButton("Skip");
    	controlsPanel.add(skipButton);
    	
    	pauseButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	mediaPlayerComponent.getMediaPlayer().pause();
    	    }
    	});

    	rewindButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	mediaPlayerComponent.getMediaPlayer().skip(-3000);
    	    }
    	});

    	skipButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	mediaPlayerComponent.getMediaPlayer().skip(2000);
    	    }
    	});

    	contentPane.add(controlsPanel, BorderLayout.NORTH);
    	
    	JPanel statusPanel = new JPanel();
    	final JLabel statusLabel = new JLabel();
    	statusPanel.add(statusLabel);
    	
    	Timer timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	statusLabel.setText((int)(mediaPlayerComponent.getMediaPlayer().getTime()/1000.0)+" s");
            }
        });
    	
    	contentPane.add(statusPanel, BorderLayout.SOUTH);
    	
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
    	
    	timer.start();
    	
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
