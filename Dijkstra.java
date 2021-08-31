// JP Valdespino
// August 2021

// Dijkstra.java
// =============
// Shortest path algorithm (from one node to all the others).
// Cannot hnadle negative edge weights.

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

// For Dijkstra's algorithm.
class Vertex implements Comparable<Vertex>
{
	public int id, dist;

	public Vertex(int id, int dist)
	{
		this.id = id;
		this.dist = dist;
	}

	public int compareTo(Vertex v)
	{
		return this.dist - v.dist;
	}

	public String toString()
	{
		return "{ id: " + this.id + ", dist: " + this.dist;
	}
}

public class Dijkstra
{
	public static int[] dijkstra(int[][] g, int src)
	{
		boolean[] visited = new boolean[g.length];
		int numVisited = 0;

		int[] dist = new int[g.length];
		Arrays.fill(dist, Graph.oo);
		dist[src] = 0;

		PriorityQueue<Edge> minheap = new PriorityQueue<>();
		minheap.add(new Edge(src, 0));

		while (!minheap.isEmpty() && numVisited < g.length)
		{
			Edge e = minheap.poll();
			if (visited[e.vertex]) continue;

			visited[e.vertex] = true;
			numVisited++;

			for (int i = 0; i < g.length; i++)
			{
				if (!visited[i] && g[e.vertex][i] < Graph.oo && e.weight + g[e.vertex][i] < dist[i])
				{
					dist[i] = e.weight + g[e.vertex][i];
					minheap.add(new Edge(i, dist[i]));
				}
			}
		}

		if (numVisited < g.length)
			System.out.println("Error: disconnected graph.");

		return dist;
	}

	public static int[] dijkstra(ArrayList<ArrayList<Edge>> g, int src)
	{
		boolean[] visited = new boolean[g.size()];
		int numVisited = 0;

		int[] dist = new int[g.size()];
		Arrays.fill(dist, Graph.oo);
		dist[src] = 0;

		PriorityQueue<Edge> minheap = new PriorityQueue<>();
		minheap.add(new Edge(src, 0));

		while (!minheap.isEmpty() && numVisited < g.size())
		{
			Edge e = minheap.poll();
			if (visited[e.vertex]) continue;

			visited[e.vertex] = true;
			numVisited++;

			for (Edge adj: g.get(e.vertex))
			{
				if (e.weight + adj.weight < dist[adj.vertex])
				{
					dist[adj.vertex] = e.weight + adj.weight;
					minheap.add(new Edge(adj.vertex, dist[adj.vertex]));
				}
			}
		}

		if (numVisited < g.size())
			System.out.println("Error: disconnected graph.");

		return dist;
	}

	public static void main(String[] args) throws IOException
	{
		Graph g = new Graph("graph-undirected-petersen.in");

		System.out.println(Arrays.toString(dijkstra(g.adjMatrix, 0)));
		System.out.println();
		System.out.println(Arrays.toString(dijkstra(g.adjList, 0)));
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