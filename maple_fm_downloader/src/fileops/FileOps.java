package fileops;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOps {
	public static void write_string_to_file(String filename, boolean append, String str){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, append));
			bw.write(str);
			bw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void clear_file(String filename){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false));
			bw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
