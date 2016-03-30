/**
 * 
 */
package easmodel;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;

/**
 * Utilities for EASModel.
 * @author xell
 */
public class Utils {
	
	private Parameters p = RunEnvironment.getInstance().getParameters();
	private boolean isDebug = p.getBoolean("isDebug");
	
	
	/**
	 * Print debug info to the stdout.
	 * @param s the string to println.
	 */
	public void debug(String s) {
		if (!isDebug) return;
		System.out.println(s);
	}
	
	/**
	 * Print debug info to the stdout with <code>toString()</code> as prefix.
	 * @param o whose <code>toString()</code> that is used.
	 * @param s the string to println.
	 */
	public void debug(Object o, String s) {
		if (!isDebug) return;
		System.out.println(o.toString() + " " + s);
	}
	
	/**
	 * Create new directory for the model data output.
	 * @return the new directory
	 * @throws IOException
	 */
	public String getDataOutputPath() throws IOException {
		// https://docs.oracle.com/javase/tutorial/essential/io/fileio.html
		Path dataOutputRootPath = Paths.get(System.getProperty("user.home"), "Code",
				"abms", "data");
		// get the subdirs of the data dir
		DirectoryStream<Path> subdirs = Files.newDirectoryStream(dataOutputRootPath);
		// get the max existed subdir as int
		int max = 0;
		for (Path subdir: subdirs) {
			try {
				max = Integer.parseInt(subdir.getFileName().toString());
			} catch (NumberFormatException x) {
			}
		}
		max++;
		// get the new subdir as string based on the max existed subdir
		int digitMax = String.valueOf(max).length();
		String newOutputSubdir = "";
		for (int i = 0; i < 3 - digitMax; i++) {
			newOutputSubdir += "0";
		}
		newOutputSubdir += max;
		Path newDataOutputPath = Paths.get(dataOutputRootPath.toString(),
				newOutputSubdir);
		// create the new subdir
		Files.createDirectory(newDataOutputPath);

		return newDataOutputPath.toString();
	}
	
	public static <T> List<T> getListFromIterator(Iterator<T> iter) {
		List<T> copy = new ArrayList<T>();
		while (iter.hasNext()) {
		    copy.add(iter.next());
		}
		return copy;

	}
	
	public static int getSizeOfIterable(Iterable<?> it) {
		  if (it instanceof Collection)
		    return ((Collection<?>)it).size();

		  // else iterate

		  int i = 0;
		  for (@SuppressWarnings("unused") Object obj : it) i++;
		  return i;
		}
	

}
