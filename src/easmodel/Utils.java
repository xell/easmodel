/**
 * 
 */
package easmodel;

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
	

}
