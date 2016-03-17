/**
 * 
 */
package easmodel;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;

/**
 * EASModel context builder for RS.
 * @author xell
 */
public class EASModel implements ContextBuilder<Object> {

	/* (non-Javadoc)
	 * @see repast.simphony.dataLoader.ContextBuilder#build(repast.simphony.context.Context)
	 */
	@Override
	public Context<Object> build(Context<Object> context) {
		context.setId("easmodel");
		ArtistWorld artistWorld = new ArtistWorld();
		context.addSubContext(artistWorld);
		context.add(artistWorld);
		
		return context;
	}

}
