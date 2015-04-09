import java.util.ArrayList;
import java.util.Random;


public class SideTest {
	
	
	ArrayList<Edge> graph;
	
	public SideTest(){
		
		graph = new ArrayList<Edge>();
		
	}
	
	public void test(){
		int counter4=0;

		int counter=0;
		int counter2=0;
		int counter3=0;
		int counter5=0;
		for (int z=0;z<1000;z++){
	
		Random rgen = new Random();  // Random number generator	
		graph.clear();
		graph.add(new Edge(0, 1));
		graph.add(new Edge(1, 2));
		graph.add(new Edge(2, 0));
		
		for (int i=0; i<200; i++){
			graph.add(new Edge(0, 3+i));
		}
		for (int i=0; i<200; i++){
			graph.add(new Edge(1, 203+i));
		}
		for (int i=0; i<200; i++){
			graph.add(new Edge(2, 403+i));
		}
//		for (int i=0;i<100;i++){
//		graph.add(new Edge(63, 64+i));
//	}
		 
		for (int i=0; i<graph.size(); i++) {
		    int randomPosition = rgen.nextInt(graph.size());
		    Edge temp2 = graph.get(i);
		    graph.set(i, graph.get(randomPosition));
		    graph.set(randomPosition, temp2);
		}
		
		Edge tempEdge;
		counter=0;
		counter2=0;
		counter3=0;
		for (int i=0;i<graph.size(); i++){
			tempEdge = graph.get(i);
			if (tempEdge.x==0 || tempEdge.x==1)
				counter++;
			if (tempEdge.x==1 || tempEdge.x==2)
				counter2++;
			if (tempEdge.x==0 || tempEdge.x==2)
				counter3++;
		}
		System.out.println(counter+"; "+counter2+"; "+counter3);
		counter=0;
		counter2=0;
		counter3=0;
		for (int i=0;i<graph.size(); i++){
			tempEdge = graph.get(i);
			if (!(tempEdge.x==0 && tempEdge.y==1) && !(tempEdge.x==1 && tempEdge.y==2) && !(tempEdge.x==2 && tempEdge.y==0)){
				if (tempEdge.x==0 || tempEdge.x==1)
					counter++;
				if (tempEdge.x==1 || tempEdge.x==2)
					counter2++;
				if (tempEdge.x==0 || tempEdge.x==2)
					counter3++;
			}
			else{
				if (tempEdge.x==0 && tempEdge.y==1){
					counter4+=counter;
					System.out.println(tempEdge.x+"-"+tempEdge.y+"-->"+counter);
				}
				else if (tempEdge.x==1 && tempEdge.y==2){
					counter4+=counter2;
					System.out.println(tempEdge.x+"-"+tempEdge.y+"-->"+counter2);
				}
				else if (tempEdge.x==2 && tempEdge.y==0){
					counter4+=counter3;
					System.out.println(tempEdge.x+"-"+tempEdge.y+"-->"+counter3);
				}
				break;
			}

			//System.out.println(counter5);
		}
		
		
//		for (int i=0;i<graph.size();i++){
//			System.out.println(graph.get(i).x+"-"+graph.get(i).y);
		}
		System.out.println(counter4/1000);
	}

}
