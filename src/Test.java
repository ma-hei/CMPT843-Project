
public class Test {
	
	public static Edge[] readBulk(GraphReader reader, int bulksize){
		
		Edge[] bulk = new Edge[bulksize];
		Edge edge = null;
		for (int i=0;i<bulksize;i++){
			edge = reader.getNextEdge();
			bulk[i] = edge;
		}
		return bulk;
	}
	
	
	
	public static void main(String[] args){
		
	
		CountAndSample counter = new CountAndSample(20000, 700000);
		counter.test();
		//FileTransformer transform = new FileTransformer();
		//transform.preprocess();
		
		//NodeCounter nodecounter = new NodeCounter();
		//System.out.println(counter.graphReader.proteincounter);
		//BiogridFileInspector inspector = new BiogridFileInspector();
		//inspector.inspect();
		
		
	}

}
