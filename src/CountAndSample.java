import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class CountAndSample {
	
	long estimateTriangle;
	graphGenerator generator;
	GraphReader graphReader;
//	BiogridFileInspector graphReader = new BiogridFileInspector();
	//ccsbHandler graphReader = new ccsbHandler();
	//CCSBFileInspector graphReader = new CCSBFileInspector();
	Estimate estimate[];
	Edge[] bulk;
	int batchsize;
	int numberEstimators;
	HashMap<Edge,ArrayList<Estimate>> r1ToEst;
	HashMap<Integer, Integer> deg;
	HashMap<ArrayList<Integer>, ArrayList<Estimate>> p;
	HashMap<ArrayList<Integer>, ArrayList<Estimate>> q;
	HashMap<ArrayList<Integer>,Integer> test;
	int batchCounter;
	boolean EndOfFile=false;
	int edgesread=0;
	
	public CountAndSample(int batchsize, int numberEstimators){
		
		//int numberTriangles=10000;
		//graphGenerator generator = new graphGenerator();
		//generator.write(numberTriangles);
		//ShuffleEdgeStream doit = new ShuffleEdgeStream();
		//doit.doit();
		graphReader = new GraphReader();
//		graphReader = new BiogridFileInspector();
		//graphReader = new CCSBFileInspector();
		//graphReader = new ccsbHandler();
		this.bulk = new Edge[batchsize];
		this.estimate = new Estimate[numberEstimators];
		for (int i=0;i<numberEstimators;i++){
			this.estimate[i] = new Estimate(i+1);
		}
		this.batchsize=batchsize;
		this.numberEstimators = numberEstimators;
		this.batchCounter = 0;
		this.deg = new HashMap<Integer, Integer>();
		this.p = new HashMap<ArrayList<Integer>, ArrayList<Estimate>>();
		this.q = new HashMap<ArrayList<Integer>, ArrayList<Estimate>>();
		this.r1ToEst = new HashMap<Edge, ArrayList<Estimate>>();
		this.test = new HashMap<ArrayList<Integer>, Integer>();
	}
	
	private void edgeIter(){
		
		//deg = new HashMap<Integer, Integer>();
		deg.clear();
		Edge e = null;
		Integer x = null;
		Integer y = null;
		
		for (Estimate est:estimate){
			est.betar1x=0;
			est.betar1y=0;
		}
		
		for (int i=0;i<batchsize;i++){
			
			e = this.bulk[i];
			if (e!=null){
			x = e.x;
			y = e.y;
			if (deg.containsKey(x)){
				deg.put(x, deg.get(x)+1);
			}
			else{
				deg.put(x, 1);
			}
			
			if (deg.containsKey(y)){
				deg.put(y, deg.get(y)+1);
			}
			else{
				deg.put(y, 1);
			}
			
			if (r1ToEst.containsKey(e)){
				for (Estimate est : r1ToEst.get(e)){
					est.betar1x=deg.get(e.x);
					est.betar1y=deg.get(e.y);
				}
			}
			}
			
		}
		
		
	}
	
	private void step1(){
		Random rand = new Random();
		int randomNum;
		//System.out.println("sampling new r1: ");
		//System.out.println("random between 1 and "+batchsize*batchCounter);
		for (int i=0;i<numberEstimators;i++){
			randomNum = rand.nextInt(edgesread)+1;
			//System.out.println("for estimator "+(i+1)+": "+randomNum);
			if (randomNum>batchsize*(batchCounter-1)){
				//System.out.println("-->new r1 for est."+(i+1)+" giving:"+((randomNum-batchsize*(batchCounter-1))-1));
				estimate[i].r1 = bulk[(randomNum-batchsize*(batchCounter-1))-1];
				estimate[i].r1sampledInBatch = batchCounter;
				estimate[i].missing = null;
				estimate[i].r2 = null;
				estimate[i].t = null;
				estimate[i].c = 0;
				estimate[i].betar1x=0;
				estimate[i].betar1y=0;
			}
		}
	}
	
	private void createHashMapR1ToEstimator(){
		
		//r1ToEst = new HashMap<Edge, ArrayList<Estimate>>();
		r1ToEst.clear();
		for (int i=0;i<numberEstimators;i++){
			if (estimate[i].r1sampledInBatch == batchCounter)
			{
				if (!r1ToEst.containsKey(estimate[i].r1)){
					r1ToEst.put(estimate[i].r1, new ArrayList<Estimate>());
					r1ToEst.get(estimate[i].r1).add(estimate[i]);
				}
				else{
					r1ToEst.get(estimate[i].r1).add(estimate[i]);
				}
				
			}
		}
		
	}
	
	private void readBulk(){
		
		Edge edge = null;
		
		for (int i=0;i<this.batchsize;i++){
			
			edge = graphReader.getNextEdge();
			this.bulk[i] = edge;
			
			if (edge!=null){
				edgesread++;
			}
		}
		
		this.batchCounter++;
		
		if (edge==null){
			EndOfFile=true;
		}
	
	}
	
	private void step2a(){
		
		edgeIter();
		
	}
	
	private void step2b(){
		
		int cminus=0;
		int cplus=0;
		int a=0;
		int b=0;
		Random rand = new Random();
		int randomNum;
		ArrayList<Integer> temp= new ArrayList<Integer>();
		//p = new HashMap<ArrayList<Integer>, ArrayList<Estimate>>();
		p.clear();
		for (Estimate est: estimate){
			//System.out.println("est"+est.id+": r1edge: "+est.r1.x+"-"+est.r1.y+" betax: "+est.betar1x+" betay: "+est.betar1y+" --> a:"+(deg.get(est.r1.x)-est.betar1x)+" b:"+(deg.get(est.r1.y)-est.betar1y));
			cminus = est.c;
			
			if (deg.containsKey(est.r1.x))
				a=(deg.get(est.r1.x)-est.betar1x);
			else
				a=0;
			
			if (deg.containsKey(est.r1.y))
				b=(deg.get(est.r1.y)-est.betar1y);
			else
				b=0;
			
			cplus = a+b;
			//System.out.println("cminus: "+cminus);
			//System.out.println("cplus: "+cplus);
			//System.out.println("Estimator: "+est.id);
			//System.out.println((cplus+cminus));
			//System.out.println("Estimator "+est.id);
			//System.out.println("r1: "+est.r1.x+"-"+est.r1.y);
			//System.out.println(a+" adjacent to first endpoint");
			//System.out.println(b+" adjacent to second endpoint");
			if (cplus>=1){
				
				//System.out.println("randon number between 1 and "+(cplus+cminus));
				randomNum = rand.nextInt(cplus+cminus) + 1;
				//System.out.println("picked: "+randomNum);
				//System.out.println("picked: "+randomNum);
				if (randomNum>cminus){
					if (randomNum<=cminus+a){
						//System.out.println("picking edge adjacent to a: "+(est.betar1x+randomNum-cminus));
						temp.clear();
						temp.add(est.r1.x);
						temp.add(est.betar1x+randomNum-cminus);
						if (p.containsKey(temp)){
							p.get(temp).add(est);
						}
						else{
							p.put((ArrayList<Integer>) temp.clone(), new ArrayList<Estimate>());
							p.get(temp).add(est);
						}
					}
					else{
						//System.out.println("picking edge adjacent to b: "+(est.betar1y+randomNum-cminus-a));
						temp.clear();
						temp.add(est.r1.y);
						temp.add(est.betar1y+randomNum-cminus-a);
						if (p.containsKey(temp)){
							p.get(temp).add(est);
						}
						else{
							p.put((ArrayList<Integer>) temp.clone(), new ArrayList<Estimate>());
							p.get(temp).add(est);
						}
					}
				}
			}
			est.c = cminus+cplus;
			//System.out.println("");
		}
		
		//for (ArrayList<Integer> l : p.keySet()){
			//System.out.print(l+" --> ");
			//for (Estimate est: p.get(l)){
				//System.out.print(est.id+", ");
			//}
			//System.out.println("");
		//}
		
	}
	
	private void edgeIter2c(){
		
		deg.clear();
		Edge e = null;
		Integer x = null;
		Integer y = null;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i=0;i<batchsize;i++){
			e = this.bulk[i];
			if (e!=null){
			x = e.x;
			y = e.y;
			if (deg.containsKey(x)){
				deg.put(x, deg.get(x)+1);
			}
			else{
				deg.put(x, 1);
			}
			
			if (deg.containsKey(y)){
				deg.put(y, deg.get(y)+1);
			}
			else{
				deg.put(y, 1);
			}
			
			temp.clear();
			temp.add(x);
			temp.add(deg.get(x));
			if (p.containsKey(temp)){
				for (Estimate est: p.get(temp)){
					est.r2 = e;
					est.missing=null;
					est.r2sampledat=batchCounter*batchsize+1+i;
					est.t = null;
				}
			}
			
			temp.clear();
			temp.add(y);
			temp.add(deg.get(y));
			if (p.containsKey(temp)){
				for (Estimate est: p.get(temp)){
					est.r2 = e;
					est.missing=null;
					est.r2sampledat=batchCounter*batchsize+1+i;
					est.t=null;
				}
			}
			}
		}
		
	}
	
	private void step2c(){
		edgeIter2c();
	}
	
	private void step3(){
		ArrayList<Integer> temp1 = new ArrayList<Integer>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		//q = new HashMap<ArrayList<Integer>, ArrayList<Estimate>>();
		q.clear();
		test.clear();
		for (int i=0;i<batchsize;i++){
			Edge e = bulk[i];
			if (e!=null){
			temp1.clear();
			temp1.add(e.x);
			temp1.add(e.y);
			test.put((ArrayList)temp1.clone(), i);
			}
		}
		int missingCounter=0;
		for (Estimate e: estimate){
				if (e.missing==null || batchCounter==1)
					e.findMissingEdge();
			if (e.missing!=null){
			
				missingCounter++;
				temp1.clear();
				temp1.add(e.missing.x);
				temp1.add(e.missing.y);
				//
				temp2.clear();
				temp2.add(e.missing.y);
				temp2.add(e.missing.x);
				//
				
				if (test.containsKey(temp1)){
					if (batchCounter*batchsize+test.get(temp1)+1>e.r2sampledat)
						e.newT(new Edge(temp1.get(0), temp1.get(1)));
				}
				if (test.containsKey(temp2)){
					if (batchCounter*batchsize+test.get(temp2)+1>e.r2sampledat)
						e.newT(new Edge(temp2.get(0), temp2.get(1)));
				}
				
//				if (!q.containsKey(temp1)){
//					q.put((ArrayList<Integer>) temp1.clone(), new ArrayList<Estimate>());
//					q.get(temp1).add(e);
//				}
//				else{
//					q.get(temp1).add(e);
//				}	
			}
		}
		
		
//		System.out.println("estimates with missing edge: "+missingCounter);
//		for (int i=0;i<batchsize;i++){
//			Edge e = bulk[i];
//			if (e!=null){
//				temp1.clear();
//				temp2.clear();
//				temp1.add(e.x);
//				temp1.add(e.y);
//				temp2.add(e.y);
//				temp2.add(e.x);
//			
//				if (q.containsKey(temp1)){
//					for (Estimate est: q.get(temp1)){
//						if (batchCounter*batchsize+i+1>est.r2sampledat)
//							est.newT(new Edge(temp1.get(0), temp1.get(1)));
//					}
//				}
//			
//				if (q.containsKey(temp2)){
//					for (Estimate est: q.get(temp2)){
//						if (batchCounter*batchsize+i+1>est.r2sampledat)
//							est.newT(new Edge(temp2.get(0), temp2.get(1)));
//					}
//				}
//			}
//		}
		
		
	}
	
	private void count(){
		
		long sum=0;
		
		for (Estimate e: estimate){
			sum=sum+e.getTau(edgesread);
		}
		long numberEstLong = (long)numberEstimators;
		sum=sum/numberEstLong;
		System.out.println("estimated number of triangles :"+sum);
		this.estimateTriangle=sum;
		
		int holdingTriangle=0;
		int cCount=0;
		for (Estimate e: estimate){
			if (e.t!=null){
				holdingTriangle++;
				cCount+=e.c;
			}
		}
		
		System.out.println("currently "+holdingTriangle+" estimates are holding a triangle");
		System.out.println("Average c value is "+(cCount/holdingTriangle));
	}
	
	public void test(){
		
		int counter=0;
		while(!EndOfFile){
			
			counter++;
			
			readBulk();
			
			
			System.out.println("--->processing batch "+counter);
//			for (Edge e : bulk){
//				if (e!=null)
//					System.out.println(e.x+"-"+e.y);
//					else
//						System.out.println("null");
//			}
			

			step1();
			createHashMapR1ToEstimator();
			step2a();
			step2b();
			step2c();
			step3();
			
			
//			if (counter<4){
//			for (Estimate est: estimate){
//				System.out.println("Estimate: "+est.id);
//					est.print();
//			}
//			}
			
			//System.out.println("---------");
			
		}
		System.out.println("edges read: "+edgesread);
		this.count();
		this.count2();
		this.testSampleT();
		
	}

	private void count2() {
		
		long sum=0;
		float answer;
		for (Estimate e: estimate){
			if (e.r1!=null && e.r2!=null)
				sum=sum+e.getOther(edgesread);
		}
		long numberEstLong = (long)numberEstimators;
		sum=sum/numberEstLong;
		answer=(float)estimateTriangle/sum;
		answer=answer*3;
		System.out.println("this other measure: "+answer);
	}

	private void testSampleT() {
		
		Triangle temp;
		ArrayList<Triangle> list = new ArrayList<Triangle>();
		ArrayList<Estimate> list2 = new ArrayList<Estimate>();
//		HashMap<Triangle,Integer> list2 = new HashMap<Triangle, Integer>();
		int otheraverage=0;
		for (Estimate e: estimate){
			
			temp = e.sampleT();
			if (temp!=null){
				list.add(temp);
				list2.add(e);
				//e.print();
				//System.out.println("this c value: "+(e.c*1.5));
				otheraverage+=(((e.c-2+(e.c-2)*0.33))*1.5);
				//System.out.println("--> "+(((e.c-2+(e.c-2)*0.33))*1.5));
				//otheraverage+=);
			}
			
		}
		otheraverage = otheraverage/list.size();
		System.out.println("got "+list.size()+" triangles");
		System.out.println("this weird measure: "+otheraverage);
		int variance=0;
		
		for (Estimate e : list2){
			variance+=(otheraverage-(((e.c-2+(e.c-2)*0.33))*1.5))*(otheraverage-(((e.c-2+(e.c-2)*0.33))*1.5));
		}
		System.out.println("this weird variance: "+variance/list2.size());
		
		try {
			PrintWriter writer = new PrintWriter("sample_random", "UTF-8");
			writer.write(list.size()+"\n");
			int point1;
			int point2;
			int point3 = 0;
			for (Triangle t: list){
				point1 = t.edge1.x;
				point2 = t.edge1.y;
				
				if (t.edge2.x == t.edge1.x){
					point3 = t.edge2.y;
				}
				else if(t.edge2.x == t.edge1.y){
					point3 = t.edge2.y;
				}
				else if(t.edge2.y == t.edge1.x){
					point3 = t.edge2.x;
				}
				else if (t.edge2.y == t.edge1.y){
					point3 = t.edge2.x;
				}
			
				writer.write(point1+" "+point2+" "+point3+"\n");
			}
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
