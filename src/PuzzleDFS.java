/* 
 * Depth First Search Algorithm to Solve 11d-puzzle
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Arrays;

public class PuzzleDFS {
	private Node root;
	private Node goal;
	private CollectionHelper collectionHelper;
    private int MAX_DEPTH;
    
	// constructor
	public PuzzleDFS (Node root, Node goal,int MAX_DEPTH)
	{
	    this.root = root;
	    this.goal = goal;
	    this.MAX_DEPTH = MAX_DEPTH;
	    collectionHelper = new CollectionHelper();
	    if (collectionHelper.equals(root.data, goal.data)) {
	    	System.out.println("Initial State and Goal are the same!");
	    	System.exit(0);
	    }
	    dfsUsingStack(root);
	}
 //Iterative DFS using stack
	public void dfsUsingStack(Node initialNode)
	{
        //
		List<int[][]> closed_list = new ArrayList<>();
        //implement track of traversed Node.data
		List<int[][]> log = new ArrayList<>();
        Stack<Node> stack=new Stack<Node>();
		stack.add(initialNode);
		//closed_list.add(initialNode.data);
		initialNode.visited=true;
		int depth = 0;
		
		while (!stack.isEmpty())
		{
			//pop a Node from the stack
			// put an if to check if mex depth has reached then ...
			Node currentNode=stack.pop();
			//record track of the popped Node
	        log.add(currentNode.data);
			closed_list.add(currentNode.data);
				
				if (collectionHelper.equals(currentNode.data, goal.data)) {
			    	System.out.println("\nGoal found!\nThis is the solution paths:");
 			    	String fileContent=collectionHelper.solutionPath(closed_list, goal.data);
			    	System.out.println(fileContent);
			    		//Write an output file
			    		String fileName="puzzleDFS";
			    		collectionHelper.writeToFile(fileContent, fileName);
			    	System.out.println();
			    	System.out.println("Iterative Deepening search with max Depth level of "+ MAX_DEPTH +" gives us a solution at"
			    			+ " depth level of "+currentNode.getDepth() + " with the following cost indications:\n\n"
			    					+ " Time Cost: "+closed_list.size()+" (number of comparisons between each current state with the goal state).\n"
			    							+ "Space Cost: "+stack.size()+" (elements remained in the stack when the goal state was found)."); 
			    	System.exit(0);
				} else if (currentNode.getDepth()<MAX_DEPTH) 
				{		depth = currentNode.getDepth();
						List<Node> childrenOfCurrentNode = getChildren(currentNode, log);
						if (childrenOfCurrentNode!=null) {
							currentNode.setChildren(childrenOfCurrentNode);
							depth++;
							//push the children into the stack
							List<Node> children=currentNode.getChildren();
							for (int i = children.size()-1; i >=0; i--) {
								Node nChild =children.get(i);
								if(nChild!=null && !nChild.visited) {
									stack.add(nChild);
									nChild.visited=true;
									nChild.setDepth(depth);
								}	else {
										System.out.println("no more child");
								}
							}
						}
				}
		}
		System.out.println("Stack is Empty and goal has not been found.");
		System.out.println("You are at depth level of "+depth+" and Max Depth level is "+ MAX_DEPTH+". You may increase your Max Depth level");
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
