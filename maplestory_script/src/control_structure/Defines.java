package control_structure;

public class Defines {
	//general defaults
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int RIGHT = 2;
	public static final int LEFT = 3;
	
	//key variables
	public static final int KEY_PRESS_MS_GENERAL = 80;
	
	//character defines
	//0 - general
	//1 - luminous
	public static final int GENERAL_CHARACTER = 0;
	public static final int LUMINOUS = 1;
	
	public static final int CURRENT_CHARCTER = LUMINOUS;

	//general character controls
	public static final int WEAPON_ATTACK = 1;
	public static final int SPELL_ATTACK_A = 2;
	public static final int SPELL_ATTACK_S = 3;
	public static final int SPELL_ATTACK_D = 4;
	public static final int SPELL_ATTACK_F = 5;
	public static final int SPELL_BUFF_Q = 6;
	public static final int SPELL_BUFF_W = 7;
	public static final int SPELL_BUFF_E = 8;
	public static final int SPELL_BUFF_R = 9;
	public static final int ITEM_PICKUP = 10;
	public static final int JUMP_UP = 11;
	public static final int JUMP_RIGHT = 12;
	public static final int JUMP_LEFT = 13;
	public static final int JUMP_DOWN = 14;
	public static final int MOVE_LEFT = 15;
	public static final int MOVE_RIGHT = 16;
	public static final int MOVE_UP = 17;
	public static final int MOVE_DOWN = 18;
	public static final int ITEM_0 = 19;
	public static final int ITEM_1 = 20;
	public static final int ITEM_2 = 21;
	public static final int ITEM_3 = 22;
	public static final int ITEM_4 = 23;
	public static final int ITEM_5 = 24;
	public static final int ITEM_6 = 25;
	public static final int ITEM_7 = 26;
	public static final int ITEM_8 = 27;
	public static final int ITEM_9 = 28;
		
	
	//custom character controls
	//luminous - teleport on q
	public static final int TELEPORT_UP = 29;
	public static final int TELEPORT_RIGHT = 30;
	public static final int TELEPORT_LEFT = 31;
	public static final int TELEPORT_DOWN = 32;
	
	
	//functions
	public static void delay(long milliseconds){
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() - start < milliseconds);
	}
}
