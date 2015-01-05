package moveset;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import maple.Script;
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
	
	public static void action(int type, int delay){
		switch(type){
		case Defines.WEAPON_ATTACK:
			keyPress(WEAPON_ATTACK);
			break;
		case Defines.SPELL_ATTACK_A:
			keyPress(SPELL_ATTACK_A);
			break;
		case Defines.SPELL_ATTACK_S:
			keyPress(SPELL_ATTACK_S);
			break;
		case Defines.SPELL_ATTACK_D:
			keyPress(SPELL_ATTACK_D);
			break;
		case Defines.SPELL_ATTACK_F:
			keyPress(SPELL_ATTACK_F);
			break;
		case Defines.SPELL_BUFF_Q:
			keyPress(SPELL_BUFF_Q);
			break;
		case Defines.SPELL_BUFF_W:
			keyPress(SPELL_BUFF_W);
			break;
		case Defines.SPELL_BUFF_E:
			keyPress(SPELL_BUFF_E);
			break;
		case Defines.SPELL_BUFF_R:
			keyPress(SPELL_BUFF_R);
			break;
		case Defines.ITEM_PICKUP:
			keyPress(ITEM_PICKUP);
			break;
		case Defines.JUMP_UP:
			keyPress(JUMP);
			break;
		case Defines.JUMP_RIGHT:
			keyPress(some_key);
			break;
		case Defines.JUMP_LEFT:
			keyPress(some_key);
			break;
		case Defines.JUMP_DOWN:
			keyPress(some_key);
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
			keyPress(ITEM_0);
			break;
		case Defines.ITEM_1:
			keyPress(ITEM_1);
			break;
		case Defines.ITEM_2:
			keyPress(ITEM_2);
			break;
		case Defines.ITEM_3:
			keyPress(ITEM_3);
			break;
		case Defines.ITEM_4:
			keyPress(ITEM_4);
			break;
		case Defines.ITEM_5:
			keyPress(ITEM_5);
			break;
		case Defines.ITEM_6:
			keyPress(ITEM_6);
			break;
		case Defines.ITEM_7:
			keyPress(ITEM_7);
			break;
		case Defines.ITEM_8:
			keyPress(ITEM_8);
			break;
		case Defines.ITEM_9:
			keyPress(ITEM_9);
			break;
		case Defines.TELEPORT_UP:
			Luminous_teleport(Defines.UP);
			break;
		case Defines.TELEPORT_RIGHT:
			Luminous_teleport(Defines.RIGHT);
			break;
		case Defines.TELEPORT_LEFT:
			Luminous_teleport(Defines.LEFT);
			break;
		case Defines.TELEPORT_DOWN:
			Luminous_teleport(Defines.DOWN);
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
	
	private static void Luminous_teleport(int direction){
		if(Defines.CURRENT_CHARCTER == Defines.LUMINOUS){
			int key_to_use = 0;
			switch(direction){
			case Defines.UP:
				key_to_use = MOVE_UP;
				break;
			case Defines.DOWN:
				key_to_use = MOVE_DOWN;
				break;
			case Defines.LEFT:
				key_to_use = MOVE_LEFT;
				break;
			case Defines.RIGHT:
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
