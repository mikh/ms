package behavior;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import control_structure.Defines;
import moveset.ThreadManager;

public class NonreactiveBehavior extends Behavior{
	ArrayList<Integer> thread_indices = new ArrayList<Integer>();
	ThreadManager t;
	
	public NonreactiveBehavior(ThreadManager t){
		this.t = t;
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		log("nonreactive_behavior_operations_log.txt", "Non-reactive behavior created on " + df.format(new Date()));
		 	//attack
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.ITEM_PICKUP, Defines.KEY_PRESS_MS_GENERAL, Defines.PICKUP_TIMING_MS));		//pickup
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.ITEM_1, Defines.KEY_PRESS_MS_GENERAL, health_potion_timing()));		//health pot
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.ITEM_2, Defines.KEY_PRESS_MS_GENERAL, mana_potion_timing()));			//mana pot
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.ITEM_0, Defines.KEY_PRESS_MS_GENERAL, Defines.PET_FOOD_TIMING_MS));	//pet food
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.MOVE_DOWN, Defines.LADDER_ESCAPE_DELAY, Defines.LADDER_ESCAPE_TIMING));
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.SPELL_ATTACK_A, Defines.KEY_PRESS_MS_GENERAL, Defines.ATTACK_TIMING_MS));
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
		
		//variations
		if(Defines.ACTIVE_VARIATION == Defines.behavior_variations.NONREACTIVE_VERTICAL_TELEPORT){
			ArrayList<Integer> key_combos = new ArrayList<Integer>();
			key_combos.add(Defines.TELEPORT_UP);
			thread_indices.add(t.addThread(Defines.thread_class.RANDOM_KEY_PRESS_THREAD, teleport_probability(), key_combos, Defines.KEY_PRESS_MS_GENERAL, teleport_timing()));
		} else if(Defines.ACTIVE_VARIATION == Defines.behavior_variations.NONREACTIVE_FULL_RANGE){
			ArrayList<Integer> key_combos = new ArrayList<Integer>();
			key_combos.add(Defines.TELEPORT_UP);
			key_combos.add(Defines.JUMP_DOWN);
			thread_indices.add(t.addThread(Defines.thread_class.RANDOM_KEY_PRESS_THREAD, teleport_probability(), key_combos, Defines.KEY_PRESS_MS_GENERAL, teleport_timing()));
		}
	}
	
	public void start(){
		for(int ii = 0; ii < thread_indices.size(); ii++){
			t.startThread(thread_indices.get(ii));
		}
	}
	
	private int health_potion_timing(){
		int ms =  Defines.MINUTE/(Defines.DAMAGE_TAKEN_PER_HIT*Defines.HITS_PER_MINUTE*2/Defines.HEALTH_PER_POTION);
		if(ms < Defines.HEALTH_POTION_COOLDOWN){
			log("nonreactive_behavior_operations_log.txt", "Hit the health potion application cooldown");
			return Defines.HEALTH_POTION_COOLDOWN;
		}
		return ms;
	}
	
	private int mana_potion_timing(){
		int ms = Defines.MINUTE/((((Defines.MINUTE/Defines.ATTACK_TIMING_MS)+1)*Defines.MP_USED_PER_ATTACK)/Defines.MP_PER_POTION);
		if(ms < Defines.MP_PER_POTION){
			log("nonreactive_behavior_operations_log.txt", "Hit the mana potion application cooldown");
			return Defines.MANA_POTION_COOLDOWN;
		}
		return ms;
	}
	
	private int walk_timing(){
		return (Defines.WALK_TIME/Defines.WALK_ITERATIONS) + Defines.ACCELERATION_TIME + Defines.DEACCELERATION_TIME;
	}
	
	private int teleport_probability(){
		return 100/Defines.NUMBER_OF_PLATFORMS;
	}
	
	private int teleport_timing(){
		return Defines.WALK_ITERATIONS/Defines.NUMBER_OF_PLATFORMS*walk_timing();
	}
}
