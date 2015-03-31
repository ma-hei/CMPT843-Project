import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class graphGenerator {
	
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
