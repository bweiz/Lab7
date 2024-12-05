import java.util.ArrayList;

public class BreadthFirstSearch {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	private final int source;
	
	public BreadthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		this.source = s;
		bfs(G, s);
	}
	private void bfs(Graph G, int s) {
		Queue<Integer> queue = new Queue<Integer>();
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = INFINITY;
		}
		distTo[s] = 0;
		marked[s] = true;
		queue.enqueue(s);
		while(!queue.isEmpty()) {
			int v = queue.dequeue();
			
			for(int w = 0; w < G.V(); w++) {
				if(G.contains(w, v) && !marked[w]) {
					queue.enqueue(w);
					marked[w] = true;
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
				}
			}
		}
		
		
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public int distTo(int s) {
		return distTo[s];
	}
	
	public ArrayList<Integer> pathTo(int x) {
		if(!hasPathTo(x)) return null;
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		for(int i = x; i != source; i = edgeTo[i]) {
			path.add(i);
		}
		path.add(source);
		return path;
		
	}
}
