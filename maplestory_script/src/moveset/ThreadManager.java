package moveset;

import java.util.ArrayList;

import control_structure.Defines;

public class ThreadManager {
	public int thread_count = 0;
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	
	public int addThread(Defines.thread_class thread_class, int key_combo, int key_press_delay, int thread_delay){
		
	}
}

class keyPressClass implements Runnable{
	
	public keyPressClass(int key_combo, int key_press_delay, int thread_delay){
		
	}
}
