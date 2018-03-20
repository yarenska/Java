package dijkstra;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Node> unvisiteds = new ArrayList<Node>();
	
	public Graph(ArrayList<Edge> edges, ArrayList<Node> nodes){
		this.edges = edges;
		this.nodes = nodes;
		this.unvisiteds = nodes;
		
		for(Edge e : edges){
			for(int i = 0; i < nodes.size(); i++){
				if(e.getFrom().getName() == nodes.get(i).getName() || e.getTo().getName() == nodes.get(i).getName()){
					nodes.get(i).addEdge(e);
				}
			}
		}
	}
	
	public void DijkstrasAlgorithm(){
		//Source is considered as nodes(0)
		nodes.get(0).setDistanceFromSource(0);
		Node selected = nodes.get(0);
		Node previous;
		int number = unvisiteds.size();
		
		while(number != 0){
			ArrayList<Edge> nodesEdges = selected.getNodesEdges();
			
			//Set the minimum distances
			for(int i = 0; i < nodesEdges.size(); i++)
			{
				//System.out.println(""+nodesEdges.get(i).getFrom().getName() + " " + nodesEdges.get(i).getTo().getName());
				if(nodesEdges.get(i).getNeighbour(selected.getName()).isVisited() == false)
				{	
					int distance = 0;
					distance = nodesEdges.get(i).getWeight() + selected.getDistanceFromSource();
					//System.out.println(""+distance);
					if(distance < nodesEdges.get(i).getNeighbour(selected.getName()).getDistanceFromSource())
						nodesEdges.get(i).getNeighbour(selected.getName()).setDistanceFromSource(distance);
			    }
			}
			
			previous = selected;
			previous.setVisited(true);
			int selectedDistance = Integer.MAX_VALUE;
			
			//Select the next node which has the shortest distance from the source and is unvisited.
			for(int i = 0; i < nodesEdges.size(); i++)
			{
				//System.out.println(""+nodesEdges.get(i).getFrom().getName() + " " + nodesEdges.get(i).getTo().getName());
				if(nodesEdges.get(i).getNeighbour(previous.getName()).getDistanceFromSource() < selectedDistance && 
						nodesEdges.get(i).getNeighbour(previous.getName()).isVisited() == false){
					selectedDistance = nodesEdges.get(i).getNeighbour(previous.getName()).getDistanceFromSource();
 					selected = nodesEdges.get(i).getNeighbour(previous.getName());
				 }
			    } 
			
			//System.out.println(""+selected.getName());
			number--;
			
		}
	}
	
	public void printShortestDistances(){
		for(int i = 0; i < nodes.size(); i++)
			System.out.println("The shortest path from " + nodes.get(i).getName()+" source is: " + nodes.get(i).getDistanceFromSource());
	}
	
	public static void main(String[] args){
		/*Node A = new Node(1);
		Node B = new Node(2);
		Node D = new Node(3);
		Node E = new Node(4);
		Node C = new Node(5);
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(A);
		nodes.add(B);
		nodes.add(D);
		nodes.add(E);
		nodes.add(C);
		
		ArrayList<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge(A,B,6));
		edges.add(new Edge(A,D,1));
		edges.add(new Edge(B,C,5));
		edges.add(new Edge(B,E,2));
		edges.add(new Edge(E,C,5));
		edges.add(new Edge(D,B,2));
		edges.add(new Edge(D,E,1));*/
		
		
		Node A = new Node(1);
		Node B = new Node(2);
		Node C = new Node(3);
		Node D = new Node(4);
		Node E = new Node(5);
		Node F = new Node(6);
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(A);
		nodes.add(B);
		nodes.add(C);
		nodes.add(D);
		nodes.add(E);
		nodes.add(F);
		
		ArrayList<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge(A,B,9));
		edges.add(new Edge(A,D,5));
		edges.add(new Edge(A,C,1));
		edges.add(new Edge(B,E,5));
		edges.add(new Edge(E,C,1));
		edges.add(new Edge(C,D,3));
		edges.add(new Edge(D,E,2));
		edges.add(new Edge(E,F,4));
		edges.add(new Edge(D,F,1));
		
		Graph g = new Graph(edges,nodes);	
		g.DijkstrasAlgorithm();
		g.printShortestDistances();
	}
}
