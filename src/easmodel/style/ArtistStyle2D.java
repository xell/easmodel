/**
 * 
 */
package easmodel.style;

import java.awt.Color;
import java.awt.Font;

import easmodel.Artist;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;
import saf.v3d.ShapeFactory2D;
import saf.v3d.scene.Position;
import saf.v3d.scene.VSpatial;

/**
 * @author xell
 *
 */
public class ArtistStyle2D extends DefaultStyleOGL2D {
	

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#init(saf.v3d.ShapeFactory2D)
	 */
	@Override
	public void init(ShapeFactory2D factory) {
		// TODO Auto-generated method stub
		super.init(factory);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getVSpatial(java.lang.Object, saf.v3d.scene.VSpatial)
	 */
	@Override
	public VSpatial getVSpatial(Object agent, VSpatial spatial) {
		// TODO Auto-generated method stub
		return super.getVSpatial(agent, spatial);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getColor(java.lang.Object)
	 */
	@Override
	public Color getColor(Object agent) {
		// TODO Auto-generated method stub
		return super.getColor(agent);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getRotation(java.lang.Object)
	 */
	@Override
	public float getRotation(Object agent) {
		// TODO Auto-generated method stub
		return super.getRotation(agent);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getBorderColor(java.lang.Object)
	 */
	@Override
	public Color getBorderColor(Object object) {
		// TODO Auto-generated method stub
		return super.getBorderColor(object);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getBorderSize(java.lang.Object)
	 */
	@Override
	public int getBorderSize(Object object) {
		// TODO Auto-generated method stub
		return super.getBorderSize(object);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getScale(java.lang.Object)
	 */
	@Override
	public float getScale(Object object) {
		Artist artist = (Artist) object;
		return (float)(artist.getTestAttr() * 2.0);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getLabel(java.lang.Object)
	 */
	@Override
	public String getLabel(Object object) {
		Artist artist = (Artist) object;
		return artist.ID + "";
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getLabelFont(java.lang.Object)
	 */
	@Override
	public Font getLabelFont(Object object) {
		return new Font("Menlo", Font.PLAIN, 12);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getLabelPosition(java.lang.Object)
	 */
	@Override
	public Position getLabelPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getLabelPosition(object);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getLabelXOffset(java.lang.Object)
	 */
	@Override
	public float getLabelXOffset(Object object) {
		// TODO Auto-generated method stub
		return super.getLabelXOffset(object);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getLabelYOffset(java.lang.Object)
	 */
	@Override
	public float getLabelYOffset(Object object) {
		// TODO Auto-generated method stub
		return super.getLabelYOffset(object);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultStyleOGL2D#getLabelColor(java.lang.Object)
	 */
	@Override
	public Color getLabelColor(Object object) {
		// TODO Auto-generated method stub
		return super.getLabelColor(object);
	}

	
	

}
