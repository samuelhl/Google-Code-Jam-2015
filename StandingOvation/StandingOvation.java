import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StandingOvation {
	
	public static int minFriends(ArrayList<Integer> audience) {
		int i = 1;
		int minFriends = 0;
		int stoodUp = audience.get(0);
		
		while(i < audience.size()) {
			if((audience.get(i) > 0) && (i > stoodUp)) {
				minFriends += i - stoodUp;
				stoodUp += minFriends;
			}
			stoodUp += audience.get(i);
			i++;
		}
		return minFriends;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		int tests = Integer.parseInt(in.readLine());
		int i = 1;
		
		while(tests >= i) {
			ArrayList<Integer> audience = new ArrayList<Integer>();
			Scanner scanner = new Scanner(in.readLine());
			int maxS = scanner.nextInt();
			
			String nums = scanner.next();
			for(int j = 0; j <= maxS; j++) {
				audience.add(Character.getNumericValue(nums.charAt(j)));
			}
			
			out.println("Case #" + i + ": " + minFriends(audience));
			i++;
			scanner.close();
		}
		in.close();
		out.close();
	}
}
