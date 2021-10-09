// JP Valdespino
// August 2021

// ContainsCycle.java
// ==================
// Different functions for directed and undirected graphs.

// Why different algorithms for unweighted directed and unweighted undirected graphs?
// ----------------------------------------------------------------------------------
// Take a two vertex graph for example, vertex A and B in an undirected graph, 
// an edge connecting these two vertecies does not count as a cycle;
// But in a directed graph, where there's a directed edge from vertex A to vertex B
// and there's another directed edge from vertex B to vertex A, then that does count
// as a cycle, even though these two distinct graphs are practically the same.

import java.io.File;
import java.io.IOException;

import java.util.Queue;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class ContainsCycle
{
	public static boolean containsCycle_forUndirectedGraphs(int[][] g)
	{
		boolean[] visited = new boolean[g.length];
		return containsCycle_forUndirectedGraphs(g, visited, -1, 0);
	}

	private static boolean containsCycle_forUndirectedGraphs(int[][] g, boolean[] visited, int parent, int vertex)
	{
		if (visited[vertex]) return true;

		visited[vertex] = true;

		for (int i = 0; i < g.length; i++)
			if (g[vertex][i] < Graph.oo && i != parent)
				if (containsCycle_forUndirectedGraphs(g, visited, vertex, i))
					return true;

		return false;
	}

	public static boolean containsCycle_forUndirectedGraphs(ArrayList<ArrayList<Edge>> g)
	{
		boolean[] visited = new boolean[g.size()];
		return containsCycle_forUndirectedGraphs(g, visited, -1, Graph.oo, 0);
	}

	private static boolean containsCycle_forUndirectedGraphs(ArrayList<ArrayList<Edge>> g, boolean[] visited, int parent, int weight, int vertex)
	{
		if (visited[vertex]) return true;

		visited[vertex] = true;

		for (Edge e: g.get(vertex))
			if (!(e.vertex == parent && e.weight == weight))
				if (containsCycle_forUndirectedGraphs(g, visited, vertex, e.weight, e.vertex))
					return true;

		return false;
	}

	public static boolean containsCycle_forDirectedGraphs(int[][] g)
	{
		int[] incoming = new int[g.length];

		for (int i = 0; i < g.length; i++)
			for (int j = 0; j < g[i].length; j++)
				if (g[i][j] < Graph.oo)
					incoming[j]++;
		
		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 0; i < incoming.length; i++)
			if (incoming[i] <= 0)
				q.add(i);

		int unprocessed = g.length;
		
		while (!q.isEmpty())
		{
			Integer v = q.remove();
			unprocessed--;

			for (int i = 0; i < g[v].length; i++)
				if (g[v][i] < Graph.oo)
					if (--incoming[i] <= 0)
						q.add(i);
		}

		return unprocessed > 0;
	}

	public static boolean containsCycle_forDirectedGraphs(ArrayList<ArrayList<Edge>> g)
	{
		int[] incoming = new int[g.size()];

		for (int i = 0; i < g.size(); i++)
			for (Edge e: g.get(i))
				incoming[e.vertex]++;
		
		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 0; i < incoming.length; i++)
			if (incoming[i] <= 0)
				q.add(i);

		int unprocessed = g.size();

		while (!q.isEmpty())
		{
			Integer v = q.remove();
			unprocessed--;

			for (Edge e: g.get(v))
				if (--incoming[e.vertex] <= 0)
					q.add(e.vertex);
		}

		return unprocessed > 0;
	}

	public static void main(String[] args) throws IOException
	{
		Graph g1 = new Graph("graphs/graph-undirected-petersen.in");
		Graph g2 = new Graph("graphs/graph-undirected-tree.in");
		Graph g3 = new Graph("graphs/graph-directed-scc.in");

		System.out.println("contains cycle petersen graph (adjMatrix): " + containsCycle_forUndirectedGraphs(g1.adjMatrix));
		System.out.println("contains cycle petersen graph (adjList): " + containsCycle_forUndirectedGraphs(g1.adjList));
		System.out.println("contains cycle tree graph (adjMatrix): " + containsCycle_forUndirectedGraphs(g2.adjMatrix));
		System.out.println("contains cycle tree graph (adjList): " + containsCycle_forUndirectedGraphs(g2.adjList));
		System.out.println("contains cycle directed scc (adjMatrix): " + containsCycle_forDirectedGraphs(g3.adjMatrix));
		System.out.println("contains cycle directed scc graph (adjList): " + containsCycle_forDirectedGraphs(g3.adjList));
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

	public void displayAdjMatrix()
	{
		for (int i = 0; i < this.adjMatrix.length; i++)
			System.out.println(i + ": " + Arrays.toString(this.adjMatrix[i]));
	}

	public static void displayAdjMatrix(int[][] adjMatrix)
	{
		for (int i = 0; i < adjMatrix.length; i++)
			System.out.println(i + ": " + Arrays.toString(adjMatrix[i]));
	}

	public void displayAdjList()
	{
		int i = 0;
		for (ArrayList<Edge> v: this.adjList)
			System.out.println(i++ + ": " + Arrays.toString(v.toArray(new Edge[v.size()])));
	}

	public static void displayAdjList(ArrayList<ArrayList<Edge>> adjList)
	{
		int i = 0;
		for (ArrayList<Edge> v: adjList)
			System.out.println(i++ + ": " + Arrays.toString(v.toArray(new Edge[v.size()])));
	}
}