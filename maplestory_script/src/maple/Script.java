package maple;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Script {
	
	public static final int ATTACK = KeyEvent.VK_A;
	public static final int LOOT_PICKUP = KeyEvent.VK_Z;
	public static final int TELEPORT = KeyEvent.VK_Q;
	public static final int JUMP = KeyEvent.VK_ALT;
	public static final int HEALTH_POTION = KeyEvent.VK_1;
	public static final int MANA_POTION = KeyEvent.VK_2;
	public static final int PET_FOOD = KeyEvent.VK_0;
	public static final int UP = KeyEvent.VK_UP;
	public static final int LEFT = KeyEvent.VK_LEFT;
	public static final int RIGHT = KeyEvent.VK_RIGHT;
	
	public static final int MAX_HP = 700;
	public static final int MAX_MP = 1100;
	public static final int ATTACK_MP = 17;
	public static final int HEALTH_PER_HEAL = 150;
	public static final int MANA_PER_HEAL = 100;
	public static final int DAMAGE_TAKEN_PER_HIT = 60;
	public static final int HITS_PER_MINUTE = 10;
	public static final int ATTACK_TIMING_MS = 1600;
	public static final int ACCELERATION_TIME = 50;
	public static final int DEACCELERATION_TIME = 50;
	public static final int WALK_TIME = 11720;
	public static final int ITERATIONS = 50;
	
	public static void delay(long milliseconds){
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() - start < milliseconds);
	}
	/*
	public static void main(String[] args){
		System.out.println("Image test");
		ImageMatch im = new ImageMatch();
	//	im.match_images();
	}
	*/
	/*
	public static void main(String[] args){
		System.out.println("Starting script");
		
		Thread t_attack, t_jump, t_health_potion, t_mana_potion, t_pet_food, t_side_to_side, t_pickup, t_screenshot;
		t_attack = new Thread(new keyPressThread(ATTACK, ATTACK_TIMING_MS, 100));
		t_pickup = new Thread(new keyPressThread(LOOT_PICKUP, 520, 100));
		//t_attack.setPriority(Thread.MAX_PRIORITY);
		t_health_potion = new Thread(new keyPressThread(HEALTH_POTION, calculate_HP_timing(), 100));
		t_mana_potion = new Thread(new keyPressThread(MANA_POTION, calculate_MP_timing(), 100));
		t_pet_food = new Thread(new keyPressThread(PET_FOOD, 20*60*1000,100));
		t_side_to_side = new Thread(new moveSideToSideThread(LEFT, RIGHT, UP, TELEPORT,  2*1000,calculate_walk_time(),50));
		//t_screenshot = new Thread(new screenshotThread(1000,"Images/scrsht"));
		t_screenshot = new Thread(new keyPressThread(KeyEvent.VK_SCROLL_LOCK, 1000, 100));
		
		t_attack.start();
		t_pickup.start();
		t_health_potion.start();
		t_mana_potion.start();
		t_pet_food.start();
		t_side_to_side.start();
		t_screenshot.start();
		
		System.out.println("Script complete");
	}*/
	
	private  static int calculate_MP_timing(){
		int number_of_attacks_per_minute = 60000/ATTACK_TIMING_MS;
		number_of_attacks_per_minute++;
		int mp_used = number_of_attacks_per_minute*ATTACK_MP;
		int potions_per_minute = mp_used/MANA_PER_HEAL;
		return 60000/potions_per_minute;
	}

	private static int calculate_HP_timing(){
		int damage_per_minute = DAMAGE_TAKEN_PER_HIT*HITS_PER_MINUTE*2;
		int potions_per_minute = damage_per_minute/HEALTH_PER_HEAL;
		return 60000/potions_per_minute;
	}
	
	private static int calculate_walk_time(){
		int time_per_iteration = (WALK_TIME-ACCELERATION_TIME-DEACCELERATION_TIME)/ITERATIONS;
		return time_per_iteration + ACCELERATION_TIME + DEACCELERATION_TIME;
	}
}

class moveSideToSideThread implements Runnable{
	int TELEPORT_CHANCE = 10;


	int key_left, key_right, key_up, key_teleport;
	long ms_delay, key_press_delay;
	int iterations;
	
	public moveSideToSideThread(int key_left, int key_right, int key_up, int key_teleport, long ms_delay, long key_press_delay, int iterations){
		this.key_left = key_left;
		this.key_right = key_right;
		this.ms_delay = ms_delay;
		this.key_press_delay = key_press_delay;
		this.iterations = iterations;
		this.key_up = key_up;
		this.key_teleport = key_teleport;
	}

	private void teleport_up(){
		try{
			Robot r = new Robot();
			r.keyPress(key_teleport);
			r.keyPress(key_up);
			Script.delay(key_press_delay);
			r.keyRelease(key_up);
			r.keyRelease(key_teleport);
		}
		catch(AWTException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		try{
			Robot r = new Robot();
			int cur_key = key_left, cur_iter = 0;
			while(true){
				r.keyPress(cur_key);
				Script.delay(key_press_delay);
				r.keyRelease(cur_key);
				cur_iter++;
				if(cur_iter > iterations){
					if(cur_key == key_left)
						cur_key = key_right;
					else
						cur_key = key_left;
					cur_iter = 0;
				}

				Random rrr = new Random(System.currentTimeMillis());
				if(rrr.nextInt(100) < TELEPORT_CHANCE)
					teleport_up();

				Thread.sleep(ms_delay);
			}
		} catch(AWTException | InterruptedException e){
			e.printStackTrace();
		}
	}
}

class screenshotThread implements Runnable{
	int ms_delay;
	String base;
	
	public screenshotThread(int ms_delay, String base){
		this.ms_delay = ms_delay;
		this.base = base;
	}
	
	@Override
	public void run(){
		try{
			Robot r = new Robot();
			int cur_image = 0;
			while(true){
				BufferedImage image = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				ImageIO.write(image, "png", new File(base+cur_image+".png"));
				cur_image++;
				Thread.sleep(ms_delay);
			}
		} catch(AWTException | IOException | InterruptedException e){
			e.printStackTrace();
		}
	}
}

class keyPressThread implements Runnable{
	int key;
	long ms_delay;
	long key_press_delay;
	
	public keyPressThread(int key, long ms_delay, long key_press_delay){
		this.key = key;
		this.ms_delay = ms_delay;
		this.key_press_delay = key_press_delay;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			Robot r = new Robot();
			while(true){
				r.keyPress(key);
				Script.delay(key_press_delay);
				r.keyRelease(key);
				Thread.sleep(ms_delay);
			}	
		} catch(AWTException | InterruptedException e){
			e.printStackTrace();
		}
	}
}
