//Implementing Dijkstra's Algorithm with the utilization of Java Frame and Panel

package dijkstra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

//Some parts are written between the lines of the actual codes for us to keep track of the current situation.

public class Graph extends JPanel{
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
	
	public void paint(Graphics g){
		
		for(Node n : nodes){
			g.fillOval(n.getX()-35/2,n.getY()-35/2,35,35);
		}
		for(Edge e : edges)
			g.drawLine(e.getFrom().getX(), e.getFrom().getY(), e.getTo().getX(), e.getTo().getY());
		
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
		
		
		Node A = new Node(1,60,300);
		Node B = new Node(2,60,60);
		Node C = new Node(3,140,150);
		Node D = new Node(4,250,302);
		Node E = new Node(5,200,50);
		Node F = new Node(6,400,90);
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
		
		JFrame myFrame = new JFrame();
		myFrame.setPreferredSize(new Dimension(600,500));
		myFrame.setBackground(Color.WHITE);
		myFrame.getContentPane().add(g);
		myFrame.pack();
		myFrame.setResizable(false);
		myFrame.setVisible(true);
		
		g.DijkstrasAlgorithm();
		g.printShortestDistances();
	}
}
