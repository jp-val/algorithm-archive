// JP Valdespino
// August 2021

// DFS_v2.java
// ===========
// Depth First Search Version 2: Iterative implementation, simulating recursion 
// using a Stack.

import java.util.*;

public class DFS_v2
{
	public static void dfs(boolean[][] g, int start)
	{
		boolean[] visited = new boolean[g.length];
		Stack<Integer> s = new Stack<Integer>();

		// Add start node to stack.
		s.push(start);
		visited[start] = true;
		
		while(!s.isEmpty())
		{
			// Pop a node off the stack and process it.
			int node = s.pop();
			System.out.println(node);

			// Add unvisited neighbors to the Stack.
			for (int i = 0; i < g[node].length; i++)
				if (g[node][i] && !visited[i])
				{
					s.push(i);
					visited[i] = true;
				}
		}
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