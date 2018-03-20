package dijkstra;

import java.util.ArrayList;

public class Node {
	private int name;
	private boolean visited;
	private int distanceFromSource = Integer.MAX_VALUE;
	private ArrayList<Edge> nodesEdges = new ArrayList<Edge>();
	private int numberEdges;
	
	public Node(int name){
		this.name = name;
		visited = false;
		}
	
	public boolean isVisited(){
		return visited;
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	public int getDistanceFromSource() {
		return distanceFromSource;
	}

	public void setDistanceFromSource(int distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}

	public ArrayList<Edge> getNodesEdges() {
		return nodesEdges;
	}
	
	public void addEdge(Edge e){
		nodesEdges.add(e);
		numberEdges++;
		
	}
	
	public int getName(){
		return name;
	}
	
	public int getNumberEdges(){
		return numberEdges;
	}
}
