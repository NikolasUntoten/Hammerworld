import java.io.*;
/*
 * Made by Nikolas Gaub, 8/7/17
 * Randomly generates a Hammerwatch world
 */
public class Generator {
	
	//xml file output
	public static PrintWriter t;
	
	//Generates a text file to be used as the world map for Hammerworld.
	public static void main(String[] args) {
		
		try {
			t = new PrintWriter("HammerWorld.xml");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		gen();
		
		t.close();
	}
	
	//generates text within file.
	private static void gen() {
		String indent = "";
		t.println("<dictionary>");
		indent = add(indent);
		
		tileMap(indent);
		doodads(indent);
		actors(indent);
		items(indent);
		scripting(indent);
		lighting(indent);
		prefabs(indent);
		
		indent = sub(indent);
		t.print("</dictionary>");
	}
	
	//Below methods help gen()
	private static void tileMap(String indent) {
		println(indent, "<dictionary name=\"tilemap\">");
		indent = add(indent);
		
		//stuff
		
		indent = sub(indent);
		println(indent, "</dictionary>");
	}
	
	private static void doodads(String indent) {
	}
	
	private static void actors(String indent) {
	}
	
	private static void items(String indent) {
	}
	
	private static void scripting(String indent) {
	}
	
	private static void lighting(String indent) {
	}
	
	private static void prefabs(String indent) {
	}
	
	//method to ease printing with indent
	private static void println(String indent, String text) {
		t.println(indent + text);
	}
	
	//method that adds indent to a given string, and returns it (helper)
	private static String add(String indent) {
		return indent += "    ";
	}
	
	//method that subtracts indent to a given string, and return it (helper)
	private static String sub(String indent) {
		if (indent.length() < 4) throw new IllegalArgumentException();
		return indent.substring(0, indent.length()-4);
	}
}
