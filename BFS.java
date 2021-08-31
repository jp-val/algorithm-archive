// JP Valdespino
// August 2021

// BFS.java
// ========
// Breadth First Search

import java.io.File;
import java.io.IOException;

import java.util.Queue;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class BFS
{
	public static void bfs(int[][] g, int start)
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
				if (!visited[i] && g[node][i] < Graph.oo)
				{
					visited[i] = true;
					q.add(i);
				}
		}
	}

	public static void bfs(ArrayList<ArrayList<Edge>> g, int start)
	{
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[g.size()];

		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty())
		{
			// Remove a node from the queue and process it.
			int node = q.remove();
			System.out.println(node);
			
			// Adds all unvisited neighbors to the queue.
			for (Edge e: g.get(node))
				if (!visited[e.vertex] && e.weight < Graph.oo)
				{
					visited[e.vertex] = true;
					q.add(e.vertex);
				}
		}
	}

	public static void main(String[] args) throws IOException
	{
		Graph g = new Graph("graph-undirected-petersen.in");
		
		bfs(g.adjMatrix, 0);
		System.out.println();
		bfs(g.adjList, 0);
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
	public static final int oo = (int)1e9; // Infinity

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