/**
 * 
 */
package easmodel;

import java.util.ArrayList;
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
	
	public static <T> List<T> getListFromIterator(Iterator<T> iter) {
		List<T> copy = new ArrayList<T>();
		while (iter.hasNext()) {
		    copy.add(iter.next());
		}
		return copy;

	}
	

}
