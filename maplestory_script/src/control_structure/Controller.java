package control_structure;

import moveset.ThreadManager;
import behavior.Behavior;
import behavior.ImageDetectionExperimentBehavior;
import behavior.NonreactiveBehavior;
import behavior.NonreactiveMiningBehavior;

public class Controller {
	public static void main(String[] args){
		System.out.println("Engaging Scripting Behavior.Please switch to Maplestory within the next 10s.");
		Behavior b = null;
		ThreadManager t = new ThreadManager();
		switch(Defines.ACTIVE_BEHAVIOR){
		case NONREACTIVE:
			b = new NonreactiveBehavior(t);
			break;
		case IMAGE_DETECTION_EXPERIMENT:
			b = new ImageDetectionExperimentBehavior(t);
			break;
		case IMAGE_DETECTION:
			break;
		case NONREACTIVE_MINING:
			b = new NonreactiveMiningBehavior(t);
			break;
		default:
			System.out.println("Behavior not implemented");
			break;
		}
		Defines.delay(Defines.MINUTE/6);
		System.out.println("Script Running");
		b.start();
	}
}
