package behavior;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import control_structure.Defines;
import moveset.ThreadManager;

public class NonreactiveMiningBehavior extends Behavior{
	ArrayList<Integer> thread_indices = new ArrayList<Integer>();
	ThreadManager t;
	
	public NonreactiveMiningBehavior(ThreadManager t){
		this.t = t;
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		log("nonreactive_behavior_operations_log.txt", "Non-reactive Mining behavior created on " + df.format(new Date()));
		 	//attack
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.ITEM_PICKUP, Defines.KEY_PRESS_MS_GENERAL, Defines.PICKUP_TIMING_MS));		//pickup
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.ITEM_0, Defines.KEY_PRESS_MS_GENERAL, Defines.PET_FOOD_TIMING_MS));	//pet food
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.SPACE, Defines.KEY_PRESS_MS_GENERAL, Defines.MINING_DELAY));	//mine thread
		//movement
		ArrayList<Integer> keys = new ArrayList<Integer>(), press_delay = new ArrayList<Integer>(), iteration_delay = new ArrayList<Integer>(), iterations = new ArrayList<Integer>(), thread_delay = new ArrayList<Integer>();
		keys.add(Defines.MOVE_RIGHT);
		keys.add(Defines.MOVE_LEFT);
		press_delay.add(walk_timing());
		press_delay.add(walk_timing());
		iteration_delay.add(Defines.WALK_DELAY);
		iteration_delay.add(Defines.WALK_DELAY);
		iterations.add(Defines.WALK_ITERATIONS);
		iterations.add(Defines.WALK_ITERATIONS);
		thread_delay.add(Defines.WALK_DELAY);
		thread_delay.add(Defines.WALK_DELAY);
		thread_indices.add(t.addThread(Defines.thread_class.SUCCESSIVE_KEY_PRESS_THREAD, keys, press_delay, iteration_delay, iterations, thread_delay));
	}
	
	public void start(){
		for(int ii = 0; ii < thread_indices.size(); ii++){
			t.startThread(thread_indices.get(ii));
		}
	}
	
	private int walk_timing(){
		return (Defines.WALK_TIME/Defines.WALK_ITERATIONS) + Defines.ACCELERATION_TIME + Defines.DEACCELERATION_TIME;
	}
}
