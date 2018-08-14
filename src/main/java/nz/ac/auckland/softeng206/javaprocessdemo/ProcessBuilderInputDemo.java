package nz.ac.auckland.softeng206.javaprocessdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ProcessBuilderInputDemo {

	public static void main(String[] args) {
		try {
			
			ProcessBuilder builder = new ProcessBuilder("wc");
			Process process = builder.start();
			
			OutputStream in = process.getOutputStream();
			PrintWriter stdin = new PrintWriter(in);
			
			stdin.println("This is one line");
			stdin.println("And another");
			stdin.close();
			
			InputStream stdout = process.getInputStream();
			BufferedReader stdoutBuffered = new BufferedReader(new InputStreamReader(stdout));
			String line = null;
			while ((line = stdoutBuffered.readLine()) != null ) {
				System.out.println(line);
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
