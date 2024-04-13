package trax;

import edu.princeton.cs.algs4.Queue;

public class GraphTest {

//	private static In file = new In("src/resources/graph.txt");
//    private static Graph G = new Graph(file);
	
	public static void main(String[] args) {
//		BreadthFirstPaths bfp = new BreadthFirstPaths(G, 1);
//		System.out.println(bfp.pathTo(65));
		Queue<String> test = FileIO.getStationList(RailLines.BLUE_LINE);
		for (String s : test)
			System.out.println(s);
		System.out.println();
	}
}
