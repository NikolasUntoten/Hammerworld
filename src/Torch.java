
public class Torch {
	int x;
	int y;
	
	public Torch(int initX, int initY) {
		x = initX;
		y = initY;
	}
	
	public void print(String indent, int number) {
		print(indent, number, x, y);
	}
	
	public static void print(String indent, int number, int x, int y) {
		Gen.println(indent, "<dictionary>");
		indent = Gen.add(indent);
		
		Gen.println(indent, "<int name=\"id\">" + number + "</int>");
		Gen.println(indent, "<string name=\"type\">" + "doodads/generic/lamp_torch.xml" + "</string>");
		Gen.println(indent, "<vec2 name=\"pos\">" + x + " " + y + "</vec2>");
		Gen.println(indent, "<bool name=\"need-sync\">False</bool>");

		indent = Gen.sub(indent);
		Gen.println(indent, "</dictionary>");
	}
}
