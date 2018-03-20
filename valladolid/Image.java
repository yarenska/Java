package valladolid;

import java.util.ArrayList;

public class Image {
	 private ArrayList<String> list;
	 private int numOfLines;
	 private int width;
	 private int lastSpace;
	 
	 public Image(){
		list = new ArrayList<String>();
	 }
	 
	 public void addLine(String line){
		 list.add(line);
		 setWidth(line);
	 }
	
	 public void setNumOfLines(int number){
		 numOfLines = number;
	 }
	 
	 public int getNumOfLines(){
		 return numOfLines;
	 }
	 
	 public void setWidth(String line){
		 width = line.length();
	 }

	 public int getWidth(){
		 return width;
	 }
	 
	 public String toString(){
		 return " " + width;
	 }
	 
	 public int getSpaceNumber(){
		 calculate();
		 return lastSpace; 
	 }

	private void calculate() {
		ArrayList<Integer> numOfSpaces = new ArrayList<Integer>();
		int counter = 0;
		
		// creating the ArrayList to hold number of spaces in each line.
		for(int i = 0; i < numOfLines; i++){
			counter = 0;
			String line = list.get(i);
			for(int j = 0; j < width; j++){
				if(line.charAt(j) == 'x')
					counter++;	
			}
			numOfSpaces.add(width-counter);
		}
		
		// finding the minimum of the number of spaces ArrayList.
		int min;
		min = findMin(numOfSpaces);
		
		int sum = 0;
		for(int i = 0; i < numOfSpaces.size(); i++)
			sum += (numOfSpaces.get(i) - min);
		
		this.lastSpace = sum;
	}

	private int findMin(ArrayList<Integer> numOfSpaces) {
		int min = numOfSpaces.get(0);
		for(int i = 0; i < numOfSpaces.size(); i++)
			if(numOfSpaces.get(i) < min)
				min = numOfSpaces.get(i);
		
		return min;
	}
}
