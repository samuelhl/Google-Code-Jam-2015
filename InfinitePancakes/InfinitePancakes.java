import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class InfinitePancakes {
	
	public static int minutes(ArrayList<Integer> pancakes) {
		int minutes = 0;
		
		while(totalPancakes(pancakes) > 0) {
			int max = Collections.max(pancakes);
			if(predictSplits(pancakes) && (timeGain(max) > 0)) {
				pancakes.remove(pancakes.indexOf(max));
				pancakes.add((int)Math.ceil(max/2d));
				pancakes.add((int)Math.floor(max/2d));
			} else {
				for(int i = 0; i < pancakes.size(); i++) {
					if(pancakes.get(i) > 0) pancakes.set(i, pancakes.get(i) - 1);
				}
			}
			minutes++;
		}
		
		return minutes;
	}
	
	public static int timeGain(int pancakes) {
		return pancakes - (int)Math.ceil(pancakes/2d) - 1;
	}
	
	public static int totalPancakes(ArrayList<Integer> pancakes) {
		int total = 0;
		for(int p : pancakes) {
			total += p;
		}
		return total;
	}
	
	public static boolean predictSplits(ArrayList<Integer> pancakes) {
		int max = Collections.max(pancakes);
		int ocurrences = Collections.frequency(pancakes, max);
		if((ocurrences + nextMax(pancakes)) < max || (ocurrences + (int)Math.ceil(max/2d) < max)) {
			return true;
		}
		return false;
	}
	
	public static int nextMax(ArrayList<Integer> pancakes) {
		ArrayList<Integer> copy = new ArrayList<Integer>(pancakes);
		int max = Collections.max(copy);
		copy.removeAll(Collections.singleton(max));
		if(copy.isEmpty()) {
			return (int)Math.ceil(max/2d);
		} else {
			return Math.max((int)Math.ceil(max/2d), Collections.max(copy));
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		int tests = Integer.parseInt(in.readLine());
		int i = 1;
		
		while(tests >= i) {
			ArrayList<Integer> pancakes = new ArrayList<Integer>();
			Scanner scanner = new Scanner(in.readLine());
			int diners = scanner.nextInt();
			
			Scanner scanner2 = new Scanner(in.readLine());
			for(int j = 0; j < diners; j++) {
				pancakes.add(scanner2.nextInt());
			}
			
			out.println("Case #" + i + ": " + minutes(pancakes));
			i++;
			scanner.close();
			scanner2.close();
		}
		in.close();
		out.close();
	}
}
