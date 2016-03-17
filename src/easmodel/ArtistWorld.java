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
	// number of artists in total
	private final int NUM_ARTISTS = p.getInteger("numArtists");
	// interval of generation, default 0 means all at once
	private final int GEN_INTERVAL = p.getInteger("genInterval");
	// number of artists per generation, default 0 means all at once
	private final int NUM_PER_GEN = p.getInteger("numPerGen");
	// record the number of dead artists
	private int numDeadArtists = 0;
	private int artistID = 1;
	

	
	public ArtistWorld() {
		super("Artist World");
		
		// born the artists by generation per interval
		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();
		if (GEN_INTERVAL > 0) {
			int num_per_gen = NUM_PER_GEN <= 0 ? 1 : NUM_PER_GEN;
			int numGen = NUM_ARTISTS / num_per_gen;
			int gen = 1;
			for (; gen <= numGen; gen++) {
				for (; artistID <= gen * num_per_gen; artistID++) {
					ScheduleParameters params = ScheduleParameters.
							createOneTime(GEN_INTERVAL * (gen - 1),
									ScheduleParameters.FIRST_PRIORITY);
					schedule.schedule(params, this, "addArtistToWorld", 
							artistID);

				}
			}
			for (; artistID <= NUM_ARTISTS; artistID++) {
				ScheduleParameters params = ScheduleParameters.
						createOneTime(GEN_INTERVAL * (gen - 1),
								ScheduleParameters.FIRST_PRIORITY);
				schedule.schedule(params, this, "addArtistToWorld", 
						artistID);
			}
		} else {
			for (; artistID <= NUM_ARTISTS; artistID++) {
				ScheduleParameters params = ScheduleParameters.
						createOneTime(0, ScheduleParameters.RANDOM_PRIORITY);
				schedule.schedule(params, this, "addArtistToWorld", 
						artistID);
			}
		}
		
		// end the run of simulation
		ScheduleParameters endRunTime = ScheduleParameters.
    			createRepeating(2 + 0 * (NUM_ARTISTS - 1), 1);
		schedule.schedule(endRunTime, this, "endRun");
		
	}
	
	public void endRun() {
		if (numDeadArtists < NUM_ARTISTS) {
			return;
		}
		Utils.pl("shutdown in artistworld");
		RunEnvironment.getInstance().endRun();
	}
	
	public void addArtistToWorld(int id) {
		this.add(new Artist(id, RandomHelper.nextIntFromTo(20, 100), this));
	}
	
	public void addOneDeadArtist() {
		numDeadArtists++;
	}

}
