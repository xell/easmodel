/**
 * 
 */
package easmodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import mjson.Json;
import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.graph.Network;

/**
 * The artist world.
 * @author xell
 */
public class ArtistWorld extends DefaultContext<Artist> {
	
	Parameters p = RunEnvironment.getInstance().getParameters();
	// number of artists in total
	public final int NUM_ARTISTS = p.getInteger("numArtists");
	// interval of generation, default 0 means all at once
	private final int GEN_INTERVAL = p.getInteger("genInterval");
	// number of artists per generation, default 0 means all at once
	private final int NUM_PER_GEN = p.getInteger("numPerGen");
	// record the number of dead artists
	private int numDeadArtists = 0;
	private int artistID = 0;
	private Network<Artist> network;

	private ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();
	
	private Utils u = new Utils();
	private String dataOutputPath = "";
	private Json data;
	
	public ArtistWorld() {
		super("ArtistWorld");
		
		data = Json.object();

		ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null)
				.createContinuousSpace(
				"ArtistSpace",
				this,
				new RandomCartesianAdder<Artist>(),
				new repast.simphony.space.continuous.WrapAroundBorders(),
				50, 50);
//		NetworkFactoryFinder.createNetworkFactory(null).
//				createNetwork("ArtistNetwork", this, true);

		NetworkBuilder<Artist> netBuilder = new NetworkBuilder<Artist>(
				"ArtistNetwork", this, true);
		network = netBuilder.buildNetwork();

		// born the artists by generation per interval
		if (GEN_INTERVAL > 0) {
			int numPerGen = NUM_PER_GEN <= 0 ? 1 : NUM_PER_GEN;
			int numGen = NUM_ARTISTS / numPerGen;
			int gen = 1;
			for (; gen <= numGen; gen++) {
				for (; artistID < gen * numPerGen; artistID++) {
					ScheduleParameters params = ScheduleParameters.
							createOneTime(GEN_INTERVAL * (gen - 1),
									ScheduleParameters.FIRST_PRIORITY);
					schedule.schedule(params, this, "addArtistToWorld", 
							artistID);

				}
			}
			for (; artistID < NUM_ARTISTS; artistID++) {
				ScheduleParameters params = ScheduleParameters.
						createOneTime(GEN_INTERVAL * (gen - 1),
								ScheduleParameters.FIRST_PRIORITY);
				schedule.schedule(params, this, "addArtistToWorld", 
						artistID);
			}
		} else {
			for (; artistID < NUM_ARTISTS; artistID++) {
				ScheduleParameters params = ScheduleParameters.
						createOneTime(0, ScheduleParameters.RANDOM_PRIORITY);
				schedule.schedule(params, this, "addArtistToWorld", 
						artistID);
			}
		}
		
		// step method of the whole world for global actions
		ScheduleParameters step = ScheduleParameters.createRepeating(1, 1,
				ScheduleParameters.LAST_PRIORITY);
		schedule.schedule(step, this, "step");
		
		data.set("numArtists", NUM_ARTISTS);
		data.set("genInterval", GEN_INTERVAL);
		data.set("numPerGen", NUM_PER_GEN);
		data.set("tickAtArtistWorldConstructor", schedule.getTickCount());
		
		try {
			dataOutputPath = u.getDataOutputPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Step method of the artist world for global actions.
	 * @throws IOException
	 */
	public void step() throws IOException {
		if (numDeadArtists < NUM_ARTISTS) {
			return;
		}
		u.debug("shutdown in artistworld at " + schedule.getTickCount());
		RunEnvironment.getInstance().endRun();
		
		Files.write(Paths.get(dataOutputPath + "/a.json"),
				data.toString().getBytes());
	}
	
	public void addArtistToWorld(int id) {
		this.add(new Artist(id, RandomHelper.nextIntFromTo(20, 100), this));
	}
	
	public void addOneDeadArtist() {
		numDeadArtists++;
	}
	
	public Network<Artist> getNetwork() {
		return network;
	}
	public String getDataOutputPath() {
		return dataOutputPath;
	}
	
}
