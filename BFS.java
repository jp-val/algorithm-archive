// JP Valdespino
// August 2021

// BFS.java
// ========
// Breadth First Search

import java.util.*;

public class BFS
{
	public static void bfs(boolean[][] g, int start)
	{	
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[g.length];

		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty())
		{
			// Remove a node from the queue and process it.
			int node = q.remove();
			System.out.println(node);
			
			// Adds all unvisited neighbors to the queue.
			for (int i = 0; i < g.length; i++)
				if (g[node][i] && !visited[i])
				{
					visited[i] = true;
					q.add(i);
				}
		}
	}

	public static void main(String[] args)
	{
		// Undirected graph.
		boolean[][] g = { { false, true, false, false, false, true },
						  { false, false, true, true, false, false },
						  { false, true, false, true, false, false },
						  { false, true, false, false, true, false },
						  { false, false, false, true, false, true },
						  { true, false, false, false, true, false } };

		bfs(g, 0);
	}
}