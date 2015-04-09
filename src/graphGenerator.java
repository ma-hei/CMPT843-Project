import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;


public class graphGenerator {
	
	public void writeRandom(int howmany){
		
		PrintWriter writer;
		ArrayList<Integer> adjacents = new ArrayList<Integer>();
		
		try {
			writer = new PrintWriter("random_graph", "UTF-8");
			
			int additionalEdgesCounter=0;
			int offset=0;
			int tempoffset=0;
			int adjacent1=0;
			int adjacent2=0;
			int adjacent3=0;
			int average=0;
			for (int i=0; i<howmany; i++){
				
				writer.write((i*3+offset+1)+" "+(i*3+offset+2)+"\n");
				writer.write((i*3+offset+2)+" "+(i*3+offset+3)+"\n");
				writer.write((i*3+offset+3)+" "+(i*3+offset+1)+"\n");
				
				tempoffset=0;
				Random rand = new Random();
				Integer randomnumber = rand.nextInt(30)+1;
				adjacents.add(randomnumber);
				for (int k=0;k<3;k++){
					
					for (int y=0;y<randomnumber;y++){
						writer.write((i*3+offset+(k+1))+" "+(i*3+3+offset+tempoffset+(y+1))+"\n");
					}
					if (k==0){
						adjacent1=randomnumber;
						tempoffset+=randomnumber;
					}
					if (k==1){
						adjacent2=randomnumber;
						tempoffset+=randomnumber;
					}
					if (k==2){
						adjacent3=randomnumber;
						tempoffset+=randomnumber;
					}
					
				}
				System.out.println((i+1)+": "+(adjacent1+adjacent2+adjacent3));
				offset=offset+adjacent1+adjacent2+adjacent3;
			}
			int variance=0;
			for (Integer i: adjacents){
				variance+=((offset/howmany)-i)*((offset/howmany)-i);
			}
			
			System.out.println("average: "+offset/howmany);
			System.out.println("variance: "+variance/howmany);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}
	
	public void writeFour(int howmany){
		
		PrintWriter writer;
		
		try {
			writer = new PrintWriter("test_graph1", "UTF-8");
			
			for (int i=0;i< howmany;i++){
				writer.write((i*4+1)+"-"+(i*4+2)+"\n");
				writer.write((i*4+2)+"-"+(i*4+3)+"\n");
				writer.write((i*4+3)+"-"+(i*4+4)+"\n");
				writer.write((i*4+4)+"-"+(i*4+1)+"\n");
				//writer.write((i*4+2)+"-"+(i*4+5)+"\n");
			}
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void write2(int howmany){
		
		PrintWriter writer;
		
		try {
			writer = new PrintWriter("just_triangles","UTF-8");
			
			for (int i=0;i<howmany; i++){
				writer.write((i*4+1)+" "+(i*4+2)+"\n");
				writer.write((i*4+3)+" "+(i*4+4)+"\n");
				writer.write((i*4+3)+" "+(i*4+2)+"\n");
				writer.write((i*4+1)+" "+(i*4+4)+"\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(int howmany){
		PrintWriter writer;
		try {
			writer = new PrintWriter("graph1", "UTF-8");
			
			for (int i=0;i<howmany;i++){

				writer.write((i*17+1)+" "+(i*17+2)+"\n");
				writer.write((i*17+2)+" "+(i*17+3)+"\n");
				writer.write((i*17+3)+" "+(i*17+1)+"\n");
				writer.write((i*17+1)+" "+(i*17+18)+"\n");
				

				writer.write((i*17+1)+" "+(i*17+4)+"\n");
				writer.write((i*17+1)+" "+(i*17+5)+"\n");
				writer.write((i*17+1)+" "+(i*17+6)+"\n");
				writer.write((i*17+1)+" "+(i*17+7)+"\n");
				
				writer.write((i*17+2)+" "+(i*17+8)+"\n");
				writer.write((i*17+2)+" "+(i*17+9)+"\n");
				writer.write((i*17+2)+" "+(i*17+10)+"\n");
				writer.write((i*17+2)+" "+(i*17+11)+"\n");
				writer.write((i*17+2)+" "+(i*17+12)+"\n");
				
				writer.write((i*17+3)+" "+(i*17+13)+"\n");
				writer.write((i*17+3)+" "+(i*17+14)+"\n");
				writer.write((i*17+3)+" "+(i*17+15)+"\n");
				writer.write((i*17+3)+" "+(i*17+16)+"\n");
				writer.write((i*17+3)+" "+(i*17+17)+"\n");
				
			}
			
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
