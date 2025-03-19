package robot_controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotController {
	
	public static void delay(long milliseconds){
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() - start < milliseconds);
	}
	
	public static void selectAll(){
		try{
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			delay(100);
			r.keyPress(KeyEvent.VK_A);
			delay(100);
			r.keyRelease(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_CONTROL);			
		}catch(AWTException e){
			e.printStackTrace();
		}
	}
	
	public static void copy(){
		try{
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			delay(100);
			r.keyPress(KeyEvent.VK_C);
			delay(100);
			r.keyRelease(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_CONTROL);			
		}catch(AWTException e){
			e.printStackTrace();
		}
	}
}
