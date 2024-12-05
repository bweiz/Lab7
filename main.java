import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("inlab7.txt"));
		Map<String, Integer> statesMap = new HashMap<String, Integer>();
		
		
		int numStates = Integer.parseInt(in.nextLine());
		int connections = Integer.parseInt(in.nextLine());
		
		// -------- Map States to Integers ------------------------
		int i = 0;
		
		ArrayList<String> states = new ArrayList<String>();
		while (in.hasNext()) {
			String[] nextLine = in.nextLine().split(" ");
			if(!states.contains(nextLine[0])) {
				states.add(nextLine[0]);
				i++;
			}
		}
		
		for (int x = 0; x < states.size(); x++) {
			statesMap.put(states.get(x), x);
		}
		// --------------------------------------------------------
		
		// ---- Create graph and read in file ---------------------
		Graph statesGraph = new Graph(numStates, numStates);
		
		Scanner in2 = new Scanner(new File("inlab7.txt"));
		
		in2.nextLine();
		in2.nextLine();
		while(in2.hasNext()) {
			String[] nextLine2 = in2.nextLine().split(" ");
			for(int z = 1; z < nextLine2.length; z++) {
				statesGraph.addEdge(statesMap.get(nextLine2[0]), statesMap.get(nextLine2[z]));
			}
		}
		// --------------------------------------------------------
		
		// -------  Write to output file --------------------------
		try {
			
			
			
			BufferedWriter writer = new  BufferedWriter(new FileWriter("lab7_output.txt"));
			for(int y = 0; y < numStates; y++) {
				StringBuilder str = new StringBuilder();
				for(Entry<String, Integer> entry : statesMap.entrySet()) {
					if(entry.getValue().equals(y)) {
						str.append(entry.getKey());
						for(int a = str.length(); a < 20; a++) {
							str.append(" ");
						}
						str.append(": ");
					}
				}
				for(int s = 0; s < numStates; s++) {
					if(statesGraph.contains(y, s)) {
						for(Entry<String, Integer> entry2 : statesMap.entrySet()) {
							if(entry2.getValue().equals(s)) {
								str.append(entry2.getKey() + " ");
							}
						}
					}
				}
				
				writer.write(str.toString());
				writer.write("\n");
			}
			writer.write("\n");
			writer.close();	
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
		
		// ------------------------------------------------------
		
		// ------ Breadth First Search to find distTo > 1, < 4 --
		try {
			BufferedWriter writer2 = new BufferedWriter(new FileWriter("lab7_output2.txt"));
			BreadthFirstSearch bfs = new BreadthFirstSearch(statesGraph, statesMap.get("Montana"));
			for(int s : statesMap.values()) {
				if (bfs.hasPathTo(s) && bfs.distTo(s) > 1 && bfs.distTo(s) < 4) {
					StringBuilder str = new StringBuilder();
					str.append("Montana to ");
					for(Entry<String, Integer> entry : statesMap.entrySet()) {
						if(entry.getValue().equals(s)) {
							str.append(entry.getKey());
						}
					}
					for(int a = str.length(); a < 30; a++) {
						str.append(" ");
					}
					str.append("(" + bfs.distTo(s) + "): ");
					for(int q = bfs.distTo(s); q > -1; q--) {
						for(Entry<String, Integer> entry : statesMap.entrySet()) {
							if(entry.getValue().equals(bfs.pathTo(s).get(q))) {
								str.append(entry.getKey());
								if(q > 0) {
									str.append(" - ");
								}
							}
						}
						
					}
						writer2.write(str.toString() + "\n");
				}
			}
		
			writer2.close();
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}

	}
}
