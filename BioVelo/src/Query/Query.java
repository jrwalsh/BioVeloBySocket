package Query;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.iastate.javacyco.JavacycConnection;
import edu.iastate.javacyco.PtoolsErrorException;


// "dbs" will give all databases (objects, not names!)
// "[x^names:x<-CORN^^genes]" returns all names of corn genes

public class Query {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		if(args.length < 1 || args.length > 1) {
			System.out.println("Usage: Main Query");
			System.exit(0);
		}
		JavacycConnection conn = new JavacycConnection();
		
		try {
			String bioVeloQuery = args[0];
			ArrayList<String> returnVal = new ArrayList<String>();
			try {
				returnVal = conn.callFuncText("biovelo \"" + bioVeloQuery + "\"", false);
			} catch (PtoolsErrorException e) {
				e.printStackTrace();
			}
			
			String output = "";
			for (String line : returnVal) {
				output += line.toString() + "\n";
			}
			
			printString("output.txt", output);
		
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Caught a "+e.getClass().getName()+". Shutting down...");
		}
		finally {
			conn.close();
		}
	}
	
	private static void printString(String fileName, String printString) {
		PrintStream o = null;
		try {
			o = new PrintStream(new File(fileName));
			o.println(printString);
			o.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
}
