/**
 * 
 */
package easmodel.style;

import java.awt.Color;

import repast.simphony.space.graph.RepastEdge;
import repast.simphony.visualizationOGL2D.DefaultEdgeStyleOGL2D;

/**
 * @author xell
 *
 */
public class ArtistEdgeStyle2D extends DefaultEdgeStyleOGL2D {

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultEdgeStyleOGL2D#getColor(repast.simphony.space.graph.RepastEdge)
	 */
	@Override
	public Color getColor(RepastEdge<?> edge) {
		return new Color(128, 128, 128);
	}

	/* (non-Javadoc)
	 * @see repast.simphony.visualizationOGL2D.DefaultEdgeStyleOGL2D#getLineWidth(repast.simphony.space.graph.RepastEdge)
	 */
	@Override
	public int getLineWidth(RepastEdge<?> edge) {
		return 2;
	}
	
	

}
