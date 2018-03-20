package valladolid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		ArrayList<Image> myImages = new ArrayList<Image>();
		String fileName = "input.txt";
	
            
		Scanner inputStream = null;
	    try{
	        inputStream = new Scanner (new File (fileName));
	        myImages = readFromFile(inputStream);
	    }
	    catch (FileNotFoundException e){
	    	System.out.println ("Error opening the file " +fileName);
	    	System.exit (0);
	    }
	    
	   //System.out.print(""+ myImages.size());
	    
	    // HERE IS THE RESULT TO DISPLAY THE SPACES LEFT AFTER COLLISION HAPPENS FOR EACH IMAGE!
	    for(int i = 0; i < myImages.size(); i++){
	    	System.out.println("Image "+ (i+1) + ": " + myImages.get(i).getSpaceNumber());
	    }
	    
	    
	   
}

	private static ArrayList<Image> readFromFile(Scanner inputStream) {
		String line;
		int count = 0;
		ArrayList<Image> myImages = new ArrayList<Image>();
		Image img = new Image();
		while(inputStream.hasNext()){
			line = inputStream.nextLine();
			if(line.length() != 0)
			{	
				img.addLine(line);
				count++;
			 }
			else {
				img.setNumOfLines(count);
				myImages.add(img);
				count = 0;
				img = new Image();
			}
		}
		img.setNumOfLines(count);
		myImages.add(img);
		return myImages;
		
	}
	
}