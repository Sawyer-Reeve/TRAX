package trax;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

public class GraphTest {

	private static In file = new In("src/resources/graph.txt");
    private static Graph G = new Graph(file);
	
	public static void main(String[] args) {
		BreadthFirstPaths bfp = new BreadthFirstPaths(G, 1);
		System.out.println(bfp.pathTo(65));
		
	}
}
