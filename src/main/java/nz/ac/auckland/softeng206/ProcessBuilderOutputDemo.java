package nz.ac.auckland.softeng206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessBuilderOutputDemo {

	public static void main(String[] args) {
				
		try {
			ProcessBuilder builder = new ProcessBuilder("ls", "-l", "/usr");
//			ProcessBuilder builder = new ProcessBuilder("wget", "http://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4");
			Process process = builder.start();
			
			InputStream stdout = process.getInputStream();
			InputStream stderr = process.getErrorStream();
			BufferedReader stdoutBuffered = new BufferedReader(new InputStreamReader(stdout));
			BufferedReader stderrBuffered = new BufferedReader(new InputStreamReader(stderr));
			
			String line = null;
			while ((line = stderrBuffered.readLine()) != null ) {
				System.out.println(line);
			}
			while ((line = stdoutBuffered.readLine()) != null ) {
				System.out.println(line);
			}
			
			stdoutBuffered.close();
			stderrBuffered.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

}
