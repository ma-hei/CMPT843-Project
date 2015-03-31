import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ccsbHandler {
	
	BufferedReader in;
	HashMap<String, Integer> proteins;
	int proteincounter;
	
	public ccsbHandler(){
		
		proteins=new HashMap<String, Integer>();
		proteincounter=0;
		
		try {
			in = new BufferedReader (new FileReader("ccsb.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Edge getNextEdge(){
		
		try {
			String temp = in.readLine();
			if (temp!=null){
			String words[] = temp.split("\t");
			
			String protein1 = words[0];
			String protein2 = words[1];
			
			if (!proteins.containsKey(protein1)){
				proteincounter++;
				proteins.put(protein1, proteincounter);
			}
			if (!proteins.containsKey(protein2)){
				proteincounter++;
				proteins.put(protein2, proteincounter);
			}
			
			return new Edge(proteins.get(protein1), proteins.get(protein2));
			}
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

}
