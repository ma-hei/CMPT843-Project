
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
		
	
//		CountAndSample counter = new CountAndSample(20000, 512000);
//		counter.test();
		//FileTransformer transform = new FileTransformer();
		//transform.preprocess();
		
		//NodeCounter nodecounter = new NodeCounter();
		//System.out.println(counter.graphReader.proteincounter);
		//BiogridFileInspector inspector = new BiogridFileInspector();
		//inspector.inspect();
		
//		graphGenerator generator = new graphGenerator();
//		generator.writeFour(5);
		//ShuffleEdgeStream shuffler = new ShuffleEdgeStream();
		//shuffler.doit();
		
//		CountFourCliques fourcliquecounter = new CountFourCliques(100000);
//		fourcliquecounter.test();
//		fourcliquecounter.count();
		//System.out.println("----");
		
//		graphGenerator generator = new graphGenerator();
//		generator.writeRandom(500);
//		ShuffleEdgeStream shuffler = new ShuffleEdgeStream();
//		shuffler.doit();
		
		CountAndSample counter = new CountAndSample(10000, 900000);
		counter.test();
		
//		SideTest side = new SideTest();
//		side.test();
		
		
	}

}
