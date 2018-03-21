package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class PuzzleSolver {
	private char [][]charMatrix;
	public ArrayList<String> wordList = new ArrayList<String>();
	private int row;
	private int col;
	
	
	public void readFromFile(String fileName)
	{
                
        Scanner inputStream = null;
        System.out.println ("The file " + fileName  + " " + "contains words.\n");
        try        {
            inputStream = new Scanner (new File (fileName));
        }
        catch (FileNotFoundException e)        {
            System.out.println ("Error opening the file " +fileName);
            System.exit (0);
        }  
        
        row = inputStream.nextInt();
        col = inputStream.nextInt();
        charMatrix = new char[row][col];
        inputStream.nextLine();
        for(int i = 0; i < row; i++)
        {
        
        	String line;
        	line = inputStream.nextLine();
        	char []charArr = convertToCharArray(line);
        	charMatrix[i] = charArr;
        	
        }
        
        while(inputStream.hasNextLine()){
        	String line = inputStream.nextLine();
        	wordList.add(line);
        }
        
        inputStream.close();
        
	}
	
	private char[] convertToCharArray(String line) {
		char []charArr = new char[col];
		for(int i = 0; i < line.length(); i++){
			charArr[i] = line.charAt(i);
			}
		return charArr;
	}
	
	public void solveAndPrint(String myWord)
	{
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		int i,j;
		String s1;
		char[] arr;
		int old_r = 0, old_c = 0, new_r = 0, new_c = 0;
		char ch;
		
		int x,y;
		int count1 = 0;
		int flag = 0;
		
		s1 = myWord;
		arr = s1.toCharArray();
		ch = arr[0];
			/*System.out.print(ch);*/
		  for(i = 0; i < row; i++){
			  for(j = 0; j < col; j++){
				  if(ch == charMatrix[i][j])
				  {
					   new_r = i;
					   old_r = i;
					   new_c = j;
					   old_c = j;
					   
					   indexes.add(new_r);
					   indexes.add(new_c);
					   
					  // looking in the east direction
					   for(x = 0; x < arr.length-1; x++){
						   if((new_r < row) && (new_c+x+1 < col)){
							   if(look_thr(arr[x+1],charMatrix[new_r][new_c+x+1],new_r,(new_c+x+1),indexes))
						    	   count1++;
						   }
					       
					   }
					  
					   if(count1 < (arr.length)-1){
						   indexes.clear();
						   new_r = old_r;
						   new_c = old_c;
						   indexes.add(new_r);
						   indexes.add(new_c);
						   count1 = 0;
						   // looking in the south direction
						   for(x = 0; x < arr.length-1; x++){
							  if((new_r+x+1 < row) && (new_c < col)){
								 if(look_thr(arr[x+1],charMatrix[new_r+x+1][new_c],(new_r+x+1),(new_c),indexes))
								    count1++; 
							  }
							   
						   }
						   
						   if(count1 < arr.length-1 ){
							   indexes.clear();
							   new_r = old_r;
							   new_c = old_c;
							   indexes.add(new_r);
							   indexes.add(new_c);
							   count1 = 0;
							   // looking in the south-east direction
							   for(x = 0; x < arr.length-1; x++){
								   if((new_r+x+1 < row) && (new_c+x+1 < col)){
									   if(look_thr(arr[x+1],charMatrix[new_r+x+1][new_c+x+1],(new_r+x+1),(new_c+x+1),indexes)){
										   count1++;
								   }
								  }
							   }
							   
							   if(count1 < arr.length-1){
								   indexes.clear();
								   new_r = old_r;
								   new_c = old_c;
								   indexes.add(new_r);
								   indexes.add(new_c);
								   count1 = 0;
								   // looking in the west direction
								   for(x = 0; x < arr.length-1; x++){
									   if((new_r) < row && (new_c-x-1) >= 0){
										   if(look_thr(arr[x+1],charMatrix[new_r][new_c-x-1],(new_r),(new_c-x-1),indexes)){
											   count1++;
									       }
								        }
							          }
								   if(count1 < arr.length-1){
									   indexes.clear();
									   new_r = old_r;
									   new_c = old_c;
									   indexes.add(new_r);
									   indexes.add(new_c);
									   count1 = 0; 
									   // looking in the north direction
									   for(x = 0; x < arr.length-1; x++){
										   if((new_r-x-1) >= 0 && (new_c) >= 0){
											   if(look_thr(arr[x+1],charMatrix[new_r-x-1][new_c],(new_r-x-1),(new_c),indexes)){
												   count1++;
										       }
									        }
								          }
									   if (count1 < arr.length-1){
										   indexes.clear();
										   new_r = old_r;
										   new_c = old_c;
										   indexes.add(new_r);
										   indexes.add(new_c);
										   count1 = 0; 
										   // looking in the north-east direction
										   for(x = 0; x < arr.length-1; x++){
											   if((new_r-x-1) >= 0 && (new_c+x+1) < col){
												   if(look_thr(arr[x+1],charMatrix[new_r-x-1][new_c+x+1],(new_r-x-1),(new_c+x+1),indexes)){
													   count1++;
											       }
										        }
									          }
										   if (count1 == arr.length-1){
											   indexes.clear();
											   new_r = old_r;
											   new_c = old_c;
											   indexes.add(new_r);
											   indexes.add(new_c);
											   count1 = 0; 
											   // looking in the north-west direction
											   for(x = 0; x < arr.length-1; x++){
												   if((new_r-x-1) >= 0 && (new_c-x-1) >= 0){
													   if(look_thr(arr[x+1],charMatrix[new_r-x-1][new_c-x-1],(new_r-x-1),(new_c-x-1),indexes)){
														   count1++;
												       }
											        }
										          }
											   if(count1 < arr.length-1){
												   indexes.clear();
												   new_r = old_r;
												   new_c = old_c;
												   indexes.add(new_r);
												   indexes.add(new_c);
												   count1 = 0; 
												   // looking in the south-west direction
												   for(x = 0; x < arr.length-1; x++){
													   if((new_r+x+1) < row && (new_c-x-1) >= 0){
														   if(look_thr(arr[x+1],charMatrix[new_r+x+1][new_c-x-1],(new_r+x+1),(new_c-x-1),indexes)){    
															   count1++;
													       }
												        }
											          }
												   if(count1 == (arr.length)-1){
													   flag = 1;
												   }
												   else{
													   new_r = 0;
													   old_r = 0;
													   new_r = 0;
													   new_c = 0;
													   indexes.clear();
													   count1 = 0;
												   }
											   }
											   else if(count1 == arr.length-1){
												   flag = 1;
											   }
										   }
										   else if (count1 == arr.length-1){
											   flag = 1;
										   }
									   }
									   else if (count1 == arr.length-1){
										   flag = 1;
									   }
								   }
								   
								   else if(count1 == arr.length-1){
									   flag = 1;
								   }
							 }
						       else if(count1 == (arr.length)-1){
							       flag = 1;
						   }
					   }
					      else if(count1 == (arr.length)-1){
						      flag = 1;
					   }
				     }
				      else if(count1 == arr.length-1){
				          flag = 1;
					   }
			   }
			  
			
			  }
			  if(flag == 1){
				  break;
		    }
		  }
		  
		  String s2;
		  s2 = s1.toUpperCase();
		  
		  if(flag == 1){
			  System.out.println(""+s2+":Found");
			  System.out.println("Coordinates:");
			  for(x = 0; x < arr.length; x++){
				  System.out.print(""+arr[x]+":["+indexes.get(x*2)+","+indexes.get(x*2+1)+"] ");
			  }
			  System.out.println();
			  System.out.println();
		  }
		  
		  else{
			  System.out.println("" +s2+ ":Not found");
			  System.out.println();
		  }
   

	}
	
	private boolean look_thr(char c, char d, int a, int b, ArrayList<Integer> indexes) {
		if  (c == d){
			indexes.add(a);
			indexes.add(b);
			return true;
			}
		return false;
	}

}
