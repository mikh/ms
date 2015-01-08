package behavior;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import moveset.ThreadManager;

public abstract class Behavior {
	protected ArrayList<Integer> thread_indices = new ArrayList<Integer>();
	protected ThreadManager t;
	
	protected void log(String log_filename, String str){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(log_filename, true));
			bw.write(str);
			System.out.println(str);
			bw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void start(){
		for(int index : thread_indices){
			t.startThread(index);
		}
	}
}
