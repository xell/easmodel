/**
 * 
 */
package easmodel;

import java.util.Iterator;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.graph.Network;

/**
 * @author xell
 *
 */
public class Artist {
	
	public final int ID;
	public final double BOD;
	public final int LIFE_SPAN;
	private int age;
	private ArtistWorld artistWorld = null;
	
	private double testAttr = 0.0;
	
	public Artist(int id, int lifespan, ArtistWorld artistWorld) {
		this.ID = id;
		this.LIFE_SPAN = lifespan;
		age = 0;
		this.artistWorld = artistWorld;
		
		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();

		// in construct stage, getTickCount() = -1
		BOD = schedule.getTickCount() + 1;
		
		ScheduleParameters init = ScheduleParameters.createOneTime(BOD, 
				ScheduleParameters.FIRST_PRIORITY);
		schedule.schedule(init, this, "init");
		
		ScheduleParameters step = ScheduleParameters.createRepeating(BOD + 1, 1, 
				ScheduleParameters.RANDOM_PRIORITY);
		schedule.schedule(step, this, "step");

		ScheduleParameters end = ScheduleParameters.createAtEnd(
				ScheduleParameters.END);
		schedule.schedule(end, this, "end");
		
		Utils.plts(this, LIFE_SPAN + ":" + BOD);
	}
	
	public void init() {
		Utils.plts(this, "init");
	}
	
	public void step() {
		age++;
		if (age == LIFE_SPAN + 1) {
			artistWorld.addOneDeadArtist();
			Utils.plts(this, "died in " + LIFE_SPAN);
		} else if (age <= LIFE_SPAN) {
			setTestAttr(RandomHelper.nextDouble());
			@SuppressWarnings("unchecked")
			Network<Artist> network = (Network<Artist>)artistWorld.getProjection("ArtistNetwork");
			Iterator<Artist> allArtists = network.getNodes().iterator();
			if (allArtists.hasNext()) {
				network.addEdge(this, allArtists.next());
			}

		} else {
			
		}
	}
	
	public void end() {
		Utils.plts(this, "end");
	}
	
	public int getAge() {
		return age;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		int NUM_ARTISTS = RunEnvironment.getInstance().getParameters().getInteger("numArtists");

		int digitNumArtitis = String.valueOf(NUM_ARTISTS).length();
		int digitID = String.valueOf(ID).length();
		String blanks = "";
		for (int i = 0; i < digitNumArtitis - digitID; i++) {
			blanks += " ";
		}
		return "[" + blanks + ID + "]";
	}

	public double getTestAttr() {
		return testAttr;
	}

	public void setTestAttr(double testAttr) {
		this.testAttr = testAttr;
	}

}
