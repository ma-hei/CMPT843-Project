import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class GraphReader {
	
	BufferedReader in;
	
	public GraphReader(){
		
		try {
			in = new BufferedReader (new FileReader("livejournal.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public Edge getNextEdge(){
		
		try {
			String temp =  in.readLine();
			
			if (temp == null){
				return null;
			}
			
			String arr[] = temp.split("\t");
			int intArray[] = new int[2];
			intArray[0] = Integer.parseInt(arr[0]);
			intArray[1] = Integer.parseInt(arr[1]);
			return new Edge(intArray[0], intArray[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
