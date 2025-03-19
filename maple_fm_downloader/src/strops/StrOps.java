package strops;

import java.util.ArrayList;

public class StrOps {
	public static String getFollowing(String str, String pattern){
		int index = str.indexOf(pattern);
		if(index == -1)
			return str;
		return str.substring(index+pattern.length());
	}
	
	public static String getPrevious(String str, int index){
		return str.substring(0, index);
	}
	
	public static ArrayList<Object> findStringWithNumbers(String str, String pattern){
		ArrayList<Object> results = new ArrayList<Object>();
		int num_index = 1, start_pattern_index = 0, start_str_index = 0;
		
		num_index = pattern.indexOf("#",start_pattern_index);
		String start_pattern = "";
		if(num_index == -1){
			start_pattern = pattern;
		} else{
			start_pattern = pattern.substring(0,num_index);
		}
		ArrayList<Integer> matches = new ArrayList<Integer>();
		int str_index = 0;
		while(str_index != -1){
			str_index = str.indexOf(start_pattern, start_str_index);
			if(str_index != -1){
				matches.add(str_index);
				start_str_index = str_index + 1;
			}
		}
		
		if(matches.size() == 0){
			return null;
		}
		
		for(int ii : matches){
			if(num_index == -1){
				results.add(ii);
				results.add(pattern.length());
				results.add(pattern);
				return results;
			}
			int i_start = ii;
			ii += start_pattern.length();
			int pos = start_pattern.length();
			boolean pass = true;
			while(pos < pattern.length()){
				int end_of_number = str.indexOf(" ", ii);
				String number = str.substring(ii, end_of_number);
				number = number.replace(",", "");
				try{
					Integer.parseInt(number);
				} catch(Exception e){
					pass = false;
					break;
				}
				ii = end_of_number;
				pos++;
				num_index = pattern.indexOf("#",pos);
				String subpattern = "";
				if(num_index == -1)
					subpattern = pattern.substring(pos);
				else
					subpattern = pattern.substring(pos,num_index);
				if(!str.substring(ii,ii+subpattern.length()).equals(subpattern)){
					pass = false;
					break;
				}
				pos += subpattern.length();
				ii += subpattern.length();
			}
			if(pass){
				//System.out.println(String.format("Match found at location %d with length %d and text %s",i_start, ii-i_start, str.substring(i_start, ii)));
				results.add(i_start);
				results.add(ii-i_start);
				results.add( str.substring(i_start, ii));
				return results;
			}
		}
		return null;
	}
	
	public static int extractNthNumber(String str, int n){
		int cur_n = 1;
		for(int ii = 0; ii < str.length(); ii++){
			if(Character.isDigit(str.charAt(ii))){
				int index = str.indexOf(" ", ii);
				if(index == -1)
					return -1;
				if(cur_n == n){
					String num = str.substring(ii, index);
					num = num.replace(",", "");
					return Integer.parseInt(num);
				} else{
					ii = index;
					cur_n++;
				}
			}
		}
		return -1;
	}
}
