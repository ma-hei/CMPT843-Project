import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileTransformer {
	
	HashMap<String, String> dict = new HashMap<String, String>();
	HashMap<String,ArrayList<String>> interactions = new HashMap<String, ArrayList<String>>();
	
	public void preprocess(){
		
		int alreadSeenCounter=0;
		BufferedReader in;
		String current;
		try {
			in = new BufferedReader (new FileReader("ppi_homosapiens_tab.txt"));
			current = in.readLine();
			current = in.readLine();
			String[] tabs;
			String officiala;
			String officialb;
			while(current!=null){
				
				tabs = current.split("\\t");
				officiala = " ";
				officialb = " ";
				officiala = tabs[2];
				officialb = tabs[3];
				
				if (!interactions.containsKey(officiala)){
					interactions.put(officiala, new ArrayList<String>());
					interactions.get(officiala).add(officialb);
				}
				
				if (interactions.containsKey(officialb) && interactions.get(officialb).contains(officiala)){
					alreadSeenCounter++;
				}
				
				current = in.readLine();
			}

			in.close();
			System.out.println(alreadSeenCounter);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void transform(){
		BufferedReader in;
		BufferedReader in2;
		String current;
		String current2;
		String[] tabs;
		String officiala;
		String officialb;
		String inofficiala;
		String inofficialb;
		int counter=1;
		try {
			in = new BufferedReader (new FileReader("ppi_homosapiens_tab.txt"));
			in2 = new BufferedReader (new FileReader("ppi_homosapiens.txt"));
			in2.readLine();
			in.readLine();
			current = in.readLine();
			current2 = in2.readLine();
			Pattern p = Pattern.compile("^[^\\t]*BIOGRID:(\\d*).*$");
			Pattern p2 = Pattern.compile("^.*\\t.[^\\t]*BIOGRID:(\\d*).*$");
			Matcher matcher = null;
			Matcher matcher2 = null;

			counter=2;
			while(current!=null){
				matcher = p.matcher(current2);
				matcher2 = p2.matcher(current2);
				
				tabs = current.split("\\t");
				officiala = " ";
				officialb = " ";
				officiala = tabs[2];
				officialb = tabs[3];
				
				//System.out.println(counter+": "+current);
				
				if (matcher.matches() && matcher2.matches()){
					//System.out.println(matcher.group(1)+" "+matcher2.group(1)+" "+officiala+" "+officialb);
					if (!dict.containsKey(matcher.group(1)))
						dict.put(matcher.group(1), officiala);
					if (!dict.containsKey(matcher2.group(1)))
						dict.put(matcher2.group(1), officialb);
				}
				current = in.readLine();
				current2 = in2.readLine();
				counter++;
			}

			in.close();
			in2.close();
			System.out.println(dict.size());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			in = new BufferedReader (new FileReader("sample3"));
			PrintWriter writer = new PrintWriter("sample3_transformed", "UTF-8");
			in.readLine();
			String triangle = in.readLine();
			String[] nodes = triangle.split(" ");
			while (triangle!=null){
				writer.write(dict.get(nodes[0])+" "+dict.get(nodes[1])+" "+dict.get(nodes[2])+"\n");
				triangle = in.readLine();
				if (triangle!=null)
					nodes = triangle.split(" ");
			}
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
