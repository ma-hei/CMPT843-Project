import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CCSBFileInspector {
	
	BufferedReader in;
	
public CCSBFileInspector(){
		
		try {
			in = new BufferedReader (new FileReader("ppi_ccsb.txt"));
			in.readLine();
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
		while (temp!=null){
			
		if (temp == null)
			return null;
		String[] words = temp.split("\\t");
		int a = Integer.parseInt(words[0]);
		int b = Integer.parseInt(words[2]);
		
		if (a!=b)
			return new Edge(a,b);
		temp = in.readLine();
		}
			
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
	
}

}
