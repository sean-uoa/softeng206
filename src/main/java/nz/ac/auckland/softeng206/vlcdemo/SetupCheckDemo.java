package nz.ac.auckland.softeng206.vlcdemo;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class SetupCheckDemo{
	
	private static final String NATIVE_LIBRARY_SEARCH_PATH = "/usr/lib";
	
	public static void main(String[] args) {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), NATIVE_LIBRARY_SEARCH_PATH);
        System.out.println(LibVlc.INSTANCE.libvlc_get_version());
	}
}