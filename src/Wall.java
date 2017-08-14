import java.util.Map;
import java.util.TreeMap;

public class Wall {
	private static final Map<Material, String> matID = new TreeMap<Material, String>();

	static {
		matID.put(Material.HORIZ, "doodads/theme_a/a_h_8.xml");
		matID.put(Material.VERT, "doodads/theme_a/a_v_8.xml");
		matID.put(Material.C_RU, "doodads/theme_a/a_crn_r_up.xml");
		matID.put(Material.C_RD, "doodads/theme_a/a_crn_r_dn.xml");
		matID.put(Material.C_LU, "doodads/theme_a/a_crn_l_up.xml");
		matID.put(Material.C_LD, "doodads/theme_a/a_crn_l_dn.xml");
		matID.put(Material.V_CAP_DN, "doodads/theme_a/a_v_cap_dn.xml");
		matID.put(Material.V_CAP_UP, "doodads/theme_a/a_v_cap_up.xml");
		matID.put(Material.H_CAP_R, "doodads/theme_a/a_h_cap_r.xml");
		matID.put(Material.H_CAP_L, "doodads/theme_a/a_h_cap_l.xml");
		matID.put(Material.X_DN, "doodads/theme_a/a_x_t_dn.xml");
		matID.put(Material.X_L, "doodads/theme_a/a_x_t_l.xml");
		matID.put(Material.X_R, "doodads/theme_a/a_x_t_r.xml");
		matID.put(Material.X_UP, "doodads/theme_a/a_x_t_up.xml");
		matID.put(Material.X_X, "doodads/theme_a/a_x_x.xml");
	}
	
	public int x;
	public int y;
	private Material mat;
	
	public Wall(int x, int y, Material m) {
		this.x = x;
		this.y = y;
		mat = m;
	}
	
	public void print(String indent, int number) {
		Gen.println(indent, "<dictionary>");
		indent = Gen.add(indent);
		
		Gen.println(indent, "<int name=\"id\">" + number + "</int>");
		Gen.println(indent, "<string name=\"type\">" + getMatID() + "</string>");
		Gen.println(indent, "<vec2 name=\"pos\">" + x + " " + y + "</vec2>");
		Gen.println(indent, "<bool name=\"need-sync\">False</bool>");
		
		
		indent = Gen.sub(indent);
		Gen.println(indent, "</dictionary>");
	}
	
	private String getMatID() {
		return matID.get(mat);
	}
	
	
	public enum Material {
		HORIZ, VERT, C_RU, C_RD, C_LU, C_LD, V_CAP_DN, V_CAP_UP, H_CAP_R, H_CAP_L, X_DN, X_L, X_R, X_UP, X_X
	}
}
