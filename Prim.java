// JP Valdespino
// August 2021

// Prim.java
// =========
// Minimum Spanning Tree Algorithm
// a set of edges that connects all the nodes with the minimum cost of traversal

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim
{
	public static Edge2[] prim(int[][] g)
	{
		PriorityQueue<Edge2> minheap = new PriorityQueue<>();

		for (int i = 0; i < g.length; i++)
			if (g[0][i] < Graph.oo)
				minheap.add(new Edge2(0, i, g[0][i]));

		ArrayList<Edge2> mst = new ArrayList<>();

		boolean[] inMST = new boolean[g.length];
		inMST[0] = true;

		while (!minheap.isEmpty())
		{
			Edge2 e = minheap.poll();

			if (inMST[e.toVertex]) continue;
			inMST[e.toVertex] = true;

			mst.add(e);

			for (int i = 0; i < g.length; i++)
				if (g[e.toVertex][i] < Graph.oo)
					minheap.add(new Edge2(e.toVertex, i, g[e.toVertex][i]));
		}

		return mst.toArray(new Edge2[mst.size()]);
	}

	public static Edge2[] prim(ArrayList<ArrayList<Edge>> g)
	{
		PriorityQueue<Edge2> minheap = new PriorityQueue<>();

		for (Edge e: g.get(0))
				minheap.add(new Edge2(0, e.vertex, e.weight));

		ArrayList<Edge2> mst = new ArrayList<>();

		boolean[] inMST = new boolean[g.size()];
		inMST[0] = true;

		while (!minheap.isEmpty())
		{
			Edge2 e = minheap.poll();

			if (inMST[e.toVertex]) continue;
			inMST[e.toVertex] = true;

			mst.add(e);

			for (Edge e2: g.get(e.toVertex))
				minheap.add(new Edge2(e.toVertex, e2.vertex, e2.weight));
		}

		return mst.toArray(new Edge2[mst.size()]);
	}

	public static void main(String[] args) throws Exception
	{
		Graph g = new Graph("graph-undirected-petersen.in");
		
		System.out.println(Arrays.toString(prim(g.adjMatrix)));
		System.out.println();
		System.out.println(Arrays.toString(prim(g.adjList)));
	}
}

// Edge object for Prim's algorithm.
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