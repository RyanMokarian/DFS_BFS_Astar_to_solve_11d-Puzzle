/* 
 * Best First Search Algorithm to Solve 11d-puzzle with 2 heuristics:
 * (1) h1(n) = Hamming Distance
 * (2) h2(n) = Manhattan Distance
 */
	import java.util.PriorityQueue;
	import java.util.Comparator;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Arrays;
	import java.lang.*;

	public class PuzzleBFS {
		private Node root;
		private Node goal;
		private CollectionHelper collectionHelper;
	    private int MAX_DEPTH;
	    private String heuristic;
		// constructor
		public PuzzleBFS (Node root, Node goal, int MAX_DEPTH, String heuristic)
		{
		    this.root = root;
		    this.goal = goal;
		    this.MAX_DEPTH = MAX_DEPTH;
		    this.heuristic = heuristic;
		    collectionHelper = new CollectionHelper();
		    if (collectionHelper.equals(root.data, goal.data)) {
		    	System.out.println("Initial State and Goal are the same!");
		    	System.exit(0);
		    }
		    BFS_UsingPriorityQueue(root);
		}
	    //BFS using Priority Queue
		public void BFS_UsingPriorityQueue(Node initialNode)
		{
			//Assign cost to the Node
			if (heuristic == "Hamming_Distance")
			initialNode.setCost(costHamming(initialNode, goal));
			if (heuristic == "Manhattan_Distance")
			initialNode.setCost(costManhattan(initialNode, goal));
			//
			List<int[][]> closed_list = new ArrayList<>();
	        //implement track of traversed Node.data
			List<int[][]> log = new ArrayList<>();
			PriorityQueue<Node> priorityQueue=new PriorityQueue<Node>();
			priorityQueue.add(initialNode);
			initialNode.visited=true;
			int depth = 0;
			
			while (!priorityQueue.isEmpty())
			{
				Node currentNode=priorityQueue.remove();
				//record track of the removed Node
		        log.add(currentNode.data);
				closed_list.add(currentNode.data);
					if (collectionHelper.equals(currentNode.data, goal.data)) {
				    	System.out.println("\nGoal found!\nThis is the solution paths:");
				    	String fileContent=collectionHelper.solutionPath(closed_list, goal.data);
				    	System.out.println(fileContent);
				    		//Write an output file
				    		String fileName="";
				    		if(heuristic=="Hamming_Distance") fileName = "puzzleBFS-h1";
				    		if(heuristic=="Manhattan_Distance") fileName = "puzzleBFS-h2";
				    		collectionHelper.writeToFile(fileContent, fileName);
				    	System.out.println();
				    	System.out.println("Bist First search with \""+heuristic+"\" heuristic gives us a solution at"
				    			+ " depth level of "+currentNode.getDepth() + " with the following cost indications:\n\n"
				    					+ " Time Cost: "+closed_list.size()+" (number of comparisons between each current state with the goal state).\n"
				    							+ "Space Cost: "+priorityQueue.size()+" (elements remained in the Queue when the goal state was found)."); 
				    	System.exit(0);
					} else if (currentNode.getDepth()<MAX_DEPTH) 
					{		depth = currentNode.getDepth();
							List<Node> childrenOfCurrentNode = getChildren(currentNode, log);
							if (childrenOfCurrentNode!=null) {
								currentNode.setChildren(childrenOfCurrentNode);
								depth++;
								//push the children into the Queue
								List<Node> children=currentNode.getChildren();
										for (int i = 0; i < children.size(); i++)
										{
											Node nChild =children.get(i);
											if(nChild!=null && !nChild.visited) {
												priorityQueue.add(nChild);
												nChild.visited=true;
												nChild.setDepth(depth);
											}	else {
													System.out.println("no more child");
											}
										}
							}
					}
			}
			System.out.println("Queue is Empty and goal has not been found.");
			System.out.println("You are at depth level of "+depth+" and Max Depth level is "+ MAX_DEPTH+". You may increase your Max Depth level");
		}
	
		public int costHamming (Node currentNode, Node goal) {
			int cost = 0;
			for (int i = 0; i < goal.data.length; i++) 
		         for (int j = 0; j < goal.data[i].length; j++) 
		        	 if ((currentNode.data[i][j]!=0) && (currentNode.data[i][j]!=goal.data[i][j])) cost++;
			return cost;
		}
		
		public int costManhattan (Node currentNode, Node goal) {
			int cost = 0;
			int rowCurrent=0, rowGoal=0, columnCurrent=0, columnGoal=0;
				for (int k=1; k<12;k++) 
				{
					//get location of k for current node
					for (int i = 0; i < currentNode.data.length; i++) 
				         for (int j = 0; j < currentNode.data[i].length; j++) 
				        	 if (currentNode.data[i][j] == k) {
				        		 rowCurrent=i;
				        		 columnCurrent=j;
				        	 }
					//get location of k for Goal node
					for (int i = 0; i < goal.data.length; i++) 
				         for (int j = 0; j < goal.data[i].length; j++) 
				        	 if (goal.data[i][j] == k) {
				        		 rowGoal=i;
				        		 columnGoal=j;
				        	 }				
				//add cost of number k
				cost+= Math.abs(rowCurrent-rowGoal)+Math.abs(columnCurrent-columnGoal);
				}
			return cost;
		}		
		
		public List<Node> getChildren(Node currentNode, List<int[][]> log ) {
	        List<Node> children = new ArrayList<Node>();
	         //deep copy from the Node array
			 int [][] copyArray = new int [currentNode.data.length][currentNode.data[0].length];
		      for (int i = 0; i < copyArray.length; ++i) {
		         copyArray[i] = new int[currentNode.data[i].length];
		         for (int j = 0; j < copyArray[i].length; ++j) {
		            copyArray[i][j] = currentNode.data[i][j];
		         }
		      }
	        // find location of zero grid
		     int zero_x = 0, zero_y=0;		
			 for (int i = 0; i < copyArray.length; i++)                                               
		            for (int j = 0; j < copyArray[0].length; j++) {
		            	if (copyArray[i][j] == 0) {
		            		zero_x = i;
		            		zero_y = j;
		            		break;
		            	}
		            }
		     // create array x and y to clock around zero grid
			 int [] x = {-1,-1,0,1,1,1,0,-1};
			 int [] y = {0,1,1,1,0,-1,-1,-1};
			 //determine all children using a for loop
			 for (int i = 0; i < 8; i++)    {		 
				  if((zero_x+x[i]>=0) && (zero_y+y[i]>=0) && (zero_x+x[i]<copyArray.length) && (zero_y+y[i]<copyArray[0].length)){
					  //swap zero grid with each surrounding grid
					  copyArray[zero_x][zero_y]= copyArray[zero_x+x[i]][zero_y+y[i]]; copyArray[zero_x+x[i]][zero_y+y[i]]=0;
							//deep copy   
						  	int [][] tempArray = new int [copyArray.length][copyArray[0].length]; 				
							  for (int k = 0; k < copyArray.length; k++)                                               
							            for (int l = 0; l < copyArray[0].length; l++)
							            	tempArray[k][l]=copyArray[k][l];
							//
						if (!new CollectionHelper().contains(log,tempArray)) {
							Node tempNode = new Node(tempArray);
							//Assign cost to the Node
							if (heuristic == "Hamming_Distance")
								tempNode.setCost(costHamming(tempNode, goal));
							if (heuristic == "Manhattan_Distance")
								tempNode.setCost(costManhattan(tempNode, goal));
							//
							children.add(tempNode);	
					        log.add(tempNode.data);
						}
						else {
						}
					// back to the original grid values
					  copyArray[zero_x+x[i]][zero_y+y[i]]=copyArray[zero_x][zero_y]; copyArray[zero_x][zero_y]=0;			  
					  }
			 }	 
			 return children;	
		}
}
