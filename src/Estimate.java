import java.util.Random;


public class Estimate {
	
	Edge r1;
	Edge r2;
	Triangle t;
	int c;
	int endpoint1;
	int endpoint2;
	int r1sampledInBatch;
	int r2sampledat;
	int betar1x;
	int betar1y;
	int id;
	int cminus;
	int cplus;
	Edge missing;
	
	public Estimate(int id){
		
		r1 = null;
		r2 = null;
		t = null;
		c = 0;
		cminus = 0;
		cplus = 0;
		endpoint1=0;
		endpoint2=0;
		betar1x = 0;
		betar1y = 0;
		this.id = id;
	}
	
	private void getEndpointsOfWedge(){
		
		if (r1.x == r2.x ){
			endpoint1 = r1.y;
			endpoint2 = r2.y;
		}
		else if (r1.x == r2.y){
			endpoint1 = r1.y;
			endpoint2 = r2.x;
		}
		else if (r1.y == r2.x){
			endpoint1 = r1.x;
			endpoint2 = r2.y;
		}
		else if (r1.y == r2.y){
			endpoint1 = r1.x;
			endpoint2 = r2.x;
		}
		
	}
	
	public boolean closesTriangle(Edge e){
		
		this.getEndpointsOfWedge();
		if (e.x == endpoint1 && e.y == endpoint2){
			return true;
		}
		else if (e.x == endpoint2 && e.y == endpoint1){
			return true;
		}
		else
			return false;
	}

	private void newR1(Edge e){
		this.r1 = e;
		this.r2 = null;
		this.t = null;
		this.c=0;
	}
	
	private void newR2(Edge e){
		this.r2 = e;
		this.t = null;
	}
	
	public void newT(Edge e){
		this.t = new Triangle(this.r1, this.r2, e);
	}
	
	public void sample(Edge e, int step){
		
		double random = Math.random();
		
		if (random<((double) 1/step)){
			newR1(e);
		}
		else if (r1.isAdjacent(e)){
			
			c=c+1;
			random = Math.random();
			if (random<((double) 1/c)){
				newR2(e);
			}
			else if(this.closesTriangle(e)){
				newT(e);
			}
			
		}
		
	}
	
	public Triangle sampleT(){
		Random rand = new Random();
		Integer randomnumber = rand.nextInt(2*41093)+1;
		
		if (randomnumber<=this.c){
			return this.t;
		}
		return null;
		
	}
	
	public long getTau(int step){
		
		long returnthis = 0;
		if (this.t!=null){
			returnthis = (long)step;
			returnthis = (long)(returnthis*(long)c);
			return returnthis;
		}
		else
			return 0;
		
	}
	
	public long getOther(int step){
		long returnthis = 0;
		returnthis = (long) step;
		returnthis = (long)(returnthis*(long)c);
		return returnthis;
	}

	public void print(){
		
		if (this.r1!=null){
		System.out.println("r1: "+this.r1.x+"-"+this.r1.y);
			if (this.r2!=null){
				System.out.println("r2: "+this.r2.x+"-"+this.r2.y);
				if (this.t!=null){
					System.out.println("triangle with: "+this.t.edge3.x+"-"+this.t.edge3.y);
				}
			}
		System.out.println("c: "+c);

		}
		else{
			System.out.println("nothing sampled");
		}
		
	}
	
	public void findMissingEdge(){
		
		missing = null;
		
		if (r1!=null && r2!=null){
			
			getEndpointsOfWedge();
			missing = new Edge(endpoint1,endpoint2);
			
		}
		
	}
	
	public void printId(){
		System.out.println("this is estimate"+id);
	}
}
