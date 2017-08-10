import java.io.*;
/*
 * Made by Nikolas Gaub, 8/7/17
 * Randomly generates a Hammerwatch world
 */
public class Gen {
	
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
		
		TileMap m = new TileMap();
		m.fill(-200, -200, 400, 400, TileMap.Material.a_default);
		m.printMap(indent);
		
		indent = sub(indent);
		println(indent, "</dictionary>");
	}
	
	private static void doodads(String indent) {
		println(indent, "<dictionary name=\"doodads\">");
		indent = add(indent);
		
		//println(indent, "<array name=\"doodads\"></array>");
		WallMap m = new WallMap();
		m.corner(1, 5, 0, 5);
		m.printMap(indent);
		
		indent = sub(indent);
		println(indent, "</dictionary>");
	}
	
	private static void actors(String indent) {
		println(indent, "<dictionary name=\"actors\">");
		indent = add(indent);
		
		
		indent = sub(indent);
		println(indent, "</dictionary>");
	}
	
	private static void items(String indent) {
		println(indent, "<dictionary name=\"items\">");
		indent = add(indent);
		
		
		indent = sub(indent);
		println(indent, "</dictionary>");
	}
	
	private static void scripting(String indent) {
		println(indent, "<dictionary name=\"scripting\">");
		indent = add(indent);
		
		println(indent, "<array name=\"nodes\"></array>");
		
		indent = sub(indent);
		println(indent, "</dictionary>");
	}
	
	private static void lighting(String indent) {
		println(indent, "<dictionary name=\"lighting\">");
		indent = add(indent);
		
		println(indent, "<array name=\"lights\"></array>");
		println(indent, "<int-arr name=\"shadow-color\">135 128 128 255</int-arr>");
		println(indent, "<int-arr name=\"ambient-color\">50 50 50 255</int-arr>");
		println(indent, "<int-arr name=\"add-color\">0 0 0 255</int-arr>");
		println(indent, "<float name=\"shadow-length\">1</float>");
		
		indent = sub(indent);
		println(indent, "</dictionary>");
	}
	
	private static void prefabs(String indent) {
		println(indent, "<dictionary name=\"prefabs\">");
		indent = add(indent);
		
		
		indent = sub(indent);
		println(indent, "</dictionary>");
	}
	
	//method to ease printing with indent
	public static void println(String indent, String text) {
		t.println(indent + text);
	}
	
	//method that adds indent to a given string, and returns it (helper)
	public static String add(String indent) {
		return indent += "    ";
	}
	
	//method that subtracts indent to a given string, and return it (helper)
	public static String sub(String indent) {
		if (indent.length() < 4) throw new IllegalArgumentException();
		return indent.substring(0, indent.length()-4);
	}
}
