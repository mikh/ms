package control_structure;

import moveset.ThreadManager;
import behavior.Behavior;
import behavior.NonreactiveBehavior;

public class Controller {
	public static void main(String[] args){
		System.out.println("Engaging Scripting Behavior.Please switch to Maplestory within the next 10s.");
		Behavior b = null;
		ThreadManager t = new ThreadManager();
		switch(Defines.ACTIVE_BEHAVIOR){
		case NONREACTIVE:
			b = new NonreactiveBehavior(t);
		}
		Defines.delay(Defines.MINUTE/6);
		System.out.println("Script Running");
		b.start();
	}
}
