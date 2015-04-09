
public class CountFourCliques {
	
	GraphReader graphReader;
	FourCliqueEstimate[] estimates;
	int numberEstimates;
	int edgesRead;
	
	public CountFourCliques(int numberEstimates){
		
		edgesRead=0;
		graphReader = new GraphReader();
		estimates = new FourCliqueEstimate[numberEstimates];
		this.numberEstimates=numberEstimates;
		for (int i=0;i<numberEstimates;i++){
			estimates[i] = new FourCliqueEstimate(1);
		}
		//for (int i=numberEstimates;i<numberEstimates*2;i++){
		//	estimates[i] = new FourCliqueEstimate(2);
		//}
		
	}
	
	public void count(){
		
		int counter1=0;
		int formingcounter=0;
		for (int i=0;i<this.numberEstimates;i++){
			if (estimates[i].type1Forming()){
				formingcounter++;
				counter1+=estimates[i].c1*estimates[i].c2*edgesRead;
			}
		}
		counter1=counter1/numberEstimates;
		System.out.println("counted "+counter1+" type1 four cliques");
//		System.out.println("currently "+formingcounter+" are hodling one");
//		int counter2=0;
//		for (int i=this.numberEstimates;i<this.numberEstimates*2;i++){
//			if (estimates[i].type2holding){
//				counter2+=1200*1200;
//			}
//		}
//		counter2=counter2/numberEstimates;
//		System.out.println(counter2);
		
	}
	
	public void test(){
		
		Edge temp;
		temp = graphReader.getNextEdge();
		int step=1;
		edgesRead=1;
		int holdingcounter=0;
		while (temp!=null){
			System.out.println(temp.x+"-"+temp.y);
			for (FourCliqueEstimate est: estimates){
				est.sample(temp,step);
			}
			temp = graphReader.getNextEdge();
			if (temp!=null){
				edgesRead++;
			}
			step++;
			
			holdingcounter=0;
			for (FourCliqueEstimate est: estimates){
				if (est.type1Forming()){
					holdingcounter++;
				}
			}
//			System.out.println("currently "+holdingcounter+" are holding one\n");
//			if (step==5){
//				for (int i=0;i<numberEstimates;i++){
//					System.out.println("estimate: "+(i+1));
//					estimates[i].print();
//					if (estimates[i].type1Forming())
//						System.out.println("yes");
//					System.out.println("---");
//				}
//			}
//			System.out.println("---->");
		}
//		for (int i=0;i<numberEstimates;i++){
//			System.out.println("estimate: "+(i+1));
//			estimates[i].print();
//			System.out.println("---");
//		}

		//System.out.println(edgesRead);
//		for (int i=0;i<numberEstimates;i++){
//			System.out.println("estimate: "+(i+1));
//			estimates[i].print();
//			if (estimates[i].type1Forming())
//				System.out.println("yes");
//			System.out.println("---");
//		}
	
	}

}
