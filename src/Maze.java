import java.util.HashSet;
import java.util.Set;

public class Maze {

	private boolean[][] sides;
	private boolean[][] tops;

	private int size;
	private int scale;
	private int totalSize;

	public Maze() {
		// number of squares in maze
		size = 50;

		// pixels per square
		scale = 500 / size;

		totalSize = size * scale;
		createMaze();
	}

	public Maze(int size) {
		this.size = size;
		scale = 500 / size;
		totalSize = size * scale;
		createMaze();
	}

	public Maze(int size, int scale) {
		this.size = size;
		this.scale = scale;
		totalSize = size * scale;
		createMaze();
	}

	public void createMaze() {
		// bars on side of square
		sides = new boolean[size + 1][size];

		// bars on top of square
		tops = new boolean[size][size + 1];

		// how many openings each square should have (for debug reasons)
		int[][] counter = new int[size][size];

		// FORGIVE ME FOR THIS MONSTROSITY OF INDENTATION, I AM LAZY
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				// choose number of openings
				double rand = Math.random();
				int openings = 1;
				if (rand < .1) {
					openings = 1;
				} else if (rand < .7) {
					openings = 2;
				} else if (rand < .99) {
					openings = 3;
				} else {
					openings = 4;
				}
				// check how many openings there already are on square
				counter[x][y] = openings;
				if (sides[x][y])
					openings--;
				if (sides[x + 1][y])
					openings--;
				if (tops[x][y])
					openings--;
				if (tops[x][y + 1])
					openings--;
				// safety counter, because randomness = bad
				int count = 0;
				// choose random side to open until you run out of openings
				while (openings > 0 && count < 30) {
					count++;
					// randomly choose side to open
					int rand2 = (int) (Math.random() * 2);
					int xTemp = x;
					int yTemp = y;
					boolean horiz = true;
					switch (rand2) {
					case 0:
						xTemp = x + 1;
						yTemp = y;
						horiz = true;
						break;
					case 1:
						xTemp = x;
						yTemp = y + 1;
						horiz = false;
						break;
					}

					// check if it is already open. if not, open it.
					if (horiz) {
						if (!sides[xTemp][yTemp]) {
							openings--;
							sides[xTemp][yTemp] = true;
						}
					} else {
						if (!tops[xTemp][yTemp]) {
							openings--;
							tops[xTemp][yTemp] = true;
						}
					}
					// repeat
				}
			}
		}
	}

	public void addWalls(WallMap map) {
		int offset = totalSize / 2 + scale / 2;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				corner(map, x, y);
			}
		}
	}
	
	private void corner(WallMap map, int x, int y) {
		int offset = totalSize / 2 + scale / 2;
		
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;
		
		//up
		if (y > 0) {
			if (!sides[x][y-1]) {
				map.vertStretch(y * scale - scale/2 - offset, y * scale - 1 - offset, x * scale - offset);
				up = true;
			}
		}
		
		//down
		if (y+1 < size) {
			if (!sides[x][y]) {
				map.vertStretch(y * scale + scale/2 - offset, y * scale + 1 - offset, x * scale - offset);
				down = true;
			}
		}
		
		//left
		if (x > 0) {
			if (!tops[x-1][y]) {
				map.horizStretch(x * scale - scale/2 - offset, x * scale - 1 - offset, y*scale - offset);
				left = true;
			}
		}
		
		//right
		if (x+1 < size) {
			if (!tops[x][y]) {
				map.horizStretch(x * scale + scale/2 - offset, x * scale + 1 - offset, y*scale - 1 - offset);
				right = true;
			}
		}
		
		Wall.Material p = pillar(up, down, left, right, x, y);
		Set<Wall.Material> s = new HashSet<Wall.Material>();
		s.add(Wall.Material.HORIZ);
		s.add(Wall.Material.C_LD);
		s.add(Wall.Material.C_RD);
		s.add(Wall.Material.V_CAP_DN);
		s.add(Wall.Material.H_CAP_L);
		s.add(Wall.Material.H_CAP_R);
		s.add(Wall.Material.X_DN);
		
		if (s.contains(p)) {
			map.set(x * scale - offset, y * scale - offset + 1, p);
		} else {
			map.set(x * scale - offset, y * scale - offset, p);
		}
	}
	
	private Wall.Material pillar(boolean up, boolean down, boolean left, boolean right, int x, int y) {
		
		if (up && right && left && down) {
			return Wall.Material.X_X;
		}
		
		if (left && up && right) {
			return Wall.Material.X_DN;
		}
		
		if (left && down && right) {
			return Wall.Material.X_UP;
		}
		
		if (up && left && down) {
			return Wall.Material.X_R;
		}
		
		if (up && right && down) {
			return Wall.Material.X_L;
		}
		
		if (up && down) {
			return Wall.Material.VERT;
		}
		
		if (left && right) {
			return Wall.Material.HORIZ;
		}
		
		if (left && up) {
			return Wall.Material.C_RD;
		}
		
		if (left && down) {
			return Wall.Material.C_RU;
		}
		
		if (right && up) {
			return Wall.Material.C_LD;
		}
		
		if (right && down) {
			return Wall.Material.C_LU;
		}
		
		if (up) {
			return Wall.Material.V_CAP_DN;
		}
		
		if (down) {
			return Wall.Material.V_CAP_UP;
		}
		
		if (left) {
			return Wall.Material.H_CAP_R;
		}
		
		if (right) {
			return Wall.Material.H_CAP_L;
		}
		
		return Wall.Material.HORIZ;
	}

}
