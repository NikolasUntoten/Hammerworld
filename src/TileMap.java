import java.util.Map;
import java.util.TreeMap;

public class TileMap {
	
	public static final int DEFAULT_SIZE = 400;
	
	private static final Map<Material, String> matID = new TreeMap<Material, String>();
	
	static {
		matID.put(Material.a_default, "tilemaps/a_default.xml");
	}
	
	private Map<Material, boolean[][]> map;
	private int width;
	private int height;
	
	//default constructor, initializes fields
	public TileMap() {
		map = new TreeMap<Material, boolean[][]>();
		width = DEFAULT_SIZE;
		height = DEFAULT_SIZE;
	}
	
	//sets width of map, resets map
	public void setWidth(int w) {
		width = w;
		map = new TreeMap<Material, boolean[][]>();
	}
	
	//sets height of map, resets map
	public void setHeight(int h) {
		height = h;
		map = new TreeMap<Material, boolean[][]>();
	}
	
	//sets a specific location of a material to true
	public void set(int x, int y, Material mat) {
		if (x < -width/2 || y < -height/2 || x >= width/2 || y >= height/2)
			throw new IllegalArgumentException();
		
		if (!map.containsKey(mat)) {
			map.put(mat, new boolean[width][height]);
		}
		map.get(mat)[x + width/2][y + height/2] = true;
	}
	
	public void fill(int x, int y, int width, int height, Material mat) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				set(x+i, y+j, mat);
			}
		}
	}
	
	//prints all map data
	public void printMap(String indent) {
		Gen.println(indent, "<array name=\"tiledata\">");
		indent = Gen.add(indent);
		
		for (Material m : map.keySet()) {
			printMapData(indent, map.get(m), m);
		}
		
		indent = Gen.sub(indent);
		Gen.println(indent, "</array>");
	}
	
	//prints all map data for a specific material
	private void printMapData(String indent, boolean[][] data, Material mat) {
		for (int x = 0; x < width / 20; x++) {
			for (int y = 0; y < height / 20; y++) {
				boolean[][] mini = new boolean[20][20];
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						mini[i][j] = data[x*20 + i][y*20 + j];
					}
				}
				int tempX = x*20 - width/2;
				int tempY = y*20 - height/2;
				printMapChunk(indent, mini, tempX, tempY, mat);
			}
		}
	}
	
	//prints a section of the map (DATA ARRAY SHOULD BE 20x20!!) in correct xml syntax
	private void printMapChunk(String indent, boolean[][] data, int x, int y, Material mat) {
		Gen.println(indent, "<dictionary>");
		indent = Gen.add(indent);
		
		Gen.println(indent, "<int name=\"x\">" + x + "</int>");
		Gen.println(indent, "<int name=\"y\">" + y + "</int>");
		
		Gen.println(indent, "<array name=\"datasets\">");
		indent = Gen.add(indent);
		Gen.println(indent, "<dictionary>");
		indent = Gen.add(indent);
		
		Gen.println(indent, "<string name=\"tileset\">" + matID.get(mat) +"</string>");
		
		String arr1 = "", arr2 = "";
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j]) {
					arr1 += "1 ";
					arr2 += "255 ";
				} else {
					arr1 += "0 ";
					arr2 += "0 ";
				}
			}
		}
		
		Gen.println(indent, "<int-arr name=\"data-t\">" + arr1.trim() + "</int-arr>");
		Gen.println(indent, "<int-arr name=\"data-a\">" + arr2.trim() + "</int-arr>");
		
		indent = Gen.sub(indent);
		Gen.println(indent, "</dictionary>");
		indent = Gen.sub(indent);
		Gen.println(indent, "</array>");
		indent = Gen.sub(indent);
		Gen.println(indent, "</dictionary>");
	}
	
	//enum for various materials
	public enum Material {
		a_default
	}
}
