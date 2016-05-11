
public class PrimAlgorithm {
	
	int[][] Matrix;
	int matrixSize;
	int [] visited;
	int min = 999;
	int u = 0;
	int v = 0;
	int totalWeight = 0;
	
	PrimAlgorithm(int [][] AdjacencyMatrix, int matrixSize) {
		
		this.Matrix = new int[matrixSize][matrixSize];
		
		for(int source = 0; source < matrixSize; source++) {
			
			for(int destination = 0; destination < matrixSize; destination++) {
				
				this.Matrix[source][destination] = AdjacencyMatrix[source][destination];
			}
		}
		
		this.matrixSize = matrixSize;
		this.visited = new int[matrixSize];
		
		//set visited array to all zero
		for(int i = 0; i < matrixSize -1; ++i) {
			
			visited[i] = 0; 
		}
		
	}
	
	public int calculateWeight() {
		
		visited[0] = 1; //picking the first node
		
		//loop through every node
		for(int count = 0; count < matrixSize - 1; ++count) {
			
			min = 999; 
			
			//runs through all the visited nodes
			for(int i = 0; i < matrixSize; ++i) {
				
				//if visited, run through all edges
				if(visited[i] == 1) {
					
					for(int j = 0; j < matrixSize; ++j) {
						
						//if the node has not been visited
						if(visited[j] == 0) {
							
							//if a new minimum edge weight is found
							if(min > Matrix[i][j]) {
								
								//Current edge becomes the new min
								min = Matrix[i][j];
								u = i;
								v = j;
								
							}
						}
					}
				}
			}
			
			visited[v] = 1; //Marks the new node that is connected as visited
			totalWeight = totalWeight + min;
			System.out.println("New Edge added: Node: " + u + " to Node: " + v + " Weight: " + min);
			
		}
		
		System.out.println("Total Weight of Spanning Tree: " + totalWeight);
		
		
		return totalWeight;
	}
	
}
