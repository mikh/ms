package data_structure;

public class data_entry {
	/*
	 *data is in the following format:
	 *0- item_name (1 line)
	 *1- item_name (1 line)
	 *2- qty (number)
	 *3- bundle (number)
	 *4- price (number with commas)
	 *5- channel (number)
	 *6- room (number)
	 *7- shop (description - can be multiple lines and use any characters)
	 *8- seller (word)
	 *9- % (number w/ % at end)
	 */
	
	public String item_name, shop, owner, percent;
	public int qty, bundle, channel, room;
	public double price;	
	
	public String print(){
		return String.format("%-50s - %-30s  q.%-5d b.%-5d ch.%-2d rm.%-2d    %15.0f meso\n", item_name, owner, qty, bundle, channel, room, price);
	}
}
