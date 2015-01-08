package control_structure;

public class Defines {
	//general defaults
	public static enum direction{UP, DOWN, RIGHT, LEFT};
	public static enum thread_class{KEY_PRESS_THREAD, SUCCESSIVE_KEY_PRESS_THREAD};
	public static final int MINUTE = 60000;

	
	//behavior defines
	public static enum behaviors{NONREACTIVE}; 
	public static enum behavior_variations{NONREACTIVE_NO_VERTICAL, NONREACTIVE_VERTICAL_TELEPORT, NONREACTIVE_FULL_RANGE};
	
	public static final behaviors ACTIVE_BEHAVIOR = behaviors.NONREACTIVE;
	public static final behavior_variations ACTIVE_VARIATION = behavior_variations.NONREACTIVE_FULL_RANGE;
	
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
	
	//nonreactive behavior defines
	public static final int ATTACK_TIMING_MS = 1200;
	public static final int PICKUP_TIMING_MS = 500;
	public static final int DAMAGE_TAKEN_PER_HIT = 85;
	public static final int HITS_PER_MINUTE = 20;
	public static final int HEALTH_PER_POTION = 50;
	public static final int HEALTH_POTION_COOLDOWN = 20;
	
	public static final int MP_USED_PER_ATTACK = 17;
	public static final int MP_PER_POTION = 100;
	public static final int MANA_POTION_COOLDOWN = 20;
	
	
	
	//functions
	public static void delay(long milliseconds){
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() - start < milliseconds);
	}
}
