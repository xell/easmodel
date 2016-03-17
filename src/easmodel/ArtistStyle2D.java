/**
 * 
 */
package easmodel;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

/**
 * @author xell
 *
 */
public class ArtistStyle2D extends DefaultStyleOGL2D {

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getScale(java.lang.Object)
	 */
	@Override
	public float getScale(Object object) {
		
		Artist artist = (Artist) object;
		return (float)(artist.getTestAttr() * 10.0);
	}

}
