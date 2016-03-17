/**
 * 
 */
package easmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utilities for EASModel.
 * @author xell
 */
public class Utils {
	
	/**
	 * Print to the stdout.
	 * @param s the string to println.
	 */
	public static void pl(String s) {
		System.out.println(s);
	}
	
	/**
	 * Print to the stdout with <code>toString()</code> as prefix.
	 * @param o whose <code>toString()</code> that is used.
	 * @param s the string to println.
	 */
	public static void plts(Object o, String s) {
		System.out.println(o.toString() + " " + s);
	}
	
	public static <T> List<T> getListFromIterator(Iterator<T> iter) {
		List<T> copy = new ArrayList<T>();
		while (iter.hasNext()) {
		    copy.add(iter.next());
		}
		return copy;

	}
	

}
