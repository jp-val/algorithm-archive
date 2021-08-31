// JP Valdespino
// August 2021

// DFS_v2.java
// ===========
// Depth First Search Version 2: Iterative implementation, simulating recursion 
// using a Stack.

import java.io.File;
import java.io.IOException;

import java.util.Stack;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class DFS_v2
{
	public static void dfs(int[][] g, int start)
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
				if (!visited[i] && g[node][i] < Graph.oo)
				{
					s.push(i);
					visited[i] = true;
				}
		}
	}

	public static void dfs(ArrayList<ArrayList<Edge>> g, int start)
	{
		boolean[] visited = new boolean[g.size()];
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
			for (Edge e: g.get(node))
				if (!visited[e.vertex])
				{
					s.push(e.vertex);
					visited[e.vertex] = true;
				}
		}
	}

	public static void main(String[] args) throws IOException
	{
		Graph g = new Graph("graph-undirected-petersen.in");

		dfs(g.adjMatrix, 0);
		System.out.println();
		dfs(g.adjList, 0);
	}
}

// Edge object for graph representation utilizing Adjacency List.
class Edge implements Comparable<Edge>
{
	int vertex, weight;

	public Edge(int vertex, int weight)
	{
		this.vertex = vertex;
		this.weight = weight;
	}

	public int compareTo(Edge e)
	{
		return this.weight - e.weight;
	}

	public String toString()
	{
		return "{ v: " + this.vertex + ", w: " + this.weight + " }";
	}
}

class Graph
{
	public static final int oo = (int)1e9;

	// These two strcutures represent the same graph.
	public int[][] adjMatrix;
	public ArrayList<ArrayList<Edge>> adjList;

	public Graph(String filename) throws IOException
	{
		Scanner stdin = new Scanner(new File(filename));

		int n = stdin.nextInt();

		this.adjMatrix = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(this.adjMatrix[i], Graph.oo);

		this.adjList = new ArrayList<>();
		for (int i = 0; i < n; i++)
			this.adjList.add(new ArrayList<Edge>());

		for (int i = 0; i < n; i++)
		{
			int numEdges = stdin.nextInt();
			for (int j = 0; j < numEdges; j++)
			{
				int vertex = stdin.nextInt();
				int weight = stdin.nextInt();
	 			
				this.adjMatrix[i][vertex] = weight;
				this.adjList.get(i).add(new Edge(vertex, weight));
			}
		}

		stdin.close();
	}
}