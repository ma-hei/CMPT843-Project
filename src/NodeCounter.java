import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class NodeCounter {
	
	HashMap<String,Integer> occCounter = new HashMap<String, Integer>();
	BufferedReader in;
	String filename;
	
	public NodeCounter(){
		int filecounter=1;
		String filebase = "sample";
		String currentfile;
		String currentline;
		String[] nodes;
		try {
			while (filecounter<=30){
			currentfile = filebase.concat(Integer.toString(filecounter));
			in = new BufferedReader (new FileReader(currentfile));
			in.readLine();
			currentline = in.readLine();
			nodes = currentline.split(" ");
			System.out.println(currentfile);
			
			while (currentline!=null){
				if (!occCounter.containsKey(nodes[0]))
					occCounter.put(nodes[0],1);
				else
					occCounter.put(nodes[0], occCounter.get(nodes[0])+1);
				
				if (!occCounter.containsKey(nodes[1]))
					occCounter.put(nodes[1],1);
				else
					occCounter.put(nodes[1], occCounter.get(nodes[1])+1);
				
				if (!occCounter.containsKey(nodes[2]))
					occCounter.put(nodes[2],1);
				else
					occCounter.put(nodes[2], occCounter.get(nodes[2])+1);
				
				currentline = in.readLine();
				if (currentline!=null)
				nodes = currentline.split(" ");
			}
			in.close();
			filecounter++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(occCounter.size());
		
	}

}
