package control_structure;

import robot_controller.RobotController;
import web.Client;

public class Control {
	public static void main(String[] args){
		System.out.println("Starting downloader");
		Client c = new Client(Defines.START_URL, null, Defines.FIREFOX_EXE);
		c.setSelectOption(Defines.SELECT_FIELD_XPATH, Defines.SELECT_FIELD_VALUE);
		System.out.println("Waiting");
		RobotController.delay(60000);
		System.out.println("Done waiting");
		RobotController.selectAll();
		RobotController.copy();
		System.out.println("Task complete");
	}
}
