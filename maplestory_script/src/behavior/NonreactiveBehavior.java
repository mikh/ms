package behavior;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import control_structure.Defines;
import moveset.ThreadManager;

public class NonreactiveBehavior extends Behavior{
	ArrayList<Integer> thread_indices = new ArrayList<Integer>();
	public NonreactiveBehavior(ThreadManager t){
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		log("nonreactive_behavior_operations_log.txt", "Non-reactive behavior created on " + df.format(new Date()));
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.SPELL_ATTACK_A, Defines.KEY_PRESS_MS_GENERAL, Defines.ATTACK_TIMING_MS)); 	//attack
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.ITEM_PICKUP, Defines.KEY_PRESS_MS_GENERAL, Defines.PICKUP_TIMING_MS));		//pickup
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.ITEM_1, Defines.KEY_PRESS_MS_GENERAL, health_potion_timing()));		//health pot
		thread_indices.add(t.addThread(Defines.thread_class.KEY_PRESS_THREAD, Defines.ITEM_2, Defines.KEY_PRESS_MS_GENERAL, mana_potion_timing()));			//mana pot

		//movement
		ArrayList<Integer> keys = new ArrayList<Integer>(), press_delay = new ArrayList<Integer>(), iteration_delay = new ArrayList<Integer>(), iterations = new ArrayList<Integer>(), thread_delay = new ArrayList<Integer>();
		keys.add(Defines.MOVE_RIGHT);
		keys.add(Defines.MOVE_LEFT);
		
		
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
}
