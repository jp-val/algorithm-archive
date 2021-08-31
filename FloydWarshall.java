// JP Valdespino
// August 2021

// FloydWarshall.java
// ==================
// All pairs shortest paths algorithm.

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class FloydWarshall
{
	public static int[][] floydWarshall(int[][] g)
	{
		int n = g.length;
		int[][] sp = new int[n][n];

		// Initialize base cases.
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				sp[i][j] = (i == j) ? 0 : g[i][j];

		// Floyd-Warshall.
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					sp[i][j] = Math.min(sp[i][j], sp[i][k] + sp[k][j]);

		// Negative cycle detection. This is quite cheeky. You probably shouldn't
		// kill the whole program just because you see a negative cycle.
		for (int i = 0; i < n; i++)
			if (sp[i][i] < 0)
				System.out.println("Error: negative cycle detected.");

		return sp;
	}

	public static int[][] floydWarshall(ArrayList<ArrayList<Edge>> g)
	{
		int n = g.size();
		int[][] sp = new int[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				sp[i][j] = (i == j) ? 0 : Graph.oo;

		// Initialize base cases.
		for (int i = 0; i < n; i++)
			for (Edge e: g.get(i))
				sp[i][e.vertex] = (i == e.vertex) ? 0 : e.weight;

		// Floyd-Warshall.
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					sp[i][j] = Math.min(sp[i][j], sp[i][k] + sp[k][j]);

		// Negative cycle detection. This is quite cheeky. You probably shouldn't
		// kill the whole program just because you see a negative cycle.
		for (int i = 0; i < n; i++)
			if (sp[i][i] < 0)
				System.out.println("Error: negative cycle detected.");

		return sp;
	}

	public static void printMatrix(int[][] m)
	{
		for (int i = 0; i < m.length; i++)
			System.out.println(i + ": " + Arrays.toString(m[i]));
	}

	public static void main(String[] args) throws IOException
	{
		Graph g = new Graph("graph-undirected-petersen.in");

		printMatrix(floydWarshall(g.adjMatrix));
		System.out.println();
		printMatrix(floydWarshall(g.adjList));
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