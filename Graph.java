import java.lang.Object;
import edu.princeton.cs.algs4.StdRandom;			// Princeton StdRandom library

public class Graph {
	
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int V;
	private int E;
	private boolean[][] adj;
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		this.adj = new boolean[V][V];
	}
	
	public Graph(int V, int E) {
		this(V);
		
		while(this.E != E) {
			int v = StdRandom.uniformInt(V);		
			int w = StdRandom.uniformInt(V);
			addEdge(v,w);
		}
	}
	
	public void addEdge(int v, int w) {
		if (!adj[v][w]) E++;
		adj[v][w] = true;
		adj[w][v] = true;
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}

	
	public boolean contains(int v, int w) {
		return adj[v][w];
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for(int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (boolean w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	public boolean[] toArray(int v) {
		return adj[v];
	}
}


