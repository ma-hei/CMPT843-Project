import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;


public class ShuffleEdgeStream {
	
	BufferedReader in;
	ArrayList<String> allEdges;
	
	public ShuffleEdgeStream(){
		
		allEdges = new ArrayList<String>();
		
	}
	
	public void doit(){
		

		int counter=0;
		
		try {
			
			in = new BufferedReader (new FileReader("random_graph"));
			String temp;
			temp=in.readLine();
			counter++;
			while (temp!=null){
				counter++;
				allEdges.add(temp);
				temp=in.readLine();
			}

			System.out.println(allEdges.size());
			in.close();
			
			Random rgen = new Random();  // Random number generator			
			 
			for (int i=0; i<allEdges.size(); i++) {
			    int randomPosition = rgen.nextInt(allEdges.size());
			    String temp2 = allEdges.get(i);
			    allEdges.set(i, allEdges.get(randomPosition));
			    allEdges.set(randomPosition, temp2);
			}
			
			PrintWriter writer = new PrintWriter("random_graph_shuffled", "UTF-8");
			
			for (String s : allEdges){
				
				writer.write(s);
				writer.write("\n");
				
			}
			
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
