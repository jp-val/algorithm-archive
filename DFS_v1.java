// JP Valdespino
// August 2021

// DFS_v1.java
// ===========
// Depth First Search Version 1: Recursive implementation, clean and simple, 
// but recursion can be slow.

import java.util.*;

public class DFS_v1
{
	public static void dfs(boolean[][] g, int start) // Wrapper Function
	{
		boolean[] visited = new boolean[g.length];
		dfs(g, start, visited);
	}

	private static void dfs(boolean[][] g, int node, boolean[] visited)
	{
		// Mark current node as visited to avoid an infinite loop.
		visited[node] = true;
		
		// Process the current node.
		System.out.println(node);
		
		// Calls DFS on all unvisited neighbors.
		for (int i = 0; i < g.length; i++)
			if (g[node][i] && !visited[i])
				dfs(g, i, visited);
	}

	public static void main(String[] args)
	{
		// Undirected graph.
		boolean[][] g = { { false, true, false, false, false, true },
						  { false, false, true, true, false, false },
						  { false, true, false, true, false, true },
						  { false, true, false, false, true, false },
						  { false, false, false, true, false, true },
						  { true, false, true, false, true, false } };
		
		dfs(g, 0);
	}
}