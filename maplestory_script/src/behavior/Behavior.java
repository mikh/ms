package behavior;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Behavior {
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
}
