import java.util.ArrayList;
import java.util.List;

public class WallMap {

	private List<Wall> walls;
	private List<Torch> torches;

	public WallMap() {
		walls = new ArrayList<Wall>();
		torches = new ArrayList<Torch>();
	}

	// sets a specific location of a material to true
	public void set(int x, int y, Wall.Material mat) {
		walls.add(new Wall(x, y, mat));
	}
	
	public void addTorch(int x, int y) {
		torches.add(new Torch(x, y));
	}

	public void horizStretch(int startX, int endX, int y) {
		y++;
		if (startX > endX) {
			horizStretch(endX, startX, y);
			return;
		}
		for (int i = startX; i <= endX; i++) {
			walls.add(new Wall(i, y, Wall.Material.HORIZ));
		}
	}
	
	public void cappedHorizStretch(int startX, int endX, int y) {
		y++;
		if (startX > endX) {
			horizStretch(endX, startX, y);
			return;
		}

		walls.add(new Wall(startX, y, Wall.Material.H_CAP_L));
		walls.add(new Wall(endX, y, Wall.Material.H_CAP_R));
		for (int i = startX + 1; i < endX; i++) {
			walls.add(new Wall(i, y, Wall.Material.HORIZ));
		}
	}

	public void vertStretch(int startY, int endY, int x) {
		if (startY > endY) {
			vertStretch(endY, startY, x);
			return;
		}
		
		for (int i = startY; i <= endY; i++) {
			walls.add(new Wall(x, i, Wall.Material.VERT));
		}
	}
	
	public void cappedVertStretch(int startY, int endY, int x) {
		if (startY > endY) {
			vertStretch(endY, startY, x);
			return;
		}

		walls.add(new Wall(x, startY, Wall.Material.V_CAP_UP));
		walls.add(new Wall(x, endY, Wall.Material.V_CAP_DN));
		for (int i = startY + 1; i < endY; i++) {
			walls.add(new Wall(x, i, Wall.Material.VERT));
		}
	}

	public void corner(int startX, int midX, int midY, int endY) {
		horizStretch(startX, midX-1, midY);
		vertStretch(midY+1, endY, midX);
		
		Wall corner;
		if (startX < midX) { // LEFT
			if (midY < endY) { //DOWN
				corner = new Wall(midX, midY, Wall.Material.C_RU);
			} else { //UP
				corner = new Wall(midX, midY, Wall.Material.C_RD);
			}
		} else { //RIGHT
			if (midY < endY) { //DOWN
				corner = new Wall(midX, midY, Wall.Material.C_LU);
			} else { //UP
				corner = new Wall(midX, midY, Wall.Material.C_LD);
			}
		}
		walls.add(corner);
	}

	public void fill(int x, int y, int width, int height, Wall.Material mat) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				set(x + i, y + j, mat);
			}
		}
	}

	// prints all map data
	public int printMap(String indent) {
		
		int x = 1;
		for (Wall w : walls) {
			w.print(indent, x);
			x++;
		}
		
		for (Torch t : torches) {
			t.print(indent, x);
			x++;
		}

		return x;
	}
}
