import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/*
 * Une arête n'est rien de plus que deux renvois à ses extremités
 */
public class Arete {
	private Sommet orig, extr;

	Arete(Sommet orig, Sommet extr) {
		this.orig = orig;
		this.extr = extr;
		toutesLesAretes.add(this);
	}

	public Sommet getOrig() {
		return orig;
	}

	public Sommet getExtr() {
		return extr;
	}

	public String toString() {
		return "\"" + orig.getEtiquette() + "\" \"" + extr.getEtiquette()
				+ "\"";
	}

	/*
	 * Membres de classe
	 */
	private static Collection<Arete> toutesLesAretes = new Vector<Arete>();

	public static int nombreAretes() {
		return toutesLesAretes.size();
	}

	public static Iterator<Arete> iterator() {
		return toutesLesAretes.iterator();
	}
}
