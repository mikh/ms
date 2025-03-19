package control_structure;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import clipboard.Clip;
import data_parser.Parser;
import data_structure.data_entry;
import fileops.FileOps;
import robot_controller.RobotController;
import strops.StrOps;
import web.Client;

public class Control {
	public static void main(String[] args){
		System.out.println("Starting downloader");
		Ini ini = null;
		try{
			ini = new Ini(new File(Defines.PATH_DEFINES));
		} catch(IOException e){
			e.printStackTrace();
		}
		while(true){
			Client c = new Client(Defines.START_URL, null, ini.get("PATHS", "FIREFOX_EXE"));
			c.setSelectOption(Defines.SELECT_FIELD_XPATH, Defines.SELECT_FIELD_VALUE);
			System.out.println("Waiting");
			RobotController.delay(5000);
			System.out.println("Done waiting");
			
			RobotController.selectAll();
			RobotController.copy();
			RobotController.copy();
			String data = Clip.getTextOnClipboard();
			FileOps.write_string_to_file("debug0.txt", false, data);
			
			data = StrOps.getFollowing(data, Defines.DATA_START);
			FileOps.write_string_to_file("debug1.txt", false, data);
			ArrayList<Object> entry_string = StrOps.findStringWithNumbers(data, Defines.DATA_FINISH);
			if(entry_string == null){
				System.out.println("Error! cannot find end of data");
			} else{
				data = StrOps.getPrevious(data, (int)(entry_string.get(0)));
				FileOps.write_string_to_file("data.txt", false, data+"\n\n");
				
				int all_vals = StrOps.extractNthNumber((String)entry_string.get(2), 3);
				int cur_val = 250;
				while(cur_val < all_vals){
					c.click(Defines.NEXT_BUTTON_ID, false);
					System.out.println("Waiting");
					RobotController.delay(5000);
					System.out.println("Done waiting");
					RobotController.selectAll();
					RobotController.copy();
					data = Clip.getTextOnClipboard();
					data = StrOps.getFollowing(data, Defines.DATA_START);
					entry_string = StrOps.findStringWithNumbers(data, Defines.DATA_FINISH);
					data = StrOps.getPrevious(data, (int)(entry_string.get(0)));
					FileOps.write_string_to_file("data.txt", true, data+"\n\n");
					cur_val += 250;
				}
				
				ArrayList<data_entry> parsed_data = Parser.parse("data.txt");
				
				DateFormat df = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
				String data_file_name = String.format("%s\\data_%s.txt",Defines.DATA_OUTPUT_LOCATION, df.format(new Date()));
				FileOps.clear_file(data_file_name);
				for(data_entry d : parsed_data){
					FileOps.write_string_to_file(data_file_name, true, d.print());
				}
			}
		
			
			c.close();
			RobotController.delay(30*60000);
		}
		//System.out.println("Task complete");
	}
}
