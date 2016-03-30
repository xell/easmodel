/**
 * 
 */
package easmodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import mjson.Json;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.graph.Network;
import repast.simphony.util.SimUtilities;

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
	private Network<Artist> network = null;

	private ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();

	private Utils u = new Utils();
	private Json data;
	
	private double testAttr = 0.0;
	
	public Artist(int id, int lifespan, ArtistWorld artistWorld) {
		data = Json.object();
		
		this.ID = id;
		this.LIFE_SPAN = lifespan;
		age = 0;
		this.artistWorld = artistWorld;
		this.network = artistWorld.getNetwork();
		

		// in construct stage, getTickCount() = 0
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
		
		u.debug(this, LIFE_SPAN + ":" + BOD);
	}
	
	public void init() {
		age++;
		data.set("id", ID);
		data.set("lifeSpan", LIFE_SPAN);
		data.set("BOD", BOD);
		data.set("testAttr", new LinkedList<Double>());
		data.set("ngbh", new LinkedList<LinkedList<Integer>>());
		
		u.debug(this, "init at " + schedule.getTickCount());
	}
	
	public void step() {
		if (age < LIFE_SPAN) {
			age++;
			setTestAttr(RandomHelper.nextDouble());
			data.at("testAttr").add(testAttr);

			if (age % 20 == 0) {
				List<Artist> allArtists = Utils.getListFromIterator(
						network.getNodes().iterator());
				SimUtilities.shuffle(allArtists, RandomHelper.getUniform());
				Artist newNgb = allArtists.get(0);
				network.addEdge(this, newNgb);
				List<Integer> ngbh = new LinkedList<>();
				for (int i = 0; i < allArtists.size(); i++) {
					if (i == newNgb.ID) {
						ngbh.add(1);
					} else {
						ngbh.add(0);
					}
				}
				data.at("ngbh").add(ngbh);
			}
//			u.debug(this, "complete step at " + schedule.getTickCount());
		} else if (age == LIFE_SPAN) {
			age++;
			artistWorld.addOneDeadArtist();
			u.debug(this, "died at " + schedule.getTickCount());
		} else {
		}
	}
	
	public void end() throws IOException {
		u.debug(this, "end at " + schedule.getTickCount());

		Files.write(Paths.get(artistWorld.getDataOutputPath() + "/a" + ID +  ".json"),
				data.toString().getBytes());
	}
	
	public int getAge() {
		return age;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		int digitNumArtitis = String.valueOf(artistWorld.NUM_ARTISTS).length();
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
