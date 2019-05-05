
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
    Initial state example: [1, 2, 6, 4, 5, 9, 7, 3, 0, 10, 11, 8]

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
