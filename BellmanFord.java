// JP Valdespino
// August 2021

// BellmanFord.java
// ================
// Shortest path algorithm (from one source node to all the other nodes).
// Slower than Dijkstra's but can handle negative edge weights.
// Algorithm breaks if there's a negtaive cycle.

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class BellmanFord
{
	public static int[] bellmanFord(int[][] g, int src)
	{
		int[] dist = new int[g.length];
		Arrays.fill(dist, Graph.oo);
		dist[src] = 0;

		for (int i = 0; i < g.length-1; i++)
			// These two nested loops iterate through all edges in the graph.
			for (int u = 0; u < g.length; u++)
				for (int v = 0; v < g.length; v++)
					if (g[u][v] < Graph.oo)
						dist[v] = Math.min(dist[v], dist[u] + g[u][v]);

		return dist;
	}
	
	public static int[] bellmanFord(ArrayList<ArrayList<Edge>> g, int src)
	{
		int[] dist = new int[g.size()];
		Arrays.fill(dist, Graph.oo);
		dist[src] = 0;

		for (int i = 0; i < g.size()-1; i++)
			// These two nested loops iterate through all edges in the graph.
			for (int j = 0; j < g.size(); j++)
				for (Edge e: g.get(j))
					dist[e.vertex] = Math.min(dist[e.vertex], dist[j] + e.weight);

		return dist;
	}

	public static void main(String[] args) throws IOException
	{
		Graph g = new Graph("graph-undirected-petersen.in");
		
		System.out.println(Arrays.toString(bellmanFord(g.adjMatrix, 0)));
		System.out.println();
		System.out.println(Arrays.toString(bellmanFord(g.adjList, 0)));
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