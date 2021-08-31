// JP Valdespino
// August 2021

// Kruskal.java
// ============
// Minimum Spanning Tree Algorithm
// a set of edges that connects all the vertices with the minimum cost of traversal

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Kruskal
{
	public static Edge2[] kruskal(int[][] g)
	{
		DisjointSet set = new DisjointSet(g.length);
		PriorityQueue<Edge2> minheap = new PriorityQueue<>();
		
		for (int i = 0; i < g.length; i++)
			for (int j = 0; j < g.length; j++)
				if (g[i][j] < Graph.oo)
					minheap.add(new Edge2(i, j, g[i][j]));
		
		ArrayList<Edge2> mst = new ArrayList<>();
		while (!minheap.isEmpty() && set.getSetCount() > 1)
		{
			Edge2 e = minheap.poll();
			if (set.union(e.fromVertex, e.toVertex))
				mst.add(e);
		}

		if (set.getSetCount() > 1)
			System.out.println("Error: disconnected graph.");

		return mst.toArray(new Edge2[mst.size()]);
	}

	public static Edge2[] kruskal(ArrayList<ArrayList<Edge>> g)
	{
		DisjointSet set = new DisjointSet(g.size());
		PriorityQueue<Edge2> minheap = new PriorityQueue<>();

		for (int i = 0; i < g.size(); i++)
			for (Edge e: g.get(i))
				minheap.add(new Edge2(i, e.vertex, e.weight));
		
		ArrayList<Edge2> mst = new ArrayList<>();
		while (!minheap.isEmpty() && set.getSetCount() > 1)
		{
			Edge2 e = minheap.poll();
			if (set.union(e.fromVertex, e.toVertex))
				mst.add(e);
		}

		if (set.getSetCount() > 1)
			System.out.println("Error: disconnected graph.");

		return mst.toArray(new Edge2[mst.size()]);
	}

	public static void main(String[] args) throws Exception
	{
		Graph g = new Graph("graph-undirected-petersen.in");

		System.out.println(Arrays.toString(kruskal(g.adjMatrix)));
		System.out.println();
		System.out.println(Arrays.toString(kruskal(g.adjList)));
	}
}

class Edge2 implements Comparable<Edge2>
{
	public int fromVertex, toVertex, weight;

	public Edge2(int fromVertex, int toVertex, int weight)
	{
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.weight = weight;
	}

	public int compareTo(Edge2 e)
	{
		return this.weight - e.weight;
	}

	public String toString()
	{
		return "{ from: " + this.fromVertex + ", to: " + this.toVertex + ", weight: " + this.weight + " }";
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

class DisjointSet
{
	private int[] parent;
	private int setCount;

	public DisjointSet(int n)
	{
		this.parent = new int[n];
		for (int i = 0; i < n; i++)
			this.parent[i] = i;
		
		this.setCount = n;
	}

	public int find(int v)
	{
		if (this.parent[v] == v) return v;

		int retval = find(this.parent[v]);
		this.parent[v] = retval;

		return retval;
	}

	public boolean union(int v1, int v2)
	{
		int rootv1 = find(v1);
		int rootv2 = find(v2);

		if (rootv1 == rootv2) return false;

		this.parent[rootv2] = rootv1;
		setCount--;

		return true;
	}

	public int getSetCount()
	{
		return this.setCount;
	}
}