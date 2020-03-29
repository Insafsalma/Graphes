import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * Les sommets du graphe
 */
public class Sommet {
	private int x, y;
	private String etiquette;

	public Sommet(String etiquette, int x, int y) {
		setPosition(x, y);
		this.etiquette = etiquette;
		tousLesSommets.put(etiquette, this);
	}

	public Sommet(int x, int y) {
		this("s" + tousLesSommets.size(), x, y);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getEtiquette() {
		return etiquette;
	}

	public void setEtiquette(String etiquette) {
		tousLesSommets.remove(this.etiquette);
		this.etiquette = etiquette;
		tousLesSommets.put(etiquette, this);
	}

	public String toString() {
		return "\"" + etiquette + "\" " + x + " " + y;
	}

	public boolean equals(Object o) {
		return o instanceof Sommet && x == ((Sommet) o).x
				&& y == ((Sommet) o).y;
	}

	/*
	 * Membres de classe
	 */
	private static Map<String, Sommet> tousLesSommets = new HashMap<String, Sommet>();

	public static int nombreSommets() {
		return tousLesSommets.size();
	}

	public static Sommet trouverSommet(String etiquette) {
		return tousLesSommets.get(etiquette);
	}

	public static Iterator<Sommet> iterator() {
		return tousLesSommets.values().iterator();
	}
}
