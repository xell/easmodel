/**
 * 
 */
package easmodel;

import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;

/**
 * The artist world.
 * @author xell
 */
public class ArtistWorld extends DefaultContext<Object> {
	
	Parameters p = RunEnvironment.getInstance().getParameters();
	private final int NUM_ARTISTS = p.getInteger("numArtists");
	private int numDeadArtists = 0;
	

	
	public ArtistWorld() {
		super("Artist World");
		
		for (int i = 1; i <= NUM_ARTISTS; i++) {
			this.add(new Artist(i, RandomHelper.nextIntFromTo(20, 100), this));
		}
		
		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();

		ScheduleParameters shutdownTime = ScheduleParameters.
    			createRepeating(2 + 0 * (NUM_ARTISTS - 1), 1);
		schedule.schedule(shutdownTime, this, "shutdown");
		
		Utils.pl("init artistworld " + numDeadArtists);

	}
	
	public void shutdown() {
		if (numDeadArtists < NUM_ARTISTS) {
			return;
		}
		Utils.pl("shutdown in artistworld");
		RunEnvironment.getInstance().endRun();
	}
	
	public void addOneDeadArtist() {
		numDeadArtists++;
	}

}
