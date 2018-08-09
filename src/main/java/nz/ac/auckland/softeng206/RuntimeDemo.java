package nz.ac.auckland.softeng206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RuntimeDemo {

	public static void main(String[] args) {
		try {
			// The command
			String cmd = "wget http://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4 -P dat/";
			
			// Create a separate process
			Process process = Runtime.getRuntime().exec(cmd);
			
			// This (parent) process wait till the completion of the children process
			process.waitFor();
			
			InputStream stdout = process.getInputStream();
			InputStream stderr = process.getErrorStream();
			BufferedReader stdoutBuffered = new BufferedReader(new InputStreamReader(stdout));
			BufferedReader stderrBuffered = new BufferedReader(new InputStreamReader(stderr));
			
			String line = null;
			while ((line = stdoutBuffered.readLine()) != null ) {
				System.out.println(line);
			}
			stdoutBuffered.close();
			
			while ((line = stderrBuffered.readLine()) != null ) {
				System.out.println(line);
			}
			stderrBuffered.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
