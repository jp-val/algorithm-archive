// JP Valdespino
// August 2021

// ContainsCycle.java
// ==================

import java.io.File;
import java.io.IOException;

import java.util.Queue;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class ContainsCycle
{
	public static boolean containsCycle(int[][] g)
	{
		boolean[] visited = new boolean[g.length];
		Queue<Integer> q = new LinkedList<Integer>();

		q.add(0);

		while (!q.isEmpty())
		{
			Integer vertex = q.poll();
			
			if (visited[vertex])
				return true;
			else
				visited[vertex] = true;

			for (int i = 0; i < g.length; i++)
				if (g[vertex][i] < Graph.oo)
					q.add(i);
		}

		return false;
	}

	public static boolean containsCycle(ArrayList<ArrayList<Edge>> g)
	{
		boolean[] visited = new boolean[g.size()];
		Queue<Integer> q = new LinkedList<Integer>();

		q.add(0);

		while (!q.isEmpty())
		{
			Integer vertex = q.poll();
			
			if (visited[vertex])
				return true;
			else
				visited[vertex] = true;

			for (Edge e: g.get(vertex))
				q.add(e.vertex);
		}

		return false;
	}

	public static void main(String[] args) throws IOException
	{
		Graph g1 = new Graph("graph-undirected-petersen.in");
		Graph g2 = new Graph("graph-undirected-tree.in");

		System.out.println("contains cycle petersen graph (adjMatrix): " + containsCycle(g1.adjMatrix));
		System.out.println("contains cycle petersen graph (adjList): " + containsCycle(g1.adjList));
		System.out.println("contains cycle tree graph (adjMatrix): " + containsCycle(g2.adjMatrix));
		System.out.println("contains cycle tree graph (adjList): " + containsCycle(g2.adjList));
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