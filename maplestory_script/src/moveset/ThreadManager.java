package moveset;

import java.util.ArrayList;
import java.util.Random;

import control_structure.Defines;

public class ThreadManager {
	public int thread_count = 0;
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	
	public int addThread(Defines.thread_class thread_class, int key_combo, int key_press_delay, int thread_delay){
		Thread new_thread = null;
		if(thread_class == Defines.thread_class.KEY_PRESS_THREAD)
			new_thread = new Thread(new keyPressClass(key_combo, key_press_delay, thread_delay));
		if(thread_class == Defines.thread_class.SUCCESSIVE_KEY_PRESS_THREAD){
			System.out.println("Need to pass array lists for SUCCESSIVE_KEY_PRESS_THREAD");
			return -1;
		}
		if(thread_class == Defines.thread_class.RANDOM_KEY_PRESS_THREAD){
			System.out.println("Need to pass probability values for RANDOM_KEY_PRESS_THREAD");
			return -1;
		}
		threads.add(new_thread);
		return threads.size()-1;
	}
	
	public int addThread(Defines.thread_class thread_class, ArrayList<Integer> keys, ArrayList<Integer> press_delay, ArrayList<Integer> iteration_delay, ArrayList<Integer> iterations, ArrayList<Integer> thread_delay){
		Thread new_thread = null;
		if(thread_class == Defines.thread_class.KEY_PRESS_THREAD){
			System.out.println("Need to pass int values for KEY_PRESS_THREAD");
			return -1;
		}
		if(thread_class == Defines.thread_class.SUCCESSIVE_KEY_PRESS_THREAD)
			new_thread = new Thread(new successiveKeyPressClass(keys, press_delay, iteration_delay, iterations, thread_delay));
		if(thread_class == Defines.thread_class.RANDOM_KEY_PRESS_THREAD){
			System.out.println("Need to pass probability values for RANDOM_KEY_PRESS_THREAD");
			return -1;
		}
		threads.add(new_thread);
		return threads.size()-1;
	}
	
	public int addThread(Defines.thread_class thread_class, int probability, ArrayList<Integer> keyCombos, int keyPressDelay, int thread_delay){
		Thread new_thread = null;
		if(thread_class == Defines.thread_class.KEY_PRESS_THREAD){
			System.out.println("Need to pass int values for KEY_PRESS_THREAD");
			return -1;
		}
		if(thread_class == Defines.thread_class.SUCCESSIVE_KEY_PRESS_THREAD){
			System.out.println("Need to pass array lists for SUCCESSIVE_KEY_PRESS_THREAD");
			return -1;
		}
		if(thread_class == Defines.thread_class.RANDOM_KEY_PRESS_THREAD){
			new_thread = new Thread(new randomKeyPressClass(probability, keyCombos, keyPressDelay, thread_delay));
		}
		threads.add(new_thread);
		return threads.size()-1;
	}
	
	public void startThread(int index){
		if(index >= 0 && index < threads.size()){
			threads.get(index).start();
		}
	}
}

class keyPressClass implements Runnable{
	int key, press_delay, thread_delay;
	
	public keyPressClass(int key_combo, int key_press_delay, int thread_delay){
		this.key = key_combo;
		this.press_delay = key_press_delay;
		this.thread_delay = thread_delay;
	}
	
	@Override
	public void run(){
		try{
			while(true){
				MoveSet.action(key, press_delay);
				Thread.sleep(thread_delay);
			}
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

class successiveKeyPressClass implements Runnable{
	ArrayList<Integer> keys, press_delay, iteration_delay, iterations, thread_delay;
	
	public successiveKeyPressClass(ArrayList<Integer> keys, ArrayList<Integer> press_delay, ArrayList<Integer> iteration_delay, ArrayList<Integer> iterations, ArrayList<Integer> thread_delay){
		this.keys = keys;
		this.press_delay = press_delay;
		this.iteration_delay = iteration_delay;
		this.iterations = iterations;
		this.thread_delay = thread_delay;
	}
	
	@Override
	public void run(){
		try{
			while(true){
				for(int ii = 0; ii < keys.size(); ii++){
					for(int jj = 0; jj < iterations.get(ii); jj++){
						MoveSet.action(keys.get(ii), press_delay.get(ii));
						Thread.sleep(iteration_delay.get(ii));
					}
					Thread.sleep(thread_delay.get(ii));
				}
			}
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

class randomKeyPressClass implements Runnable{
	int probability, thread_delay, keyPressDelay;
	ArrayList<Integer> keyChoices = new ArrayList<Integer>();
	
	public randomKeyPressClass(int probability, ArrayList<Integer> keyChoices, int keyPressDelay, int thread_delay){
		this.probability = probability;
		this.keyChoices = keyChoices;
		this.thread_delay = thread_delay;
		this.keyPressDelay = keyPressDelay;
	}
	
	@Override
	public void run(){
		try{
			while(true){
				Random r = new Random(System.currentTimeMillis());
				if(r.nextInt(100) <= probability){
					MoveSet.action(keyChoices.get(r.nextInt(keyChoices.size())), keyPressDelay);
				}				
				Thread.sleep(thread_delay);
			}			
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
