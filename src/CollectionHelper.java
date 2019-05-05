import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CollectionHelper {

	public boolean contains(List<int[][]> log, int[][] tempArray) {
		for(int[][] element:log) {
	        	
		if(equals(element,tempArray) )	 
		{
			return true;
		}
	        }
   	return false;
	}
	
	 public  boolean equals (int[][] m1, int[][] m2)
	    {
	       if (m1.length != m2.length) return false;
	       for (int i = 0; i < m1.length; i++) {
	         if (m1[i].length != m2[i].length) return false;
	         for (int j = 0; j < m1[i].length; j++) {
	           int b1 = m1[i][j];
	           int b2 = m2[i][j];
	           if (b1 != b2) return false;
	         }
	      }
	      return true;
	    }
	 
	 public  String stringifyArray(int[][] m1) {
			String content="";
				content+="[";
			    for (int i = 0; i < m1.length; i++) 
			         for (int j = 0; j < m1[i].length; j++) 
			        	 if (i==m1.length-1 && j==m1[i].length-1) content += m1[i][j];
			        	 else content += m1[i][j] + ", ";
			    
			    content+="] \n";
       return content;
	    }

	public void printStack(Stack<Node> stack) {
		for(Node element:stack) {
			System.out.print(stringifyArray(element.data));
			System.out.println();

		}
	}

	public void printPriorityQueue(PriorityQueue<Node> PriorityQueue) {
		for(Node element:PriorityQueue) {
			System.out.print(stringifyArray(element.data));
			System.out.println();

		}
	}	
	
	public String pathToExploredNodes(List<int[][]> log) 	    {
		String content="";
		for (int k = 0; k < log.size(); k++) {
			int[][] item = log.get(k);
		 	
			content+=(k+1)+": [";
		    for (int i = 0; i < item.length; i++) 
		         for (int j = 0; j < item[i].length; j++) 
		        	 if (i==item.length-1 && j==item[i].length-1) content += item[i][j];
		        	 else content += item[i][j] + ", ";
		    
		    content+="] \n";

			}
	      return content;
	}

	public String solutionPath(List<int[][]> closed_list, int[][] goal) 	    {
		String content="";
		for (int k = 0; k < closed_list.size(); k++) {
			int[][] item = closed_list.get(k);
		 	
			content+=" "+zeroAssociatedCharacter (k, item)+" [";
		    for (int i = 0; i < item.length; i++) 
		         for (int j = 0; j < item[i].length; j++) 
		        	 if (i==item.length-1 && j==item[i].length-1) content += item[i][j];
		        	 else content += item[i][j] + ", ";
		    
		    content+="] \n";
			}
		return content;
	}	

	public char zeroAssociatedCharacter (int k, int[][] item) {
		char zeroChar = '0'; 
			if (k!=0)
			{
				for (int i = 0; i < item.length; i++) 
			    	for (int j = 0; j < item[i].length; j++) {
			    		if (item[0][0]==0) zeroChar='a'; 
			    		if (item[0][1]==0) zeroChar='b'; 	    		
			    		if (item[0][2]==0) zeroChar='c'; 	    		
			    		if (item[0][3]==0) zeroChar='d'; 	    		
			    		if (item[1][0]==0) zeroChar='e'; 
			    		if (item[1][1]==0) zeroChar='f'; 	    		
			    		if (item[1][2]==0) zeroChar='g'; 	    		
			    		if (item[1][3]==0) zeroChar='h'; 	    	
			    		if (item[2][0]==0) zeroChar='i'; 
			    		if (item[2][1]==0) zeroChar='j'; 	    		
			    		if (item[2][2]==0) zeroChar='k'; 	    		
			    		if (item[2][3]==0) zeroChar='l'; 	    	
		    	}
			}
	    return zeroChar;
	}

	public void writeToFile(String fileContent, String fileName) {
	      BufferedWriter bw = null;
 		 String fileNameFromUser = fileName;
	   	 try {
	    	  String mycontent = fileContent;
	    	  //String fileNameFromUser =" ";
	    	  //Specify the file name and path here
	    		 File  folder = new File("C://FAMILY//SHAHRAM_MOKARIAN//COMPUTER_CONCORDIA//FALL2018//COMP6721//Mini_Project/");
	    		 File  file = new File(folder, fileNameFromUser + ".txt");
		 /* This logic will make sure that the file 
		  * gets created if it is not present at the
		  * specified location*/
		  if (!file.exists()) {
		     file.createNewFile();
		  }

		  FileWriter fw = new FileWriter(file);
		  bw = new BufferedWriter(fw);
		  bw.write(mycontent);
	          System.out.println("File written Successfully");

	      } catch (IOException ioe) {
		   ioe.printStackTrace();
		}
		finally
		{ 
		   try{
		      if(bw!=null)
			 bw.close();
		   }catch(Exception ex){
		       System.out.println("Error in closing the BufferedWriter"+ex);
		    }
		}	

	}
}

