/*
    Implementation of DFS, BFS and A-Star algorithms to solve a 11d-Puzzle

	DESCRIPTION
	This project implements a solution for the 11d-puzzle (a board of 3x4) using the following
    search algorithms and heuristics:

        1. Depth-first search (DFS)
        2. Best-first search (BFS) – Hamming Distance Heuristic
        3. BFS – Manhattan Distance Heuristic
        4. Algorithm A* (A*) – Hamming Distance Heuristic
        5. A* – Manhattan Distance Heuristic

    The puzzle goal state is: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0]
    You are asked to enter your preferred search algorithm and an initial state.
    Example: [1, 2, 6, 4, 5, 9, 7, 3, 0, 10, 11, 8]

    INSTRUCTION TO RUN THE PROGRAM
    Attached to this README file are six following files as Java source code files in order to run the MP1 program.
    Driver, PuzzleDFS, PuzzleBFS, Puzzle AStar, Node, CollectionHelper.
    Above files are classes that can be located in a package under a Java project. Mission of each class is described
	below.

	    Drive: this class includes Main and ask user for input data from the console to run the program.
        PuzzleDFS: Depth First search algorithm is run in this class.
        PuzzleBFS: Best First search algorithm is run in this class.
        Puzzle AStar: A-Star search algorithm is run in this class.
        Node: this class is to store and get data related to each puzzle state (node).
        CollectionHelper: most of the required functions have been prepared in this class.
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Driver {
	final static private int [][] GOAL_STATE = {{1,2,3,4},{5,6,7,8},{9,10,11,0}};
	
	 public static void main(String[] args) {
		CollectionHelper collectionHelper = new CollectionHelper();
		int [][] INITIAL_STATE = new int [3][4];
		Scanner kb = new Scanner (System.in);
		System.out.println("\n    *** Welcome to 11d-puzzle game (COMP6721-MP1). ***\n        Prepared by R. Mokarian\n"
				+ "\n\nEnter values to set an initial state for the puzzle.");
	    for (int i = 0; i < INITIAL_STATE.length; i++) 
	         for (int j = 0; j < INITIAL_STATE[i].length; j++) 
	        	 INITIAL_STATE[i][j] = kb.nextInt();
    	List<Node> nodes = new ArrayList<Node>();
		Node initial =new Node(INITIAL_STATE);
    	Node goal =new Node(GOAL_STATE);
    	nodes.add(initial);
    	nodes.add(goal);
    	System.out.println("Puzzle initial state is:\n"+collectionHelper.stringifyArray(initial.data));    	
    	System.out.println("Puzzle goal state is:\n"+collectionHelper.stringifyArray(goal.data));    	
    	System.out.println("What is your preferred maximum depth level to search?");
    	int MAX_DEPTH = kb.nextInt();
    	System.out.println("Maximum depth level is "+MAX_DEPTH);    	
    	System.out.println("\nChoose your search algorithm:\n1 for Depth First\n2 for Best First (Hamming Distance)"
    			+ "\n3 for Best First (Manhattan Distance)\n4 for A* (Hamming Distance)\n5 for A* (Manhattan Distance)");
    	int searchMethod = kb.nextInt();
    	PuzzleDFS DFS;
    	PuzzleBFS BFS;
    	PuzzleAStar AStar;
    	switch (searchMethod)
    	{
	    	case (1):
	    	System.out.println("You selected Depth First.");
	    	new PuzzleDFS(initial, goal, MAX_DEPTH);
	    	break;
	    	case (2):
	    	System.out.println("You selected Best First (Hamming Distance).");
	    	String bfs_h1 ="Hamming_Distance";
	    	new PuzzleBFS(initial, goal, MAX_DEPTH, bfs_h1);
	    	break;
	    	case (3):
	    	System.out.println("You selected Best First (Manhattan Distance).");
	    	String bfs_h2 ="Manhattan_Distance";
	    	new PuzzleBFS(initial, goal, MAX_DEPTH, bfs_h2);
	    	break;    	
	    	case (4):
	    	System.out.println("You selected A Star (Hamming Distance).");
	    	String astar_h1 ="Hamming_Distance";
	    	new PuzzleAStar(initial, goal, MAX_DEPTH, astar_h1);
	    	break;
	    	case (5):
	    	System.out.println("You selected A Star (Manhattan Distance).");
	    	String astar_h2 ="Manhattan_Distance";
	    	new PuzzleAStar(initial, goal, MAX_DEPTH, astar_h2);
	    	break;
	    	default : 
    	}
    }
}