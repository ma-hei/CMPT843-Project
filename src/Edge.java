


public class Edge implements Comparable<Edge>{
	
	
	int x;
	int y;
	
	
	
	public Edge(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean isAdjacent(Edge otherEdge){
		
		if (otherEdge.x == this.x || otherEdge.y == this.x || otherEdge.y == this.y || otherEdge.x ==this.y){
			return true;
		}
		else
			return false;
		
	}

	@Override
	public int compareTo(Edge o) {
		if (this.x == o.x && this.y ==o.y)
			return 1;
		if (this.x == o.y && this.y == o.x){
			return 1;
		}
		return 0;
	}

	


	
}
