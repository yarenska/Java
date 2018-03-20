package dijkstra;

import java.util.ArrayList;

public class Node {
	private int name;
	private boolean visited;
	private int distanceFromSource = Integer.MAX_VALUE;
	private ArrayList<Edge> nodesEdges = new ArrayList<Edge>();
	private int numberEdges;
	private int x,y;
	
	public Node(int name, int x, int y){
		this.name = name;
		this.x = x;
		this.y = y;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
