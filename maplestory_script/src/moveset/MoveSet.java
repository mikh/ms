package moveset;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import control_structure.Defines;

public class MoveSet {
	public static final int WEAPON_ATTACK = KeyEvent.VK_CONTROL;
	public static final int SPELL_ATTACK_A = KeyEvent.VK_A;
	public static final int SPELL_ATTACK_S = KeyEvent.VK_S;
	public static final int SPELL_ATTACK_D = KeyEvent.VK_D;
	public static final int SPELL_ATTACK_F = KeyEvent.VK_F;
	public static final int SPELL_BUFF_Q = KeyEvent.VK_Q;
	public static final int SPELL_BUFF_W = KeyEvent.VK_W;
	public static final int SPELL_BUFF_E = KeyEvent.VK_E;
	public static final int SPELL_BUFF_R = KeyEvent.VK_R;
	public static final int ITEM_PICKUP = KeyEvent.VK_Z;
	public static final int JUMP = KeyEvent.VK_ALT;
	public static final int MOVE_LEFT = KeyEvent.VK_LEFT;
	public static final int MOVE_RIGHT = KeyEvent.VK_RIGHT;
	public static final int MOVE_UP = KeyEvent.VK_UP;
	public static final int MOVE_DOWN = KeyEvent.VK_DOWN;
	public static final int ITEM_0 = KeyEvent.VK_0;
	public static final int ITEM_1 = KeyEvent.VK_1;
	public static final int ITEM_2 = KeyEvent.VK_2;
	public static final int ITEM_3 = KeyEvent.VK_3;
	public static final int ITEM_4 = KeyEvent.VK_4;
	public static final int ITEM_5 = KeyEvent.VK_5;
	public static final int ITEM_6 = KeyEvent.VK_6;
	public static final int ITEM_7 = KeyEvent.VK_7;
	public static final int ITEM_8 = KeyEvent.VK_8;
	public static final int ITEM_9 = KeyEvent.VK_9;
	public static final int SPACE = KeyEvent.VK_SPACE;
	
	public static void action(int type, int delay){
		switch(type){
		case Defines.WEAPON_ATTACK:
			keyPress(WEAPON_ATTACK, delay);
			break;
		case Defines.SPELL_ATTACK_A:
			keyPress(SPELL_ATTACK_A, delay);
			break;
		case Defines.SPELL_ATTACK_S:
			keyPress(SPELL_ATTACK_S, delay);
			break;
		case Defines.SPELL_ATTACK_D:
			keyPress(SPELL_ATTACK_D, delay);
			break;
		case Defines.SPELL_ATTACK_F:
			keyPress(SPELL_ATTACK_F, delay);
			break;
		case Defines.SPELL_BUFF_Q:
			keyPress(SPELL_BUFF_Q, delay);
			break;
		case Defines.SPELL_BUFF_W:
			keyPress(SPELL_BUFF_W, delay);
			break;
		case Defines.SPELL_BUFF_E:
			keyPress(SPELL_BUFF_E, delay);
			break;
		case Defines.SPELL_BUFF_R:
			keyPress(SPELL_BUFF_R, delay);
			break;
		case Defines.ITEM_PICKUP:
			keyPress(ITEM_PICKUP, delay);
			break;
		case Defines.JUMP_UP:
			keyPress(JUMP, delay);
			break;
		case Defines.JUMP_RIGHT:
			directional_jump(Defines.direction.RIGHT);
			break;
		case Defines.JUMP_LEFT:
			directional_jump(Defines.direction.LEFT);
			break;
		case Defines.JUMP_DOWN:
			directional_jump(Defines.direction.DOWN);
			break;
		case Defines.MOVE_LEFT:
			keyPress(MOVE_LEFT, delay);
			break;
		case Defines.MOVE_RIGHT:
			keyPress(MOVE_RIGHT, delay);
			break;
		case Defines.MOVE_UP:
			keyPress(MOVE_UP, delay);
			break;
		case Defines.MOVE_DOWN:
			keyPress(MOVE_DOWN, delay);
			break;
		case Defines.ITEM_0:
			keyPress(ITEM_0, delay);
			break;
		case Defines.ITEM_1:
			keyPress(ITEM_1, delay);
			break;
		case Defines.ITEM_2:
			keyPress(ITEM_2, delay);
			break;
		case Defines.ITEM_3:
			keyPress(ITEM_3, delay);
			break;
		case Defines.ITEM_4:
			keyPress(ITEM_4, delay);
			break;
		case Defines.ITEM_5:
			keyPress(ITEM_5, delay);
			break;
		case Defines.ITEM_6:
			keyPress(ITEM_6, delay);
			break;
		case Defines.ITEM_7:
			keyPress(ITEM_7, delay);
			break;
		case Defines.ITEM_8:
			keyPress(ITEM_8, delay);
			break;
		case Defines.ITEM_9:
			keyPress(ITEM_9, delay);
			break;
		case Defines.SPACE:
			keyPress(SPACE, delay);
			break;
		case Defines.TELEPORT_UP:
			Luminous_teleport(Defines.direction.UP);
			break;
		case Defines.TELEPORT_RIGHT:
			Luminous_teleport(Defines.direction.RIGHT);
			break;
		case Defines.TELEPORT_LEFT:
			Luminous_teleport(Defines.direction.LEFT);
			break;
		case Defines.TELEPORT_DOWN:
			Luminous_teleport(Defines.direction.DOWN);
			break;
		default:
			System.out.println("Unrecognized Command");
		}
	}
	
	private static void keyPress(int key){
		try{
			Robot r = new Robot();
			r.keyPress(key);
			Defines.delay(Defines.KEY_PRESS_MS_GENERAL);
			r.keyRelease(key);
		} catch(AWTException e){
			e.printStackTrace();
		}
	}
	
	private static void keyPress(int key, int override_delay){
		try{
			Robot r = new Robot();
			r.keyPress(key);
			Defines.delay(override_delay);
			r.keyRelease(key);
		} catch(AWTException e){
			e.printStackTrace();
		}
	}
	
	private static void directional_jump(Defines.direction direction){
		int key_to_use = 0;
		switch(direction){
		case DOWN:
			key_to_use = MOVE_DOWN;
			break;
		case LEFT:
			key_to_use = MOVE_LEFT;
			break;
		case RIGHT:
			key_to_use = MOVE_RIGHT;
			break;
		}
		try{
			Robot r = new Robot();
			r.keyPress(key_to_use);
			r.keyPress(JUMP);
			Defines.delay(Defines.KEY_PRESS_MS_GENERAL);
			r.keyRelease(JUMP);
			r.keyRelease(key_to_use);
		} catch(AWTException e){
			e.printStackTrace();
		}
	}
	
	private static void Luminous_teleport(Defines.direction direction){
		if(Defines.CURRENT_CHARCTER == Defines.LUMINOUS){
			int key_to_use = 0;
			switch(direction){
			case UP:
				key_to_use = MOVE_UP;
				break;
			case DOWN:
				key_to_use = MOVE_DOWN;
				break;
			case LEFT:
				key_to_use = MOVE_LEFT;
				break;
			case RIGHT:
				key_to_use = MOVE_RIGHT;
				break;
			}
			try{
				Robot r = new Robot();
				r.keyPress(SPELL_BUFF_Q);
				r.keyPress(key_to_use);
				Defines.delay(Defines.KEY_PRESS_MS_GENERAL);
				r.keyRelease(key_to_use);
				r.keyRelease(SPELL_BUFF_Q);
			} catch(AWTException e){
				e.printStackTrace();
			}
		}
	}
}
