// JP Valdespino
// August 2021

// DFS_v1.java
// ===========
// Depth First Search Version 1: Recursive implementation, clean and simple, 
// but recursion can be slow.

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class DFS_v1
{
	public static void dfs(int[][] g, int start) // Wrapper Function
	{
		boolean[] visited = new boolean[g.length];
		dfs(g, start, visited);
	}

	private static void dfs(int[][] g, int vertex, boolean[] visited)
	{
		// Mark current node as visited to avoid an infinite loop.
		visited[vertex] = true;
		
		// Process the current node.
		System.out.println(vertex);
		
		// Calls DFS on all unvisited neighbors.
		for (int i = 0; i < g.length; i++)
			if (!visited[i] && g[vertex][i] < Graph.oo)
				dfs(g, i, visited);
	}

	public static void dfs(ArrayList<ArrayList<Edge>> g, int start) // Wrapper Function
	{
		boolean[] visited = new boolean[g.size()];
		dfs(g, start, visited);
	}

	private static void dfs(ArrayList<ArrayList<Edge>> g, int vertex, boolean[] visited)
	{
		visited[vertex] = true;

		System.out.println(vertex);

		for (Edge e: g.get(vertex))
			if (!visited[e.vertex])
				dfs(g, e.vertex, visited);
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