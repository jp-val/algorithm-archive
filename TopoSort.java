// JP Valdespino
// August 20201

// TopoSort.java
// =============
// Topological Sort

import java.util.*;

public class TopoSort
{
	public static void topoSort(boolean[][] g)
	{
		int[] incoming = new int[g.length];
		int cnt = 0;

		// Counts the number of incoming edges incident to each vertex.
		for (int i = 0; i < g.length; i++)
			for (int j = 0; j < g.length; j++)
				incoming[j] += (g[i][j] ? 1 : 0);

		Queue<Integer> q = new ArrayDeque<Integer>();

		// Adds any vertex with zero incoming edges to the queue.
		for (int i = 0; i < g.length; i++)
			if (incoming[i] == 0)
				q.add(i);

		while (!q.isEmpty())
		{
			// Pulls a vertex out of the queue and processes it.
			int node = q.remove();
			System.out.println(node);

			// Increments the count of unique vertices we've visited.
			++cnt;

			// Decrements the number of incoming edges of neighboring vertices.
			// It also adds any nodes with zero incoming edges to the queue.
			for (int i = 0; i < g.length; i++)
				if (g[node][i] && --incoming[i] == 0)
					q.add(i);
		}

		// If the number of visited vertices does not equal the number of vertices 
		// in the graph then there's a cycle and the topological sort is invalid.
		if (cnt != g.length)
			System.out.println("Error: Graph contains a cycle!");
	}
	
	public static void main(String[] args)
	{
		// Directed graph without any cycles.
		boolean[][] g = { { false, true, true, true, false, false, false, false, false },
						  { false, false, false, false, true, false, false, false, false },
						  { false, false, false, false, false, true, false, false, false },
						  { false, false, false, false, false, true, false, false, false },
						  { false, false, false, false, false, false, false, true, false },
						  { false, false, false, false, false, false, true, false, true },
						  { false, false, false, false, false, false, false, false, false },
						  { false, false, false, false, false, false, false, false, true },
						  { false, false, false, false, false, false, false, false, false } };

		topoSort(g);
	}
}