// JP Valdespino
// August 20201

// TopoSort.java
// =============
// Topological Sort

import java.io.File;
import java.io.IOException;

import java.util.Queue;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class TopoSort
{
	public static void topoSort(int[][] g)
	{
		int[] incoming = new int[g.length];
		int cnt = 0;

		// Counts the number of incoming edges incident to each vertex.
		for (int i = 0; i < g.length; i++)
			for (int j = 0; j < g.length; j++)
				incoming[j] += (g[i][j] < Graph.oo) ? 1 : 0;

		Queue<Integer> q = new ArrayDeque<Integer>();

		// Adds any vertex with zero incoming edges to the queue.
		for (int i = 0; i < g.length; i++)
			if (incoming[i] == 0)
				q.add(i);

		while (!q.isEmpty())
		{
			// Pulls a vertex out of the queue and processes it.
			int vertex = q.remove();
			System.out.println(vertex);

			// Increments the count of unique vertices we've visited.
			++cnt;

			// Decrements the number of incoming edges of neighboring vertices.
			// It also adds any nodes with zero incoming edges to the queue.
			for (int i = 0; i < g.length; i++)
				if (g[vertex][i] < Graph.oo && --incoming[i] == 0)
					q.add(i);
		}

		// If the number of visited vertices does not equal the number of vertices 
		// in the graph then there's a cycle and the topological sort is invalid.
		if (cnt != g.length)
			System.out.println("Error: Graph contains a cycle!");
	}

	public static void topoSort(ArrayList<ArrayList<Edge>> g)
	{
		int[] incoming = new int[g.size()];
		int cnt = 0;

		// Counts the number of incoming edges incident to each vertex.
		for (int i = 0; i < g.size(); i++)
			for (Edge e: g.get(i))
				incoming[e.vertex]++;

		Queue<Integer> q = new ArrayDeque<Integer>();

		// Adds any vertex with zero incoming edges to the queue.
		for (int i = 0; i < g.size(); i++)
			if (incoming[i] == 0)
				q.add(i);

		while (!q.isEmpty())
		{
			// Pulls a vertex out of the queue and processes it.
			int vertex = q.remove();
			System.out.println(vertex);

			// Increments the count of unique vertices we've visited.
			++cnt;

			// Decrements the number of incoming edges of neighboring vertices.
			// It also adds any nodes with zero incoming edges to the queue.
			for (Edge e: g.get(vertex))
				if (--incoming[e.vertex] == 0)
					q.add(e.vertex);
		}

		// If the number of visited vertices does not equal the number of vertices 
		// in the graph then there's a cycle and the topological sort is invalid.
		if (cnt != g.size())
			System.out.println("Error: Graph contains a cycle!");
	}
	
	public static void main(String[] args) throws IOException
	{
		Graph g = new Graph("graph-directed-tree.in");

		topoSort(g.adjMatrix);
		System.out.println();
		topoSort(g.adjList);
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