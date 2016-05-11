import java.io.File;
import java.util.*;

class WeightedGraph {

   private int[][] AdjacencyMatrix;
   public int matrixSize;
   static final int MAX = 999;

   //constructor
   WeightedGraph(String inputFile) {

	Scanner stream; 

	try {
	  
 	   stream = new Scanner(new File(inputFile));
 	}
	catch(Exception e) {

	   System.out.println("File could not be opned");
	   return;
	}
	if(!stream.hasNext()) return; 
		
	matrixSize = stream.nextInt();

	AdjacencyMatrix = new int[matrixSize][matrixSize];

	for(int i = 0; i < matrixSize; ++i) {

	   for(int j = 0; j < matrixSize; ++j) {


		AdjacencyMatrix[i][j] = stream.nextInt();
		if(AdjacencyMatrix[i][j] == 0) {
			
			AdjacencyMatrix[i][j] = MAX;
		}
	   }
	}
   }

   public void DisplayMatrix() {

	for(int i = 0; i < matrixSize; ++i) {
	
	   System.out.print("\n");
	   for(int j = 0; j < matrixSize; ++j) {

		System.out.format("%d ", this.AdjacencyMatrix[i][j]);
	   }
	}
   }

   public static void main(String [] args) {

	WeightedGraph graph = new WeightedGraph(args[0]);

	//graph.DisplayMatrix();
	
	PrimAlgorithm Prim = new PrimAlgorithm(graph.AdjacencyMatrix, graph.matrixSize);
	KruskalAlgorithm Kruskal = new KruskalAlgorithm(graph.AdjacencyMatrix, graph.matrixSize);
	
	System.out.println("\n\nRunning Prims algorithm on input graph");
	Prim.calculateWeight();
	
	System.out.println("\n\nRunning Kruskal's algorithm on input graph");
	Kruskal.kruskalAlgorithm();

  }

}

 
