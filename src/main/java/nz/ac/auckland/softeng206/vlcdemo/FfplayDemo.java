package nz.ac.auckland.softeng206.vlcdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FfplayDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			ProcessBuilder ffplayProcessBuilder = new ProcessBuilder("bash", "-c", 
					"ffplay -autoexit -i dat/BigBuckBunny_320x180.mp4 -ss 00:00:10 -t 13");
			Process ffplayProcess = ffplayProcessBuilder.start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(ffplayProcess.getErrorStream()));
			String line = null;
			while ((line=br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
