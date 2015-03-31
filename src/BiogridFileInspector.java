import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BiogridFileInspector {
	
	BufferedReader in;
	HashMap<String, Integer> all = new HashMap<String, Integer>();
	
	public BiogridFileInspector(){
		
		try {
			in = new BufferedReader (new FileReader("ppi_homosapiens.txt"));
			in.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class ValueComparator implements Comparator<String> {

	    Map<String, Integer> base;
	    public ValueComparator(Map<String, Integer> base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(String a, String b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }
	}
	
	public void inspect(){
		
		int totalcounter=0;
		int matchingcounter=0;
		
		
		try {
			String temp = in.readLine();
			//System.out.println(temp);
			Pattern p = Pattern.compile("^[^\\t]*BIOGRID:(\\d*).*$");
			Pattern p2 = Pattern.compile("^.*\\t.[^\\t]*BIOGRID:(\\d*).*$");
			Matcher matcher = null;
			Matcher matcher2 = null;
			while (temp!=null){
				totalcounter++;
				temp = in.readLine();
				if (temp!=null){
				matcher = p.matcher(temp);
				matcher2 = p2.matcher(temp);
				if (matcher.matches() && matcher2.matches()){
					matchingcounter++;
					//System.out.println(matcher.group(1)+" : "+matcher2.group(1));
					if (!all.containsKey(matcher.group(1))){
						all.put(matcher.group(1), 1);
					}
					else{
						all.put(matcher.group(1), all.get(matcher.group(1))+1);
					}
					
					if (!all.containsKey(matcher2.group(1))){
						all.put(matcher2.group(1), 1);
					}
					else{
						all.put(matcher2.group(1), all.get(matcher2.group(1))+1);
					}
				}
				}
				
			}
			
			in.close();
			System.out.println((totalcounter-1)+": "+matchingcounter);
			System.out.println(all.size());
			for (String i : all.keySet()){
				System.out.println(all.get(i));
			}
			
			ValueComparator bvc =  new ValueComparator(all);
	        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);

	        sorted_map.putAll(all);

	        System.out.println("results: "+sorted_map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Edge getNextEdge(){
		
		try {
			String temp = in.readLine();
			
			if (temp == null)
				return null;
			
			Pattern p = Pattern.compile("^[^\\t]*BIOGRID:(\\d*).*$");
			Pattern p2 = Pattern.compile("^.*\\t.[^\\t]*BIOGRID:(\\d*).*$");
			Matcher matcher = null;
			Matcher matcher2 = null;
			
			matcher = p.matcher(temp);
			matcher2 = p2.matcher(temp);
			if (matcher.matches() && matcher2.matches()){
					int intArray[] = new int[2];
					intArray[0] = Integer.parseInt(matcher.group(1));
					intArray[1] = Integer.parseInt(matcher2.group(1));
					return new Edge(intArray[0], intArray[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	

}
