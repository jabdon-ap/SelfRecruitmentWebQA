package Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {
	
	public static String generateNIN() {
		ArrayList<String> not_Allowed = new ArrayList<> (Arrays.asList("GB","BG","NK","KN","TN", "NT","ZZ"));
		ArrayList<String> first_Allowed = new ArrayList<> (Arrays.asList("A","B","C","E","G","H","J","K","L","M","N","O","P","R", "S","T","W","X","Y","Z"));
		ArrayList<String> second_Allowed = new ArrayList<> (Arrays.asList("A","B","C","E","G","H","J","K","L","M","N","P","R", "S","T","W","X","Y","Z"));
		ArrayList<String> last_Allowed = new ArrayList<> (Arrays.asList("A","B","C","D", ""));

		String group1 = first_Allowed.get (new Random().nextInt(first_Allowed.size())) + second_Allowed.get (new Random().nextInt(second_Allowed.size()));
	
		while(not_Allowed.contains(group1)) {
			group1 = first_Allowed.get (new Random().nextInt(first_Allowed.size())) + second_Allowed.get (new Random().nextInt(second_Allowed.size()));
		}
		String group2 = Integer.toString(new Random().nextInt(100)) ;
		String group3 = Integer.toString(new Random().nextInt(100)) ;
		String group4 = Integer.toString(new Random().nextInt(100)) ;
		String group8 = last_Allowed.get(new Random().nextInt(last_Allowed.size()));
		
		 String result = group1 + group2 + group3 + group4 + group8 ;
		 return result;
	}
	
	public static void loadInfo(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
