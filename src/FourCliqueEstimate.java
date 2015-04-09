import java.util.ArrayList;


public class FourCliqueEstimate {
	
	Edge r1;
	Edge r2;
	Edge r3;
	Edge type2r1;
	Edge type2r2;
	ArrayList<Edge> clique;
	int c1;
	int c2;
	int type2substreamcount;
	int type;
	boolean type2holding;
	
	public FourCliqueEstimate(int type){
		
		r1 = null;
		r2 = null;
		r3 = null;
		clique = new ArrayList<Edge>();
		c1 = 0;
		c2 = 0;
		this.type = type;
		this.type2substreamcount=0;
		this.type2holding=false;
	}
	
	public boolean type1Forming(){
		
		ArrayList<Integer> all = new ArrayList<Integer>();
		for (Edge e: clique){
			if (!all.contains(e.x))
				all.add(e.x);
			if (!all.contains(e.y))
				all.add(e.y);
		}
		if (all.size()==4)
			return true;
		return false;
	}
	
	
	public void sample(Edge e, int step){
		
		if (this.type==1){
			double random = Math.random();
			
			if (random<((double)1/step)){
				this.r1 = e;
				this.c1 = 0;
				clique.clear();
				clique.add(r1);
			}
			else{
				if (r1.isAdjacent(e)){
					c1++;
					random = Math.random();
					if (random<((double)1/c1)){
						this.r2 = e;
						c2 = 0;
						clique.clear();
						clique.add(r1);
						clique.add(r2);
					}
					else{
						if (r1.isAdjacent(e) && r2.isAdjacent(e)){
							clique.add(e);
						}
						else{
							if(r1.isAdjacent(e) || r2.isAdjacent(e)){
								c2++;
								random = Math.random();
								if (random<((double)1/c2)){
									this.r3=e;
									clique.add(r3);
								}
								else{
									if ((r1.isAdjacent(e) && r2.isAdjacent(e))||(r1.isAdjacent(e) && r3.isAdjacent(e)) || (r2.isAdjacent(e) && r3.isAdjacent(e))){
										clique.add(e);
									}
								}
							}
						}
					}
				}
				else{
					if (r2!=null && r2.isAdjacent(e)){
						c2++;
						random = Math.random();
						if (random<((double)1/c2)){
							this.r3=e;
							clique.add(r3);
						}
					}
				}
			}
			
		}
		
	}
	
	
	
	public void print(){

		System.out.println("r1: "+r1.x+"-"+r1.y);
		if (r2!=null)
			System.out.println("r2: "+r2.x+"-"+r2.y);
		if(r3!=null)
			System.out.println("r3: "+r3.x+"-"+r3.y);
		
		for (Edge e: clique){
			System.out.println(e.x+"--"+e.y);
		}
		System.out.println("c1: "+c1);
		System.out.println("c2: "+c2);
	}

}
