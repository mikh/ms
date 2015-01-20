package data_parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import data_structure.data_entry;

public class Parser {

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
	
	
	public static ArrayList<data_entry> parse(String filename){
		ArrayList<data_entry> data = new ArrayList<data_entry>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = "";
			int state = 0;
			data_entry d = new data_entry();
		
			ArrayList<String> entries = new ArrayList<String>();
			String prev_line = "&&&&&&&&&&&&&";
			ArrayList<String> cur_entry = new ArrayList<String>();
			while((line = br.readLine()) != null){
				line = line.trim();
				if(line.length() > 0){
					if((prev_line.equals("&&&&&&&&&&&&&")) || (line.length() < prev_line.length()) || (!line.substring(0, prev_line.length()).equals(prev_line))){
						cur_entry.add(line);
						prev_line = line;
					} else{
						String name = cur_entry.get(cur_entry.size()-1);
						cur_entry.remove(cur_entry.size()-1);
						String entry = "";
						for(int jj = 0; jj < cur_entry.size(); jj++)
							entry += (cur_entry.get(jj) + "\n");
						cur_entry.clear();
						cur_entry.add(name);
						entries.add(entry);
					}
				}
			}
			String entry = "";
			for(int jj = 0; jj < cur_entry.size(); jj++)
				entry += (cur_entry.get(jj) + "\n");
			entries.add(entry);
			
			String seperator = "////////////////////////////////////////////////////////////////////////////////////\n\n\n";
			BufferedWriter bw = new BufferedWriter(new FileWriter("seperated_data.txt", false));
			
			for(String e : entries){
				bw.write(e + "\n\n\n" + seperator);
			}
			bw.close();
				
			br.close();
			br = new BufferedReader(new FileReader("seperated_data.txt"));
			boolean name_set = false, in_category = false;
			ArrayList<String> parts = new ArrayList<String>();
			while((line = br.readLine()) != null){
				line = line.trim();
				if(line.startsWith("Category:")){
					in_category = true;
				}
				if(in_category && line.length() > 0){
					char first_char = line.charAt(0);
					if(first_char >= '0' && first_char <= '9')
						in_category = false;
				}
				if(line.length() > 0 && !in_category){
					if(line.equals(seperator.trim())){
						if(name_set){
							name_set = false;
							if(parts.size() >= 8){
								if(parts.get(0).equals("Category:"))
									System.out.println("Category here");
								d.qty = Integer.parseInt(parts.get(0));
								d.bundle = Integer.parseInt(parts.get(1));
								d.price = Double.parseDouble(parts.get(2).replace(",", ""));
								d.channel = Integer.parseInt(parts.get(3));
								d.room = Integer.parseInt(parts.get(4));
								d.shop = "";
								for(int rr = 5; rr < parts.size()-2; rr++){
									d.shop += parts.get(rr);
								}
								d.owner = parts.get(parts.size()-2);
								d.percent = parts.get(parts.size()-1);
								data.add(d);
								d = new data_entry();
								parts.clear();
							}
						}
					} else{
						if(!name_set){
							d.item_name = line;
							name_set = true;
						} else{
							int kk = 1;
							while(kk != -1){
								kk = line.indexOf(" ");
								int tt = line.indexOf("\t");
								if(kk == -1 || (tt != -1 && tt < kk))
									kk = tt;
								if(kk != -1){
									if(line.substring(0,kk).trim().length() > 0)
										parts.add(line.substring(0,kk).trim());
									line = line.substring(kk).trim();
								}
							}
							if(line.trim().length() > 0)
								parts.add(line.trim());							
						}
					}
				}
			}
				
			br.close();
			
			
		} catch(IOException e){
			e.printStackTrace();
		}
		return data;
	}
}

