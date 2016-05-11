import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class KruskalAlgorithm {
	
	private int Matrix[][];
	private List<Edge> edgeList;
	private int matrixSize;
	public static final int MAX = 999; 
	private int visited[];
	private int spanningTree[][];
	
	//constructor
	public KruskalAlgorithm(int[][] Matrix, int matrixSize) {
		
		this.matrixSize = matrixSize;
		this.Matrix = new int[matrixSize][matrixSize];
		
		for(int source = 0; source < matrixSize; source++) {
			
			for(int destination = 0; destination < matrixSize; destination++) {
				
				this.Matrix[source][destination] = Matrix[source][destination]; 
				
			}
		}
		
		spanningTree = new int[matrixSize][matrixSize];
		visited = new int[matrixSize];
		edgeList = new LinkedList<Edge>();
		
	}
	
	public void kruskalAlgorithm() {
		
		//run through matrix and create edge list
		for(int Source = 0; Source < matrixSize; ++Source) {
			
			for(int Destination = 0; Destination < matrixSize; ++Destination) {
				
				//if edge has not already been added && not an edge to itself
				if(Matrix[Source][Destination] != MAX && Source != Destination) {
					
					//create edge and add to edge list
					Edge edge = new Edge(Source, Destination, Matrix[Source][Destination]);
					edgeList.add(edge);
					
					//Mark edge as visited
					Matrix[Source][Destination] = MAX;
					Matrix[Destination][Source] = MAX;
				}
			}
		}
		
		//sort Edges by weight
		Collections.sort(edgeList, new EdgeComparator());
		CycleCheck cycleCheck = new CycleCheck();
		
		boolean Done = false;
		int totalWeight = 0;
		
		//For every edge in the list
		for (Edge currentEdge : edgeList) {
			
			//retrieves next edge in list
	
			
			//increment list counter for next edge
			
	 
			
			//Adds edge to the Spanning tree
			spanningTree[currentEdge.sourceVertex][currentEdge.destinationVertex] = currentEdge.weight;
			spanningTree[currentEdge.destinationVertex][currentEdge.sourceVertex] = currentEdge.weight;
			
			//If the new edge adds a cycle
			if(cycleCheck.cycleCheck(spanningTree, currentEdge.sourceVertex)) {
				
				//remove it from spanning tree
				spanningTree[currentEdge.sourceVertex][currentEdge.destinationVertex] = 0;
				spanningTree[currentEdge.destinationVertex][currentEdge.sourceVertex] = 0;
				
				currentEdge.weight = -1; 
			}
			
			//Adds the vertices to the visited set
			visited[currentEdge.destinationVertex] = 1;
			visited[currentEdge.sourceVertex] = 1;
			
			//if the Edge was successfully added
			if(currentEdge.weight > 0) {
				
				//output edge information
				totalWeight += currentEdge.weight;
				System.out.print("\nNew Edge added: Node: " + currentEdge.sourceVertex);
				System.out.print(" to Node: " + currentEdge.destinationVertex);
				System.out.print(" Weight: " + currentEdge.weight);
			}
			
			
			
			for(int i = 0; i < matrixSize; ++i) {
				
				if(visited[i] == 0) {
					
					Done = false;
					break;
				}
				else Done = true; 
			}
			
			if(Done) break; 
		}
		System.out.println("\nTotal Weight of Spanning Tree: " + totalWeight);
		return;
	}
	

}

class Edge {
	
	int sourceVertex;
	int destinationVertex;
	int weight;
	
	public Edge(int sourceVertex, int destinationVertex, int weight) {
		
		this.sourceVertex = sourceVertex;
		this.destinationVertex = destinationVertex;
		this.weight = weight;
	}

}

class CycleCheck {
	
	Stack<Integer> stack;
	int cycleMatrix[][];
	
	public CycleCheck() {
		
		stack = new Stack<Integer>();
	}
	
	//takes in current MST and the source vertex of the newly added edge
	public boolean cycleCheck(int matrix[][], int sourceVertex) {
		
		//How many vertices? 
		int vertexNum = matrix[sourceVertex].length; 
		
		//build cycleMatrix from input matrix
		cycleMatrix = new int[vertexNum][vertexNum];
		
		for(int source = 0; source < vertexNum; source++) {
			
			for(int destination = 0; destination < vertexNum; destination++) {
				
				cycleMatrix[source][destination] = matrix[source][destination]; 
				
			}
		} 
	    
        
		
		int visited[] = new int[vertexNum];
		visited[sourceVertex] = 1;
		
		int element = sourceVertex;
		int i = sourceVertex;
		
		boolean hasCycle = false;
		stack.push(sourceVertex);
		
		//while we still have vertices to search through
		while(!stack.isEmpty()) {
			
			element = stack.peek();
			i = element; 
			
			while(i < vertexNum) {
				
				//If there is an edge && its already been visited
				if(cycleMatrix[element][i] >= 1 && visited[i] == 1) {
					
					//if we've already visited it this cycle
					if(stack.contains(i)) {
						
						hasCycle = true;
						return hasCycle;
					}
				}
				
				//if there is an edge && vertex hasn't been visited yet
				if(cycleMatrix[element][i] >= 1 && visited[i] == 0) {
					
					//visit the vertex
					stack.push(i);
					visited[i] = 1;
					
					//tell that edge has been visited
					cycleMatrix[element][i] = 0;
					cycleMatrix[i][element] = 0;
					
					//start off at the new vertex 
					element = i;
					i = 0;
					continue;
				
				}
				
				//no edge at indexes, move to the next
				++i; 
			}
			
			//no cycles on this vertex
			stack.pop();
			
		}
		
		return hasCycle;
		
		
	}

}

class EdgeComparator implements Comparator<Edge> {

	@Override 
	public int compare(Edge edgeA, Edge edgeB) {
		
		if (edgeA.weight < edgeB.weight) return -1;
		if (edgeA.weight > edgeB.weight) return 1;
		
		//if the weights are equal
		return 0;
	}

}


